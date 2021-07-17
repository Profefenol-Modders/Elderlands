package io.github.profefenol.elderland.runtime.armor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Map;

public class RuntimeArmorConfig {
    public static Codec<RuntimeArmorConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(getter -> getter.name),
            Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("durability").forGetter(getter -> getter.durability),
            Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("protection").forGetter(getter -> getter.protection)
    ).apply(instance, RuntimeArmorConfig::new));

    private final String name;
    private final Map<String, Integer> durability;
    private final Map<String, Integer> protection;

    public RuntimeArmorConfig(String name, Map<String, Integer> durability, Map<String, Integer> protection) {
        this.name = name;
        this.durability = durability;
        this.protection = protection;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getDurability() {
        return durability;
    }

    public Map<String, Integer> getProtection() {
        return protection;
    }
}
