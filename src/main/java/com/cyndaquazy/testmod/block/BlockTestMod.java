package com.cyndaquazy.testmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.cyndaquazy.testmod.ref.Textures;
import com.cyndaquazy.testmod.util.LogHelper;
import com.cyndaquazy.testmod.util.TestModTab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTestMod extends Block
{
  public BlockTestMod()
  {
    this(Material.iron);
  }

  public BlockTestMod(Material material)
  {
    super(material);

    this.setCreativeTab(TestModTab.getInstance());
  }

  @Override
  public String getUnlocalizedName()
  {
    return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }

  protected String getUnwrappedUnlocalizedName(String unlocalizedName)
  {
    return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister)
  {
    blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
  }
}
