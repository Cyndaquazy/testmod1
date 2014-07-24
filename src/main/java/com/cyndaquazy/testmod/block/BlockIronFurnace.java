package com.cyndaquazy.testmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.cyndaquazy.testmod.ref.Names;
import com.cyndaquazy.testmod.tileentity.TileEntityIronFurnace;
import com.cyndaquazy.testmod.util.LogHelper;


public class BlockIronFurnace extends BlockTestMod implements ITileEntityProvider
{
  public BlockIronFurnace()
  {
    super();
    this.setBlockName(Names.Blocks.IRON_FURNACE);
    this.setHardness(4.0F);
    this.setHarvestLevel("pickaxe", 1);
  }
  
  @Override
  public TileEntity createNewTileEntity(World w, int i)
  {
    return new TileEntityIronFurnace();
  }
  
  @Override
  public boolean hasTileEntity(int metadata)
  {
    return true;
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
