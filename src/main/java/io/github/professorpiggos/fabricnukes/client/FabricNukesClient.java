package io.github.professorpiggos.fabricnukes.client;

import io.github.professorpiggos.fabricnukes.FabricNukes;
import io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow.BlackYellowMissileRenderer;
import io.github.professorpiggos.fabricnukes.entity.missiles.jonarbuckle.JonArbuckleMissileRenderer;
import io.github.professorpiggos.fabricnukes.entity.weezer.WeezerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class FabricNukesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(FabricNukes.WEEZER, WeezerRenderer::new);
        EntityRendererRegistry.register(FabricNukes.BLACK_YELLOW_MISSILE, BlackYellowMissileRenderer::new);
        EntityRendererRegistry.register(FabricNukes.JON_ARBUCKLE_MISSILE, JonArbuckleMissileRenderer::new);
    }
}
