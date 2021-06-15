package io.github.catchyaintit.ui.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.awt.*;

public class StatsScreen extends Screen {
    public StatsScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        client.textRenderer.draw(matrices, new LiteralText("test"), 10, 10, Color.RED.getRGB());
        renderBackgroundTexture(0);
    }



    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
