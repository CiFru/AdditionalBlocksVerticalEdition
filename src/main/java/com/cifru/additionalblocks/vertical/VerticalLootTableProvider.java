package com.cifru.additionalblocks.vertical;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created 02/03/2023 by SuperMartijn642
 */
public class VerticalLootTableProvider extends LootTableProvider {

    public VerticalLootTableProvider(DataGenerator generator){
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation,LootTable.Builder>>>,LootContextParamSet>> getTables(){
        BlockLoot blockLoot = new BlockLoot() {
            @Override
            protected Iterable<Block> getKnownBlocks(){
                return ForgeRegistries.BLOCKS.getEntries().stream()
                    .filter(entry -> entry.getKey().location().getNamespace().equals("abverticaledition"))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            }

            @Override
            protected void addTables(){
                // Slabs
                ForgeRegistries.BLOCKS.getEntries().stream()
                    .filter(entry -> entry.getKey().location().getNamespace().equals("abverticaledition"))
                    .map(Map.Entry::getValue)
                    .filter(VerticalSlabBlock.class::isInstance)
                    .forEach(block -> this.add(block, LootTable.lootTable().withPool(LootPool.lootPool().add(applyExplosionDecay(block.asItem(), LootItem.lootTableItem(block.asItem()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(VerticalSlabBlock.SHAPE_PROPERTY, VerticalSlabBlock.SlabShape.FULL)))))))));

                // Stairs
                ForgeRegistries.BLOCKS.getEntries().stream()
                    .filter(entry -> entry.getKey().location().getNamespace().equals("abverticaledition"))
                    .map(Map.Entry::getValue)
                    .filter(VerticalStairBlock.class::isInstance)
                    .forEach(this::dropSelf);
            }
        };
        return ImmutableList.of(Pair.of(() -> blockLoot, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation,LootTable> map, ValidationContext validationContext){
        map.forEach((a, b) -> LootTables.validate(validationContext, a, b));
    }
}
