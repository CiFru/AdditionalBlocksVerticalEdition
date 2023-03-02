package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class VerticalBlockStateProvider extends BlockStateProvider {
    public VerticalBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen.getPackOutput(), modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (VerticalBlockType value : VerticalBlockType.ALL.values()) {
            this.getVariantBuilder(value.getSlab())
                .forAllStatesExcept(
                    state -> {
                        VerticalSlabBlock.SlabShape shape = state.getValue(VerticalSlabBlock.SHAPE_PROPERTY);
                        VerticalSlabBlock.SlabConnection connection = state.getValue(VerticalSlabBlock.CONNECTION_PROPERTY);
                        ConfiguredModel model = shape == VerticalSlabBlock.SlabShape.FULL ? new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath() + "_full")))
                            : connection == VerticalSlabBlock.SlabConnection.NONE ? new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath())), 0, state.getValue(VerticalSlabBlock.SHAPE_PROPERTY).getModelRotation(), true)
                            : new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath() + "_post")), 0, (int)(connection == VerticalSlabBlock.SlabConnection.LEFT ? shape.getDirection() : shape.getDirection().getClockWise()).toYRot() - 180, true);
                        return new ConfiguredModel[]{model};
                    },
                    BlockStateProperties.WATERLOGGED
                );
            this.getVariantBuilder(value.getStair())
                .forAllStatesExcept(
                    state -> new ConfiguredModel[]{new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.stairRegistryName.getPath())), 0, (int)state.getValue(VerticalStairBlock.DIRECTION_PROPERTY).toYRot() - 180, true)},
                    BlockStateProperties.WATERLOGGED
                );
        }
    }
}
