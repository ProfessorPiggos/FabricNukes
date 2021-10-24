package io.github.professorpiggos.fabricnukes.client;

import io.github.professorpiggos.fabricnukes.FabricNukes;
import io.github.professorpiggos.fabricnukes.entity.weezer.WeezerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

@Environment(EnvType.CLIENT)
public class FabricNukesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(FabricNukes.WEEZER, WeezerRenderer::new);
    }
}
