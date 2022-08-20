package com.cifru.additionalblocks.vertical;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class VerticalStairBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction> DIRECTION_PROPERTY = EnumProperty.create("state", Direction.class, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    public static final VoxelShape[] COLLISION_BOXES = {
            Shapes.or(Shapes.create(0, 0, 0.5, 1, 1, 1), Shapes.create(0, 0, 0, 0.5, 1, 1)),
            Shapes.or(Shapes.create(0, 0, 0, 0.5, 1, 1), Shapes.create(0, 0, 0, 1, 1, 0.5)),
            Shapes.or(Shapes.create(0, 0, 0, 1, 1, 0.5), Shapes.create(0.5, 0, 0, 1, 1, 1)),
            Shapes.or(Shapes.create(0.5, 0, 0, 1, 1, 1), Shapes.create(0, 0, 0.5, 1, 1, 1)),
    };

    public VerticalStairBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(DIRECTION_PROPERTY, Direction.NORTH).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION_PROPERTY, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos position, CollisionContext context) {
        return COLLISION_BOXES[state.getValue(DIRECTION_PROPERTY).get2DDataValue()];
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(DIRECTION_PROPERTY, Direction.fromYRot(context.getRotation() - 45)).setValue(BlockStateProperties.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor level, BlockPos position, BlockPos position2) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.getLiquidTicks().scheduleTick(position, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, state2, level, position, position2);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos position, PathComputationType type) {
        return false;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
}
