package com.rumaruka.scp.common.items;

import com.rumaruka.scp.client.creativetabs.SCPTab;
import com.rumaruka.scp.common.registry.DocumentRegistry;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.util.EnumHandler;
import com.rumaruka.scp.util.EnumHandler.SCPType;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSCPDocument extends Item {
    private String[] levels = new String[3];
    public ItemSCPDocument(String name) {
        this.setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab== SCPTab.scpTab){
            for(int i = 0; i< SCPType.values().length;i++){
                items.add(new ItemStack(this,1,i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < SCPType.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getUnlocalizedName() + "_" + SCPType.values()[i].getName();
            } else {
                continue;
            }
        }
        return this.getUnlocalizedName() + "." + SCPType.SAFE.getName();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
       // String scp =DocumentRegistry.scpList.get(stack.getItemDamage()).name;
      //  String level = getColor(stack);
      //  tooltip.add(I18n.format(new StringBuilder().append("scp.").append(scp).append(".name").toString(), new Object[0]));
      //  tooltip.add(level+I18n.format(new StringBuilder().append("scp.").append(scp).append(".desc").toString(), new Object[0]));
    }
    private String getColor(ItemStack stack)
    {
        return this.levels[DocumentRegistry.scpList.get(stack.getItemDamage()).level];
    }

}
