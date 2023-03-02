package com.cifru.additionalblocks.vertical;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

/**
 * Created 18/03/2022 by SuperMartijn642
 */
@Mod("abverticaledition")
public class AdditionalBlocks {

    public static final CreativeModeTab GROUP = new CreativeModeTab("abverticaledition") {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(stone_brick_vertical_slab);
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items){
            VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getStair).map(Block::asItem).map(Item::getDefaultInstance).forEach(items::add);
            VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getSlab).map(Block::asItem).map(Item::getDefaultInstance).forEach(items::add);
        }
    };

    @ObjectHolder(value = "stone_brick_vertical_slab")
    public static Block stone_brick_vertical_slab;

    public AdditionalBlocks(){
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, (RegistryEvent.Register<Block> e) -> registerBlocks(e.getRegistry()));
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, (RegistryEvent.Register<Item> e) -> registerItems(e.getRegistry()));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AdditionalBlocks::onGatherDataEvent);
    }

    private static void registerBlocks(IForgeRegistry<Block> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            registry.register(new VerticalSlabBlock(BlockBehaviour.Properties.copy(value.parentSlabBlock.get())).setRegistryName(value.slabRegistryName));
            registry.register(new VerticalStairBlock(BlockBehaviour.Properties.copy(value.parentStairBlock.get())).setRegistryName(value.stairRegistryName));
        }
    }

    private static void registerItems(IForgeRegistry<Item> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            registry.register(new BlockItem(value.getSlab(), new Item.Properties().tab(GROUP)).setRegistryName(value.slabRegistryName));
            registry.register(new BlockItem(value.getStair(), new Item.Properties().tab(GROUP)).setRegistryName(value.stairRegistryName));
        }
    }

    private static void onGatherDataEvent(GatherDataEvent e){
        if(e.includeClient()){
            e.getGenerator().addProvider(new VerticalLanguageProvider(e.getGenerator(), "abverticaledition", "en_us"));
            e.getGenerator().addProvider(new VerticalBlockModelProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
            e.getGenerator().addProvider(new VerticalItemModelProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
            e.getGenerator().addProvider(new VerticalBlockStateProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
        }
        if(e.includeServer()){
            e.getGenerator().addProvider(new VerticalRecipeProvider(e.getGenerator()));
            e.getGenerator().addProvider(new VerticalTagsProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
            e.getGenerator().addProvider(new VerticalLootTableProvider(e.getGenerator()));
        }
    }
}
