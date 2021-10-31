package io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BlackYellowMissileEntity extends PathAwareEntity implements IAnimatable {
    private static final AnimationBuilder BLACK_YELLOW_MISSILE_IDLE = new AnimationBuilder().addAnimation("animation.blackyellowmissile.idle", true);
    private final AnimationFactory factory = new AnimationFactory(this);

    public BlackYellowMissileEntity(EntityType<? extends PathAwareEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreCameraFrustum = false;
    }

    public static DefaultAttributeContainer.Builder blackYellowMissileDefaultAttributes() {
        return PathAwareEntity.createMobAttributes();
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(BLACK_YELLOW_MISSILE_IDLE);
        return PlayState.CONTINUE;
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
