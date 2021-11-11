package io.github.professorpiggos.fabricnukes.entity.missiles.jonarbuckle;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JonArbuckleMissileModel extends AnimatedGeoModel<JonArbuckleMissileEntity> {
    @Override
    public Identifier getModelLocation(JonArbuckleMissileEntity object) {
        return new Identifier("fabricnukes", "geo/jonarbucklemissile.geo.json");
    }

    @Override
    public Identifier getTextureLocation(JonArbuckleMissileEntity object) {
        return new Identifier("fabricnukes", "textures/gecko/jonarbucklemissile.png");
    }

    @Override
    public Identifier getAnimationFileLocation(JonArbuckleMissileEntity animatable) {
        return new Identifier("fabricnukes", "animations/jonarbucklemissile.animation.json");
    }
}
