package io.github.catchyaintit.block;

import io.github.catchyaintit.ElderLand;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static Block UNSTABLE_ELDERIUM = new UnstableElderium(FabricBlockSettings.of(Material.METAL).hardness(8.0f));

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(ElderLand.MODID, "unstable_elderium"), UNSTABLE_ELDERIUM);

    }
}
