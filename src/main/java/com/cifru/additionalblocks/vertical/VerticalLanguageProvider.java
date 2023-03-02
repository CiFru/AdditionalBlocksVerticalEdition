package com.cifru.additionalblocks.vertical;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class VerticalLanguageProvider extends LanguageProvider {

    public VerticalLanguageProvider(PackOutput output, String modid, String locale){
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations(){
        this.add("itemGroup.abverticaledition", "Additional Blocks: Vertical Edition");

        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            this.add(value.getSlab(), value.slabTranslation);
            this.add(value.getStair(), value.stairTranslation);
        }
    }
}
