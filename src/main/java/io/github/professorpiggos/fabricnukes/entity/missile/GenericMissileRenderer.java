package io.github.professorpiggos.fabricnukes.entity.missile;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GenericMissileRenderer extends GeoEntityRenderer<GenericMissileEntity> {
    public GenericMissileRenderer(EntityRendererFactory.Context a) {
        super(a, new GenericMissileModel());
        this.shadowRadius =  0f;
    }
}
