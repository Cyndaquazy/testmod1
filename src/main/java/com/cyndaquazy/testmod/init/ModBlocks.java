package com.cyndaquazy.testmod.init;

import com.cyndaquazy.testmod.block.BlockGoldFurnace;
import com.cyndaquazy.testmod.block.BlockIronFurnace;
import com.cyndaquazy.testmod.block.BlockTestMod;
import com.cyndaquazy.testmod.ref.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
  public static final BlockTestMod ironFurnace = new BlockIronFurnace();
  public static final BlockTestMod goldFurnace = new BlockGoldFurnace();
  
  public static void registerBlocks()
  {
    GameRegistry.registerBlock(ironFurnace, Names.Blocks.IRON_FURNACE);
    GameRegistry.registerBlock(goldFurnace, Names.Blocks.GOLD_FURNACE);
  }
}
