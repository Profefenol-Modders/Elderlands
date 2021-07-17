package io.github.profefenol.elderland.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class BlobBossEntity extends HostileEntity {

    public BlobBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        //TODO understand what lb what trying to do:
//        this.visib
    }

    //TODO make this work later
    @Override
    protected void initGoals() {
        this.targetSelector.add(1, new FollowTargetGoal(this, PlayerEntity.class, true));
    }
}
