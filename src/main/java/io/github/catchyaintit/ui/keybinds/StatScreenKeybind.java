package io.github.catchyaintit.ui.keybinds;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class StatScreenKeybind extends KeyBinding {
    public StatScreenKeybind(String translationKey, int code, String category) {
        super(translationKey, code, category);
    }

    public StatScreenKeybind(String translationKey, InputUtil.Type type, int code, String category) {
        super(translationKey, type, code, category);
    }

}
