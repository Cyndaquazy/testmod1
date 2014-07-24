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
    GameRegistry.registerItem(goldPlating, Names.Items.GOLD_PLATING);
    GameRegistry.registerItem(ironPlating, Names.Items.IRON_PLATING);
    GameRegistry.registerItem(coalChunk, Names.Items.COAL_CHUNK);
    GameRegistry.registerItem(coalBits, Names.Items.COAL_BITS);
  }
}
