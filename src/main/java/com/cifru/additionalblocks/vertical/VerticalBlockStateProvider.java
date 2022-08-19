package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class VerticalBlockStateProvider extends BlockStateProvider {
    public VerticalBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (VerticalBlockType value : VerticalBlockType.ALL.values()) {
            this.getVariantBuilder(value.getSlab())
                    .forAllStatesExcept(state -> state.getValue(VerticalSlabBlock.STATE_PROPERTY) == VerticalSlabBlock.SlabShape.FULL ?
                                    new ConfiguredModel[]{new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath() + "_full")))} :
                                    new ConfiguredModel[]{new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath())), 0, state.getValue(VerticalSlabBlock.STATE_PROPERTY).getModelRotation(), true)},
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
