package io.github.professorpiggos.fabricnukes.block.missilelaunchpad;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import io.github.professorpiggos.fabricnukes.FabricNukes;
import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui.LaunchpadGui;
import io.github.professorpiggos.fabricnukes.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
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
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;

public class MissileLaunchpadEntity extends BlockEntity implements ImplementedInventory, PropertyDelegateHolder, InventoryProvider, NamedScreenHandlerFactory {
    public MissileLaunchpadEntity(BlockPos pos, BlockState state) {
        super(FabricNukes.MISSILE_LAUNCHPAD_ENTITY, pos, state);
    }

    private final Inv inventory = new Inv();
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(LaunchpadGui.INVENTORY_SIZE, ItemStack.EMPTY);
    private int destinationX = 0;
    private int destinationY = 0;
    private int missileType = 0;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch(index) {
                case 0 -> destinationX;
                case 1 -> destinationY;
                case 2 -> missileType;
                default -> -1;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> destinationX = value;
                case 1 -> destinationY = value;
                case 2 -> missileType = value;
            }
        }

        @Override
        public int size() {
            return LaunchpadGui.PROPERTY_COUNT;
        }
    };
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
        return new LaunchpadGui(syncId, inventory, ScreenHandlerContext.create(world,pos));
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return inventory;
    }

    static class Inv extends SimpleInventory implements SidedInventory {
        Inv() {
            super(2);
        }
        @Override
        public int getMaxCountPerStack() {
            return 1;
        }

        @Override
        public int[] getAvailableSlots(Direction side) {
            return new int[0];
        }

        @Override
        public boolean canInsert(int slot, ItemStack stack, Direction dir) {
            return false;
        }

        @Override
        public boolean canExtract(int slot, ItemStack stack, Direction dir) {
            return false;
        }
    }
}
