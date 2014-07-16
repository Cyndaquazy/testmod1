package com.cyndaquazy.testmod.ref;

import java.io.File;
import java.util.TreeMap;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import com.cyndaquazy.testmod.util.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * This reference class provides and updates configuration information loaded
 * from the mod's configuration file.
 * @author Cyndaquazy
 *
 */
public class ConfigReference
{
  private boolean isLoaded = false;
  private Configuration config;
  private TreeMap<Integer, Object> modConfiguration;
  
  /* Singleton Instance */
  private static ConfigReference instance;
  
  /* Configuration identifiers in the config file. 
   * 
   * All of these constants must be prefixed with CFG_.
   * 
   * Following that is the type indicator:
   *     B_ -- boolean,
   *     I_ -- int,
   *     S_ -- String,
   *     D_ -- double,
   *     F_ -- float,
   *     L_ -- long.
   * 
   */
  public static final int CFG_B_FLAG = 1;
  public static final int CFG_I_VALUE = 2;
  
  /* Config file categories. MUST BE LOWERCASE! */
  public static final String CATEGORY_BLOCKIDS = "blocks";
  
  /* Private constructor of singleton class. */
  private ConfigReference()
  {
    modConfiguration = new TreeMap<Integer, Object>();
  }
  
  /**
   * Get the instance of the ConfigReference class.
   * Create the instance if needed.
   * 
   * @return The instance of this class.
   */
  public static ConfigReference getInstance()
  {
    if (instance == null) { instance = new ConfigReference(); }
    
    return instance;
  }
  
  /**
   * Check whether or not the configuration was loaded.
   * @return
   */
  public boolean isLoaded()
  {
    return isLoaded;
  }
  
  /**
   * Initialize and load the configuration from the provided config file.
   * 
   * @param configFile The file containing the mod's config data.
   */
  public void initConfiguration(File configFile)
  {
    if (config == null)
    {
      config = new Configuration(configFile);
      loadConfiguration();
    }
    else
    {
      LogHelper.warn("Attempted to reinitialize configuration settings. Ignoring...");
    }
  }
  
  /**
   * Load, or reload, the configuration data for the mod.
   */
  public void loadConfiguration()
  {
    // Clear the mod's configuration.
    modConfiguration.clear();
    
    // When loading the config data, first store them in a local variable for logging,
    // then transfer the data to the modConfiguration, using the appropriate CFG_* constant.
    boolean aFlag = config.getBoolean("aFlag", Configuration.CATEGORY_GENERAL, true, "A boolean flag.");
    int intValue = config.getInt("furnace.iron.id", CATEGORY_BLOCKIDS, 1993, 1, 2047, "Iron Furnace block ID.");
    
    LogHelper.info("Configuration (Re)Loaded:");
    LogHelper.info("       aFlag: " + aFlag);
    LogHelper.info("    intValue: " + intValue);
    
    modConfiguration.put(CFG_B_FLAG, aFlag);
    modConfiguration.put(CFG_I_VALUE, intValue);
    
    // If our configuration has changed, update the config file.
    if (config.hasChanged())
    {
      config.save();
    }
    
    // The config has successfully loaded, so update isLoaded.
    isLoaded = true;
  }
  
  /**
   * Returns the value of the config datum point identified by the
   * provided ID.
   * 
   * @param configID one of the CFG_* constants.
   * @return the Object value of the associated configuration ID, or {@code null} if none exists.
   */
  public Object getConfigValue(int configID)
  {
    return modConfiguration.get(configID);
  }
  
 
  
  /**
   * Window method to get the configuration categories from the underlying {@code Configuration} object.
   * 
   * @param category The category name to retrieve.
   * @return The corresponding ConfigCategory object.
   */
  public ConfigCategory getCategory(String category)
  {
    return config.getCategory(category);
  }
}
