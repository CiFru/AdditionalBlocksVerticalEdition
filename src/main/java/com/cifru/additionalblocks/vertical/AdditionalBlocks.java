package com.cifru.additionalblocks.vertical;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Created 18/03/2022 by SuperMartijn642
 */
public class AdditionalBlocks implements ModInitializer, DataGeneratorEntrypoint {

    static{
        ((ItemGroupExtensions)CreativeModeTab.TAB_BUILDING_BLOCKS).fabric_expandArray();
    }

    public static final CreativeModeTab GROUP = new CreativeModeTab(CreativeModeTab.TABS.length - 1, "abverticaledition") {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(VerticalBlockType.STONE_BRICKS.getSlab());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items){
            VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getStair).map(Block::asItem).map(Item::getDefaultInstance).forEach(items::add);
            VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getSlab).map(Block::asItem).map(Item::getDefaultInstance).forEach(items::add);
        }
    };

    @Override
    public void onInitialize(){
    }

    public static void registerBlocks(){
        registerBlocks(Registry.BLOCK);
        registerItems(Registry.ITEM);
    }

    private static void registerBlocks(Registry<Block> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            Registry.register(registry, value.slabRegistryName, new VerticalSlabBlock(BlockBehaviour.Properties.copy(value.parentSlabBlock.get())));
            Registry.register(registry, value.stairRegistryName, new VerticalStairBlock(BlockBehaviour.Properties.copy(value.parentStairBlock.get())));
        }
    }

    private static void registerItems(Registry<Item> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            Registry.register(registry, value.slabRegistryName, new BlockItem(value.getSlab(), new Item.Properties().tab(GROUP)));
            Registry.register(registry, value.stairRegistryName, new BlockItem(value.getStair(), new Item.Properties().tab(GROUP)));
        }
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator){
        generator.addProvider(VerticalLanguageProvider::new);
        generator.addProvider(VerticalModelProvider::new);
        generator.addProvider(VerticalRecipeProvider::new);
        generator.addProvider(VerticalTagsProvider::new);
        generator.addProvider(VerticalLootTableProvider::new);
    }
}
