package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class VerticalBlockModelProvider extends BlockModelProvider {
    public VerticalBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (VerticalSlabType value : VerticalSlabType.ALL.values()) {
            this.withExistingParent(value.registryName.getPath(), new ResourceLocation("abverticaledition", "block/vertical_slab")).texture("all", new ResourceLocation("minecraft", "block/stone"));
            this.withExistingParent(value.registryName.getPath() + "_full", new ResourceLocation("minecraft", "block/cube_all")).texture("all", new ResourceLocation("minecraft", "block/stone"));
        }
    }
}