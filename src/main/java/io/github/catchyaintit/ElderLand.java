package io.github.catchyaintit;

import io.github.catchyaintit.block.Blocks;
import io.github.catchyaintit.item.Items;
import io.github.catchyaintit.network.packet.NetworkingConstants;
import io.github.catchyaintit.network.server.ServerClientStatsManager;
import io.github.catchyaintit.ui.keybinds.StatScreenKeybind;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;

public class ElderLand implements ModInitializer {
	public static String MODID = "elderland";
	public static ServerClientStatsManager serverClientStatsManager = ServerClientStatsManager.createEmpty();
	@Override
	public void onInitialize() {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->{
			if (serverClientStatsManager.hasStat(handler.getPlayer().getUuid()) == false) {
				serverClientStatsManager.createEmptyStat(handler.getPlayer().getUuid());
			}

			PacketByteBuf buf = PacketByteBufs.create();

			try {
				buf.writeIntArray(serverClientStatsManager.getStat(handler.getPlayer().getUuid()).getStatArray());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ServerPlayNetworking.send(handler.getPlayer(), NetworkingConstants.LOGIN_PACKET, buf);
		});

		// this is dumb but to not clutter this method all blocks are registered here
		Blocks.register();
		Items.register();
	}
}
