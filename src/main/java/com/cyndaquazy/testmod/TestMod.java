package com.cyndaquazy.testmod;

import com.cyndaquazy.testmod.handler.EventHandler;
import com.cyndaquazy.testmod.init.ModBlocks;
import com.cyndaquazy.testmod.init.ModEntities;
import com.cyndaquazy.testmod.init.ModItems;
import com.cyndaquazy.testmod.init.Recipes;
import com.cyndaquazy.testmod.proxy.IProxy;
import com.cyndaquazy.testmod.ref.ConfigReference;
import com.cyndaquazy.testmod.ref.GlobalConstants;
import com.cyndaquazy.testmod.util.LogHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * The base class for Cyndaquazy's Test Mod.
 * 
 * This class is loaded by ForgeModLoader and takes care of all of the
 * initializations required when Minecraft first loads.
 * 
 * @author Cyndaquazy
 *
 */
@Mod(
      modid = GlobalConstants.MOD_ID,
       name = GlobalConstants.MOD_NAME,
    version = GlobalConstants.MOD_VERSION,
    guiFactory = GlobalConstants.GUI_FACTORY_CLASS
)
public class TestMod
{
  
  @Mod.Instance(GlobalConstants.MOD_ID)
  public static TestMod instance;
  
  @SidedProxy(
      clientSide = GlobalConstants.CLIENT_PROXY_CLASS,
      serverSide = GlobalConstants.SERVER_PROXY_CLASS
  )
  public static IProxy proxy;
  
  /**
   * This handler executes before the mod is initialized.
   * 
   * This handler takes care of loading the mod's configuration from a config file, setting up the
   * mod's network handling, and instantiates all of the blocks and items added by this mod.
   * 
   * @param preInitEvt The event object passed to the mod by ForgeModLoader during the pre-
   * initialization phase.
   */
  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent preInitEvt)
  {
    LogHelper.info("Gathering materials...");
    
    //Load information from the configuration file using my ConfigReference (under ref package).
    ConfigReference.getInstance().initConfiguration(preInitEvt.getSuggestedConfigurationFile());

    // Register all of our mod's event listeners.
    EventHandler.getInstance().registerEventListeners();
    
    // Register all of our items.
    ModItems.registerItems();
    
    // Register all of our blocks.
    ModBlocks.registerBlocks();
    
  }
  
  
  /**
   * This handler executes as the mod is initialized.
   * 
   * This handler takes care of registering GUIs, TileEntities, crafting recipes
   * -- essentially all of the "behind the scenes" aspects of the mod.
   * 
   * @param initEvt The event object passed to the mod by ForgeModLoader during the initialization
   * phase.
   */
  @Mod.EventHandler
  public void init(FMLInitializationEvent initEvt)
  {
    LogHelper.info("Constructing facilities...");
    
    // Register our recipes.
    Recipes.initRecipes();
    
    // Register our entities.
    ModEntities.initEntities();
    
  }
  
  
  /**
   * This handler executes after the mod is initialized.
   * 
   * This handler is where the mod can go through and analyze what other mods have been initialized.
   * Therefore, this is the method where the mod can create special recipes using other mod items, etc.
   * 
   * @param postInitEvt The even object passed to the mod by ForgeModLoader during the post-
   * initialization phase.
   */
  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent postInitEvt)
  {
    LogHelper.info("Adding last-minute touches...");
    
    
    
    LogHelper.info("TestMod is ready!");
  }
}
