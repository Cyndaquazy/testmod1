package com.cyndaquazy.testmod.block;

import com.cyndaquazy.testmod.ref.Names;


public class BlockGoldFurnace extends BlockTestMod
{
  public BlockGoldFurnace()
  {
    super();
    this.setBlockName(Names.Blocks.GOLD_FURNACE);
    this.setHardness(4.0F);
    this.setHarvestLevel("pickaxe", 1);
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
