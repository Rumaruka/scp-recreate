package com.rumaruka.scp.common.items;

import com.rumaruka.scp.core.LibMisc;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class ItemSCPKnife extends ItemSword {

    public ItemSCPKnife(Item.ToolMaterial m,String name){
        super(m);
        this.setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }
}
