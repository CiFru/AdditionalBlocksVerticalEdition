package com.cifru.additionalblocks.vertical;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class VerticalSlabBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<SlabShape> STATE_PROPERTY = EnumProperty.create("state", SlabShape.class);
    public static final VoxelShape[] COLLISION_BOXES = {
            Shapes.create(0, 0, 0, 1, 1, 0.5),
            Shapes.create(0.5, 0, 0, 1, 1, 1),
            Shapes.create(0, 0, 0.5, 1, 1, 1),
            Shapes.create(0, 0, 0, 0.5, 1, 1),
            Shapes.block()
    };

    public VerticalSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(STATE_PROPERTY, SlabShape.NORTH).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE_PROPERTY, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos position, CollisionContext context) {
        return COLLISION_BOXES[state.getValue(STATE_PROPERTY).ordinal()];
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        SlabShape slabShape = state.getValue(STATE_PROPERTY);
        return slabShape != SlabShape.FULL && context.getItemInHand().is(this.asItem()) && (context.replacingClickedOnBlock() || slabShape.direction.getOpposite() == context.getClickedFace());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState oldState = context.getLevel().getBlockState(context.getClickedPos());
        if (oldState.is(this) && oldState.getValue(STATE_PROPERTY) != SlabShape.FULL) {
            return this.defaultBlockState().setValue(STATE_PROPERTY, SlabShape.FULL).setValue(BlockStateProperties.WATERLOGGED, false);
        } else {
            FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
            return this.defaultBlockState().setValue(STATE_PROPERTY, SlabShape.fromDirection(context.getHorizontalDirection())).setValue(BlockStateProperties.WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos position, BlockState state, FluidState fluidState) {
        return state.getValue(STATE_PROPERTY) != SlabShape.FULL && SimpleWaterloggedBlock.super.placeLiquid(level, position, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter level, BlockPos position, BlockState state, Fluid fluid) {
        return state.getValue(STATE_PROPERTY) != SlabShape.FULL && SimpleWaterloggedBlock.super.canPlaceLiquid(level, position, state, fluid);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor level, BlockPos position, BlockPos position2) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.scheduleTick(position, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, state2, level, position, position2);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos position, PathComputationType type) {
        return type == PathComputationType.WATER && level.getFluidState(position).is(FluidTags.WATER);
    }

    public enum SlabShape implements StringRepresentable {
        NORTH(Direction.NORTH), EAST(Direction.EAST), SOUTH(Direction.SOUTH), WEST(Direction.WEST), FULL(null);

        private final Direction direction;

        SlabShape(Direction direction) {
            this.direction = direction;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getModelRotation() {
            return this == FULL ? 0 : (int) this.direction.toYRot() - 180;

        }

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        public static SlabShape fromDirection(Direction direction) {
            for (SlabShape value : values()) {
                if (value.direction == direction)
                    return value;
            }
            return null;
        }
    }
}
