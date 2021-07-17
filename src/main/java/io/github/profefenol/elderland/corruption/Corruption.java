package io.github.profefenol.elderland.corruption;

import io.github.profefenol.elderland.Player;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.PacketByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Corruption {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Player player;
    private int corruption;
    private int corruptionSmall;
    private int corruptionSmallTicks;

    public Corruption(Player player, int corruption) {
        this.player = player;
        this.corruption = corruption;
    }

    public void corruptedItemTick(int corruptionStrength) {
        corruptionSmallTicks++;
        if (corruptionSmallTicks == 20) {
            corruptionSmallTicks = 0;
            corruptionSmall += corruptionStrength;
            tryIncrementCorruption();
        }
    }

    private void tryIncrementCorruption() {
        if (corruptionSmall >= corruption * 10) {
            corruptionSmall -= corruption * 10;
            corruption++;
            log.info("Corruption increased to {}. Sending changes", corruption);
            player.getEntity().ifPresent(entity -> entity.damage(DamageSource.MAGIC, 0.1f));
            player.sendState();
        }
    }

    public void save(PacketByteBuf buf) {
        buf.writeInt(corruption);
    }

    public int amount() {
        return corruption;
    }
}
