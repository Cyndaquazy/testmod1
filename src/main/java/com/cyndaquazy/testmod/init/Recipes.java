package com.cyndaquazy.testmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.cyndaquazy.testmod.util.ModFuelHandler;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Initializing class that handles the registration of recipes and fuels.
 * 
 * Due to the lack of documentation from Forge's JavaDoc, this will serve as a quick overview of adding recipes:
 * 
 * 
 * <h3>Shaped Recipes</h3>
 * 
 * Method Signature: GameRegistry.addShapedRecipe(ItemStack output, Object... args)
 * output: The ItemStack yielded by the recipe (both Item type and quantity).
 * args: A list of arguments as follows:
 *     1, 2, or 3 strings that represents the rows of the crafting table, top to bottom, with characters representing items.
 *     A series of 'char', ItemStack pairs for each character in the above Strings.
 *     
 * Example: TNT
 * <pre>
 *     a | b | a    Where
 *     --|---|--
 *     b | a | b      a = gunpowder
 *     --|---|--      b = sand
 *     a | b | a
 *     
 *     The above is registered as ...(new ItemStack(TNT[, 1]), "aba", "bab", "aba", 'a', new ItemStack(gunpowder), 'b', new ItemStack(sand));
 * </pre>
 *     
 * <h3>Shapeless Recipes</h3>
 * 
 * MethodSignature: GameRegistry.addShapelessRecipe(ItemStack output, Object... args)
 * output: the ItemStack yielded by the recipe (both Item type and quantity).
 * args: A list of up to 9 ItemStacks of items used in the recipe.
 * 
 * Example: Colored wool (single)
 * 
 *    Recipe is any dye, say blue dye, combined with a block of white wool.
 *    The above is registered as ...(new ItemStack(Wool, 1, <blue meta id>), new ItemStack(lapis), new ItemStack(wool, 1, <white meta id>));
 *    
 *    
 * <h3>Fuels</h3>
 * 
 * See {@link com.cyndaquazy.testmod.util.ModFuelHandler}.
 * 
 * @author Myndert
 *
 */
public class Recipes
{
  
  public static void initRecipes()
  {
    // Smelting recipes.
    GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(ModItems.ironPlating), 0.35F);
    GameRegistry.addSmelting(Items.gold_ingot, new ItemStack(ModItems.goldPlating), 0.7F);
    
    // Shaped recipes.
    GameRegistry.addShapedRecipe(new ItemStack(Items.coal), "cc", "cc", 'c', new ItemStack(ModItems.coalChunk));
    GameRegistry.addShapedRecipe(new ItemStack(ModItems.coalChunk), "bb", "bb", 'b', new ItemStack(ModItems.coalBits));
    GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.ironFurnace), "ppp", "pfp", "ppp", 'p', new ItemStack(ModItems.ironPlating), 'f', new ItemStack(Blocks.furnace));
    GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.goldFurnace), "ppp", "pfp", "ppp", 'p', new ItemStack(ModItems.goldPlating), 'f', new ItemStack(Blocks.furnace));

    
    // Shapeless recipes.
    GameRegistry.addShapelessRecipe(new ItemStack(ModItems.coalChunk, 4), new ItemStack(Items.coal));
    GameRegistry.addShapelessRecipe(new ItemStack(ModItems.coalBits, 4), new ItemStack(ModItems.coalChunk));
    
    // Because fuels is a (somewhat) subsection of recipes, this is where we register our FuelHandler.
    GameRegistry.registerFuelHandler(new ModFuelHandler());
  }
}
