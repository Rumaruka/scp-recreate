package com.rumaruka.scp.common.items;

import com.rumaruka.scp.client.creativetabs.SCPTab;
import com.rumaruka.scp.core.LibMisc;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemSCP500 extends Item
{
    public ItemSCP500(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
        this.setMaxStackSize(1);
        setCreativeTab(SCPTab.scpTab);
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote)
            entityLiving.curePotionEffects(stack);
        if (entityLiving instanceof EntityPlayer)
        {
            this.removeNegativeEffect(entityLiving);




        }

        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            stack.shrink(1);
        }

        return stack;
    }
    public static void removeNegativeEffect(EntityLivingBase entity)
    {
        List<Potion> potions = new ArrayList<>();
        potions.addAll(entity.getActivePotionMap().keySet());
        potions.stream().filter(potion -> potion.isBadEffect()).forEach(entity::removeActivePotionEffect);
    }



    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }


    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.EAT;
    }



    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
