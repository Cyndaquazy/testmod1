package com.cyndaquazy.testmod.init;

import com.cyndaquazy.testmod.item.*;
import com.cyndaquazy.testmod.ref.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
  public static final ItemTestMod goldPlating = new ItemGoldPlating();
  public static final ItemTestMod ironPlating = new ItemIronPlating();
  
  public static void registerItems()
  {
    GameRegistry.registerItem(goldPlating, Names.Items.GOLD_PLATING);
    GameRegistry.registerItem(ironPlating, Names.Items.IRON_PLATING);
  }
}
