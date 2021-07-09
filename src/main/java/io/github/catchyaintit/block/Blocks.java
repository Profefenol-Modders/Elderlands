package io.github.catchyaintit.block;

import io.github.catchyaintit.ElderLand;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static Block UNSTABLE_ELDERIUM = new Block(FabricBlockSettings.of(Material.METAL));
    public static Block STABLE_ELDERIUM = new Block(FabricBlockSettings.of(Material.METAL));

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(ElderLand.MODID, "unstable_elderium_item.json"), UNSTABLE_ELDERIUM);
        Registry.register(Registry.BLOCK, new Identifier(ElderLand.MODID, "stable_elderium_item.json"), STABLE_ELDERIUM);

    }
}
