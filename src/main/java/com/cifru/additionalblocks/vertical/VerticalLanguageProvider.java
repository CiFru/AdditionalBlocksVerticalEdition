package com.cifru.additionalblocks.vertical;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class VerticalLanguageProvider extends FabricLanguageProvider {

    public VerticalLanguageProvider(FabricDataOutput output){
        super(output);
    }

    @Override
    public void generateTranslations(TranslationBuilder translations){
        translations.add("itemGroup.abverticaledition", "Additional Blocks: Vertical Edition");

        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            translations.add(value.getSlab(), value.slabTranslation);
            translations.add(value.getStair(), value.stairTranslation);
        }
    }
}
