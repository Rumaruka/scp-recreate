package com.rumaruka.scp.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.world.World;

public class ContainerDocument extends Container {
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    public ContainerDocument(InventoryPlayer inventory, World world, int x, int y, int z)
    {

    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }
}
