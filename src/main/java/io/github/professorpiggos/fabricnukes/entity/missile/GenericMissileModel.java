package io.github.professorpiggos.fabricnukes.entity.missile;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class GenericMissileModel extends AnimatedGeoModel<GenericMissileEntity> {
    @Override
    public Identifier getModelLocation(GenericMissileEntity object) {
        return new Identifier("fabricnukes","geo/weezermob.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GenericMissileEntity object) {
        return new Identifier("fabricnukes","textures/gecko/weezermob.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GenericMissileEntity animatable) {
        return new Identifier("fabricnukes","animations/weezermob.animation.json");
    }
}
