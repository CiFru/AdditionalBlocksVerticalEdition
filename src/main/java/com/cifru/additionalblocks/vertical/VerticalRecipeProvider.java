package com.cifru.additionalblocks.vertical;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class VerticalRecipeProvider extends RecipeProvider {

    public VerticalRecipeProvider(DataGenerator data) {
        super(data.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer){
        for (VerticalBlockType value : VerticalBlockType.ALL.values()) {
            ShapedRecipeBuilder slabRecipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, value.getSlab(), 6)
                .pattern("X")
                .pattern("X")
                .pattern("X")
                .define('X', value.recipeBlock.get())
                .unlockedBy("has_item", has(value.recipeBlock.get()));
            saveRecipe(value, value.slabRegistryName, slabRecipe::save, recipeConsumer);
            ShapedRecipeBuilder stairRecipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, value.getStair(), 4)
                    .pattern("XXX")
                    .pattern(" XX")
                    .pattern("  X")
                    .define('X', value.recipeBlock.get())
                    .unlockedBy("has_item", has(value.recipeBlock.get()));
            saveRecipe(value, value.stairRegistryName, stairRecipe::save, recipeConsumer);
            if (value.hasStoneCutterRecipe) {
                SingleItemRecipeBuilder slabStonecuttingRecipe = SingleItemRecipeBuilder
                        .stonecutting(Ingredient.of(value.recipeBlock.get()), RecipeCategory.BUILDING_BLOCKS, value.getSlab(), 2)
                        .unlockedBy("has_item", has(value.recipeBlock.get()));
                saveRecipe(value, new ResourceLocation("abverticaledition", value.slabRegistryName.getPath() + "_stonecutting"), slabStonecuttingRecipe::save, recipeConsumer);
                SingleItemRecipeBuilder stairStonecuttingRecipe = SingleItemRecipeBuilder
                        .stonecutting(Ingredient.of(value.recipeBlock.get()), RecipeCategory.BUILDING_BLOCKS, value.getStair(), 1)
                        .unlockedBy("has_item", has(value.recipeBlock.get()));
                saveRecipe(value, new ResourceLocation("abverticaledition", value.stairRegistryName.getPath() + "_stonecutting"), stairStonecuttingRecipe::save, recipeConsumer);
            }
        }
    }

    private static void saveRecipe(VerticalBlockType type, ResourceLocation recipeLocation, BiConsumer<Consumer<FinishedRecipe>,ResourceLocation> recipe, Consumer<FinishedRecipe> recipeConsumer) {
        // If the block type is not dependent on other mods, just save the recipe the regular way
        if (type.dependentMods.isEmpty()) {
            recipe.accept(recipeConsumer, recipeLocation);
            return;
        }

        // Create a conditional recipe
        ConditionalRecipe.Builder builder = ConditionalRecipe.builder();
        // For each dependent mod, add a condition for that mod to be available
        type.dependentMods.stream().map(ModLoadedCondition::new).forEach(builder::addCondition);
        builder.addRecipe(consumer -> recipe.accept(consumer, recipeLocation));
        builder.build(recipeConsumer, recipeLocation);
    }
}
