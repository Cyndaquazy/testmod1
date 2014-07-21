package com.cyndaquazy.testmod.block;

import com.cyndaquazy.testmod.ref.Names;


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
