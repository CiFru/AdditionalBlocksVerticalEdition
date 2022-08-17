package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class SlabLanguageProvider extends LanguageProvider {

    public SlabLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        for (SlabType value : SlabType.ALL.values()) {
            this.add(ForgeRegistries.BLOCKS.getValue(value.registryName), value.translation);
        }
    }
}
