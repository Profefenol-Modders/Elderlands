package io.github.profefenol.elderland.items;

import io.github.profefenol.elderland.ElderLand;
import io.github.profefenol.elderland.blocks.UnstableElderium;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class UnstableElderiumItem extends BlockItem {

    public static final BlockItem INSTANCE = new UnstableElderiumItem();
    public static final Identifier IDENTIFIER = new Identifier(ElderLand.MOD_ID, "unstable_elderium_item");

    private UnstableElderiumItem() {
        super(UnstableElderium.INSTANCE, new FabricItemSettings().fireproof().rarity(Rarity.EPIC));
    }
}
