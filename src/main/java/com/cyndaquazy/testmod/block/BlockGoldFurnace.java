package com.cyndaquazy.testmod.block;

import com.cyndaquazy.testmod.ref.Names;


public class BlockGoldFurnace extends BlockTestMod
{
  public BlockGoldFurnace()
  {
    super();
    this.setBlockName(Names.Blocks.GOLD_FURNACE);
  }
  
  /*
  @Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister)
  {
    blockIcon = iconRegister.registerIcon("testmod:goldFurnace");
  }
  */
}
