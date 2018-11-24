package com.rumaruka.scp.common.items;

import com.google.common.collect.Sets;
import com.rumaruka.scp.core.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

public class ItemSCP063 extends ItemTool {


    public ItemSCP063(String name,float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn,effectiveBlocksIn);
        this.setMaxDamage(-99);
        this.setHarvestLevels(materialIn.getHarvestLevel());
        setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
    }




    public void setHarvestLevels(int amount) {
        for(String s : this.getToolClasses(null)){
            this.setHarvestLevel(s,amount);
        }
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return  state.getMaterial().isToolNotRequired() || (state.getBlock() == Blocks.SNOW_LAYER || state.getBlock() == Blocks.SNOW || (state.getBlock() == Blocks.OBSIDIAN ? this.toolMaterial.getHarvestLevel() >= 3 : (state.getBlock() != Blocks.DIAMOND_BLOCK && state.getBlock() != Blocks.DIAMOND_ORE ? (state.getBlock() != Blocks.EMERALD_ORE && state.getBlock() != Blocks.EMERALD_BLOCK ? (state.getBlock() != Blocks.GOLD_BLOCK && state.getBlock() != Blocks.GOLD_ORE ? (state.getBlock() != Blocks.IRON_BLOCK && state.getBlock() != Blocks.IRON_ORE ? (state.getBlock() != Blocks.LAPIS_BLOCK && state.getBlock() != Blocks.LAPIS_ORE ? (state.getBlock() != Blocks.REDSTONE_ORE && state.getBlock() != Blocks.LIT_REDSTONE_ORE ? (state.getMaterial() == Material.ROCK || (state.getMaterial() == Material.IRON || state.getMaterial() == Material.ANVIL)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2)));
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return Sets.newHashSet("pickaxe","axe","shovel");
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
             return 100.0f;
         }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }
}
