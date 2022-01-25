package io.github.profefenol.elderlands.armor;

import io.github.profefenol.elderlands.gui.ModItemGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Armor implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {20, 22, 23, 20};
    private static final int[] PROTECTION_VALUES = new int[] {5, 8, 10, 5};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 50;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems();
    }

    @Override
    public String getName() {
        // Must be all lowercase
        return "elderium";
    }

    @Override
    public float getToughness() {
        return 14.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.8F;
    }
    public class RegisterItems {
        public static final ArmorMaterial ELDERIUM_ARMOR_MATERIAL = new Armor();
        public static final Item ELDERIUM_MATERIAL_HELMET = new ArmorItem(ELDERIUM_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ModItemGroup.ELDERLANDS_ARMOR));
        public static final Item ELDERIUM_MATERIAL_CHESTPLATE = new ArmorItem(ELDERIUM_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ModItemGroup.ELDERLANDS_ARMOR));
        public static final Item ELDERIUM_MATERIAL_LEGGINGS = new ArmorItem(ELDERIUM_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ModItemGroup.ELDERLANDS_ARMOR));
        public static final Item ELDERIUM_MATERIAL_BOOTS = new ArmorItem(ELDERIUM_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ModItemGroup.ELDERLANDS_ARMOR));

        public static void register() {
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_helmet"), ELDERIUM_MATERIAL_HELMET);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_chestplate"), ELDERIUM_MATERIAL_CHESTPLATE);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_leggings"), ELDERIUM_MATERIAL_LEGGINGS);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_boots"), ELDERIUM_MATERIAL_BOOTS);
        }
    }
}
