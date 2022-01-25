package io.github.profefenol.elderlands;

import io.github.profefenol.elderlands.entities.GrapesterEntityRenderer;
import io.github.profefenol.elderlands.gui.StatsScreen;
import io.github.profefenol.elderlands.network.Packets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElderLandClient implements ClientModInitializer {

    private static final KeyBinding statScreenKeybinding = new KeyBinding(
            "key.elderland.statscreenkeybind",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "category.elderland.ui");
    public static int corruption;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(statScreenKeybinding);

        ClientPlayNetworking.registerGlobalReceiver(Packets.CORRUPTION_PACKET, (client, handler, buf, responseSender) -> {
            corruption = buf.readInt();
            if (client.player != null) {
                log.info("Corruption packet received, corruption = {} for player {}", corruption, client.player.getUuid());
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (statScreenKeybinding.wasPressed()) {
                log.info("Opening stats screen");
                client.setScreen(new StatsScreen());
            }
        });

        EntityRendererRegistry.INSTANCE.register(ElderLand.grapester, (context) -> {
            log.info("Registering Grapester renderer");
            return new GrapesterEntityRenderer(context);
        });
    }
}
