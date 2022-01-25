package io.github.profefenol.elderlands.items;

import io.github.profefenol.elderlands.gui.ModItemGroup;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


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