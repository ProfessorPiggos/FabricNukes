package io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BlackYellowMissileEntity extends MobEntity implements IAnimatable {
    private MissileStates state = MissileStates.PRELAUNCH;

    /*
    private final Vec3d velocitySelector = switch (state) {
        case UP -> easedUpAndDownVelocity(true);
        case DOWN -> easedUpAndDownVelocity(false);
        default -> Vec3d.ZERO;
    };
     */
    public BlackYellowMissileEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreCameraFrustum = false;
    }

    private static final AnimationBuilder BLACK_YELLOW_MISSILE_IDLE = new AnimationBuilder().addAnimation("animation.blackyellowmissile.idle", true);
    private final AnimationFactory factory = new AnimationFactory(this);

    public static DefaultAttributeContainer.Builder blackYellowMissileDefaultAttributes() {
        return MobEntity.createMobAttributes();
    }

    /**
     * Returns velocity for missile launcj
     * Uses easeInSine from easings.net
     * Assumed end at world height
     * @param startValue launch location of missile
     * @return Vec3d of missile velocity
     */
    protected Vec3d easedUpVelocity(double startValue) {
        return new Vec3d(
            0D,
            0.75D / (1D - Math.cos((((this.getY() - startValue) / (256D - startValue)) * Math.PI) / 2)),
            0D);
    }

    /**
     * Returns velocity for missile fall
     * Uses easeInQuad from easings.net
     * Assumed start at world height
     * @param destinationValue where missile will land
     * @return Vec3d of missile velocity
     */
    protected Vec3d easedFallVelocity(double destinationValue) {
        return new Vec3d(
            0D,
            0.75D / Math.pow((this.getY() - destinationValue) / (destinationValue - 256D), 2),
            0D
        );
    }
    @Override
    public void tick() {
        super.tick();
    }

    protected enum MissileStates {
        PRELAUNCH,
        LAUNCH,
        UP,
        DOWN,
        IMPACT
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
