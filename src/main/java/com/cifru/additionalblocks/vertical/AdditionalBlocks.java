package com.cifru.additionalblocks.vertical;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Created 18/03/2022 by SuperMartijn642
 */
public class AdditionalBlocks implements ModInitializer, DataGeneratorEntrypoint {

    @Override
    public void onInitialize(){
        registerCreativeTab();
    }

    public static void registerBlocks(){
        registerBlocks(BuiltInRegistries.BLOCK);
        registerItems(BuiltInRegistries.ITEM);
    }

    private static void registerBlocks(Registry<Block> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            Registry.register(registry, value.slabRegistryName, new VerticalSlabBlock(BlockBehaviour.Properties.copy(value.parentSlabBlock.get())));
            Registry.register(registry, value.stairRegistryName, new VerticalStairBlock(BlockBehaviour.Properties.copy(value.parentStairBlock.get())));
        }
    }

    private static void registerItems(Registry<Item> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            Registry.register(registry, value.slabRegistryName, new BlockItem(value.getSlab(), new Item.Properties()));
            Registry.register(registry, value.stairRegistryName, new BlockItem(value.getStair(), new Item.Properties()));
        }
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator){
        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider((output, registriesFuture) -> new VerticalLanguageProvider(output));
        pack.addProvider((output, registriesFuture) -> new VerticalModelProvider(output));
        pack.addProvider((output, registriesFuture) -> new VerticalRecipeProvider(output));
        pack.addProvider(VerticalTagsProvider::new);
        pack.addProvider((output, registriesFuture) -> new VerticalLootTableProvider(output));
    }

    private static void registerCreativeTab(){
        FabricItemGroup.builder(new ResourceLocation("abverticaledition", "main")).icon(() -> VerticalBlockType.STONE_BRICKS.getSlab().asItem().getDefaultInstance()).displayItems((parameters, output) -> {
            VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getStair).map(Block::asItem).map(Item::getDefaultInstance).forEach(output::accept);
            VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getSlab).map(Block::asItem).map(Item::getDefaultInstance).forEach(output::accept);
        }).title(Component.translatable("itemGroup.abverticaledition")).build();
    }
}
