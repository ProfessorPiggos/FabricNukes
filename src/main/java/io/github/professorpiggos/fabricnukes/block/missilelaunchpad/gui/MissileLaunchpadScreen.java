package io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class MissileLaunchpadScreen extends CottonInventoryScreen<MissileLaunchpadGuiDescription> {
    public MissileLaunchpadScreen(MissileLaunchpadGuiDescription gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}
