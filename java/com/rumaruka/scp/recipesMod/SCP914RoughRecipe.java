package com.rumaruka.scp.recipesMod;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class SCP914RoughRecipe {

    private static final SCP914RoughRecipe scpVRBase = new SCP914RoughRecipe();

    private final Map<ItemStack, ItemStack> scpvrList = Maps.<ItemStack, ItemStack>newHashMap();

    public static SCP914RoughRecipe instance()
    {
        return scpVRBase;
    }



    private SCP914RoughRecipe(){
        addRough(Items.DIAMOND,new ItemStack(Items.COAL));

    }
    public void addRoughRecipeForBlock(Block input, ItemStack stack)
    {
        this.addRough(Item.getItemFromBlock(input), stack);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addRough(Item input, ItemStack stack)
    {
        this.addRoughRecipe(new ItemStack(input, 1, 32767), stack);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addRoughRecipe(ItemStack input, ItemStack stack)
    {
        if (getRoughResult(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
        this.scpvrList.put(input, stack);

    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getRoughResult(ItemStack stack)
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

    public Map<ItemStack, ItemStack> getRoughtList()
    {
        return this.scpvrList;
    }
}
