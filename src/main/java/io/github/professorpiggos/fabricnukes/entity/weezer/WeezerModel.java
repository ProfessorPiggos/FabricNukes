package io.github.professorpiggos.fabricnukes.entity.weezer;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class WeezerModel extends AnimatedGeoModel<WeezerEntity> {
    @Override
    public Identifier getModelLocation(WeezerEntity object) {
        return new Identifier("fabricnukes","geo/weezermob.geo.json");
    }

    @Override
    public Identifier getTextureLocation(WeezerEntity object) {
        return new Identifier("fabricnukes","textures/gecko/weezermob.png");
    }

    @Override
    public Identifier getAnimationFileLocation(WeezerEntity animatable) {
        return new Identifier("fabricnukes","animations/weezer.animation.json");
    }
}
