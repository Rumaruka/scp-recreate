package com.rumaruka.scp.common.items;

import com.rumaruka.scp.client.creativetabs.SCPTab;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.util.EnumHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemSCPKeycard extends Item {
    public ItemSCPKeycard(String name) {
        this.setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
        setHasSubtypes(true);
        setCreativeTab(SCPTab.scpTab);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab== SCPTab.scpTab){
            for(int i = 0; i< EnumHandler.KeyCardType.values().length; i++){
                items.add(new ItemStack(this,1,i));
            }
        }
    }


    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < EnumHandler.SCPType.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getUnlocalizedName() + "_" + EnumHandler.KeyCardType.values()[i].getName();
            } else {
                continue;
            }
        }
        return this.getUnlocalizedName() + "." + EnumHandler.KeyCardType.I.getName();
    }
}
