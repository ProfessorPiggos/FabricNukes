package io.github.professorpiggos.fabricnukes.entity.weezer;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
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
    private static final AnimationBuilder WEEZER_ATTACK = new AnimationBuilder().addAnimation("animation.weezermob.attack",false);
    private boolean isMovingXZ() { return (this.getVelocity().getX() != 0.0f) && (this.getVelocity().getZ() != 0.0f); }
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (isMovingXZ()) {event.getController().setAnimation(WEEZER_WALK);}
        else {event.getController().setAnimation(WEEZER_IDLE);}
        return PlayState.CONTINUE;
    }
    public WeezerEntity(EntityType<? extends HostileEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreCameraFrustum = true;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}