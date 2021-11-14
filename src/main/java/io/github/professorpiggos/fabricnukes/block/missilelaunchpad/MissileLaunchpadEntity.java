package io.github.professorpiggos.fabricnukes.block.missilelaunchpad;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import io.github.professorpiggos.fabricnukes.FabricNukes;
import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui.MissileLaunchpadGuiDescription;
import io.github.professorpiggos.fabricnukes.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class MissileLaunchpadEntity extends BlockEntity implements ImplementedInventory, PropertyDelegateHolder, NamedScreenHandlerFactory {
    public MissileLaunchpadEntity(BlockPos pos, BlockState state) {
        super(FabricNukes.MISSILE_LAUNCHPAD_ENTITY, pos, state);
    }

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(MissileLaunchpadGuiDescription.INVENTORY_SIZE, ItemStack.EMPTY);

    @Override
    public boolean canPlayerUse(@NotNull PlayerEntity player) {
        return pos.isWithinDistance(player.getBlockPos(),4.5D);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        return super.writeNbt(nbt);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
        return new MissileLaunchpadGuiDescription(syncId, inventory, ScreenHandlerContext.create(world,pos));
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return null;
    }
}
