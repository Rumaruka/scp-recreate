package com.rumaruka.scp.recipesMod;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class SCP914VeryFineRecipe {
    private static final SCP914VeryFineRecipe scpVRBase = new SCP914VeryFineRecipe();

    private final Map<ItemStack, ItemStack> scpvrList = Maps.<ItemStack, ItemStack>newHashMap();

    public static SCP914VeryFineRecipe instance()
    {
        return scpVRBase;
    }



    private SCP914VeryFineRecipe(){
        addVeryFine(Items.DIAMOND,new ItemStack(Items.COAL));

    }
    public void addVeryFineRecipeForBlock(Block input, ItemStack stack)
    {
        this.addVeryFine(Item.getItemFromBlock(input), stack);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addVeryFine(Item input, ItemStack stack)
    {
        this.addVeryFineRecipe(new ItemStack(input, 1, 32767), stack);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addVeryFineRecipe(ItemStack input, ItemStack stack)
    {
        if (getVeryFineResult(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
        this.scpvrList.put(input, stack);

    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getVeryFineResult(ItemStack stack)
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

    public Map<ItemStack, ItemStack> getVeryFinetList()
    {
        return this.scpvrList;
    }
}
