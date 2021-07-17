package io.github.profefenol.elderland;

import io.github.profefenol.elderland.corruption.Corruption;
import io.github.profefenol.elderland.network.Packets;
import io.github.profefenol.elderland.persistance.PlayerData;
import io.github.profefenol.elderland.persistance.PlayerDatabase;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class Player {

    public final Corruption corruption;
    private final PlayerDatabase database;
    private final PlayerManager playerManager;
    private final UUID uuid;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public Player(PlayerDatabase database, PlayerManager playerManager, UUID uuid, PlayerData data) {
        this.database = database;
        this.playerManager = playerManager;
        this.uuid = uuid;
        this.corruption = new Corruption(this, data.corruption);
    }

    public Optional<ServerPlayerEntity> getEntity() {
        return Optional.ofNullable(playerManager.getPlayer(uuid));
    }

    public void sendState() {
        database.update(uuid, new PlayerData(corruption.amount()));
        log.info("Sending player data");
        final PacketByteBuf buf = PacketByteBufs.create();
        corruption.save(buf);
        getEntity().ifPresent(entity -> ServerPlayNetworking.send(entity, Packets.CORRUPTION_PACKET, buf));
    }
}
