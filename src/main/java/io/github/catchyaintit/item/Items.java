package io.github.catchyaintit.item;

import io.github.catchyaintit.ElderLand;
import io.github.catchyaintit.block.Blocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Items {
    public static BlockItem UNSTABLE_ELDERIUM = new BlockItem(Blocks.UNSTABLE_ELDERIUM, new FabricItemSettings().fireproof().rarity(Rarity.EPIC));
    public static Item UNSTABLE_ELDERIUM_SHARD = new UnstableShard(new FabricItemSettings().rarity(Rarity.EPIC).fireproof().maxCount(1));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(ElderLand.MODID, "unstable_elderium_item"), UNSTABLE_ELDERIUM);
        Registry.register(Registry.ITEM, new Identifier(ElderLand.MODID, "unstable_elderium_shard"), UNSTABLE_ELDERIUM_SHARD);
    }
}
