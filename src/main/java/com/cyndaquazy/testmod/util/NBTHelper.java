package com.cyndaquazy.testmod.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * NBTHelper
 *
 * @author Myndert
 * @version 1.0, 17 07 2014
 */
public class NBTHelper
{
  public static final int TAG_ID_END = 0;
  public static final int TAG_ID_BYTE = 1;
  public static final int TAG_ID_SHORT = 2;
  public static final int TAG_ID_INT = 3;
  public static final int TAG_ID_LONG = 4;
  public static final int TAG_ID_FLOAT = 5;
  public static final int TAG_ID_DOUBLE = 6;
  public static final int TAG_ID_BYTES = 7;
  public static final int TAG_ID_STRING = 8;
  public static final int TAG_ID_LIST = 9;
  public static final int TAG_ID_COMPOUND = 10;
  public static final int TAG_ID_INTS = 11;

  /**
   * Initializes the ItemStack's root Compound NBT tag. If the tag already exists, nothing will be done.
   *
   * @param stack The ItemStack whose root tag should be initialized.
   */
  private static void initNBTTagCompound(ItemStack stack)
  {
    if (stack.stackTagCompound == null)
    {
      stack.stackTagCompound = new NBTTagCompound();
    }
  }

  /**
   * Checks whether or not the ItemStack already has a tag with the provided key.
   *
   * @param stack The ItemStack to work with.
   * @param tagKey The key to look for.
   * @return {@code true} if the ItemStack has an NBT tag with the provided key. Returns {@code false} if stack is null,
   * if the stack's compound tag hasn't been initialized, or if the specified key does not exist.
   */
  public static boolean hasNBTTag(ItemStack stack, String tagKey)
  {
    if (stack == null || stack.stackTagCompound == null)
    {
      return false;
    }

    return stack.stackTagCompound.hasKey(tagKey);
  }

  /**
   * Removes a tag from the ItemStack's NBT data with the specified key, if it exists.
   *
   * @param stack The ItemStack to work with.
   * @param tagKey The key to look for.
   */
  public static void removeTag(ItemStack stack, String tagKey)
  {
    if (hasNBTTag(stack, tagKey))
    {
      stack.stackTagCompound.removeTag(tagKey);
    }
  }

  // ****************************************

  /**
   * Gets a byte from an NBT tag. If the tag does not exist, then it will be created with value 0.
   *
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The byte value of the tag.
   */
  public static byte getByte(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setByte(stack, tagKey, (byte)0);
    }

