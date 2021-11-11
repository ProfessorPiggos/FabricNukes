package io.github.professorpiggos.fabricnukes.entity.missiles.jonarbuckle;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class JonArbuckleMissileRenderer extends GeoEntityRenderer<JonArbuckleMissileEntity> {
    public JonArbuckleMissileRenderer(EntityRendererFactory.Context a) {
        super(a, new JonArbuckleMissileModel());
        this.shadowRadius = 0.9f;
    }
}
