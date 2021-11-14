package io.github.professorpiggos.fabricnukes.block.missilelaunchpad;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class MissileLaunchpad extends Block implements BlockEntityProvider {
    public MissileLaunchpad(Settings settings) {
        super(settings);
    }

    public static final Identifier ID = new Identifier("fabricnukes","missile_launchpad");

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 2.0f, 1.125f);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MissileLaunchpadEntity(pos, state);
    }
}