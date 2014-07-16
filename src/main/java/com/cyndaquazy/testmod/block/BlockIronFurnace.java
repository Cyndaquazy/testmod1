package com.cyndaquazy.testmod.block;

import net.minecraft.client.renderer.texture.IIconRegister;

import com.cyndaquazy.testmod.ref.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockIronFurnace extends BlockTestMod
{
  public BlockIronFurnace()
  {
    super();
    this.setBlockName(Names.Blocks.IRON_FURNACE);
  }
  /*
  @Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister)
  {
    blockIcon = iconRegister.registerIcon("testmod:ironFurnace");
  }
  */
}
