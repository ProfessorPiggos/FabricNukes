package io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
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

public class BlackYellowMissileEntity extends MobEntity implements IAnimatable {
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

    protected Vec3d velocityCalculator = switch (state) {
        case PRELAUNCH -> new Vec3d(0,0.2,0); // debug
        case UP -> easedUpVelocity(this.getY(), startY);
        case DOWN -> easedFallVelocity(this.getY(), startY);
        default -> Vec3d.ZERO;
    };

    public BlackYellowMissileEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreCameraFrustum = false;
        startY = this.getY();
        destinationX = this.getX() + 10;
        destinationZ = this.getZ() + 10;
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
    @Contract("_, _ -> new")
    public @NotNull Vec3d easedUpVelocity(double curY, double startValue) {
        if (curY < 256D) {
            return new Vec3d(
                    0D,
                    0.75D / (1D - Math.cos((((curY - (startValue - 0.01D)) / (256D - startValue)) * Math.PI) / 2)),
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
                    -0.75D / Math.pow((((curY - (destinationValue - 0.01D)) / (256D - destinationValue)) - 1), 2),
                    0D
            );
        } else {
            return Vec3d.ZERO;
        }
    }

    @Override
    public void tick() {
        this.setVelocity(velocityCalculator);
        super.tick();
        switch (state) {
            case DONEUP -> {
                super.teleport(destinationX, this.getY() ,destinationZ);
                state = MissileStates.DOWN;
            }
            case UP, DOWN -> {

            }
        }
    }

    private <E extends IAnimatable> PlayState predicate(@NotNull AnimationEvent<E> event) {
        event.getController().setAnimation(BLACK_YELLOW_MISSILE_IDLE);
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
