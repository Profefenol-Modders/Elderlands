package io.github.profefenol.elderlands.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GrapesterEntityRenderer extends MobEntityRenderer<GrapesterEntity, GrapesterModel> {

    public GrapesterEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GrapesterModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(GrapesterEntity entity) {
        return new Identifier("assets/elderlands/textures/entity/debug.png");
    }
}
