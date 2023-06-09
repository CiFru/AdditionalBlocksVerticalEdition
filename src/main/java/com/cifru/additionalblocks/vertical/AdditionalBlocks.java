package com.cifru.additionalblocks.vertical;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegisterEvent;

/**
 * Created 18/03/2022 by SuperMartijn642
 */
@Mod("abverticaledition")
public class AdditionalBlocks {

    @ObjectHolder(value = "stone_brick_vertical_slab", registryName = "minecraft:block")
    public static Block stone_brick_vertical_slab;

    public AdditionalBlocks(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AdditionalBlocks::onRegisterEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AdditionalBlocks::onGatherDataEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AdditionalBlocks::registerCreativeModeTab);
    }

    private static void onRegisterEvent(RegisterEvent e){
        if(e.getRegistryKey() == ForgeRegistries.Keys.BLOCKS)
            registerBlocks(e.getForgeRegistry());
        else if(e.getRegistryKey() == ForgeRegistries.Keys.ITEMS)
            registerItems(e.getForgeRegistry());
        else if(e.getRegistryKey() == Registries.CREATIVE_MODE_TAB)
            registerCreativeModeTab(e.getVanillaRegistry());
    }

    private static void registerBlocks(IForgeRegistry<Block> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            registry.register(value.slabRegistryName, new VerticalSlabBlock(BlockBehaviour.Properties.copy(value.parentSlabBlock.get())));
            registry.register(value.stairRegistryName, new VerticalStairBlock(BlockBehaviour.Properties.copy(value.parentStairBlock.get())));
        }
    }

    private static void registerItems(IForgeRegistry<Item> registry){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            registry.register(value.slabRegistryName, new BlockItem(value.getSlab(), new Item.Properties()));
            registry.register(value.stairRegistryName, new BlockItem(value.getStair(), new Item.Properties()));
        }
    }

    private static void onGatherDataEvent(GatherDataEvent e){
        e.getGenerator().addProvider(e.includeClient(), new VerticalLanguageProvider(e.getGenerator().getPackOutput(), "abverticaledition", "en_us"));
        e.getGenerator().addProvider(e.includeClient(), new VerticalBlockModelProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeClient(), new VerticalItemModelProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeClient(), new VerticalBlockStateProvider(e.getGenerator(), "abverticaledition", e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new VerticalRecipeProvider(e.getGenerator()));
        e.getGenerator().addProvider(e.includeServer(), new VerticalTagsProvider(e.getGenerator(), e.getLookupProvider(), "abverticaledition", e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new VerticalLootTableProvider(e.getGenerator()));
    }

    private static void registerCreativeModeTab(Registry<CreativeModeTab> registry){
        Registry.register(registry, new ResourceLocation("abverticaledition", "main"),
            CreativeModeTab.builder().icon(() -> stone_brick_vertical_slab.asItem().getDefaultInstance()).displayItems((parameters, output) -> {
                VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getStair).map(Block::asItem).map(Item::getDefaultInstance).forEach(output::accept);
                VerticalBlockType.ALL_ORDERED.stream().map(VerticalBlockType::getSlab).map(Block::asItem).map(Item::getDefaultInstance).forEach(output::accept);
            }).title(Component.translatable("itemGroup.abverticaledition")).build()
        );
    }
}
