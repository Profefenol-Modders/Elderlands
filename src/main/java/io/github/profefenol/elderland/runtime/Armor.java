package io.github.profefenol.elderland.runtime;

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

    // In which A is helmet, B chestplate, C leggings and D boots.
    // For reference, Leather uses {1, 2, 3, 1}, and Diamond/Netherite {3, 6, 8, 3}
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

        public static final ArmorMaterial CUSTOM_ARMOR_MATERIAL = new Armor();
        // If you made a new material, this is where you would note it.
        public static final Item CUSTOM_MATERIAL_HELMET = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings());
        public static final Item CUSTOM_MATERIAL_CHESTPLATE = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());
        public static final Item CUSTOM_MATERIAL_LEGGINGS = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());
        public static final Item CUSTOM_MATERIAL_BOOTS = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings());

        public static void register() {
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_helmet"), CUSTOM_MATERIAL_HELMET);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_chestplate"), CUSTOM_MATERIAL_CHESTPLATE);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_leggings"), CUSTOM_MATERIAL_LEGGINGS);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elderium_boots"), CUSTOM_MATERIAL_BOOTS);
        }
    }
}
