package com.cyndaquazy.testmod.util;

import org.apache.logging.log4j.Level;

import com.cyndaquazy.testmod.ref.GlobalConstants;

import cpw.mods.fml.common.FMLLog;

/**
 * This class provides utlity methods for logging information to Forge's
 * log file. It supports ERROR, FATAL, INFO, and WARN-level messages (the
 * default levels printed by the console window).
 * 
 * @author Cyndaquazy
 */
public class LogHelper
{
  public static void log(Level logLevel, Object object)
  {
    FMLLog.log(GlobalConstants.MOD_NAME, logLevel, String.valueOf(object));
  }
  
  public static void error(Object o) { log(Level.ERROR, o); }
  
  public static void fatal(Object o) { log(Level.FATAL, o); }
  
  public static void info(Object o) { log(Level.INFO, o); }
  
  public static void warn(Object o) { log(Level.WARN, o); }
}
