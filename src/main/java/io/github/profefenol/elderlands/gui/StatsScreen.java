package io.github.profefenol.elderlands.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.profefenol.elderlands.ElderLandClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.awt.*;

public class StatsScreen extends Screen {

    private static final Identifier STAT_SCREEN_BACKGROUND = new Identifier("elderlands", "textures/screen/stat_background.png");

    public StatsScreen() {
        super(new TranslatableText("gui.elderland.stat_screen_title"));
    }

    @Override
    protected void init() {
        super.init();
        final ButtonWidget exitButton = new ButtonWidget(width / 2 + 60, height / 2 + 60, 35, 20, new TranslatableText("gui.elderlands.exit_button"), press -> {
            onClose();
        });
        this.addSelectableChild(exitButton);
        this.addDrawableChild(exitButton);
    }

    // TODO: 6/16/21 finish stat screen MDR
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        client.textRenderer.draw(matrices, new LiteralText(String.valueOf(ElderLandClient.corruption)), 10, 10, Color.RED.getRGB());
    }


    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, STAT_SCREEN_BACKGROUND);
        this.drawTexture(matrices, (width / 2) - 128, (height / 2) - 100, 0, 0, 256, 256);
    }
}
