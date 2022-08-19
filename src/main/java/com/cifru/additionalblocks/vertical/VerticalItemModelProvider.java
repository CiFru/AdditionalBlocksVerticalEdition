package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class VerticalItemModelProvider extends ItemModelProvider {
    public VerticalItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (VerticalBlockType value : VerticalBlockType.ALL.values()) {
            this.withExistingParent(value.slabRegistryName.getPath(), new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath()));
            this.withExistingParent(value.stairRegistryName.getPath(), new ResourceLocation("abverticaledition", "block/" + value.stairRegistryName.getPath()));
        }
    }
}
