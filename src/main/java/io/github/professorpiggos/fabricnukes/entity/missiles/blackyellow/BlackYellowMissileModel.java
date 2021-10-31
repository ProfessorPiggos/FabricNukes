package io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class BlackYellowMissileModel extends AnimatedGeoModel<BlackYellowMissileEntity> {
    @Override
    public Identifier getModelLocation(BlackYellowMissileEntity object) {
        return new Identifier("fabricnukes", "geo/blackyellowmissile.geo.json");
    }

    @Override
    public Identifier getTextureLocation(BlackYellowMissileEntity object) {
        return new Identifier("fabricnukes", "textures/gecko/blackyellowmissile.png");
    }

    @Override
    public Identifier getAnimationFileLocation(BlackYellowMissileEntity animatable) {
        return new Identifier("fabricnukes", "animations/blackyellowmissile.animation.json");
    }
}
