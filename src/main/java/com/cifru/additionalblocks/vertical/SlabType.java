package com.cifru.additionalblocks.vertical;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SlabType {

    public static final Map<ResourceLocation, SlabType> ALL = new HashMap<>();

    public final ResourceLocation registryName;
    public final String translation;
    public final Supplier<Block> parentBlock;
    public final Supplier<Block> recipeBlock;
    public final ResourceLocation texture;

    public SlabType(ResourceLocation registryName, String translation, Supplier<Block> parentBlock, Supplier<Block> recipeBlock, ResourceLocation texture) {
        this.registryName = registryName;
        this.translation = translation;
        this.parentBlock = parentBlock;
        this.recipeBlock = recipeBlock;
        this.texture = texture;
    }
}
