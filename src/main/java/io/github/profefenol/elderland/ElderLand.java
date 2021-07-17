package io.github.profefenol.elderland;

import io.github.profefenol.elderland.blocks.UnstableElderium;
import io.github.profefenol.elderland.corruption.Corruption;
import io.github.profefenol.elderland.entities.BlobBossEntity;
import io.github.profefenol.elderland.items.UnstableElderiumItem;
import io.github.profefenol.elderland.items.UnstableShard;
import io.github.profefenol.elderland.persistance.PlayerData;
import io.github.profefenol.elderland.persistance.PlayerDatabase;
import io.github.profefenol.elderland.persistance.SaveRunner;
import io.github.profefenol.elderland.runtime.RuntimeArmorGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static net.minecraft.util.registry.Registry.BLOCK;
import static net.minecraft.util.registry.Registry.ITEM;

public class ElderLand implements ModInitializer {

    private static final Map<UUID, Player> players = new HashMap<>();
    public static String MOD_ID = "elderland";
    //TODO move this somewhere else
    public static final EntityType<BlobBossEntity> BLOB_BOSS =
            Registry.register(Registry.ENTITY_TYPE,
                    new Identifier(ElderLand.MOD_ID, "elderium_blob_boss"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MISC, BlobBossEntity::new)
                            .dimensions(EntityDimensions.fixed(10f, 10f))
                            .build());
    private final Logger log = LoggerFactory.getLogger(getClass());
    private SaveRunner saveRunner;
    private PlayerDatabase playerDatabase;

    public static Optional<Corruption> getCorruptionFor(Entity entity) {
        return Optional.ofNullable(players.get(entity.getUuid())).map(player -> player.corruption);
    }

    @NotNull
    private static synchronized File getModFolder(MinecraftServer server) {
        final File folder = new File(server.getSavePath(WorldSavePath.ROOT).toFile(), "elderland");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    @Override
    public void onInitialize() {
        ServerPlayConnectionEvents.JOIN.register(this::onPlayerJoin);
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            log.info("Server started");
            final File file = new File(getModFolder(server), "players.json");
            try {
                playerDatabase = new PlayerDatabase(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveRunner = new SaveRunner(playerDatabase);
            saveRunner.start();

        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> saveRunner.stop());

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new RuntimeArmorGenerator());
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new RuntimeArmorGenerator());

        Registry.register(BLOCK, UnstableElderium.IDENTIFIER, UnstableElderium.INSTANCE);
        Registry.register(ITEM, UnstableElderiumItem.IDENTIFIER, UnstableElderiumItem.INSTANCE);
        Registry.register(ITEM, UnstableShard.IDENTIFIER, UnstableShard.INSTANCE);
        FabricDefaultAttributeRegistry.register(BLOB_BOSS, BlobBossEntity.createMobAttributes());
    }

    private void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        log.info("New client connected {}", handler.player.getUuid());
        final PlayerData data = playerDatabase.getOrCreate(handler.player.getUuid());
        final Player player = new Player(playerDatabase, server.getPlayerManager(), handler.player.getUuid(), data);
        players.put(handler.player.getUuid(), player);
    }
}
