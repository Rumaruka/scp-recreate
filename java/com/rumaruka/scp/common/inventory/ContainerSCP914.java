package com.rumaruka.scp.common.inventory;

import com.rumaruka.scp.common.inventory.slot.SCPSlot914;
import com.rumaruka.scp.common.tileentity.TileEntitySCP914;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSCP914 extends Container {
    private TileEntitySCP914 scp914;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastitemBurnTime;

    public ContainerSCP914(InventoryPlayer inventoryPlayer, TileEntitySCP914 scp914){
        this.lastCookTime=0;
        this.lastBurnTime=0;
        this.lastitemBurnTime=0;
        this.scp914=scp914;
        addSlotToContainer(new Slot(scp914, 0, 8, 22));
        addSlotToContainer(new Slot(scp914, 1, 44, 22));
        addSlotToContainer(new Slot(scp914, 2, 80, 22));
        addSlotToContainer(new Slot(scp914, 3, 117, 22));
        addSlotToContainer(new Slot(scp914, 4, 153, 22));
        addSlotToContainer(new SCPSlot914(inventoryPlayer.player,scp914,5,80,59));


        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 142));
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.scp914.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack=ItemStack.EMPTY;
        Slot slot_object = this.inventorySlots.get(index);
        if(slot_object!=null&&slot_object.getHasStack()){
            ItemStack stack1 = slot_object.getStack();
            stack=stack1.copy();

            if(index==0||index==1||index==2||index==3||index==4||index==5){
                if(!mergeItemStack(stack1,6,this.inventorySlots.size(),false)){
                    return ItemStack.EMPTY;
                }
            }
            else {
                return ItemStack.EMPTY;
            }
            if(stack1.isEmpty()){
                slot_object.putStack(ItemStack.EMPTY);
            }

        }
        return stack;
    }
}
