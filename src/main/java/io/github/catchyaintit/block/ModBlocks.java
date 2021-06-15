package io.github.catchyaintit.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import io.github.catchyaintit.ElderLand;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final OreBlock ELDERAN_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(7, 10).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().sounds(BlockSoundGroup.STONE));
    public static final Block ELDERAN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(7, 10).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().sounds(BlockSoundGroup.METAL));
    public static final Block GILDED_ELDERAN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(8, 10).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().sounds(BlockSoundGroup.METAL));
    public static final Block ELD_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(4, 10).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().sounds(BlockSoundGroup.STONE));

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(ElderLand.MOD_ID, "elderan_ore"), ELDERAN_ORE);
        Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, "elderan_ore"), new BlockItem(ELDERAN_ORE, new FabricItemSettings().group(ElderLand.ELDER_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(ElderLand.MOD_ID, "elderan_block"), ELDERAN_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, "elderan_block"), new BlockItem(ELDERAN_BLOCK, new FabricItemSettings().group(ElderLand.ELDER_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(ElderLand.MOD_ID, "gilded_elderan_block"), GILDED_ELDERAN_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, "gilded_elderan_block"), new BlockItem(GILDED_ELDERAN_BLOCK, new FabricItemSettings().group(ElderLand.ELDER_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(ElderLand.MOD_ID, "eld_stone"), ELD_STONE);
        Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, "eld_stone"), new BlockItem(ELD_STONE, new FabricItemSettings().group(ElderLand.ELDER_GROUP)));
    }
}
