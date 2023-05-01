package com.cifru.additionalblocks.vertical;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class VerticalRecipeProvider extends FabricRecipeProvider {

    public VerticalRecipeProvider(FabricDataOutput output){
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> recipeConsumer){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
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
            if(value.hasStoneCutterRecipe){
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

    private static void saveRecipe(VerticalBlockType type, ResourceLocation recipeLocation, BiConsumer<Consumer<FinishedRecipe>,ResourceLocation> recipe, Consumer<FinishedRecipe> recipeConsumer){
        // If the block type is not dependent on other mods, just save the recipe the regular way
        if(type.dependentMods.isEmpty()){
            recipe.accept(recipeConsumer, recipeLocation);
            return;
        }

        recipe.accept(result -> new FinishedRecipe() {

            @Override
            public JsonObject serializeRecipe(){
                JsonObject json = result.serializeRecipe();
                JsonArray conditions = new JsonArray();
                conditions.add(DefaultResourceConditions.allModsLoaded(type.dependentMods.toArray(String[]::new)).toJson());
                json.add(ResourceConditions.CONDITIONS_KEY, conditions);
                return json;
            }

            @Override
            public void serializeRecipeData(JsonObject json){
                result.serializeRecipeData(json);
            }

            @Override
            public ResourceLocation getId(){
                return result.getId();
            }

            @Override
            public RecipeSerializer<?> getType(){
                return result.getType();
            }

            @Nullable
            @Override
            public JsonObject serializeAdvancement(){
                return result.serializeAdvancement();
            }

            @Nullable
            @Override
            public ResourceLocation getAdvancementId(){
                return result.getAdvancementId();
            }
        }, recipeLocation);
    }
}
