package io.github.professorpiggos.fabricnukes.entity.missiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GenericExplodingMissile extends MobEntity implements IAnimatable {
    private double startY;
    private double destinationX;
    private double destinationZ;

    protected enum MissileStates {
        PRELAUNCH,
        LAUNCH,
        UP,
        DONEUP,
        DOWN,
        IMPACT
    }
    private MissileStates state = MissileStates.UP;

    protected Vec3d velocityCalculator() {
        switch (state) {
            case PRELAUNCH -> {
                return new Vec3d(0,0.2,0); // debug
            }
            case UP -> {
                return easedUpVelocity(this.getY(), startY);
            }
            case DOWN -> {
                return easedFallVelocity(this.getY(), startY);
            }
            default -> {
                return Vec3d.ZERO;
            }
        }
    }

    public GenericExplodingMissile(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreCameraFrustum = false;
        startY = this.getY();
        destinationX = this.getX();
        destinationZ = this.getZ();
    }

    private static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("animation.blackyellowmissile.idle", true);
    private final AnimationFactory factory = new AnimationFactory(this);

    public static DefaultAttributeContainer.Builder genericExplodingMissileDefaultAttributes() {
        return MobEntity.createMobAttributes();
    }

    /**
     * Returns velocity for missile launcj
     * Uses easeInSine from easings.net
     * Assumed end at world height
     * @param startValue launch location of missile
     * @return Vec3d of missile velocity
     */
    @Contract("_, _ -> new")
    public @NotNull Vec3d easedUpVelocity(double curY, double startValue) {
        if (curY < 250D) {
            return new Vec3d(
                    0D,
                    0.75,
                    0D);
        } else {
            state = MissileStates.DONEUP;
            return Vec3d.ZERO;
        }
    }

    /**
     * Returns velocity for missile fall
     * Uses easeInQuad from easings.net
     * Assumed start at world height
     * @param destinationValue where missile will land
     * @return Vec3d of missile velocity
     */
    @Contract("_, _ -> new")
    public @NotNull Vec3d easedFallVelocity(double curY, double destinationValue) {
        if (curY > destinationValue) {
            return new Vec3d(
                    0D,
                    -0.75D,
                    0D
            );
        } else {
            return Vec3d.ZERO;
        }
    }

    @Override
    public void tick() {
        super.tick();
        switch (state) {
            case DONEUP -> {
                state = MissileStates.DOWN;
            }
            case UP, DOWN -> {
                if (super.verticalCollision) {
                    state = MissileStates.UP;
                }
            }
        }
        this.setVelocity(velocityCalculator());
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    private <E extends IAnimatable> PlayState predicate(@NotNull AnimationEvent<E> event) {
        event.getController().setAnimation(IDLE);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(@NotNull AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
