package com.cifru.additionalblocks.vertical;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class VerticalBlockType {

    public static final Map<ResourceLocation,VerticalBlockType> ALL = new HashMap<>();
    public static final List<VerticalBlockType> ALL_ORDERED = new ArrayList<>();

    //cut copper
    public static final VerticalBlockType CUT_COPPER = createBlockType("cut_copper", "Cut Copper", () -> Blocks.CUT_COPPER_SLAB, () -> Blocks.CUT_COPPER_STAIRS, () -> Blocks.CUT_COPPER, true, new ResourceLocation("minecraft", "block/cut_copper"));
    public static final VerticalBlockType EXPOSED_CUT_COPPER = createBlockType("exposed_cut_copper", "Exposed Cut Copper", () -> Blocks.EXPOSED_CUT_COPPER_SLAB, () -> Blocks.EXPOSED_CUT_COPPER_STAIRS, () -> Blocks.EXPOSED_CUT_COPPER, true, new ResourceLocation("minecraft", "block/exposed_cut_copper"));
    public static final VerticalBlockType WEATHERED_CUT_COPPER = createBlockType("weathered_cut_copper", "Weathered Cut Copper", () -> Blocks.WEATHERED_CUT_COPPER_SLAB, () -> Blocks.WEATHERED_CUT_COPPER_STAIRS, () -> Blocks.WEATHERED_CUT_COPPER, true, new ResourceLocation("minecraft", "block/weathered_cut_copper"));
    public static final VerticalBlockType OXIDIZED_CUT_COPPER = createBlockType("oxidized_cut_copper", "Oxidized Cut Copper", () -> Blocks.OXIDIZED_CUT_COPPER_SLAB, () -> Blocks.OXIDIZED_CUT_COPPER_STAIRS, () -> Blocks.OXIDIZED_CUT_COPPER, true, new ResourceLocation("minecraft", "block/oxidized_cut_copper"));

    //waxed cut copper
    public static final VerticalBlockType WAXED_CUT_COPPER = createBlockType("waxed_cut_copper", "Waxed Cut Copper", () -> Blocks.WAXED_CUT_COPPER_SLAB, () -> Blocks.WAXED_CUT_COPPER_STAIRS, () -> Blocks.WAXED_CUT_COPPER, true, new ResourceLocation("minecraft", "block/cut_copper"));
    public static final VerticalBlockType WAXED_EXPOSED_CUT_COPPER = createBlockType("waxed_exposed_cut_copper", "Waxed Exposed Cut Copper", () -> Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, () -> Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS, () -> Blocks.WAXED_EXPOSED_CUT_COPPER, true, new ResourceLocation("minecraft", "block/exposed_cut_copper"));
    public static final VerticalBlockType WAXED_WEATHERED_CUT_COPPER = createBlockType("waxed_weathered_cut_copper", "Waxed Weathered Cut Copper", () -> Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, () -> Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS, () -> Blocks.WAXED_WEATHERED_CUT_COPPER, true, new ResourceLocation("minecraft", "block/weathered_cut_copper"));
    public static final VerticalBlockType WAXED_OXIDIZED_CUT_COPPER = createBlockType("waxed_oxidized_cut_copper", "Waxed Oxidized Cut Copper", () -> Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, () -> Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS, () -> Blocks.WAXED_OXIDIZED_CUT_COPPER, true, new ResourceLocation("minecraft", "block/oxidized_cut_copper"));

    //wood types
    public static final VerticalBlockType OAK = createBlockType("oak", "Oak", () -> Blocks.OAK_SLAB, () -> Blocks.OAK_STAIRS, () -> Blocks.OAK_PLANKS, false, new ResourceLocation("minecraft", "block/oak_planks"));
    public static final VerticalBlockType SPRUCE = createBlockType("spruce", "Spruce", () -> Blocks.SPRUCE_SLAB, () -> Blocks.SPRUCE_STAIRS, () -> Blocks.SPRUCE_PLANKS, false, new ResourceLocation("minecraft", "block/spruce_planks"));
    public static final VerticalBlockType BIRCH = createBlockType("birch", "Birch", () -> Blocks.BIRCH_SLAB, () -> Blocks.BIRCH_STAIRS, () -> Blocks.BIRCH_PLANKS, false, new ResourceLocation("minecraft", "block/birch_planks"));
    public static final VerticalBlockType JUNGLE = createBlockType("jungle", "Jungle", () -> Blocks.JUNGLE_SLAB, () -> Blocks.JUNGLE_STAIRS, () -> Blocks.JUNGLE_PLANKS, false, new ResourceLocation("minecraft", "block/jungle_planks"));
    public static final VerticalBlockType ACACIA = createBlockType("acacia", "Acacia", () -> Blocks.ACACIA_SLAB, () -> Blocks.ACACIA_STAIRS, () -> Blocks.ACACIA_PLANKS, false, new ResourceLocation("minecraft", "block/acacia_planks"));
    public static final VerticalBlockType DARK_OAK = createBlockType("dark_oak", "Dark Oak", () -> Blocks.DARK_OAK_SLAB, () -> Blocks.DARK_OAK_STAIRS, () -> Blocks.DARK_OAK_PLANKS, false, new ResourceLocation("minecraft", "block/dark_oak_planks"));
    public static final VerticalBlockType MANGROVE = createBlockType("mangrove", "Mangrove", () -> Blocks.MANGROVE_SLAB, () -> Blocks.MANGROVE_STAIRS, () -> Blocks.MANGROVE_PLANKS, false, new ResourceLocation("minecraft", "block/mangrove_planks"));
    public static final VerticalBlockType CRIMSON = createBlockType("crimson", "Crimson", () -> Blocks.CRIMSON_SLAB, () -> Blocks.CRIMSON_STAIRS, () -> Blocks.CRIMSON_PLANKS, false, new ResourceLocation("minecraft", "block/crimson_planks"));
    public static final VerticalBlockType WARPED = createBlockType("warped", "Warped", () -> Blocks.WARPED_SLAB, () -> Blocks.WARPED_STAIRS, () -> Blocks.WARPED_PLANKS, false, new ResourceLocation("minecraft", "block/warped_planks"));

    //regular stone & brick types
    public static final VerticalBlockType STONE = createBlockType("stone", "Stone", () -> Blocks.STONE_SLAB, () -> Blocks.STONE_STAIRS, () -> Blocks.STONE, true, new ResourceLocation("minecraft", "block/stone"));
    public static final VerticalBlockType SMOOTH_STONE = createBlockType("smoooth_stone", "Smooth Stone", () -> Blocks.SMOOTH_STONE_SLAB, () -> Blocks.SMOOTH_STONE, () -> Blocks.SMOOTH_STONE, true, new ResourceLocation("minecraft", "block/smooth_stone"));

    public static final VerticalBlockType SANDSTONE = createBlockType("sandstone", "Sandstone", () -> Blocks.SANDSTONE_SLAB, () -> Blocks.SANDSTONE_STAIRS, () -> Blocks.SANDSTONE, true, new ResourceLocation("minecraft", "block/sandstone"));
    public static final VerticalBlockType SMOOTH_SANDSTONE = createBlockType("smooth_sandstone", "Smooth Sandstone", () -> Blocks.SMOOTH_SANDSTONE_SLAB, () -> Blocks.SMOOTH_SANDSTONE_STAIRS, () -> Blocks.SMOOTH_SANDSTONE, true, new ResourceLocation("minecraft", "block/sandstone_top"));
    public static final VerticalBlockType RED_SANDSTONE = createBlockType("red_sandstone", "Red Sandstone", () -> Blocks.RED_SANDSTONE_SLAB, () -> Blocks.RED_SANDSTONE_STAIRS, () -> Blocks.RED_SANDSTONE, true, new ResourceLocation("minecraft", "block/red_sandstone"));
    public static final VerticalBlockType SMOOTH_RED_SANDSTONE = createBlockType("smooth_red_sandstone", "Smooth Red Sandstone", () -> Blocks.SMOOTH_RED_SANDSTONE_SLAB, () -> Blocks.SMOOTH_RED_SANDSTONE_STAIRS, () -> Blocks.SMOOTH_RED_SANDSTONE, true, new ResourceLocation("minecraft", "block/red_sandstone_top"));

    public static final VerticalBlockType STONE_BRICKS = createBlockType("stone_brick", "Stone Brick", () -> Blocks.STONE_BRICK_SLAB, () -> Blocks.STONE_BRICK_STAIRS, () -> Blocks.STONE_BRICKS, true, new ResourceLocation("minecraft", "block/stone_bricks"));
    public static final VerticalBlockType MOSSY_STONE_BRICKS = createBlockType("mossy_stone_brick", "Mossy Stone Brick", () -> Blocks.MOSSY_STONE_BRICK_SLAB, () -> Blocks.MOSSY_STONE_BRICK_STAIRS, () -> Blocks.MOSSY_STONE_BRICKS, true, new ResourceLocation("minecraft", "block/mossy_stone_bricks"));

    public static final VerticalBlockType COBBLESTONE = createBlockType("cobblestone", "Cobblestone", () -> Blocks.COBBLESTONE_SLAB, () -> Blocks.COBBLESTONE_STAIRS, () -> Blocks.COBBLESTONE, true, new ResourceLocation("minecraft", "block/cobblestone"));
    public static final VerticalBlockType MOSSY_COBBLESTONE = createBlockType("mossy_cobblestone", "Mossy Cobblestone", () -> Blocks.MOSSY_COBBLESTONE_SLAB, () -> Blocks.MOSSY_COBBLESTONE_STAIRS, () -> Blocks.MOSSY_COBBLESTONE, true, new ResourceLocation("minecraft", "block/mossy_cobblestone"));

    public static final VerticalBlockType BRICK = createBlockType("brick", "Brick", () -> Blocks.BRICK_SLAB, () -> Blocks.BRICK_STAIRS, () -> Blocks.BRICKS, true, new ResourceLocation("minecraft", "block/bricks"));
    public static final VerticalBlockType MUD_BRICK = createBlockType("mud_brick", "Mud Brick", () -> Blocks.MUD_BRICK_SLAB, () -> Blocks.MUD_BRICK_STAIRS, () -> Blocks.MUD_BRICKS, true, new ResourceLocation("minecraft", "block/mud_bricks"));

    public static final VerticalBlockType QUARTZ = createBlockType("quartz", "Quartz", () -> Blocks.QUARTZ_SLAB, () -> Blocks.QUARTZ_STAIRS, () -> Blocks.QUARTZ_BLOCK, true, new ResourceLocation("minecraft", "block/quartz_block_side"));
    public static final VerticalBlockType SMOOTH_QUARTZ = createBlockType("smooth_quartz", "Smooth Quartz", () -> Blocks.SMOOTH_QUARTZ_SLAB, () -> Blocks.SMOOTH_QUARTZ_STAIRS, () -> Blocks.SMOOTH_QUARTZ, true, new ResourceLocation("minecraft", "block/quartz_block_bottom"));

    //ADG types
    public static final VerticalBlockType ANDESITE = createBlockType("andesite", "Andesite", () -> Blocks.ANDESITE_SLAB, () -> Blocks.ANDESITE_STAIRS, () -> Blocks.ANDESITE, true, new ResourceLocation("minecraft", "block/andesite"));
    public static final VerticalBlockType POLISHED_ANDESITE = createBlockType("polished_andesite", "Polished Andesite", () -> Blocks.POLISHED_ANDESITE_SLAB, () -> Blocks.POLISHED_ANDESITE_STAIRS, () -> Blocks.POLISHED_ANDESITE, true, new ResourceLocation("minecraft", "block/polished_andesite"));
    public static final VerticalBlockType DIORITE = createBlockType("diorite", "Diorite", () -> Blocks.DIORITE_SLAB, () -> Blocks.DIORITE_STAIRS, () -> Blocks.DIORITE, true, new ResourceLocation("minecraft", "block/diorite"));
    public static final VerticalBlockType POLISHED_DIORITE = createBlockType("polished_diorite", "Polished Diorite", () -> Blocks.POLISHED_DIORITE_SLAB, () -> Blocks.POLISHED_DIORITE_STAIRS, () -> Blocks.POLISHED_DIORITE, true, new ResourceLocation("minecraft", "block/polished_diorite"));
    public static final VerticalBlockType GRANITE = createBlockType("granite", "Granite", () -> Blocks.GRANITE_SLAB, () -> Blocks.GRANITE_STAIRS, () -> Blocks.GRANITE, true, new ResourceLocation("minecraft", "block/granite"));
    public static final VerticalBlockType POLISHED_GRANITE = createBlockType("polished_granite", "Polished Granite", () -> Blocks.POLISHED_GRANITE_SLAB, () -> Blocks.POLISHED_GRANITE_STAIRS, () -> Blocks.POLISHED_GRANITE, true, new ResourceLocation("minecraft", "block/polished_granite"));

    //special block + brick types
    public static final VerticalBlockType NETHER_BRICK = createBlockType("nether_brick", "Nether Brick", () -> Blocks.NETHER_BRICK_SLAB, () -> Blocks.NETHER_BRICK_STAIRS, () -> Blocks.NETHER_BRICKS, true, new ResourceLocation("minecraft", "block/nether_bricks"));
    public static final VerticalBlockType RED_NETHER_BRICK = createBlockType("red_nether_brick", "Red Nether Brick", () -> Blocks.RED_NETHER_BRICK_SLAB, () -> Blocks.RED_NETHER_BRICK_STAIRS, () -> Blocks.RED_NETHER_BRICKS, true, new ResourceLocation("minecraft", "block/red_nether_bricks"));
    public static final VerticalBlockType BLACKSTONE = createBlockType("blackstone", "Blackstone", () -> Blocks.BLACKSTONE_SLAB, () -> Blocks.BLACKSTONE_STAIRS, () -> Blocks.BLACKSTONE, true, new ResourceLocation("minecraft", "block/blackstone"));
    public static final VerticalBlockType POLISHED_BLACKSTONE = createBlockType("polished_blackstone", "Polished Blackstone", () -> Blocks.POLISHED_BLACKSTONE_SLAB, () -> Blocks.POLISHED_BLACKSTONE_STAIRS, () -> Blocks.POLISHED_BLACKSTONE, true, new ResourceLocation("minecraft", "block/polished_blackstone"));
    public static final VerticalBlockType POLISHED_BLACKSTONE_BRICK = createBlockType("polished_blackstone_brick", "Polished Blackstone Brick", () -> Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, () -> Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, () -> Blocks.POLISHED_BLACKSTONE_BRICKS, true, new ResourceLocation("minecraft", "block/polished_blackstone_bricks"));

    public static final VerticalBlockType END_STONE_BRICK = createBlockType("end_stone_brick", "End Stone Brick", () -> Blocks.END_STONE_BRICK_SLAB, () -> Blocks.END_STONE_BRICK_STAIRS, () -> Blocks.END_STONE_BRICKS, true, new ResourceLocation("minecraft", "block/end_stone_bricks"));
    public static final VerticalBlockType PURPUR = createBlockType("purpur", "Purpur", () -> Blocks.PURPUR_SLAB, () -> Blocks.PURPUR_STAIRS, () -> Blocks.PURPUR_BLOCK, true, new ResourceLocation("minecraft", "block/purpur_block"));

    public static final VerticalBlockType PRISMARINE = createBlockType("prismarine", "Prismarine", () -> Blocks.PRISMARINE_SLAB, () -> Blocks.PRISMARINE_STAIRS, () -> Blocks.PRISMARINE, true, new ResourceLocation("minecraft", "block/prismarine"));
    public static final VerticalBlockType PRISMARINE_BRICK = createBlockType("prismarine_brick", "Prismarine Brick", () -> Blocks.PRISMARINE_BRICK_SLAB, () -> Blocks.PRISMARINE_BRICK_STAIRS, () -> Blocks.PRISMARINE_BRICKS, true, new ResourceLocation("minecraft", "block/prismarine_bricks"));
    public static final VerticalBlockType DARK_PRISMARINE = createBlockType("dark_prismarine", "Dark Prismarine", () -> Blocks.DARK_PRISMARINE_SLAB, () -> Blocks.DARK_PRISMARINE_STAIRS, () -> Blocks.DARK_PRISMARINE, true, new ResourceLocation("minecraft", "block/dark_prismarine"));

    //deepslate
    public static final VerticalBlockType COBBLED_DEEPSLATE = createBlockType("cobbled_deepslate", "Cobbled Deepslate", () -> Blocks.COBBLED_DEEPSLATE_SLAB, () -> Blocks.COBBLED_DEEPSLATE_STAIRS, () -> Blocks.COBBLED_DEEPSLATE, true, new ResourceLocation("minecraft", "block/cobbled_deepslate"));
    public static final VerticalBlockType POLISHED_DEEPSLATE = createBlockType("polished_deepslate", "Polished Deepslate", () -> Blocks.POLISHED_DEEPSLATE_SLAB, () -> Blocks.POLISHED_DEEPSLATE_STAIRS, () -> Blocks.POLISHED_DEEPSLATE, true, new ResourceLocation("minecraft", "block/polished_deepslate"));
    public static final VerticalBlockType DEEPSLATE_BRICK = createBlockType("deepslate_brick", "Deepslate Brick", () -> Blocks.DEEPSLATE_BRICK_SLAB, () -> Blocks.DEEPSLATE_BRICK_STAIRS, () -> Blocks.DEEPSLATE_BRICKS, true, new ResourceLocation("minecraft", "block/deepslate_bricks"));
    public static final VerticalBlockType DEEPSLATE_TILE = createBlockType("deepslate_tile", "Deepslate Tile", () -> Blocks.DEEPSLATE_TILE_SLAB, () -> Blocks.DEEPSLATE_TILE_STAIRS, () -> Blocks.DEEPSLATE_TILES, true, new ResourceLocation("minecraft", "block/deepslate_tiles"));

    //additional blocks: stone edition
    //quartz types
    public static final VerticalBlockType MARBLE = createBlockTypeForOtherMod("abstoneedition", "marble", "Marble", "marble_slab", "marble_stairs", "marble", true, new ResourceLocation("abstoneedition", "marble"));
    public static final VerticalBlockType MARBLE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "marble_bricks", "Marble Brick", "marble_bricks_slab", "marble_bricks_stairs", "marble_bricks", true, new ResourceLocation("abstoneedition", "marble_bricks"));
    public static final VerticalBlockType SMOOTH_MARBLE = createBlockTypeForOtherMod("abstoneedition", "smooth_marble", "Smooth Marble", "smooth_marble_slab", "smooth_marble_stairs", "smooth_marble", true, new ResourceLocation("abstoneedition", "smooth_marble"));
    public static final VerticalBlockType BLACK_MARBLE = createBlockTypeForOtherMod("abstoneedition", "black_marble", "Black Marble", "black_marble_slab", "black_marble_stairs", "black_marble", true, new ResourceLocation("abstoneedition", "black_marble"));
    public static final VerticalBlockType BLACK_MARBLE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "black_marble_bricks", "Black Marble Brick", "black_marble_bricks_slab", "black_marble_bricks_stairs", "black_marble_bricks", true, new ResourceLocation("abstoneedition", "black_marble_bricks"));
    public static final VerticalBlockType SMOOTH_BLACK_MARBLE = createBlockTypeForOtherMod("abstoneedition", "smooth_black_marble", "Smooth Black Marble", "smooth_black_marble_slab", "smooth_black_marble_stairs", "smooth_black_marble", true, new ResourceLocation("abstoneedition", "smooth_black_marble"));

    //other stones
    public static final VerticalBlockType BLOODSTONE = createBlockTypeForOtherMod("abstoneedition", "bloodstone", "Bloodstone", "bloodstone_slab", "bloodstone_stairs", "bloodstone", true, new ResourceLocation("abstoneedition", "bloodstone"));
    public static final VerticalBlockType BLOODSTONE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "bloodstone_bricks", "Bloodstone Brick", "bloodstone_bricks_slab", "bloodstone_bricks_stairs", "bloodstone_bricks", true, new ResourceLocation("abstoneedition", "bloodstone_bricks"));
    public static final VerticalBlockType SMOOTH_BLOODSTONE = createBlockTypeForOtherMod("abstoneedition", "smooth_bloodstone", "Smooth Bloodstone", "smooth_bloodstone_slab", "smooth_bloodstone_stairs", "smooth_bloodstone", true, new ResourceLocation("abstoneedition", "smooth_bloodstone"));
    public static final VerticalBlockType LIMESTONE = createBlockTypeForOtherMod("abstoneedition", "limestone", "Limestone", "limestone_slab", "limestone_stairs", "limestone", true, new ResourceLocation("abstoneedition", "limestone"));
    public static final VerticalBlockType LIMESTONE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "limestone_bricks", "Limestone Brick", "limestone_bricks_slab", "limestone_bricks_stairs", "limestone_bricks", true, new ResourceLocation("abstoneedition", "limestone_bricks"));
    public static final VerticalBlockType SMOOTH_LIMESTONE = createBlockTypeForOtherMod("abstoneedition", "smooth_limestone", "Smooth Limestone", "smooth_limestone_slab", "smooth_limestone_stairs", "smooth_limestone", true, new ResourceLocation("abstoneedition", "smooth_limestone"));
    public static final VerticalBlockType VOLCANIC_STONE = createBlockTypeForOtherMod("abstoneedition", "volcanic_stone", "Volcanic Stone", "volcanic_stone_slab", "volcanic_stone_stairs", "volcanic_stone", true, new ResourceLocation("abstoneedition", "volcanic_stone"));
    public static final VerticalBlockType VOLCANIC_STONE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "volcanic_stone_bricks", "Volcanic Stone Brick", "volcanic_stone_bricks_slab", "volcanic_stone_bricks_stairs", "volcanic_stone_bricks", true, new ResourceLocation("abstoneedition", "volcanic_stone_bricks"));
    public static final VerticalBlockType ASPHALT = createBlockTypeForOtherMod("abstoneedition", "asphalt", "Asphalt", "asphalt_slab", "asphalt_stairs", "asphalt", true, new ResourceLocation("abstoneedition", "asphalt"));

    //ADG+ Bricks
    public static final VerticalBlockType ANDESITE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "andesite_bricks", "Andesite Brick", "andesite_bricks_slab", "andesite_bricks_stairs", "andesite_bricks", true, new ResourceLocation("abstoneedition", "andesite_bricks"));
    public static final VerticalBlockType DIORITE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "diorite_bricks", "Diorite Brick", "diorite_bricks_slab", "diorite_bricks_stairs", "diorite_bricks", true, new ResourceLocation("abstoneedition", "diorite_bricks"));
    public static final VerticalBlockType GRANITE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "granite_bricks", "Granite Brick", "granite_bricks_slab", "granite_bricks_stairs", "granite_bricks", true, new ResourceLocation("abstoneedition", "granite_bricks"));
    public static final VerticalBlockType BROWN_BRICKS = createBlockTypeForOtherMod("abstoneedition", "brown_bricks", "Brown Brick", "brown_bricks_slab", "brown_bricks_stairs", "brown_bricks", true, new ResourceLocation("abstoneedition", "brown_bricks"));
    public static final VerticalBlockType GRAY_BRICKS = createBlockTypeForOtherMod("abstoneedition", "gray_bricks", "Gray Brick", "gray_bricks_slab", "gray_bricks_stairs", "gray_bricks", true, new ResourceLocation("abstoneedition", "gray_bricks"));

    //Minecraft Variations
    public static final VerticalBlockType GLOWSTONE = createBlockTypeForOtherMod("abstoneedition", "glowstone", "Glowstone", "glowstone_slab", "glowstone_stairs", "minecraft:glowstone", true, new ResourceLocation("minecraft", "block/glowstone"));
    public static final VerticalBlockType GLOWSTONE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "glowstone_bricks", "Glowstone Brick", "glowstone_bricks_slab", "glowstone_bricks_stairs", "glowstone_bricks", true, new ResourceLocation("abstoneedition", "glowstone_bricks"));
    public static final VerticalBlockType SMOOTH_GLOWSTONE = createBlockTypeForOtherMod("abstoneedition", "smooth_glowstone", "Smooth Glowstone", "smooth_glowstone_slab", "smooth_glowstone_stairs", "smooth_glowstone", true, new ResourceLocation("abstoneedition", "smooth_glowstone"));
    public static final VerticalBlockType STONE_BRICK_BLOCK = createBlockTypeForOtherMod("abstoneedition", "stone_brick_block", "Stone Brick Block", "stone_brick_block_slab", "stone_brick_block_stairs", "stone_brick_block", true, new ResourceLocation("abstoneedition", "stone_brick_block"));
    public static final VerticalBlockType SMOOTH_SMOOTH_STONE = createBlockTypeForOtherMod("abstoneedition", "smooth_smooth_stone", "Very Smooth Stone", "smooth_smooth_stone_slab", "smooth_smooth_stone_stairs", "smooth_smooth_stone", true, new ResourceLocation("abstoneedition", "smooth_smooth_stone"));
    public static final VerticalBlockType SMOOTH_STONE_BRICKS = createBlockTypeForOtherMod("abstoneedition", "smooth_stone_bricks", "Smooth Stone Brick", "smooth_stone_bricks_slab", "smooth_stone_bricks_stairs", "smooth_stone_bricks", true, new ResourceLocation("abstoneedition", "smooth_stone_bricks"));
    public static final VerticalBlockType CHARRED = createBlockTypeForOtherMod("abstoneedition", "charred", "Charred", "charred_slab", "charred_stairs", "charred_planks", true, new ResourceLocation("abstoneedition", "charred_planks"));

    //Stone Path Types
    public static final VerticalBlockType BROWN_STONE_PATH_STRAIGHT = createBlockTypeForOtherMod("abstoneedition", "brown_stone_path_straight", "Brown Straight Stone Path", "brown_stone_path_straight_slab", "brown_stone_path_straight_stairs", "brown_stone_path_straight", true, new ResourceLocation("abstoneedition", "brown_stone_path_straight"));
    public static final VerticalBlockType BROWN_STONE_PATH_CURVED = createBlockTypeForOtherMod("abstoneedition", "brown_stone_path_curved", "Brown Curved Stone Path", "brown_stone_path_curved_slab", "brown_stone_path_curved_stairs", "brown_stone_path_curved", true, new ResourceLocation("abstoneedition", "brown_stone_path_curved"));
    public static final VerticalBlockType BROWN_STONE_PATTERN = createBlockTypeForOtherMod("abstoneedition", "brown_stone_pattern", "Brown Stone Pattern", "brown_stone_pattern_slab", "brown_stone_pattern_stairs", "brown_stone_pattern", true, new ResourceLocation("abstoneedition", "brown_stone_pattern"));
    public static final VerticalBlockType BROWN_STONE_TILES = createBlockTypeForOtherMod("abstoneedition", "brown_stone_tiles", "Brown Stone Tile", "brown_stone_tiles_slab", "brown_stone_tiles_stairs", "brown_stone_tiles", true, new ResourceLocation("abstoneedition", "brown_stone_tiles"));
    public static final VerticalBlockType OLD_STONE_PATH_STRAIGHT = createBlockTypeForOtherMod("abstoneedition", "old_stone_path_straight", "Old Straight Stone Path", "old_stone_path_straight_slab", "old_stone_path_straight_stairs", "old_stone_path_straight", true, new ResourceLocation("abstoneedition", "old_stone_path_straight"));
    public static final VerticalBlockType OLD_STONE_PATH_CURVED = createBlockTypeForOtherMod("abstoneedition", "old_stone_path_curved", "Old Curved Stone Path", "old_stone_path_curved_slab", "old_stone_path_curved_stairs", "old_stone_path_curved", true, new ResourceLocation("abstoneedition", "old_stone_path_curved"));
    public static final VerticalBlockType OLD_STONE_PATTERN = createBlockTypeForOtherMod("abstoneedition", "old_stone_pattern", "Old Stone Pattern", "old_stone_pattern_slab", "old_stone_pattern_stairs", "old_stone_pattern", true, new ResourceLocation("abstoneedition", "old_stone_pattern"));
    public static final VerticalBlockType OLD_STONE_TILES = createBlockTypeForOtherMod("abstoneedition", "old_stone_tiles", "Old Stone Tile", "old_stone_tiles_slab", "old_stone_tiles_stairs", "old_stone_tiles", true, new ResourceLocation("abstoneedition", "old_stone_tiles"));
    public static final VerticalBlockType STONE_PATH_STRAIGHT = createBlockTypeForOtherMod("abstoneedition", "stone_path_straight", "Straight Stone Path", "stone_path_straight_slab", "stone_path_straight_stairs", "stone_path_straight", true, new ResourceLocation("abstoneedition", "stone_path_straight"));
    public static final VerticalBlockType STONE_PATH_CURVED = createBlockTypeForOtherMod("abstoneedition", "stone_path_curved", "Curved Stone Path", "stone_path_curved_slab", "stone_path_curved_stairs", "stone_path_curved", true, new ResourceLocation("abstoneedition", "stone_path_curved"));
    public static final VerticalBlockType STONE_PATTERN = createBlockTypeForOtherMod("abstoneedition", "stone_pattern", "Stone Pattern", "stone_pattern_slab", "stone_pattern_stairs", "stone_pattern", true, new ResourceLocation("abstoneedition", "stone_pattern"));
    public static final VerticalBlockType STONE_TILES = createBlockTypeForOtherMod("abstoneedition", "stone_tiles", "Stone Tile", "stone_tiles_slab", "stone_tiles_stairs", "stone_tiles", true, new ResourceLocation("abstoneedition", "stone_tiles"));

    private static VerticalBlockType createBlockType(String registryName, String translation, Supplier<Block> parentSlabBlock, Supplier<Block> parentStairBlock, Supplier<Block> recipeBlock, boolean hasStoneCutterRecipe, ResourceLocation texture, String... dependentMods){
        ResourceLocation resourceLocation = new ResourceLocation("abverticaledition", registryName);
        if(ALL.containsKey(resourceLocation))
            throw new RuntimeException("Tried to register two block types with registry name '" + registryName + "'!");

        VerticalBlockType type = new VerticalBlockType(resourceLocation, translation, parentSlabBlock, parentStairBlock, recipeBlock, hasStoneCutterRecipe, texture, dependentMods);
        ALL.put(resourceLocation, type);
        ALL_ORDERED.add(type);
        return type;
    }

    private static VerticalBlockType createBlockTypeForOtherMod(String modid, String registryName, String translation, String parentSlabBlock, String parentStairBlock, String recipeBlock, boolean hasStoneCutterRecipe, ResourceLocation texture){
        // Check if the other mod is loaded
        if(!ModList.get().isLoaded(modid))
            return null;

        ResourceLocation parentSlabBlockLocation = parentSlabBlock.contains(":") ? new ResourceLocation(parentSlabBlock) : new ResourceLocation(modid, parentSlabBlock);
        ResourceLocation parentStairBlockLocation = parentStairBlock.contains(":") ? new ResourceLocation(parentStairBlock) : new ResourceLocation(modid, parentStairBlock);
        ResourceLocation recipeBlockLocation = recipeBlock.contains(":") ? new ResourceLocation(recipeBlock) : new ResourceLocation(modid, recipeBlock);
        return createBlockType(registryName, translation, () -> getBlockFromOtherMod(parentSlabBlockLocation), () -> getBlockFromOtherMod(parentStairBlockLocation), () -> getBlockFromOtherMod(recipeBlockLocation), hasStoneCutterRecipe, texture, modid);
    }

    private static Block getBlockFromOtherMod(ResourceLocation location){
        Block block = ForgeRegistries.BLOCKS.getValue(location);
        if(block == null || block == Blocks.AIR)
            throw new RuntimeException("Could not find any block registered under '" + location + "'!");
        return block;
    }

    public final ResourceLocation registryName;
    public final ResourceLocation slabRegistryName;
    public final ResourceLocation stairRegistryName;
    public final String slabTranslation;
    public final String stairTranslation;
    public final Supplier<Block> parentSlabBlock;
    public final Supplier<Block> parentStairBlock;
    public final Supplier<Block> recipeBlock;
    public final boolean hasStoneCutterRecipe;
    public final ResourceLocation texture;
    /**
     * Mods which need to be present for the block type to be enabled.
     */
    public final List<String> dependentMods;

    public VerticalBlockType(ResourceLocation registryName, String translation, Supplier<Block> parentSlabBlock, Supplier<Block> parentStairBlock, Supplier<Block> recipeBlock, boolean hasStoneCutterRecipe, ResourceLocation texture, String... dependentMods){
        this.registryName = registryName;
        this.slabRegistryName = new ResourceLocation(registryName.getNamespace(), registryName.getPath() + "_vertical_slab");
        this.stairRegistryName = new ResourceLocation(registryName.getNamespace(), registryName.getPath() + "_vertical_stair");
        this.slabTranslation = "Vertical " + translation + " Slab";
        this.stairTranslation = "Vertical " + translation + " Stair";
        this.parentSlabBlock = parentSlabBlock;
        this.parentStairBlock = parentStairBlock;
        this.recipeBlock = recipeBlock;
        this.hasStoneCutterRecipe = hasStoneCutterRecipe;
        this.texture = texture;
        this.dependentMods = List.of(dependentMods);
    }

    public Block getSlab(){
        return ForgeRegistries.BLOCKS.getValue(this.slabRegistryName);
    }

    public Block getStair(){
        return ForgeRegistries.BLOCKS.getValue(this.stairRegistryName);
    }
}
