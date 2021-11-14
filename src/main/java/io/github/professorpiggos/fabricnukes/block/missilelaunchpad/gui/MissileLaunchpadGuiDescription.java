package io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.professorpiggos.fabricnukes.FabricNukes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class MissileLaunchpadGuiDescription extends SyncedGuiDescription {
    public static final int INVENTORY_SIZE = 2;

    public MissileLaunchpadGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(FabricNukes.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE))
    }
}
