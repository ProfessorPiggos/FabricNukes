package io.github.professorpiggos.fabricnukes.block.missilelaunchpad;

import io.github.professorpiggos.fabricnukes.FabricNukes;
import io.github.professorpiggos.fabricnukes.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class MissileLaunchpadEntity extends BlockEntity implements ImplementedInventory {
    public MissileLaunchpadEntity(BlockPos pos, BlockState state) {
        super(FabricNukes.MISSILE_LAUNCHPAD_ENTITY, pos, state);
    }
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
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
}
