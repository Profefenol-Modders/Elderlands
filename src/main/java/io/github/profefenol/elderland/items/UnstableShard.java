package io.github.profefenol.elderland.items;

import io.github.profefenol.elderland.ElderLand;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class UnstableShard extends Item {


    public static final Item INSTANCE = new UnstableShard();
    public static final Identifier IDENTIFIER = new Identifier(ElderLand.MOD_ID, "unstable_elderium_shard");

    public UnstableShard() {
        super(new FabricItemSettings().rarity(Rarity.EPIC).fireproof().maxCount(1));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient) {
            ElderLand.getCorruptionFor(entity).ifPresent(corruption -> corruption.corruptedItemTick(10));
        }
    }
}
