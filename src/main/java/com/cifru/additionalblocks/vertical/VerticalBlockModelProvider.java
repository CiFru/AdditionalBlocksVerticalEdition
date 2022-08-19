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
        for (VerticalBlockType value : VerticalBlockType.ALL.values()) {
            this.withExistingParent(value.slabRegistryName.getPath(), new ResourceLocation("abverticaledition", "block/vertical_slab")).texture("all", value.texture);
            this.withExistingParent(value.slabRegistryName.getPath() + "_full", new ResourceLocation("minecraft", "block/cube_all")).texture("all", value.texture);
            this.withExistingParent(value.slabRegistryName.getPath() + "_post", new ResourceLocation("abverticaledition", "block/vertical_slab_post")).texture("all", value.texture);
            this.withExistingParent(value.stairRegistryName.getPath(), new ResourceLocation("abverticaledition", "block/vertical_stair")).texture("all", value.texture);
        }
    }
}