    return stack.stackTagCompound.getByte(tagKey);
  }

  /**
   * Sets a byte to an NBT tag.
   *
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The byte value to set.
   */
  public static void setByte(ItemStack stack, String tagKey, byte tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setByte(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a boolean from an NBT tag. If the tag does not exist, it will be created with value FALSE.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The boolean value of the tag.
   */
  public static boolean getBoolean(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setBoolean(stack, tagKey, false);
    }

    return stack.stackTagCompound.getBoolean(tagKey);
  }

  /**
   * Sets a boolean to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The boolean value to set.
   */
  public static void setBoolean(ItemStack stack, String tagKey, boolean tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setBoolean(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a short from an NBT tag. If the tag does not exist, it will be created with value 0.
   *
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The short value of the tag.
   */
  public static short getShort(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setShort(stack, tagKey, (short)0);
    }

    return stack.stackTagCompound.getShort(tagKey);
  }

  /**
   * Sets a short to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The short value to set.
   */
  public static void setShort(ItemStack stack, String tagKey, short tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setShort(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets an integer from an NBT tag. If the tag does not exist, it will be created with value 0.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The integer value of the tag.
   */
  public static int getInteger(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setInteger(stack, tagKey, 0);
    }

    return stack.stackTagCompound.getInteger(tagKey);
  }

  /**
   * Sets an integer to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The integer value to set.
   */
  public static void setInteger(ItemStack stack, String tagKey, int tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setInteger(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a long from an NBT tag. If the tag does not exist, then it will be created with value 0.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The long value of the tag.
   */
  public static long getLong(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setLong(stack, tagKey, 0L);
    }

    return stack.stackTagCompound.getLong(tagKey);
  }

  /**
   * Sets a long to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The long value to set.
   */
  public static void setLong(ItemStack stack, String tagKey, long tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setLong(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a float from an NBT tag. If the tag does not exist, it will be created with value 0.0.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The float value of the tag.
   */
  public static float getFloat(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setFloat(stack, tagKey, 0.0f);
    }

    return stack.stackTagCompound.getFloat(tagKey);
  }

  /**
   * Sets a float to an NBT tag.
   *
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The float value to set.
   */
  public static void setFloat(ItemStack stack, String tagKey, float tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setFloat(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a double from an NBT tag. If the tag does not exist, it will be created with value 0.0.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The double value of the tag.
   */
  public static double getDouble(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setDouble(stack, tagKey, 0.0D);
    }

    return stack.stackTagCompound.getDouble(tagKey);
  }

  /**
   * Sets a double to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The double value to set.
   */
  public static void setDouble(ItemStack stack, String tagKey, double tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setDouble(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a byte array from an NBT tag. If the tag does not exist, it will be created with an empty array.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The byte array of the tag.
   */
  public static byte[] getByteArray(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setByteArray(stack, tagKey, new byte[]{});
    }

    return stack.stackTagCompound.getByteArray(tagKey);
  }

  /**
   * Sets a byte array to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValues The byte array to set.
   */
  public static void setByteArray(ItemStack stack, String tagKey, byte[] tagValues)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setByteArray(tagKey, tagValues);
  }

  // ****************************************

  /**
   * Gets a String from an NBT tag. If the tag does not exist, it will be created with an empty String ("").
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The String value of the tag.
   */
  public static String getString(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setString(stack, tagKey, "");
    }

    return stack.stackTagCompound.getString(tagKey);
  }

  /**
   * Sets a String to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The String value to set.
   */
  public static void setString(ItemStack stack, String tagKey, String tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setString(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a tag list from an NBT tag. If the tag does not exist, it will be created with an empty list.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @param tagID The TAG_ID_* of the NBT tags contained in the list. Used for error checking.
   * @return The tag list of the tag.
   */
  public static NBTTagList getList(ItemStack stack, String tagKey, int tagID)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setList(stack, tagKey, new NBTTagList());
    }

    return stack.stackTagCompound.getTagList(tagKey, tagID);
  }

  /**
   * Sets a tag list to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The tag list to set.
   */
  public static void setList(ItemStack stack, String tagKey, NBTTagList tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setTag(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets a compound tag from an NBT tag. If the tag does not exist, it will be created with an empty compound.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The compound tag of the tag.
   */
  public static NBTTagCompound getCompoundTag(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setCompoundTag(stack, tagKey, new NBTTagCompound());
    }

    return stack.stackTagCompound.getCompoundTag(tagKey);
  }

  /**
   * Sets a compound tag to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValue The compound tag to set.
   */
  public static void setCompoundTag(ItemStack stack, String tagKey, NBTTagCompound tagValue)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setTag(tagKey, tagValue);
  }

  // ****************************************

  /**
   * Gets an integer array from an NBT tag. If the tag does not exist, it will be created with an empty array.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to get.
   * @return The integer array of the tag.
   */
  public static int[] getIntArray(ItemStack stack, String tagKey)
  {
    initNBTTagCompound(stack);

    if (!hasNBTTag(stack, tagKey))
    {
      setIntArray(stack, tagKey, new int[]{});
    }

    return stack.stackTagCompound.getIntArray(tagKey);
  }

  /**
   * Sets an integer array to an NBT tag.
   * @param stack The ItemStack to work with.
   * @param tagKey The key of the value to set.
   * @param tagValues The integer array to set.
   */
  public static void setIntArray(ItemStack stack, String tagKey, int[] tagValues)
  {
    initNBTTagCompound(stack);
    stack.stackTagCompound.setIntArray(tagKey, tagValues);
  }

}
