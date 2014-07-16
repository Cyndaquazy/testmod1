package com.cyndaquazy.testmod.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.cyndaquazy.testmod.init.ModItems;

public class TestModTab extends CreativeTabs
{
  private static TestModTab instance = new TestModTab();
  
  private TestModTab()
  {
    super("testModTab");
  }
  
  @Override
  public Item getTabIconItem()
  {
    return ModItems.ironPlating;
  }
  
  public static TestModTab getInstance()
  {
    return instance;
  }
}
