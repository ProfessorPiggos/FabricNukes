package io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.professorpiggos.fabricnukes.FabricNukes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class MissileLaunchpadGuiDescription extends SyncedGuiDescription {
    public static final int INVENTORY_SIZE = 2;

    public MissileLaunchpadGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(FabricNukes.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);
        root.setInsets(Insets.ROOT_PANEL);
        WItemSlot MissileSlot = WItemSlot.of(blockInventory, 0);
        WItemSlot FuelSlot = WItemSlot.of(blockInventory,1);
        root.add(MissileSlot, 3, 1);
        root.add(FuelSlot, 5, 1);
        root.add(this.createPlayerInventoryPanel(), 0, 3);
        root.validate(this);
    }
}
