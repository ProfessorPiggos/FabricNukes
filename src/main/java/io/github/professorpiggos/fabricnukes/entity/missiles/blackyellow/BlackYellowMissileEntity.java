package io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow;
import io.github.professorpiggos.fabricnukes.entity.missiles.GenericExplodingMissile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class BlackYellowMissileEntity extends GenericExplodingMissile {
    public BlackYellowMissileEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
