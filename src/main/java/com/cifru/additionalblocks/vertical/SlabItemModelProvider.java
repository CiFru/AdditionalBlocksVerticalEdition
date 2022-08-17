package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SlabItemModelProvider extends ItemModelProvider {
    public SlabItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (SlabType value : SlabType.ALL.values()) {
            this.withExistingParent(value.registryName.getPath(), new ResourceLocation("abverticaledition", "block/" + value.registryName.getPath()));
        }
    }
}
