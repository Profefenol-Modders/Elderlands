package io.github.profefenol.elderland.blocks;

import io.github.profefenol.elderland.ElderLand;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;

public class UnstableElderium extends Block {

    public static final Block INSTANCE = new UnstableElderium();
    public static final Identifier IDENTIFIER = new Identifier(ElderLand.MOD_ID, "unstable_elderium");

    private UnstableElderium() {
        super(FabricBlockSettings.of(Material.METAL).hardness(8.0f));
    }

}
