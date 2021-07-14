package io.github.catchyaintit.ui.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.catchyaintit.ElderLand;
import io.github.catchyaintit.ElderLandClientEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.awt.*;

public class StatsScreen extends Screen {
    private static final Identifier STAT_SCREEN_BACKGROUND = new Identifier(ElderLand.MODID,"textures/screen/stat_background.png");
    private ButtonWidget exitButton;
    public StatsScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        exitButton = new ButtonWidget(width / 2 + (128 - 70), height / + (128 - 40),35,20, new TranslatableText("gui.elderland.exit_button"), press -> {
            onClose();
        });
        this.addSelectableChild(exitButton);
        this.addDrawableChild(exitButton);
    }

    // TODO: 6/16/21 finish stat screen
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        client.textRenderer.draw(matrices, new LiteralText(String.valueOf(ElderLandClientEntry.clientStatsManager.getCorruption())), 10, 10, Color.RED.getRGB());

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
