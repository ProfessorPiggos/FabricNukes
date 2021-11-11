package io.github.professorpiggos.fabricnukes.entity.missiles.jonarbuckle;
import io.github.professorpiggos.fabricnukes.entity.missiles.GenericExplodingMissile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class JonArbuckleMissileEntity extends GenericExplodingMissile {
    public JonArbuckleMissileEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
