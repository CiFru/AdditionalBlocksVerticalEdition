package com.cifru.additionalblocks.vertical;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class VerticalModelProvider extends FabricModelProvider {

    public VerticalModelProvider(FabricDataOutput output){
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators){
        // Models
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            this.blockModel(generators.modelOutput, value.slabRegistryName.getPath(), new ResourceLocation("abverticaledition", "block/vertical_slab"), value.texture);
            this.blockModel(generators.modelOutput, value.slabRegistryName.getPath() + "_full", new ResourceLocation("minecraft", "block/cube_all"), value.texture);
            this.blockModel(generators.modelOutput, value.slabRegistryName.getPath() + "_post", new ResourceLocation("abverticaledition", "block/vertical_slab_post"), value.texture);
            this.blockModel(generators.modelOutput, value.stairRegistryName.getPath(), new ResourceLocation("abverticaledition", "block/vertical_stair"), value.texture);
        }

        // Block states
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            generators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(value.getSlab())
                    .with(PropertyDispatch.properties(VerticalSlabBlock.SHAPE_PROPERTY, VerticalSlabBlock.CONNECTION_PROPERTY).generate(
                        (shape, connection) -> shape == VerticalSlabBlock.SlabShape.FULL ? Variant.variant().with(VariantProperties.MODEL, new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath() + "_full"))
                            : connection == VerticalSlabBlock.SlabConnection.NONE ? Variant.variant().with(VariantProperties.MODEL, new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath())).with(VariantProperties.Y_ROT, rotation(shape.getModelRotation())).with(VariantProperties.UV_LOCK, true)
                            : Variant.variant().with(VariantProperties.MODEL, new ResourceLocation("abverticaledition", "block/" + value.slabRegistryName.getPath() + "_post")).with(VariantProperties.Y_ROT, rotation((int)(connection == VerticalSlabBlock.SlabConnection.LEFT ? shape.getDirection() : shape.getDirection().getClockWise()).toYRot() - 180)).with(VariantProperties.UV_LOCK, true)
                    ))
            );
            generators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(value.getStair())
                    .with(PropertyDispatch.property(VerticalStairBlock.DIRECTION_PROPERTY).generate(
                        direction -> Variant.variant().with(VariantProperties.MODEL, new ResourceLocation("abverticaledition", "block/" + value.stairRegistryName.getPath())).with(VariantProperties.Y_ROT, rotation((int)direction.toYRot() - 180)).with(VariantProperties.UV_LOCK, true)
                    ))
            );
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators){
        for(VerticalBlockType value : VerticalBlockType.ALL.values()){
            this.itemModel(generators.output, value.slabRegistryName.getPath());
            this.itemModel(generators.output, value.stairRegistryName.getPath());
        }
    }

    private void blockModel(BiConsumer<ResourceLocation,Supplier<JsonElement>> output, String name, ResourceLocation parent, ResourceLocation texture){
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent.toString());
        JsonObject textures = new JsonObject();
        textures.addProperty("all", texture.toString());
        json.add("textures", textures);
        output.accept(new ResourceLocation("abverticaledition", "block/" + name), () -> json);
    }

    private void itemModel(BiConsumer<ResourceLocation,Supplier<JsonElement>> output, String name){
        JsonObject json = new JsonObject();
        json.addProperty("parent", "abverticaledition:block/" + name);
        output.accept(new ResourceLocation("abverticaledition", "item/" + name), () -> json);
    }

    private static VariantProperties.Rotation rotation(int rotation){
        rotation = (rotation + 360) % 360;
        return switch(rotation){
            case 0 -> VariantProperties.Rotation.R0;
            case 90 -> VariantProperties.Rotation.R90;
            case 180 -> VariantProperties.Rotation.R180;
            case 270 -> VariantProperties.Rotation.R270;
            default -> throw new IllegalStateException("Unexpected value: " + rotation);
        };
    }

    @Override
    public String getName(){
        return "Vertical Edition Model Provider";
    }
}
