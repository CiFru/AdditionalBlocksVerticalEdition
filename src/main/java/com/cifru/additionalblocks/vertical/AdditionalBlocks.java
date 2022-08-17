package com.cifru.additionalblocks.vertical;

import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.Supplier;

/**
 * Created 18/03/2022 by SuperMartijn642
 */
@Mod("abverticaledition")
public class AdditionalBlocks {

    @ObjectHolder(value = "test_slab", registryName = "minecraft:block")
    public static VerticalSlabBlock test_slab;

    public AdditionalBlocks() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AdditionalBlocks::onRegisterEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AdditionalBlocks::onGatherDataEvent);
        this.registerSlabs();
    }

    private void registerSlabs() {
        this.createSlab("stone_vertical_slab", "Vertical Stone Slab", () -> Blocks.STONE_SLAB, () -> Blocks.STONE, true, new ResourceLocation("minecraft", "block/stone"));
    }

    private void createSlab(String registryName, String translation, Supplier<Block> parentBlock, Supplier<Block> recipeBlock, boolean hasStoneCutterRecipe, ResourceLocation texture) {
        ResourceLocation resourceLocation = new ResourceLocation("abverticaledition", registryName);
        if (VerticalSlabType.ALL.containsKey(resourceLocation))
            throw new RuntimeException("Tried to register two slab types with registry name '" + registryName + "'!");

        VerticalSlabType.ALL.put(resourceLocation, new VerticalSlabType(resourceLocation, translation, parentBlock, recipeBlock, hasStoneCutterRecipe, texture));
    }

    private static void onRegisterEvent(RegisterEvent e) {
        if (e.getRegistryKey() == ForgeRegistries.Keys.BLOCKS)
            registerBlocks(e.getForgeRegistry());
        else if (e.getRegistryKey() == ForgeRegistries.Keys.ITEMS)
            registerItems(e.getForgeRegistry());
    }

    private static void registerBlocks(IForgeRegistry<Block> registry) {
        for (VerticalSlabType value : VerticalSlabType.ALL.values()) {
            registry.register(value.registryName, new VerticalSlabBlock(BlockBehaviour.Properties.copy(value.parentBlock.get())));
        }
    }

    private static void registerItems(IForgeRegistry<Item> registry) {
        for (VerticalSlabType value : VerticalSlabType.ALL.values()) {
            registry.register(value.registryName, new BlockItem(ForgeRegistries.BLOCKS.getValue(value.registryName), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        }
    }

    private static void onGatherDataEvent(GatherDataEvent e) {
        e.getGenerator().addProvider(e.includeClient(), new VerticalLanguageProvider(e.getGenerator(), "abverticaledition", "en_us"));
        e.getGenerator().addProvider(e.includeClient(), new VerticalBlockModelProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeClient(), new VerticalItemModelProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeClient(), new VerticalBlockStateProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new VerticalRecipeProvider(e.getGenerator()));
        e.getGenerator().addProvider(e.includeServer(), new VerticalTagsProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
    }
}
