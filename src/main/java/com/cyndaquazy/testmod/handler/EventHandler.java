package com.cyndaquazy.testmod.handler;

import com.cyndaquazy.testmod.ref.ConfigReference;
import com.cyndaquazy.testmod.ref.GlobalConstants;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * This class is designed to register and listen to all of the necessary events
 * fired by Forge and FML.
 * 
 * @author Cyndaquazy
 *
 */
public class EventHandler
{
  // Singleton instance.
  private static EventHandler instance;
  
  /**
   * Hide the constructor from outside classes.
   */
  private EventHandler()
  {
    
  }
  
  /**
   * Get the singleton instance of this class. Create it if necessary.
   * @return the instance of this class.
   */
  public static EventHandler getInstance()
  {
    if (instance == null)
    {
      instance = new EventHandler();
    }
    
    return instance;
  }
  
  /**
   * Method to let Forge and FML know to register methods of this class as
   * event listeners to their event buses.
   */
  public void registerEventListeners()
  {
    // Register event listeners to the FML Event bus.
    FMLCommonHandler.instance().bus().register(instance);
    
    
  }
  
  
  /**
   * Spring into action when an OnConfigChangedEvent is fired.
   * 
   * This event is fired whenever the config GUI is closed and changes were made.
   * 
   * @param event The event fired when the config GUI was closed.
   */
  @SubscribeEvent
  public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
  {
    if (event.modID.equalsIgnoreCase(GlobalConstants.MOD_ID))
    {
      // If the config GUI for my mod was closed, update any changes.
      ConfigReference.getInstance().loadConfiguration();
    }
  }
}
