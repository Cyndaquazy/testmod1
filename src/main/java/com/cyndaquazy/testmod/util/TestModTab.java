package com.cyndaquazy.testmod.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.cyndaquazy.testmod.init.ModItems;

/**
 * The primary Creative Inventory tab for my mod.
 */
public class TestModTab extends CreativeTabs
{
  /**
   * Singleton instance.
   */
  private static TestModTab instance = new TestModTab();

  private TestModTab()
  {
    super("testModTab");
  }

  /**
   * Get the singleton instance.
   * @return This class' singleton instance.
   */
  public static TestModTab getInstance()
  {
    return instance;
  }

  /**
   * Get the icon of the item to represent this tab.
   * @return The icon as described.
   */
  @Override
  public Item getTabIconItem()
  {
    return ModItems.ironPlating;
  }

  /**
   * Hard-codes the name of the tab, instead of relying on localization.
   * @return The name of this creative tab.
   */
  @Override
  public String getTranslatedTabLabel()
  {
    return "Cyndaquazy's Test Mod";
  }
}
