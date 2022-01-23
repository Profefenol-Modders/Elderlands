package io.github.profefenol.elderland;

import io.github.profefenol.elderland.entities.GrapesterEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ElderLand implements ModInitializer {
    public static String MOD_ID = "elderland";
    //TODO move this somewhere else
    public static final EntityType<GrapesterEntity> grapester =
            Registry.register(Registry.ENTITY_TYPE,
                    new Identifier(ElderLand.MOD_ID, "grapester"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MISC, GrapesterEntity::new)
                            .dimensions(EntityDimensions.fixed(10f, 10f))
                            .build());
    private final Logger log = LoggerFactory.getLogger(getClass());

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

    }
}
