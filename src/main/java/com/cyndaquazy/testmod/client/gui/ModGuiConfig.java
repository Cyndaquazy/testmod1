package com.cyndaquazy.testmod.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.cyndaquazy.testmod.ref.ConfigReference;
import com.cyndaquazy.testmod.ref.GlobalConstants;
import com.cyndaquazy.testmod.util.LogHelper;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

/**
 * This class initializes the Configuration GUI that is a new feature of 
 * MinecraftForge (version 10.13.0.1160+).
 * 
 * @author Cyndaquazy
 *
 */
public class ModGuiConfig extends GuiConfig
{
  public ModGuiConfig(GuiScreen guiScreen)
  {
    // Create the config GUI screen by adding the configuration elements, setting flags, and setting the title.
    super(guiScreen,
        getConfigElements(), // Which config elements to display.
        GlobalConstants.MOD_ID, // Your mod's id.
        false, // Should the world be restarted?
        false, // Should Minecraft be restarted?
        "Cyndaquazy's Test Mod [CONFIG]"); // Gui Title.
  }
  
  /**
   * Utility method to return all of the config elements that can be altered via the GUI.
   * 
   * As of July 2, the elements are added on a per-category basis.
   * 
   * @return The List of config elements to display in the GUI.
   */
  private static List<IConfigElement> getConfigElements()
  {
    List<IConfigElement> configElements = new ArrayList<IConfigElement>();
    
    if (ConfigReference.getInstance().isLoaded())
    {
      ConfigElement generalConfigs = new ConfigElement(ConfigReference.getInstance().getCategory(Configuration.CATEGORY_GENERAL));
      ConfigElement blockConfigs = new ConfigElement(ConfigReference.getInstance().getCategory(ConfigReference.CATEGORY_BLOCKIDS));
      
      configElements.addAll(generalConfigs.getChildElements());
      configElements.addAll(blockConfigs.getChildElements());
    }
    else
    {
      LogHelper.error("Attempted to retrieve config information from an unloaded config file.");
    }
    
    return configElements;
  }
}
