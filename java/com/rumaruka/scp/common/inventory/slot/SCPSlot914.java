package com.rumaruka.scp.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SCPSlot914 extends Slot {

    private EntityPlayer player;
    private  int removeCount;


    public SCPSlot914(EntityPlayer player,IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.player=player;

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
       if(getHasStack()){
           removeCount+=Math.min(amount,getStack().getCount());
       }
       return super.decrStackSize(amount);
    }

    @Override
    protected void onCrafting(ItemStack stack) {

        onSCP914zided(stack);
        super.onCrafting(stack);
    }
    protected void onCrafting(ItemStack stack,int par1){
        this.removeCount+=par1;
        onSCP914zided(stack);
    }


    protected void onSCP914zided(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.player.world, this.player, this.removeCount);
        this.removeCount = 0;
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerSmeltedEvent(player, par1ItemStack);
    }
}
