package com.rumaruka.scp.recipesMod;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class SCP914VeryRoughRecipe {


    private static final SCP914VeryRoughRecipe scpVRBase = new SCP914VeryRoughRecipe();

    private final Map<ItemStack, ItemStack> scpvrList = Maps.<ItemStack, ItemStack>newHashMap();

    public static SCP914VeryRoughRecipe instance()
    {
        return scpVRBase;
    }



private SCP914VeryRoughRecipe(){
        addVeryRough(Items.DIAMOND,new ItemStack(Items.COAL));

}
    public void addVeryRoughRecipeForBlock(Block input, ItemStack stack)
    {
        this.addVeryRough(Item.getItemFromBlock(input), stack);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addVeryRough(Item input, ItemStack stack)
    {
        this.addVeryRoughRecipe(new ItemStack(input, 1, 32767), stack);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addVeryRoughRecipe(ItemStack input, ItemStack stack)
    {
        if (getVeryRoughResult(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
        this.scpvrList.put(input, stack);

    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getVeryRoughResult(ItemStack stack)
    {
        for (Map.Entry<ItemStack, ItemStack> entry : this.scpvrList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getVeryRoughtList()
    {
        return this.scpvrList;
    }

   }
