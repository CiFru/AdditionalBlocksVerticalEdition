package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class VerticalLanguageProvider extends LanguageProvider {

    public VerticalLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.abverticaledition", "Additional Blocks: Vertical Edition");

        for (VerticalBlockType value : VerticalBlockType.ALL.values()) {
            this.add(value.getSlab(), value.slabTranslation);
            this.add(value.getStair(), value.stairTranslation);
        }
    }
}
