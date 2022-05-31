package io.github.profefenol.elderlands;

import io.github.profefenol.elderlands.armor.Armor;
import io.github.profefenol.elderlands.blocks.ModBlocks;
import io.github.profefenol.elderlands.entities.GrapesterEntity;
import io.github.profefenol.elderlands.items.Items;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ElderLand implements ModInitializer {
    //TODO move this somewhere else

    private final Logger log = LoggerFactory.getLogger(getClass());

    @NotNull
    private static synchronized File getModFolder(MinecraftServer server) {
        final File folder = new File(server.getSavePath(WorldSavePath.ROOT).toFile(), "elderlands");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    @Override
    public void onInitialize() {
        ModBlocks.modBlocks();
        Armor.RegisterItems.initializeArmor();
        Items.initializeItems();
        GrapesterEntity.initGrapester();
    }
}
