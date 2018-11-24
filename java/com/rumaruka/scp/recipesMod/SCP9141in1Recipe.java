package com.rumaruka.scp.recipesMod;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class SCP9141in1Recipe {
    private static final SCP9141in1Recipe scpVRBase = new SCP9141in1Recipe();

    private final Map<ItemStack, ItemStack> scpvrList = Maps.<ItemStack, ItemStack>newHashMap();

    public static SCP9141in1Recipe instance()
    {
        return scpVRBase;
    }



    private SCP9141in1Recipe(){
        add1in1(Items.DIAMOND,new ItemStack(Items.COAL));

    }
    public void add1in1RecipeForBlock(Block input, ItemStack stack)
    {
        this.add1in1(Item.getItemFromBlock(input), stack);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void add1in1(Item input, ItemStack stack)
    {
        this.add1in1Recipe(new ItemStack(input, 1, 32767), stack);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void add1in1Recipe(ItemStack input, ItemStack stack)
    {
        if (get1in1Result(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
        this.scpvrList.put(input, stack);

    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack get1in1Result(ItemStack stack)
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

    public Map<ItemStack, ItemStack> get1in1tList()
    {
        return this.scpvrList;
    }
}
