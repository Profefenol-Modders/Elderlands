package io.github.profefenol.elderlands.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class GrapesterEntity extends HostileEntity {

    public static void initGrapester() {
         final EntityType<GrapesterEntity> grapester =
                Registry.register(Registry.ENTITY_TYPE,
                        new Identifier("elderlands", "grapester"),
                        FabricEntityTypeBuilder.create(SpawnGroup.MISC, GrapesterEntity::new)
                                .dimensions(EntityDimensions.fixed(10f, 10f))
                                .build());
    }

    public GrapesterEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        //TODO understand what lb what trying to do:
//        this.visible
    }

    //TODO make this work later
    @Override
    protected void initGoals() {
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }
}
