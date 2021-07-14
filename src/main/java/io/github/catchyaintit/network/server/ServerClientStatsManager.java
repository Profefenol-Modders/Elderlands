package io.github.catchyaintit.network.server;

import io.github.catchyaintit.network.Stats;
import io.github.catchyaintit.network.packet.NetworkingConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stat;

import java.util.HashMap;
import java.util.UUID;

public class ServerClientStatsManager {
    private HashMap<UUID, Stats> stats = new HashMap<>();

    public ServerClientStatsManager( HashMap<UUID, Stats> stats) {
        this.stats = stats;
    }

    public Stats getStat(UUID id) throws Exception {
        Stats stat = stats.get(id);
        if (stat == null) {
            throw new Exception("Missing stat" + id);
        }
        return stats.get(id);
    }

    public boolean hasStat(UUID id) {
        if (stats.containsKey(id)) {
            return true;
        }
        return false;
    }

    public void createEmptyStat(UUID id) {
        stats.put(id, new Stats(0,0));
    }

    public void updateStats(ServerPlayerEntity player) throws Exception {
        PacketByteBuf buf = PacketByteBufs.create();
        Stats stat = getStat(player.getUuid());
        buf.writeIntArray(stat.getStatArray());
        ServerPlayNetworking.send(player, NetworkingConstants.STAT_ARRAY_PACKET, buf);
    }

    public static ServerClientStatsManager createEmpty() {
        return new ServerClientStatsManager(new HashMap<UUID, Stats>());
    }


}
