package io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BlackYellowMissileRenderer extends GeoEntityRenderer<BlackYellowMissileEntity> {
    public BlackYellowMissileRenderer(EntityRendererFactory.Context a) {
        super(a, new BlackYellowMissileModel());
        this.shadowRadius = 0.6f;
    }
}
