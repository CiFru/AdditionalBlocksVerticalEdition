package com.cifru.additionalblocks.vertical;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.FunctionUserBuilder;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Map;

/**
 * Created 02/03/2023 by SuperMartijn642
 */
public class VerticalLootTableProvider extends FabricBlockLootTableProvider {

    public VerticalLootTableProvider(FabricDataGenerator output){
        super(output);
    }

    @Override
    public void generateBlockLootTables(){
        // Slabs
        Registry.BLOCK.entrySet().stream()
            .filter(entry -> entry.getKey().location().getNamespace().equals("abverticaledition"))
            .map(Map.Entry::getValue)
            .filter(VerticalSlabBlock.class::isInstance)
            .forEach(block -> this.add(block, LootTable.lootTable().withPool(LootPool.lootPool().add(applyExplosionDecay(LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(VerticalSlabBlock.SHAPE_PROPERTY, VerticalSlabBlock.SlabShape.FULL)))))))));
        // Stairs
        Registry.BLOCK.entrySet().stream()
            .filter(entry -> entry.getKey().location().getNamespace().equals("abverticaledition"))
            .map(Map.Entry::getValue)
            .filter(VerticalStairBlock.class::isInstance)
            .forEach(block -> this.add(block, LootTable.lootTable().withPool(applyExplosionCondition(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block))))));
    }

    private static <T extends FunctionUserBuilder<T>> T applyExplosionDecay(FunctionUserBuilder<T> p_248548_){
        return p_248548_.apply(ApplyExplosionDecay.explosionDecay());
    }

    private static <T extends ConditionUserBuilder<T>> T applyExplosionCondition(ConditionUserBuilder<T> p_248851_){
        return p_248851_.when(ExplosionCondition.survivesExplosion());
    }
}
