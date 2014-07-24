package com.cyndaquazy.testmod.init;

import com.cyndaquazy.testmod.ref.GlobalConstants;
import com.cyndaquazy.testmod.ref.Names;
import com.cyndaquazy.testmod.tileentity.TileEntityIronFurnace;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModEntities
{
  public static void initEntities()
  {
    GameRegistry.registerTileEntity(TileEntityIronFurnace.class, String.format("%s.%s", GlobalConstants.MOD_ID, Names.Entities.IRON_FURNACE));
  }
}
