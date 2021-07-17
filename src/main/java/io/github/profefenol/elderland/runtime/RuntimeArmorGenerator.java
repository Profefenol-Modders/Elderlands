package io.github.profefenol.elderland.runtime;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import io.github.profefenol.elderland.ElderLand;
import io.github.profefenol.elderland.runtime.armor.RuntimeArmorConfig;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Locale;

public class RuntimeArmorGenerator implements SimpleSynchronousResourceReloadListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Identifier getFabricId() {
        return new Identifier(ElderLand.MOD_ID);
    }

    @Override
    public void reload(ResourceManager manager) {
        try {
            log.info("Starting Runtime Armor Generator");
            Collection<Identifier> resources = manager.findResources("armor/model", path -> path.endsWith(".json"));
            for (Identifier id : resources) {
                Resource resource = manager.getResource(id);
                log.info("Generating {}", resource.getId());

                JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(resource.getInputStream()));

                DataResult<Pair<RuntimeArmorConfig, JsonElement>> data = RuntimeArmorConfig.CODEC.decode(JsonOps.INSTANCE, jsonElement);

                RuntimeArmorConfig config = data.getOrThrow(false, err -> {
                    log.error("Failed to create config for {}", resource.getId());
                }).getFirst();
                log.info("Generated {}", config.getName());

                ArmorMaterial armorMaterial = new ArmorMaterial() {
                    @Override
                    public int getDurability(EquipmentSlot slot) {
                        return config.getDurability().get(slot.getName().toUpperCase(Locale.ROOT));
                    }

                    @Override
                    public int getProtectionAmount(EquipmentSlot slot) {
                        return config.getProtection().get(slot.getName().toUpperCase(Locale.ROOT));
                    }

                    @Override
                    public int getEnchantability() {
                        return 0;
                    }

                    @Override
                    public SoundEvent getEquipSound() {
                        return SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
                    }

                    @Override
                    public Ingredient getRepairIngredient() {
                        return null;
                    }

                    @Override
                    public String getName() {
                        return config.getName();
                    }

                    @Override
                    public float getToughness() {
                        return 0;
                    }

                    @Override
                    public float getKnockbackResistance() {
                        return 0;
                    }
                };
                log.info("Registering for final {}", armorMaterial.getName());

                final ArmorItem HEAD = new ArmorItem(armorMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
                final ArmorItem CHEST = new ArmorItem(armorMaterial, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
                final ArmorItem LEGS = new ArmorItem(armorMaterial, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
                final ArmorItem BOOTS = new ArmorItem(armorMaterial, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));


                Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, armorMaterial.getName() + "_helmet"), HEAD);
                Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, armorMaterial.getName() + "_chestplate"), CHEST);
                Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, armorMaterial.getName() + "_leggings"), LEGS);
                Registry.register(Registry.ITEM, new Identifier(ElderLand.MOD_ID, armorMaterial.getName() + "_boots"), BOOTS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
