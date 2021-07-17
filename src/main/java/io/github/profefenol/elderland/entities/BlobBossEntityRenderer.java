package io.github.profefenol.elderland.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BlobBossEntityRenderer extends MobEntityRenderer<BlobBossEntity, BlobBossEntityModel> {

    public BlobBossEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BlobBossEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(BlobBossEntity entity) {
        return new Identifier("assets/elderland/textures/entity/debug.png");
    }
}
