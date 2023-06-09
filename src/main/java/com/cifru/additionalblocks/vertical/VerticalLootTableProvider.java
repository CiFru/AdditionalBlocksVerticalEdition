package com.cifru.additionalblocks.vertical;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.FunctionUserBuilder;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created 02/03/2023 by SuperMartijn642
 */
public class VerticalLootTableProvider extends LootTableProvider {

    public VerticalLootTableProvider(DataGenerator generator){
        super(generator.getPackOutput(), Set.of(), List.of());
    }

    @Override
    public List<SubProviderEntry> getTables(){
        return List.of(new SubProviderEntry(() -> consumer -> {
            // Slabs
            ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals("abverticaledition"))
                .map(Map.Entry::getValue)
                .filter(VerticalSlabBlock.class::isInstance)
                .forEach(block -> consumer.accept(block.getLootTable(), LootTable.lootTable().withPool(LootPool.lootPool().add(applyExplosionDecay(LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(VerticalSlabBlock.SHAPE_PROPERTY, VerticalSlabBlock.SlabShape.FULL)))))))));

            // Stairs
            ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals("abverticaledition"))
                .map(Map.Entry::getValue)
                .filter(VerticalStairBlock.class::isInstance)
                .forEach(block -> consumer.accept(block.getLootTable(), LootTable.lootTable().withPool(applyExplosionCondition(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block))))));
        }, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation,LootTable> map, ValidationContext validationContext){
        map.forEach((a, b) -> b.validate(validationContext.setParams(b.getParamSet()).enterElement("{" + a + "}", new LootDataId<>(LootDataType.TABLE, a))));
    }

    private static <T extends FunctionUserBuilder<T>> T applyExplosionDecay(FunctionUserBuilder<T> p_248548_){
        return p_248548_.apply(ApplyExplosionDecay.explosionDecay());
    }

    private static <T extends ConditionUserBuilder<T>> T applyExplosionCondition(ConditionUserBuilder<T> p_248851_){
        return p_248851_.when(ExplosionCondition.survivesExplosion());
    }
}
