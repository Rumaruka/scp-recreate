package com.rumaruka.scp.common.items;

import com.rumaruka.scp.core.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemSCP000J extends Item {

    public ItemSCP000J(String name){
        setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
