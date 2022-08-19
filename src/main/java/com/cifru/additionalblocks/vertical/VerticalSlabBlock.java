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

    public static final EnumProperty<SlabShape> SHAPE_PROPERTY = EnumProperty.create("state", SlabShape.class);
    public static final EnumProperty<SlabConnection> CONNECTION_PROPERTY = EnumProperty.create("connection", SlabConnection.class);
    public static final VoxelShape[] COLLISION_BOXES = {
        Shapes.create(0, 0, 0, 1, 1, 0.5), // NORTH
        Shapes.create(0.5, 0, 0, 1, 1, 1), // EAST
        Shapes.create(0, 0, 0.5, 1, 1, 1), // SOUTH
        Shapes.create(0, 0, 0, 0.5, 1, 1), // WEST
        Shapes.block() // FULL
    };
    public static final VoxelShape[] CONNECTED_COLLISION_BOXES = {
        Shapes.create(0.5, 0, 0.5, 1, 1, 1), // SOUTH
        Shapes.create(0, 0, 0.5, 0.5, 1, 1), // WEST
        Shapes.create(0, 0, 0, 0.5, 1, 0.5), // NORTH
        Shapes.create(0.5, 0, 0, 1, 1, 0.5) // EAST
    };

    public VerticalSlabBlock(Properties properties){
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(SHAPE_PROPERTY, SlabShape.NORTH).setValue(CONNECTION_PROPERTY, SlabConnection.NONE).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block,BlockState> builder){
        builder.add(SHAPE_PROPERTY, CONNECTION_PROPERTY, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos position, CollisionContext context){
        SlabShape shape = state.getValue(SHAPE_PROPERTY);
        SlabConnection connection = state.getValue(CONNECTION_PROPERTY);
        return shape == SlabShape.FULL || connection == SlabConnection.NONE ? COLLISION_BOXES[state.getValue(SHAPE_PROPERTY).ordinal()]
            : CONNECTED_COLLISION_BOXES[(connection == SlabConnection.LEFT ? shape.direction : shape.direction.getClockWise()).get2DDataValue()];
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context){
        SlabShape shape = state.getValue(SHAPE_PROPERTY);
        if(shape != SlabShape.FULL && context.getItemInHand().is(this.asItem())){
            if(!context.replacingClickedOnBlock() || shape.direction.getOpposite() == context.getClickedFace())
                return true;

            Direction.Axis axis = shape.direction.getAxis();
            boolean isOppositeSide = (axis == Direction.Axis.X ? context.getClickLocation().x : context.getClickLocation().z) < 0.5;
            return context.getClickedFace().getAxis() != axis
                && (shape == (axis == Direction.Axis.X ? SlabShape.WEST : SlabShape.NORTH)) == isOppositeSide;
        }
        return shape != SlabShape.FULL && context.getItemInHand().is(this.asItem()) && (context.replacingClickedOnBlock() || shape.direction.getOpposite() == context.getClickedFace());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        BlockState oldState = context.getLevel().getBlockState(context.getClickedPos());
        if(oldState.is(this) && oldState.getValue(SHAPE_PROPERTY) != SlabShape.FULL){
            return this.defaultBlockState().setValue(SHAPE_PROPERTY, SlabShape.FULL).setValue(BlockStateProperties.WATERLOGGED, false);
        }else{
            FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
            Direction facing = context.getHorizontalDirection();
            return this.defaultBlockState().setValue(SHAPE_PROPERTY, SlabShape.fromDirection(facing)).setValue(CONNECTION_PROPERTY, getProperConnectionType(context.getLevel(), context.getClickedPos(), facing)).setValue(BlockStateProperties.WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
    }

    private static SlabConnection getProperConnectionType(BlockGetter level, BlockPos pos, Direction facing){
        BlockState backState = level.getBlockState(pos.relative(facing));
        if(backState.getBlock() instanceof VerticalSlabBlock && backState.getValue(SHAPE_PROPERTY) != SlabShape.FULL){
            Direction backStateFacing = backState.getValue(SHAPE_PROPERTY).direction;
            SlabConnection backStateConnection = backState.getValue(CONNECTION_PROPERTY);
            BlockState leftState = level.getBlockState(pos.relative(facing.getCounterClockWise()));
            if((!(leftState.getBlock() instanceof VerticalSlabBlock) || !facing.equals(leftState.getValue(SHAPE_PROPERTY).direction)) && backStateFacing.equals(facing.getClockWise()) && (backStateConnection == SlabConnection.NONE || backStateConnection == SlabConnection.RIGHT))
                return SlabConnection.RIGHT;
            BlockState rightState = level.getBlockState(pos.relative(facing.getClockWise()));
            if((!(rightState.getBlock() instanceof VerticalSlabBlock) || !facing.equals(rightState.getValue(SHAPE_PROPERTY).direction)) && backStateFacing.equals(facing.getCounterClockWise()) && (backStateConnection == SlabConnection.NONE || backStateConnection == SlabConnection.LEFT))
                return SlabConnection.LEFT;
        }
        return SlabConnection.NONE;
    }

    @Override
    public FluidState getFluidState(BlockState state){
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos position, BlockState state, FluidState fluidState){
        return state.getValue(SHAPE_PROPERTY) != SlabShape.FULL && SimpleWaterloggedBlock.super.placeLiquid(level, position, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter level, BlockPos position, BlockState state, Fluid fluid){
        return state.getValue(SHAPE_PROPERTY) != SlabShape.FULL && SimpleWaterloggedBlock.super.canPlaceLiquid(level, position, state, fluid);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor level, BlockPos position, BlockPos position2){
        if(state.getValue(BlockStateProperties.WATERLOGGED)){
            level.scheduleTick(position, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        SlabShape shape = state.getValue(SHAPE_PROPERTY);
        return shape == SlabShape.FULL ? super.updateShape(state, direction, state2, level, position, position2) : state.setValue(CONNECTION_PROPERTY, getProperConnectionType(level, position, shape.direction));
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos position, PathComputationType type){
        return type == PathComputationType.WATER && level.getFluidState(position).is(FluidTags.WATER);
    }

    public enum SlabShape implements StringRepresentable {
        NORTH(Direction.NORTH), EAST(Direction.EAST), SOUTH(Direction.SOUTH), WEST(Direction.WEST), FULL(null);

        private final Direction direction;

        SlabShape(Direction direction){
            this.direction = direction;
        }

        public Direction getDirection(){
            return direction;
        }

        public int getModelRotation(){
            return this == FULL ? 0 : (int)this.direction.toYRot() - 180;

        }

        @Override
        public String getSerializedName(){
            return this.name().toLowerCase(Locale.ROOT);
        }

        public static SlabShape fromDirection(Direction direction){
            for(SlabShape value : values()){
                if(value.direction == direction)
                    return value;
            }
            return null;
        }
    }

    public enum SlabConnection implements StringRepresentable {
        LEFT, RIGHT, NONE;

        @Override
        public String getSerializedName(){
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
