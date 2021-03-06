package io.github.professorpiggos.fabricnukes.entity.weezer;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class WeezerEntity extends HostileEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final AnimationBuilder WEEZER_WALK = new AnimationBuilder().addAnimation("animation.weezermob.walk",true);
    private static final AnimationBuilder WEEZER_IDLE = new AnimationBuilder().addAnimation("animation.weezermob.idle",true);
    private static final AnimationBuilder WEEZER_ATTACK = new AnimationBuilder().addAnimation("animation.weezermob.attack",true);
    private boolean isMovingXZ() { return (this.getVelocity().getX() != 0.0f) || (this.getVelocity().getZ() != 0.0f); }
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (this.isAttacking()) {
            event.getController().setAnimation(WEEZER_ATTACK);
        } else if (isMovingXZ()) {
            event.getController().setAnimation(WEEZER_WALK);
        } else {
            event.getController().setAnimation(WEEZER_IDLE);
        }
        return PlayState.CONTINUE;
    }
    public WeezerEntity(EntityType<? extends HostileEntity> type, World worldIn) {
        super(type, worldIn);
        experiencePoints = 15;
        this.ignoreCameraFrustum = false;
    }
    public static DefaultAttributeContainer.Builder weezerDefaultAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,40d)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.25d)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.55d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.5d)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,2.35d)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0.7d);
    }
    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this,1.0D,false));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 18.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(1, (new RevengeGoal(this)));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void registerControllers(@NotNull AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }
}
