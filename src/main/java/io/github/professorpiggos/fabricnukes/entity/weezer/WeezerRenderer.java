package io.github.professorpiggos.fabricnukes.entity.weezer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import io.github.professorpiggos.fabricnukes.util.MyGeoEntityRenderer;

public class WeezerRenderer extends MyGeoEntityRenderer<WeezerEntity> {
    public WeezerRenderer(EntityRendererFactory.Context a) {
        super(a, new WeezerModel());
        this.shadowRadius =  4.5f;
    }
}
