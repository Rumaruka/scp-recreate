package com.rumaruka.scp.client.creativetabs;

import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.init.SCPItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SCPTab extends CreativeTabs {

    public static SCPTab scpTab = new SCPTab();


    public SCPTab(  ) {

        super("scpTab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(SCPItem.scp1023arc);
    }
}
