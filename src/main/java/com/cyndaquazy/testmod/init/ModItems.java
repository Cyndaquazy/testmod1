package com.cyndaquazy.testmod.init;

import com.cyndaquazy.testmod.item.*;
import com.cyndaquazy.testmod.ref.Names;

import com.cyndaquazy.testmod.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
  public static final ItemTestMod goldPlating = new ItemGoldPlating();
  public static final ItemTestMod ironPlating = new ItemIronPlating();
  public static final ItemTestMod coalChunk = new ItemCoalChunk();
  public static final ItemTestMod coalBits = new ItemCoalBits();
  
  public static void registerItems()
  {
    LogHelper.info("Adding gold plating...");
    GameRegistry.registerItem(goldPlating, Names.Items.GOLD_PLATING);
    LogHelper.info("Adding iron plating...");
    GameRegistry.registerItem(ironPlating, Names.Items.IRON_PLATING);
    LogHelper.info("Adding coal chunk...");
    GameRegistry.registerItem(coalChunk, Names.Items.COAL_CHUNK);
    LogHelper.info("Adding coal bits...");
    GameRegistry.registerItem(coalBits, Names.Items.COAL_BITS);
  }
}
