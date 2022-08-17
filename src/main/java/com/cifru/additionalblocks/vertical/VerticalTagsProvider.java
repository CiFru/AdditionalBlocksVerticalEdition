package com.cifru.additionalblocks.vertical;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.VanillaPackResources;
import net.minecraft.server.packs.repository.ServerPacksSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VerticalTagsProvider extends BlockTagsProvider {
    private static final Gson GSON = new GsonBuilder().create();

    public VerticalTagsProvider(DataGenerator dataGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        List<TagKey<Block>> tags = List.of(
                BlockTags.MINEABLE_WITH_AXE,
                BlockTags.MINEABLE_WITH_HOE,
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.MINEABLE_WITH_SHOVEL,
                BlockTags.NEEDS_DIAMOND_TOOL,
                BlockTags.NEEDS_IRON_TOOL,
                BlockTags.NEEDS_STONE_TOOL
        );

        for (VerticalSlabType value : VerticalSlabType.ALL.values()) {
            for (TagKey<Block> tag : tags) {
                if (loadVanillaTag(tag.location()).contains(value.parentBlock.get()))
                    this.tag(tag).replace(false).add(ForgeRegistries.BLOCKS.getValue(value.registryName));
            }
        }
    }

    private final Map<ResourceLocation, List<Block>> loadedTags = Maps.newHashMap();
    private final PackResources vanillaResources = new VanillaPackResources(ServerPacksSource.BUILT_IN_METADATA, "minecraft");

    private List<Block> loadVanillaTag(ResourceLocation location) {
        if (this.loadedTags.containsKey(location))
            return this.loadedTags.get(location);

        try (InputStream resource = this.vanillaResources.getResource(PackType.SERVER_DATA, new ResourceLocation(location.getNamespace(), "tags/blocks/" + location.getPath() + ".json"))) {
            JsonObject json = GSON.fromJson(new InputStreamReader(resource), JsonObject.class);
            JsonArray array = json.getAsJsonArray("values");
            List<Block> blocks = new ArrayList<>();
            for (JsonElement element : array) {
                String name = element.getAsString();
                if (name.charAt(0) == '#') {
                    blocks.addAll(this.loadVanillaTag(new ResourceLocation(name.substring(1))));
                    continue;
                }
                ResourceLocation registryName = new ResourceLocation(name);
                Block block = ForgeRegistries.BLOCKS.getValue(registryName);
                if (block == null)
                    throw new JsonParseException("Unknown block '" + registryName + "' in '" + location + "'");
                blocks.add(block);
            }
            this.loadedTags.put(location, blocks);
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
