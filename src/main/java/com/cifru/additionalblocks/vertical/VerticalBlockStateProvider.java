package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class VerticalBlockStateProvider extends BlockStateProvider {
    public VerticalBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (VerticalSlabType value : VerticalSlabType.ALL.values()) {
            this.getVariantBuilder(ForgeRegistries.BLOCKS.getValue(value.registryName))
                    .forAllStatesExcept(state -> state.getValue(VerticalSlabBlock.STATE_PROPERTY) == VerticalSlabBlock.SlabShape.FULL ?
                            new ConfiguredModel[]{new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.registryName.getPath() + "_full")))} :
                            new ConfiguredModel[]{new ConfiguredModel(this.models().getExistingFile(new ResourceLocation("abverticaledition", "block/" + value.registryName.getPath())), 0, state.getValue(VerticalSlabBlock.STATE_PROPERTY).getModelRotation(), true)}
                    );
        }
    }
}
