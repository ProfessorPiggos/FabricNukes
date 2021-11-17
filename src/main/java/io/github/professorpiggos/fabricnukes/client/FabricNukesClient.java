package io.github.professorpiggos.fabricnukes.client;

import io.github.professorpiggos.fabricnukes.FabricNukes;
import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui.LaunchpadGui;
import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui.LaunchpadScreen;
import io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow.BlackYellowMissileRenderer;
import io.github.professorpiggos.fabricnukes.entity.missiles.jonarbuckle.JonArbuckleMissileRenderer;
import io.github.professorpiggos.fabricnukes.entity.weezer.WeezerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class FabricNukesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //noinspection RedundantTypeArguments
        ScreenRegistry.<LaunchpadGui, LaunchpadScreen>register(FabricNukes.SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new LaunchpadScreen(gui, inventory.player, title));
        EntityRendererRegistry.register(FabricNukes.WEEZER, WeezerRenderer::new);
        EntityRendererRegistry.register(FabricNukes.BLACK_YELLOW_MISSILE, BlackYellowMissileRenderer::new);
        EntityRendererRegistry.register(FabricNukes.JON_ARBUCKLE_MISSILE, JonArbuckleMissileRenderer::new);
    }
}
