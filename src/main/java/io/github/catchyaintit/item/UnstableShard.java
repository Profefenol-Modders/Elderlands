package io.github.catchyaintit.item;

import io.github.catchyaintit.ElderLand;
import io.github.catchyaintit.network.Stats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class UnstableShard extends Item {
    public UnstableShard(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient) {
            try {
                Stats stat = ElderLand.serverClientStatsManager.getStat(entity.getUuid());
                if (world.getServer().getTicks() >= stat.getLastTickTime() + 20) {
                    entity.damage(DamageSource.MAGIC, 0.1f);
                    stat.incrementCorruptionSmall(10);
                    if (stat.incrementCorruption()) {
                        ElderLand.serverClientStatsManager.updateStats((ServerPlayerEntity) entity);
                    }
                    stat.setLastTickTime(world.getServer().getTicks());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
