package io.github.profefenol.elderland.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerData {
    @JsonProperty
    public final int corruption;

    @JsonCreator
    public PlayerData(@JsonProperty("corruption") int corruption) {
        this.corruption = corruption;
    }
}
