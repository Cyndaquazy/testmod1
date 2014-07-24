package com.cyndaquazy.testmod.util;

import com.cyndaquazy.testmod.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

/**
 * ModFuelHandler allows for Forge and Minecraft to determine what smelt time TestMod's items provide.
 * 
 * The IFuelHandler interface replaces the old GameRegistry.addFuel() mechanic.
 * 
 * @author Myndert
 *
 */
public class ModFuelHandler implements IFuelHandler
{

  @Override
  public int getBurnTime(ItemStack fuel)
  {
    // If, for some reason, the ItemStack given, or its Item, is null, return 0.
    if (fuel == null) { return 0; }

    Item fuelItem = fuel.getItem();

    if (fuelItem == null) { return 0; }
    
    // In order to maintain balance, the burn times of coal bits and chunks
    // scale with the burn time of coal, which is 1600.
    if (fuelItem == ModItems.coalBits) { return 100; }
    else if (fuelItem == ModItems.coalChunk) { return 400; }
    
    return 0;
  }

}
