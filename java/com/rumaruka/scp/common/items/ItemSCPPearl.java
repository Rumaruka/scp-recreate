package com.rumaruka.scp.common.items;

import com.rumaruka.scp.client.creativetabs.SCPTab;
import com.rumaruka.scp.common.registry.DocumentRegistry;
import com.rumaruka.scp.core.LibMisc;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSCPPearl extends Item {

    public ItemSCPPearl(String name){
        this.setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
        setHasSubtypes(true);

    }


    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab== SCPTab.scpTab){
            for(int i = 0;i <DocumentRegistry.scpList.size();i++){
                items.add(new ItemStack(this,1,i));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String s = "scp." + ((DocumentRegistry.DocumentInfo)DocumentRegistry.scpList.get(stack.getItemDamage())).name + ".name";
        String s1 = getUnlocalizedName() + ".name";

        tooltip.add(I18n.format(s,new Object[0])+" "+I18n.format(s1,new Object[0]));
    }
}
