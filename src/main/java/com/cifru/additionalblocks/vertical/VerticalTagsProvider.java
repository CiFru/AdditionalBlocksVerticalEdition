package com.cifru.additionalblocks.vertical;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.VanillaPackResources;
import net.minecraft.server.packs.repository.ServerPacksSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class VerticalTagsProvider extends FabricTagProvider.BlockTagProvider {

    private static final Gson GSON = new GsonBuilder().create();
    private static final VanillaPackResources VANILLA_RESOURCES = ServerPacksSource.createVanillaPackSource();

    public VerticalTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider){
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider){
        List<TagKey<Block>> tags = List.of(
            BlockTags.MINEABLE_WITH_AXE,
            BlockTags.MINEABLE_WITH_HOE,
            BlockTags.MINEABLE_WITH_PICKAXE,
            BlockTags.MINEABLE_WITH_SHOVEL,
            BlockTags.NEEDS_DIAMOND_TOOL,
            BlockTags.NEEDS_IRON_TOOL,
            BlockTags.NEEDS_STONE_TOOL
        );

        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            for(TagKey<Block> tag : tags){
                if(value.dependentMods.isEmpty()){
                    if(this.loadVanillaTag(tag.location()).contains(value.parentSlabBlock.get()))
                        this.tag(tag).add(BuiltInRegistries.BLOCK.getResourceKey(value.getSlab()).get());
                    if(this.loadVanillaTag(tag.location()).contains(value.parentStairBlock.get()))
                        this.tag(tag).add(BuiltInRegistries.BLOCK.getResourceKey(value.getStair()).get());
                }else{
                    if(this.loadVanillaTag(tag.location()).contains(value.parentSlabBlock.get()))
                        this.tag(tag).addOptional(value.slabRegistryName);
                    if(this.loadVanillaTag(tag.location()).contains(value.parentStairBlock.get()))
                        this.tag(tag).addOptional(value.stairRegistryName);
                }
            }
        }
    }

    private final Map<ResourceLocation,List<Block>> loadedTags = Maps.newHashMap();

    private List<Block> loadVanillaTag(ResourceLocation location){
        if(this.loadedTags.containsKey(location))
            return this.loadedTags.get(location);

        List<Block> blocks = new ArrayList<>();

        ResourceLocation tagLocation = new ResourceLocation(location.getNamespace(), "tags/blocks/" + location.getPath() + ".json");
        try(InputStream stream = VANILLA_RESOURCES.getResource(PackType.SERVER_DATA, tagLocation).get()){
            JsonObject json = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);
            JsonArray array = json.getAsJsonArray("values");
            for(JsonElement element : array){
                String name = element.getAsString();
                if(name.charAt(0) == '#'){
                    blocks.addAll(this.loadVanillaTag(new ResourceLocation(name.substring(1))));
                    continue;
                }
                ResourceLocation registryName = new ResourceLocation(name);
                Block block = BuiltInRegistries.BLOCK.get(registryName);
                if(block == null || block == Blocks.AIR)
                    throw new JsonParseException("Unknown block '" + registryName + "' in '" + location + "'");
                blocks.add(block);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        this.loadedTags.put(location, blocks);
        return blocks;
    }
}
