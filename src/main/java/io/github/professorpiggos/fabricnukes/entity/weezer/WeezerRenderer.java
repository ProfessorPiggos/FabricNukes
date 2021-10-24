package io.github.professorpiggos.fabricnukes.entity.weezer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WeezerRenderer extends GeoEntityRenderer<WeezerEntity> {
    public WeezerRenderer(EntityRendererFactory.Context a) {
        super(a, new WeezerModel());
        this.shadowRadius =  4.5f;
    }
}
