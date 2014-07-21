package com.cyndaquazy.testmod.client.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;

/**
 * This GuiFactory is used to generate the Configuration GUI used by my mod.
 * 
 * As of July 2, the only useful method is {@link #mainConfigGuiClass()}.
 * @author Cyndaquazy
 *
 */
public class GuiFactory implements IModGuiFactory
{

  /**
   * Initialize method. Usage unknown as of July 2.
   * 
   * @param minecraftInstance the currently running instance of Minecraft.
   */
  @Override
  public void initialize(Minecraft minecraftInstance)
  {

    
  }

  /**
   * Provides the class which creates the Configuration GUI.
   * 
   * For my mod, this is {@link ModGuiConfig}.
   */
  @Override
  public Class<? extends GuiScreen> mainConfigGuiClass()
  {
    return ModGuiConfig.class;
  }

  /**
   * Method to provide a set of Runtime GUI Categories. Usage unknown as of July 2.
   */
  @Override
  public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
  {
    return null;
  }

  /**
   * Method to get the RuntomeOptionGuiHandler for the provided RuntimeOptionCategoryElement. Usage unknown as of July 2.
   */
  @Override
  public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
  {
    return null;
  }

}
