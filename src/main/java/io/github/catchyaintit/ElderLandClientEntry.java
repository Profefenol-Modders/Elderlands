package io.github.catchyaintit;

import io.github.catchyaintit.network.packet.NetworkingConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ElderLandClientEntry implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(NetworkingConstants.CORRUPTION_PACKET, (client, handler, buf, responseSender) -> {

        });
        ClientPlayNetworking.registerGlobalReceiver(NetworkingConstants.GOLD_PACKET, (client, handler, buf, responseSender) -> {

        });

    }
}
