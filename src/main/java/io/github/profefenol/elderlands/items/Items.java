package io.github.profefenol.elderlands.items;

import io.github.profefenol.elderlands.ElderLand;
import io.github.profefenol.elderlands.gui.ModItemGroup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Items implements ModInitializer {
    public static final Item UNSTABLE_SHARD = new Item(new Item.Settings().group(ModItemGroup.ELDERLANDS_ITEMS)) {
//        @Override
//        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
//            super.inventoryTick(stack, world, entity, slot, selected);
 //           if (!world.isClient) {
//                ElderLand.getCorruptionFor(entity).ifPresent(corruption -> corruption.corruptedItemTick(10));
//            }
//        }
    };
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("elderlands", "unstable_elderium_shard"), UNSTABLE_SHARD);
    }
}