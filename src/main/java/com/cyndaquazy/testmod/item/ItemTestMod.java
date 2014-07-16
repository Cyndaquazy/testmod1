package com.cyndaquazy.testmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.cyndaquazy.testmod.ref.Textures;
import com.cyndaquazy.testmod.util.TestModTab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTestMod extends Item
{
  public ItemTestMod()
  {
    super();
    this.setCreativeTab(TestModTab.getInstance());
  }
  
  /**
   * Get the internal ("unlocalized") name of this item.
   * 
   * The name returned is of the format:
   * 
   * item.testmod:[item].name
   * 
   * @return the name of this item with no regard to localization.
   */
  @Override
  public String getUnlocalizedName()
  {
    return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }
  
  /**
   * Get the internal ("unlocalized") name of this item when it is in a multi-item stack. Because we will not differentiate between
   * one or multiple items, this simply calls {@link #getUnlocalizedName()}.
   * 
   * The name returned is of the format:
   * 
   * item.testmod:[item].name
   * 
   * @return the name of this item with no regard to localization.
   */
  @Override
  public String getUnlocalizedName(ItemStack itemStack)
  {
    return this.getUnlocalizedName();
  }
  
  /**
   * Return the unwrapped, unlocalized name of this item.
   * 
   * By unwrapping the name, we strip any unnecessary prefixes from the name, such as "item.", etc.
   * 
   * @param unlocalizedName The prefixed name to unwrap.
   * @return The unwrapped item name.
   */
  protected String getUnwrappedUnlocalizedName(String unlocalizedName)
  {
    return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
  }
  
  /**
   * Register the texture for this item. In other words, tell Minecraft to load up the icon texture for this item.
   * 
   * @param iconRegister the IIconRegister object used by Minecraft to register icons.
   */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iconRegister)
  {
    itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
  }
  
}
