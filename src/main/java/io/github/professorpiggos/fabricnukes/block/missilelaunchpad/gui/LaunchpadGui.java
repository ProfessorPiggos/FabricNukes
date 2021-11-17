package io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.professorpiggos.fabricnukes.FabricNukes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.TranslatableText;

public class LaunchpadGui extends SyncedGuiDescription {
    public static final int INVENTORY_SIZE = 2;
    public static final int PROPERTY_COUNT = 4; // X, Z, Missile Type, IsReady
    public LaunchpadGui(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(FabricNukes.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context, PROPERTY_COUNT));
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setInsets(Insets.ROOT_PANEL);
        WItemSlot MissileSlot = WItemSlot.of(blockInventory, 0);
        WItemSlot FuelSlot = WItemSlot.of(blockInventory,1);
        root.add(MissileSlot, 3, 1);
        root.add(FuelSlot, 5, 1);
        WTextField x = new WTextField(new TranslatableText("X Coordinate")).setMaxLength(8);
        root.add(x, 3, 25, 60, 10);
        WTextField y = new WTextField(new TranslatableText("Y Coordinate")).setMaxLength(8);
        root.add(y, 3, 35, 60, 10);
        WButton launch = new WButton();
        launch.setSize(40, 10);
        root.add(launch, 3, 35);
        root.add(this.createPlayerInventoryPanel(), 0, 3);
        root.validate(this);
    }
}
