package com.cyndaquazy.testmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.cyndaquazy.testmod.ref.GlobalConstants;
import com.cyndaquazy.testmod.ref.Names;
import com.cyndaquazy.testmod.util.NBTHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityIronFurnace extends TileEntity implements ISidedInventory
{
  private static final int SLOT_INPUT = 0;
  private static final int SLOT_OUTPUT = 1;
  private static final int SLOT_FUEL = 2;
  
  private static final int[] slotsVisibleTop = new int[] {SLOT_INPUT};
  private static final int[] slotsVisibleBottom = new int[] {SLOT_FUEL, SLOT_OUTPUT};
  private static final int[] slotsVisibleSides = new int[] {SLOT_OUTPUT};
  
  private ItemStack[] slots = new ItemStack[3];
  
  private String customName = "";
  
  private int burnLeft = 0; // Stores the amount of time left (in ticks) of the current fuel.
  private int cookTime = 0; // Stores the number of ticks since smelt initiated.
  private int fuelTime = 0; // Stores the amount of time fresh fuel would burn.
  
  /**
   * Gets the number of item slots this IronFurnace has.
   * 
   * @return 3, one each for input, output, and fuel.
   */
  @Override
  public int getSizeInventory()
  {
    return 3;
  }

  /**
   * Gets the ItemStack in the specified slot.
   */
  @Override
  public ItemStack getStackInSlot(int slotID)
  {
    return slots[slotID];
  }

  @Override
  public ItemStack decrStackSize(int slotID, int amount)
  {
    ItemStack target = getStackInSlot(slotID);
    
    if (target == null)
    {
      return null;
    }

    ItemStack returnStack = null;
    
    // If the requested size is more than what's available, just return the entire stack
    // and empty the slot.
    if (target.stackSize <= amount)
    {
      returnStack = target;
      slots[slotID] = null;
    }
    // If the requested size is less than what's available, reduce the target's size accordingly.
    // If we happened to take the entire stack, empty the slot.
    else
    {
      returnStack = target.splitStack(amount);
      if (target.stackSize == 0) { slots[slotID] = null; }
    }
    
    return returnStack;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int slotID)
  {
    return getStackInSlot(slotID);
  }

  @Override
  public void setInventorySlotContents(int slotID, ItemStack stack)
  {
    slots[slotID] = stack;
  }

  /**
   * Get the name of this inventory.
   * 
   * @return The custom name, or a default if no name given.
   */
  @Override
  public String getInventoryName()
  {
    if (this.hasCustomInventoryName())
    {
      return customName;
    }
    
    return String.format("container.%s.%s", GlobalConstants.MOD_ID, Names.Entities.IRON_FURNACE);
  }

  /**
   * Determine whether or not this furnace has a custom name.
   */
  @Override
  public boolean hasCustomInventoryName()
  {
    return (customName != null && !customName.isEmpty());
  }

  /**
   * Gets the maximum stack size allowable in the inventory.
   * 
   * This furnace has no special limitations, so the standard maximum (64) is good enough.
   */
  @Override
  public int getInventoryStackLimit()
  {
    return 64;
  }

  /**
   * Determines whether or not this specified player can interact with this furnace.
   * 
   * The interaction is allowed if the player is within 8 blocks.
   */
  @Override
  public boolean isUseableByPlayer(EntityPlayer player)
  {
    // the *Coord and worldObj fields are provided by TileEntity class
    
    double playerDistSquared = player.getDistanceSq(
        (double)xCoord + 0.5D,
        (double)yCoord + 0.5D,
        (double)zCoord + 0.5D);
    
    // Due to client/server desynchronization, the client might attempt to interact with
    // a TileEntity that is not where it thinks it is...
    return (this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this) && (playerDistSquared <= 64.0D);
  }

  /**
   * Method not implemented because we need no special events to occur when our inventory is opened.
   */
  @Override
  public void openInventory()
  {
  }

  /**
   * Method not implemented because we need no special events to occur when our inventory is closed.
   */
  @Override
  public void closeInventory()
  {
  }

  /**
   * Determine whether or not an ItemStack is valid for the given slot. This makes sure that automation (i.e. pipes)
   * doesn't attempt to mess things up.
   */
  @Override
  public boolean isItemValidForSlot(int slotID, ItemStack stack)
  {
    switch (slotID)
    {
      case SLOT_INPUT:
        return true;
      case SLOT_OUTPUT:
        return false;
      case SLOT_FUEL:
        return isFuel(stack);
      default:
        return false;
    }
  }

  /**
   * Determines, for each side of the block, which inventory slots auto-transporters can access.
   * 
   * @param sideID the side to access. 0=bottom, 1=top, 2-5=sides.
   * @return an array of the slots accessible from the given side.
   */
  @Override
  public int[] getAccessibleSlotsFromSide(int sideID)
  {
    switch (sideID)
    {
      case 0: // Bottom
        return slotsVisibleBottom;
      case 1: // Top
        return slotsVisibleTop;
      default:
        return slotsVisibleSides;
    }
  }

  @Override
  public boolean canInsertItem(int slotID, ItemStack stack, int sideID)
  {
    int[] slotsAccessible = getAccessibleSlotsFromSide(sideID);
    
    for (int slot : slotsAccessible)
    {
      if (slot == slotID && isItemValidForSlot(slot, stack))
      {
        return true;
      }
    }
    
    return false;
  }

  @Override
  public boolean canExtractItem(int slotID, ItemStack stack, int sideID)
  {
    int[] slotsAccessible = getAccessibleSlotsFromSide(sideID);
    
    for (int slot : slotsAccessible)
    {
      if (slot == slotID) { return true; }
    }
    
    return false;
  }


  /**
   * Called when the world tick updates this entity. If the furnace is busy smelting, continue. Otherwise, see if smelting can happen.
   */
  @Override
  public void updateEntity()
  {
    boolean previousState = isBurning(); // Used to detect if we finished burning.
    boolean becameDirty = false;
    
    if (isBurning()) // If we are burning, decrease the time left.
    {
      burnLeft--;
    }
    
    // Update the entity in real time if the world is loaded on the client.
    if (!this.worldObj.isRemote)
    {
      // If there is burn time left, or the input and fuel slots have items...
      if (burnLeft != 0 || (slots[SLOT_FUEL] != null && slots[SLOT_INPUT] != null))
      {
        // If we are not burning, but can smelt...
        if (burnLeft == 0 && canSmelt())
        {
          // Get the fuel burn time of the current fuel item.
          // Divide the burn time in half, because the iron furnace uses fuel twice as fast.
          burnLeft = (int)(GameRegistry.getFuelValue(slots[SLOT_FUEL]) / 2);
          fuelTime = burnLeft;
          
          // If we started burning, the entity is now dirty. Additionally, reduce fuel stack by one, emptying the slot if necessary.
          if (burnLeft > 0)
          {
            becameDirty = true;
            
            if (slots[SLOT_FUEL] != null)
            {
              slots[SLOT_FUEL].stackSize--;
              
              if (slots[SLOT_FUEL].stackSize == 0)
              {
                slots[SLOT_FUEL] = null;
              }
            }
          }
        }
        
        // If we are burning and can continue to smelt, increase the cook time.
        if (isBurning() && canSmelt())
        {
          cookTime++;
          
          // If cooking is finished, smelt the item; the entity is now dirty.
          if (cookTime == 100)
          {
            cookTime = 0;
            smeltItem();
            becameDirty = true;
          }
        }
        // If the burning was interrupted (i.e. removal of input), reset cook time.
        else
        {
          cookTime = 0;
        }
      }
      
      // If our state changed, the entity is now dirty. Also, update the block.
      if (previousState != isBurning())
      {
        becameDirty = true;
        // BlockIronFurnace.updateFurnaceBlockState(isBurning(), worldObj, xCoord, yCoord, zCoord);
      }
    }
    
    // If the entity became dirty, mark it as such.
    if (becameDirty)
    {
      markDirty();
    }
    
  }
  

  @Override
  public void writeToNBT(NBTTagCompound root)
  {
    super.writeToNBT(root);
    root.setShort("BurnLeft", (short)burnLeft);
    root.setShort("CookTime", (short)cookTime);
    
    NBTTagList items = new NBTTagList();
    
    for (int i = 0; i < 3; i++)
    {
      if (slots[i] != null)
      {
        NBTTagCompound item = new NBTTagCompound();
        item.setByte("Slot", (byte)i);
        slots[i].writeToNBT(item);
        items.appendTag(item);
      }
    }
    
    root.setTag("Items", items);
    
    if (hasCustomInventoryName())
    {
      root.setString("CustomName", customName);
    }
  }
  
  @Override
  public void readFromNBT(NBTTagCompound root)
  {
    super.readFromNBT(root);
    
    NBTTagList items = root.getTagList("Items", NBTHelper.TAG_ID_COMPOUND);
    slots = new ItemStack[getSizeInventory()];
    
    for (int i = 0; i < items.tagCount(); i++)
    {
      NBTTagCompound item = items.getCompoundTagAt(i);
      int slot = item.getByte("Slot");
      
      if (slot >= 0 && slot < slots.length)
      {
        slots[slot] = ItemStack.loadItemStackFromNBT(item);
      }
    }
    
    burnLeft = root.getShort("BurnLeft");
    cookTime = root.getShort("CookTime");
    
   if (root.hasKey("CustomName", NBTHelper.TAG_ID_STRING))
   {
     customName = root.getString("CustomName");
   }
   else
   {
     customName = "";
   }
  }
  
  /**
   * Utility method to verify that a given ItemStack can be used a fuel.
   * @param stack The ItemStack in question.
   * @return Whether or not the stack is fuel.
   */
  private boolean isFuel(ItemStack stack)
  {
    return GameRegistry.getFuelValue(stack) > 0;
  }
  
  /**
   * Utility method to determine that we have fuel to burn.
   */
  private boolean hasFuel()
  {
    if (slots[SLOT_FUEL] == null) { return false; }
    
    return isFuel(slots[SLOT_FUEL]);
  }
  
  /**
   * Utility method to determine if the furnace is on.
   * @return Whether or not the furnace is on and burning.
   */
  private boolean isBurning()
  {
    return burnLeft > 0;
  }
  
  /**
   * Utility method to determine if the input can smelt.
   * @return true if smelting can occur; false otherwise.
   */
  private boolean canSmelt()
  {
    ItemStack itemToSmelt = slots[SLOT_INPUT];
    ItemStack outputItem = slots[SLOT_OUTPUT];
    
    if (!hasFuel()) { return false; } // We cannot smelt, if there is not fuel.
    
    if (itemToSmelt == null) // If input is empty, we cannot smelt.
    {
      return false;
    }
    else
    {
      ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(itemToSmelt);
      
      if (result == null) { return false; } // If the input has no recipe, we cannot smelt.
      if (outputItem == null) { return true; } // If the input has a recipe, but output is empty, we can smelt.
      if (!outputItem.isItemEqual(result)) { return false; } // If input has recipe, but current output doesn't match, we cannot smelt.
      
      int theoreticalOutputSize = outputItem.stackSize + result.stackSize; // Pretend smelt to see resulting stack size of output.
      
      // If all of the above checks pass, then we can smelt only if the resulting stack size remains within our bounds.
      return (theoreticalOutputSize <= getInventoryStackLimit()) && (theoreticalOutputSize <= outputItem.getMaxStackSize());
      
    }
  }
  
  /**
   * Utility method to process the input into an output item.
   */
  private void smeltItem()
  {
    if (canSmelt())
    {
      ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(slots[SLOT_INPUT]);
      
      if (slots[SLOT_OUTPUT] == null)
      {
        slots[SLOT_OUTPUT] = result.copy();
      }
      else if (slots[SLOT_OUTPUT].isItemEqual(result))
      {
        slots[SLOT_OUTPUT].stackSize += result.stackSize;
      }
      
      slots[SLOT_INPUT].stackSize--;
      
      if (slots[SLOT_INPUT].stackSize == 0) { slots[SLOT_INPUT] = null; }
    }
  }
}
