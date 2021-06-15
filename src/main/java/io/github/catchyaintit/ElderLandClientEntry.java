package io.github.catchyaintit;

import ca.weblite.objc.Client;
import io.github.catchyaintit.network.client.ClientStatsManager;
import io.github.catchyaintit.network.packet.NetworkingConstants;
import io.github.catchyaintit.ui.gui.StatsScreen;
import io.github.catchyaintit.ui.keybinds.StatScreenKeybind;
import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class ElderLandClientEntry implements ClientModInitializer {
    public static ClientStatsManager clientStatsManager = new ClientStatsManager(0,0);
    private static StatScreenKeybind statScreenKeybind = new StatScreenKeybind(
            "key.elderland.statscreenkeybind",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "category.elderland.ui"
    );
    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(statScreenKeybind);
        ClientPlayNetworking.registerGlobalReceiver(NetworkingConstants.CORRUPTION_PACKET, (client, handler, buf, responseSender) -> {
            int corruption = buf.readInt();
            clientStatsManager.setCorruption(corruption);
        });

        ClientPlayNetworking.registerGlobalReceiver(NetworkingConstants.GOLD_PACKET, (client, handler, buf, responseSender) -> {
            int gold = buf.readInt();
            clientStatsManager.setGold(gold);
        });

        ClientPlayNetworking.registerGlobalReceiver(NetworkingConstants.LOGIN_PACKET, (client, handler, buf, responseSender) ->{
            int[] dataArray = buf.readIntArray();
            int corruption = dataArray[0];
            int gold = dataArray[1];
            System.out.println(corruption);
            System.out.println(gold);
            clientStatsManager.setCorruption(corruption);
            clientStatsManager.setGold(gold);
            client.execute(() -> {
                client.player.sendMessage(new LiteralText("[Login Completed]"), false);
                client.openScreen(new StatsScreen(new LiteralText("Test")));
            });
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (statScreenKeybind.wasPressed()) {
                client.openScreen(new StatsScreen(new LiteralText("Test")));
            }
        });
    }
}
