package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class VerticalRecipeProvider extends RecipeProvider {

    public VerticalRecipeProvider(DataGenerator data) {
        super(data);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        for (VerticalSlabType value : VerticalSlabType.ALL.values()) {
            ShapedRecipeBuilder.shaped(ForgeRegistries.BLOCKS.getValue(value.registryName), 6)
                    .pattern("X")
                    .pattern("X")
                    .pattern("X")
                    .define('X', value.recipeBlock.get())
                    .unlockedBy("has_item", has(value.recipeBlock.get()))
                    .save(recipeConsumer);
            if(value.hasStoneCutterRecipe)
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(value.recipeBlock.get()),ForgeRegistries.BLOCKS.getValue(value.registryName),2)
                        .unlockedBy("has_item", has(value.recipeBlock.get()))
                        .save(recipeConsumer, new ResourceLocation("abverticaledition", value.registryName.getPath() + "_stonecutting"));
        }
    }
}
