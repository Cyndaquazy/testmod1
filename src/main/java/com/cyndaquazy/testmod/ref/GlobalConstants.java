package com.cyndaquazy.testmod.ref;

/**
 * This class defines global-type constants the are intended to be used mod-wide.
 * 
 * Constants included here are the mod's ID, name, and version strings, and the
 * fully-qualified server and client proxy class and the GuiFactory class names.
 * 
 * @author Cyndaquazy
 *
 */
public class GlobalConstants
{
  // Mod information.
  public static final String MOD_ID = "testmod";
  public static final String MOD_NAME = "Test Mod";
  public static final String MOD_VERSION = "1.7.10-indev";
  
  // Fully-qualified class names.
  public static final String SERVER_PROXY_CLASS = "com.cyndaquazy.testmod.proxy.ServerProxy";
  public static final String CLIENT_PROXY_CLASS = "com.cyndaquazy.testmod.proxy.ClientProxy";
  public static final String GUI_FACTORY_CLASS = "com.cyndaquazy.testmod.client.gui.GuiFactory";
}
