package io.github.profefenol.elderlands.gui;

import io.github.profefenol.elderlands.armor.Armor;
import io.github.profefenol.elderlands.items.Items;
import io.github.profefenol.elderlands.blocks.ModBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public abstract class ModItemGroup {
    public static final ItemGroup ELDERLANDS_ITEMS = FabricItemGroupBuilder.build(
            new Identifier("elderlands", "item_group"),
            () -> new ItemStack(Items.UNSTABLE_SHARD));

    public static final ItemGroup ELDERLANDS_BLOCKS = FabricItemGroupBuilder.create(
                    new Identifier("elderlands", "block_group"))
            .icon(() -> new ItemStack(ModBlocks.UNSTABLE_ELDERIUM))
            .build();

    public static final ItemGroup ELDERLANDS_ARMOR = FabricItemGroupBuilder.create(
                    new Identifier("elderlands", "armor_group"))
            .icon(() -> new ItemStack(Armor.RegisterItems.ELDERIUM_MATERIAL_CHESTPLATE))
            .build();

    public static final ItemGroup ELDERLANDS_MISC = FabricItemGroupBuilder.create(
                    new Identifier("elderlands", "misc_group"))
            .icon(() -> new ItemStack(ModBlocks.PORTAL_FRAME))
            .build();
}