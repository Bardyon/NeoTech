package com.teambrmodding.neotech.managers;

import com.teambrmodding.neotech.tools.UpgradeItemManager;
import com.teambrmodding.neotech.tools.modifier.ModifierAOE;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * This file was created for NeoTech
 *
 * NeoTech is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since August 14, 2015
 */
public class CraftingRecipeManager {

    public static void preInit() {
        //Electric Furnace
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.electricFurnace()),
                "ACA",
                "BDB",
                "ACA", 'A', "ingotCopper", 'B', Items.REDSTONE, 'C', Blocks.FURNACE, 'D', Blocks.REDSTONE_BLOCK));

        //Electric Crusher
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.electricCrusher()),
                "ABA",
                "DCD",
                "ABA", 'A', "ingotTin", 'B', Items.FLINT, 'C', Blocks.PISTON, 'D', Items.REDSTONE));

        //Electric Solidifier
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.electricSolidifier()),
                "SRS",
                "BTB",
                "SRS", 'T', BlockManager.ironTank(), 'S', "ingotLead", 'B', Items.SNOWBALL, 'R', Items.REDSTONE));

        //Electric Crucible
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.electricCrucible()),
                "SRS",
                "BCB",
                "SRS", 'C', Items.CAULDRON, 'S', "ingotCopper", 'B', Items.BUCKET, 'R', Items.REDSTONE));

        //Electric Alloyer
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.electricAlloyer()),
                "BLB",
                "TRT",
                "BLB", 'B', "ingotBronze", 'L', "ingotSilver", 'T', BlockManager.ironTank(), 'R', Blocks.REDSTONE_BLOCK));

        //Electric Centrifuge
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.electricCentrifuge()),
                "SRS",
                "TIT",
                "SRS", 'S', "ingotSteel", 'R', Blocks.REDSTONE_BLOCK, 'T', BlockManager.ironTank(), 'I', Blocks.IRON_BLOCK));

        //Furnace Generator
        GameRegistry.addRecipe(new ItemStack(BlockManager.furnaceGenerator()),
                "ABA",
                "CDC",
                "ABA", 'A', Items.IRON_INGOT, 'B', Items.REDSTONE, 'C', Blocks.FURNACE, 'D', Blocks.CHEST);

        //Fluid Generator
        GameRegistry.addRecipe(new ItemStack(BlockManager.fluidGenerator()),
                "ABA",
                "CDC",
                "ABA", 'A', Items.GOLD_INGOT, 'B', Items.GLOWSTONE_DUST, 'C', BlockManager.furnaceGenerator(), 'D', BlockManager.ironTank());

        //Solar Panels
        GameRegistry.addRecipe(new ItemStack(BlockManager.solarPanelT1()),
                "CCC",
                "RRR",
                "ABA", 'A', Items.IRON_INGOT, 'B', ItemManager.basicRFBattery(), 'C', Blocks.GLASS, 'R', Items.REDSTONE);
        GameRegistry.addRecipe(new ItemStack(BlockManager.solarPanelT2()),
                "CCC",
                "BSB",
                "gsg", 'C', Blocks.GLASS, 'B', Items.BLAZE_POWDER, 'S', BlockManager.solarPanelT1(), 's', ItemManager.advancedRFBattery(), 'g', Items.GOLD_INGOT);
        GameRegistry.addRecipe(new ItemStack(BlockManager.solarPanelT3()),
                "CCC",
                "PSP",
                "DED", 'D', Items.DIAMOND, 'P', Items.ENDER_PEARL, 'C', Blocks.GLASS, 'S', BlockManager.solarPanelT2(), 'E', ItemManager.eliteRFBattery());

        //Thermal Binder
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.thermalBinder()),
                "ACA",
                "BDB",
                "ACA", 'A', "ingotGold", 'B', Items.SLIME_BALL, 'C', Blocks.FURNACE, 'D', Blocks.REDSTONE_BLOCK));

        //Electric Logger
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.treeFarm()),
                "ACA",
                "BDB",
                "ACA", 'A', "ingotBronze", 'B', Items.IRON_AXE, 'C', Items.SHEARS, 'D', Blocks.REDSTONE_BLOCK));

        //RF Storage
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(BlockManager.basicRFStorage())),
                "ALA",
                "DCD",
                "ALA", 'A', "ingotIron", 'L', "ingotLead", 'C', Blocks.REDSTONE_BLOCK, 'D', new ItemStack(ItemManager.basicRFBattery(), 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(BlockManager.advancedRFStorage())),
                "ALA",
                "DCD",
                "ALA", 'A', "ingotGold", 'L', "ingotLead", 'C', BlockManager.basicRFStorage(), 'D', new ItemStack(ItemManager.advancedRFBattery(), 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(BlockManager.eliteRFStorage())),
                "ALA",
                "DCD",
                "ALA", 'A', "ingotSteel", 'L', "ingotLead", 'C', BlockManager.advancedRFStorage(), 'D', new ItemStack(ItemManager.eliteRFBattery(), 1, OreDictionary.WILDCARD_VALUE)));

        //Tanks
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.ironTank()),
                "ABA",
                "BCB",
                "ABA", 'A', Items.IRON_INGOT, 'B', "blockGlass", 'C', Items.BUCKET));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.goldTank()),
                "ABA",
                "BCB",
                "ABA", 'A', Items.GOLD_INGOT, 'B', "blockGlass", 'C', BlockManager.ironTank()));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.getItemFromBlock(BlockManager.diamondTank()),
                "ABA",
                "BCB",
                "ABA", 'A', Items.DIAMOND, 'B', "blockGlass", 'C', BlockManager.goldTank()));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.getItemFromBlock(BlockManager.voidTank()),
                "ABA",
                "BCB",
                "ABA", 'A', Blocks.OBSIDIAN, 'B', "blockGlass", 'C', Items.ENDER_PEARL));

        //Miniature Sun
        GameRegistry.addRecipe(new ItemStack(BlockManager.blockMiniatureSun()),
                "ABA",
                "BCB",
                "ABA", 'A', Items.GOLD_INGOT, 'B', Items.GLOWSTONE_DUST, 'C', Items.NETHER_STAR);

        GameRegistry.addShapelessRecipe(new ItemStack(BlockManager.blockMiniatureStar()),
                Items.GLOWSTONE_DUST, Item.getItemFromBlock(Blocks.TORCH));

        for(EnumDyeColor color : EnumDyeColor.values()) {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.getItemFromBlock(BlockManager.blockMiniatureStar()), 1, color.ordinal()),
                    "blockMiniatureStar", new ItemStack(Items.DYE, 1, color.getDyeDamage())));
        }

        //Chunk Loader
        GameRegistry.addRecipe(new ItemStack(BlockManager.chunkLoader()),
                "GIG",
                "IRI",
                "GIG", 'G', Blocks.GOLD_BLOCK, 'I', Blocks.IRON_BLOCK, 'R', Blocks.REDSTONE_BLOCK);

        //Wrench
        GameRegistry.addRecipe(new ItemStack(ItemManager.wrench()),
                " I ",
                " II",
                "I  ", 'I', Items.IRON_INGOT);

        //Mob Gun
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.mobGun(), 1),
                "   ",
                "III",
                "  B", 'I', "ingotSteel", 'B', "rfBattery"));

        //Mob Net
        GameRegistry.addRecipe(new ItemStack(ItemManager.mobNet(), 1),
                "S S",
                " E ",
                "S S", 'E', Items.ENDER_PEARL, 'S', Items.STRING);

        //Mob Stand
        GameRegistry.addRecipe(new ItemStack(BlockManager.mobStand(), 4),
                "QQQ",
                "Q Q",
                "Q Q", 'Q', Items.QUARTZ);

        //Add in Iron nugget - ingot
        MetalManager.Metal metal = MetalManager.getMetal("iron").get();
        GameRegistry.addShapelessRecipe(new ItemStack(metal.nugget().get(), 9), Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(Items.IRON_INGOT, 1),
                "III",
                "III",
                "III", 'I', metal.nugget().get());

        //RF Batteries
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.basicRFBattery()),
                "S S",
                "ILI",
                "ILI", 'S', "ingotSilver", 'L', "ingotLead", 'I', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.advancedRFBattery()),
                "S S",
                "GBG",
                "GLG", 'S', "ingotSilver", 'L', "ingotLead", 'G', Items.GOLD_INGOT, 'B', new ItemStack(ItemManager.basicRFBattery(), 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.eliteRFBattery()),
                "S S",
                "DBD",
                "DLD", 'S', "ingotSilver", 'L', "ingotLead", 'D', Items.DIAMOND, 'B', new ItemStack(ItemManager.advancedRFBattery(), 1, OreDictionary.WILDCARD_VALUE)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MetalManager.getMetal("bronze").get().dust().get(), 4),
                "dustCopper", "dustCopper", "dustCopper", "dustTin"));

        //RF Tools
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.electricPickaxe()),
                "CCC",
                " S ",
                " B ", 'C', "ingotSteel", 'S', "stickWood", 'B', "rfBattery"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.electricSword()),
                " B ",
                " B ",
                " b ", 'B', "ingotSteel", 'b', "rfBattery"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.electricArmorHelmet()),
                "SSS",
                "S S",
                " B ", 'S', "ingotSteel", 'B', "rfBattery"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.electricArmorChestplate()),
                "S S",
                "SSS",
                "SBS", 'S', "ingotSteel", 'B', "rfBattery"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.electricArmorLeggings()),
                "SSS",
                "S S",
                "SBS", 'S', "ingotSteel", 'B', "rfBattery"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.electricArmorBoots()),
                "   ",
                "S S",
                "SBS", 'S', "ingotSteel", 'B', "rfBattery"));

        // Phantom Glass
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.phantomGlass(), 8),
                "GGG",
                "GDG",
                "GGG", 'G', "blockGlass", 'D', Items.OAK_DOOR));

        // Void Glass
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.voidGlass(), 8),
                "GDG",
                "GGG",
                "GGG", 'G', "blockGlass", 'D', "dyeBlack"));

        // Rock Climbing Wall
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.rockWall(), 8),
                "GGG",
                "TGT",
                "GGG", 'G', Blocks.COBBLESTONE, 'T', "ingotTin"));

        //Smelting Recipes
        GameRegistry.addSmelting(MetalManager.getMetal("gold").get().dust().get(), new ItemStack(Items.GOLD_INGOT), 2.0F);
        GameRegistry.addSmelting(MetalManager.getMetal("iron").get().dust().get(), new ItemStack(Items.IRON_INGOT), 1.0F);

        // Upgrades
        GameRegistry.addRecipe(new ItemStack(ItemManager.processorSingleCore()),
                "RSR",
                "RIR",
                "RSR", 'R', Items.REDSTONE, 'S', Items.STRING, 'I', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(ItemManager.processorDualCore()),
                "RGR",
                "PgP",
                "RGR", 'R', Items.REDSTONE, 'G', Items.GLOWSTONE_DUST, 'P', ItemManager.processorSingleCore(), 'g', Items.GOLD_INGOT);
        GameRegistry.addRecipe(new ItemStack(ItemManager.processorQuadCore()),
                "RGR",
                "PgP",
                "RGR", 'R', Items.REDSTONE, 'G', Items.GOLD_INGOT, 'P', ItemManager.processorDualCore(), 'g', new ItemStack(Items.DYE, 1, 11));
        GameRegistry.addRecipe(new ItemStack(ItemManager.processorOctCore()),
                "RGR",
                "PgP",
                "RGR", 'R', Items.REDSTONE, 'G', Items.DIAMOND, 'P', ItemManager.processorQuadCore(), 'g', Items.DIAMOND);

        GameRegistry.addRecipe(new ItemStack(ItemManager.memoryDDR1()),
                "SSS",
                "RRR",
                "   ", 'S', Blocks.STONE_SLAB, 'R', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(ItemManager.memoryDDR2()),
                "SSS",
                "RRR",
                "M M", 'S', Blocks.STONE_SLAB, 'R', Items.REDSTONE, 'M', ItemManager.memoryDDR1());
        GameRegistry.addRecipe(new ItemStack(ItemManager.memoryDDR3()),
                "SSS",
                "RRR",
                "M M", 'S', Blocks.STONE_SLAB, 'R', Items.GOLD_INGOT, 'M', ItemManager.memoryDDR2());
        GameRegistry.addRecipe(new ItemStack(ItemManager.memoryDDR4()),
                "SSS",
                "RRR",
                "M M", 'S', Blocks.STONE_SLAB, 'R', Items.DIAMOND, 'M', ItemManager.memoryDDR3());

        //TODO: HDD

        GameRegistry.addRecipe(new ItemStack(ItemManager.psu250W()),
                "RRR",
                "R R",
                "RRR", 'R', Blocks.REDSTONE_BLOCK);
        GameRegistry.addRecipe(new ItemStack(ItemManager.psu500W()),
                "RRR",
                "RTR",
                "RRR", 'R', Blocks.REDSTONE_BLOCK, 'T', ItemManager.psu250W());
        GameRegistry.addRecipe(new ItemStack(ItemManager.psu750W()),
                "RRR",
                "RTR",
                "RRR", 'R', Blocks.REDSTONE_BLOCK, 'T', ItemManager.psu500W());
        GameRegistry.addRecipe(new ItemStack(ItemManager.psu960W()),
                "RRR",
                "RTR",
                "RRR", 'R', Blocks.REDSTONE_BLOCK, 'T', ItemManager.psu750W());

        if(Loader.isModLoaded("IC2"))
            GameRegistry.addRecipe(new ItemStack(ItemManager.transformer()),
                    "R  ",
                    "RSR",
                    "R  ", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STRING);

        GameRegistry.addShapelessRecipe(new ItemStack(ItemManager.expansion()), Items.PAPER, ItemManager.processorSingleCore());
        GameRegistry.addShapelessRecipe(new ItemStack(ItemManager.redstoneControl()), Items.REDSTONE, ItemManager.processorSingleCore());
        GameRegistry.addShapelessRecipe(new ItemStack(ItemManager.networkCard()), Items.ENDER_PEARL, ItemManager.processorSingleCore());

        UpgradeItemManager.registerRecipes();
    }
}
