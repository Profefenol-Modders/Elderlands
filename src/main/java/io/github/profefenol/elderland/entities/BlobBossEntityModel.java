package io.github.profefenol.elderland.entities;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;

public class BlobBossEntityModel extends EntityModel<BlobBossEntity> {

    private final ModelPart base;

    public BlobBossEntityModel() {
        //TODO Replace static model, with generated blockbench models
        final List<ModelPart.Cuboid> cuboids = singletonList(new ModelPart.Cuboid(0, 0, 0, 0, 0, 250, 250, 250, 0, 0, 0, false, 50, 50));
        base = new ModelPart(cuboids, emptyMap());
    }

    @Override
    public void setAngles(BlobBossEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        base.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
