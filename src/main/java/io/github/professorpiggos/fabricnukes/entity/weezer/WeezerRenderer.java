package io.github.professorpiggos.fabricnukes.entity.weezer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class WeezerRenderer extends GeoEntityRenderer<WeezerEntity> {
    public WeezerRenderer(EntityRenderDispatcher a) {
        super(a, new WeezerModel());
        this.shadowRadius =  4.5f;
    }
}
