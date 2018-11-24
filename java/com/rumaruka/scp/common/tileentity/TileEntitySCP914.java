package com.rumaruka.scp.common.tileentity;

import com.rumaruka.scp.common.inventory.ContainerSCP914;
import com.rumaruka.scp.handlers.FuelHandler;
import com.rumaruka.scp.recipesMod.*;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Collections;
import java.util.Random;

import static com.rumaruka.scp.recipesMod.SCP914VeryRoughRecipe.instance;

public class TileEntitySCP914 extends TileEntityLockable implements ITickable, IInventory {

    private ItemStack[] itemStacksSCP914 = new ItemStack[7];
    public int burnTimeSCP914=0;
    public int currentItemBurnTime=0;
    public int cookTimeSCP914=0;

    private String scp914CustomName;
    private int totalCookTime;


    @Override
    public int getSizeInventory() {
        return itemStacksSCP914.length;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack s:this.itemStacksSCP914){
            if(!s.isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.itemStacksSCP914.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if(stack!=ItemStack.EMPTY){
            if(stack.getMaxStackSize()<=count){
                setInventorySlotContents(index,ItemStack.EMPTY);


            }else {
                stack=stack.splitStack(count);
                if(stack.getMaxStackSize()==0){
                    setInventorySlotContents(index,ItemStack.EMPTY);
                }
            }
        }
        return stack;
    }
    public int getCookProgressScaled(int par1)
    {
        return this.cookTimeSCP914 * par1 / 250;
    }
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return this.itemStacksSCP914[index];
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack stack1 = this.itemStacksSCP914.get(index);
        boolean flag = !stack.isEmpty()&&stack.isItemEqual(stack1)&&ItemStack.areItemStackShareTagsEqual(stack,stack1);
        this.itemStacksSCP914.set(index,stack);

        if(stack.getCount()>this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }
        if(index==0 &&!flag){
            this.totalCookTime=this.getBurnTime(stack);
            this.cookTimeSCP914=0;
            this.markDirty();
        }

    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 2)
        {
            return false;
        }
        else if (index != 1)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = this.itemStacksSCP914.get(1);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }

    @Override
    public int getField(int id) {
        switch (id)
        {
            case 0:
                return this.burnTimeSCP914;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTimeSCP914;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }


    @Override
    public void setField(int id, int value) {
        switch (id)
        {
            case 0:
                this.burnTimeSCP914 = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTimeSCP914 = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        this.itemStacksSCP914.clear();
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {

        return new ContainerSCP914(playerInventory,this);
    }

    @Override
    public String getGuiID() {
        return "scp:scp914";
    }

    @Override
    public String getName() {
        return this.hasCustomName()?this.scp914CustomName:"container.scp914";
    }

    @Override
    public boolean hasCustomName() {
        return this.scp914CustomName!=null&&!this.scp914CustomName.isEmpty();
    }

    @Override
    public void update() {
        boolean smelt = this.isBurning();
        boolean isWork = false;

        if(this.isBurning()){
            --this.burnTimeSCP914;
        }
        if(!this.world.isRemote){
            ItemStack itemStack = this.itemStacksSCP914.get(0);
            ItemStack itemStack1 = this.itemStacksSCP914.get(1);
            ItemStack itemStack2 = this.itemStacksSCP914.get(2);
            ItemStack itemStack3 = this.itemStacksSCP914.get(3);
            ItemStack itemStack4 = this.itemStacksSCP914.get(4);

            if(this.isBurning()||!itemStack1.isEmpty()||!itemStack2.isEmpty()||!itemStack3.isEmpty()||itemStack4.isEmpty()&&!itemStack.isEmpty()){
                if(!this.isBurning()&&this.canVeryRough()||this.canRough()||this.can1_1()||this.canFine()||this.canVeryFine()){
                    this.burnTimeSCP914 = getItemBurnTime(itemStack);
                    this.currentItemBurnTime = this.burnTimeSCP914;
                }
                if(this.isBurning()){
                    isWork=true;

                    if(!itemStack1.isEmpty()||!itemStack2.isEmpty()||!itemStack3.isEmpty()||itemStack4.isEmpty()){
                        Item i = itemStack1.getItem();
                        Item i1 = itemStack2.getItem();
                        Item i2 = itemStack3.getItem();
                        Item i3 = itemStack4.getItem();
                        itemStack1.shrink(1);
                        itemStack2.shrink(1);
                        itemStack3.shrink(1);
                        itemStack4.shrink(1);
                        if(!itemStack1.isEmpty()||!itemStack2.isEmpty()||!itemStack3.isEmpty()||itemStack4.isEmpty()){
                            ItemStack stack= i.getContainerItem(itemStack1);
                            ItemStack stack1= i1.getContainerItem(itemStack2);
                            ItemStack stack2= i2.getContainerItem(itemStack3);
                            ItemStack stack3= i3.getContainerItem(itemStack4);

                            this.itemStacksSCP914.set(1,stack);
                            this.itemStacksSCP914.set(2,stack1);
                            this.itemStacksSCP914.set(3,stack2);
                            this.itemStacksSCP914.set(4,stack3);
                        }
                    }
                }

            }
            if(!this.isBurning()&&this.canVeryRough()||this.canRough()||this.can1_1()||this.canFine()||this.canVeryFine()){
                ++this.cookTimeSCP914;
                if(this.cookTimeSCP914==this.totalCookTime){
                    this.cookTimeSCP914=0;


                    this.totalCookTime=this.getBurnTime(this.itemStacksSCP914.get(0));
                    this.totalCookTime=this.getBurnTime(this.itemStacksSCP914.get(1));
                    this.totalCookTime=this.getBurnTime(this.itemStacksSCP914.get(2));
                    this.totalCookTime=this.getBurnTime(this.itemStacksSCP914.get(3));
                    this.totalCookTime=this.getBurnTime(this.itemStacksSCP914.get(4));
                    this.smeltItem();
                    isWork=true;
                }
            }
            else
            {
                this.cookTimeSCP914=0;
            }
        }
        else if(!this.isBurning()&&this.cookTimeSCP914>0){
            this.cookTimeSCP914= MathHelper.clamp(this.cookTimeSCP914-2,0,this.totalCookTime);
        }
        if(smelt !=this.isBurning()){
            isWork=true;
        }
        if(isWork){
            this.markDirty();
        }
    }







    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.itemStacksSCP914 = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacksSCP914);

        this.burnTimeSCP914 = compound.getInteger("BurnTime");
        this.cookTimeSCP914 = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.itemStacksSCP914.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.scp914CustomName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
       super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.burnTimeSCP914);
        compound.setInteger("CookTime", (short)this.cookTimeSCP914);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.itemStacksSCP914);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.scp914CustomName);
        }

        return compound;
    }

    /**
     * Mod`s method about SCP 914 Recipes
     *
     *
     */





    public boolean isBurning(){
        return this.burnTimeSCP914>0;
    }
    private boolean canVeryRough(){
        if(this.itemStacksSCP914.get(0).isEmpty()){
            return false;
        }
        ItemStack stack= SCP914VeryRoughRecipe.instance().getVeryRoughResult(this.itemStacksSCP914.get(0));
        return stack != ItemStack.EMPTY;
    }
    private boolean canRough(){
        if(this.itemStacksSCP914.get(1).isEmpty()){
            return false;
        }
        ItemStack stack= SCP914RoughRecipe.instance().getRoughResult(this.itemStacksSCP914.get(1));
        return stack != ItemStack.EMPTY;
    }
    private boolean can1_1(){
        if(this.itemStacksSCP914.get(2).isEmpty()){
            return false;
        }
        ItemStack stack= SCP9141in1Recipe.instance().get1in1Result(this.itemStacksSCP914.get(2));
        return stack != ItemStack.EMPTY;
    }
    private boolean canFine(){
        if(this.itemStacksSCP914.get(3).isEmpty()){
            return false;
        }
        ItemStack stack= SCP914FineRecipe.instance().getFineResult(this.itemStacksSCP914.get(3));
        return stack != ItemStack.EMPTY;
    }
    private boolean canVeryFine(){
        if(this.itemStacksSCP914.get(4).isEmpty()){
            return false;
        }
        ItemStack stack= SCP914VeryFineRecipe.instance().getVeryFineResult(this.itemStacksSCP914.get(4));
        return stack != ItemStack.EMPTY;
    }


    public void smeltItem(){
        if(canVeryRough()){
            ItemStack itemstack = this.itemStacksSCP914.get(0);
            ItemStack itemstack1 = SCP914VeryRoughRecipe.instance().getVeryRoughResult(itemstack);
            ItemStack itemstack2 = this.itemStacksSCP914.get(5);

            if (itemstack2.isEmpty())
            {
                this.itemStacksSCP914.set(5, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);

            }
        if(canRough()){
            ItemStack itemstack = this.itemStacksSCP914.get(1);
            ItemStack itemstack1 = SCP914RoughRecipe.instance().getRoughResult(itemstack);
            ItemStack itemstack2 = this.itemStacksSCP914.get(5);

            if (itemstack2.isEmpty())
            {
                this.itemStacksSCP914.set(5, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);

        }
        if(can1_1()){
            ItemStack itemstack = this.itemStacksSCP914.get(2);
            ItemStack itemstack1 = SCP9141in1Recipe.instance().get1in1Result(itemstack);
            ItemStack itemstack2 = this.itemStacksSCP914.get(5);

            if (itemstack2.isEmpty())
            {
                this.itemStacksSCP914.set(5, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);

        }
        if(canFine()){
            ItemStack itemstack = this.itemStacksSCP914.get(3);
            ItemStack itemstack1 = SCP914FineRecipe.instance().getFineResult(itemstack);
            ItemStack itemstack2 = this.itemStacksSCP914.get(5);


            if (itemstack2.isEmpty())
            {
                this.itemStacksSCP914.set(5, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);

        }
        if(canVeryFine()){
            ItemStack itemstack = this.itemStacksSCP914.get(4);
            ItemStack itemstack1 = SCP914VeryFineRecipe.instance().getVeryFineResult(itemstack);
            ItemStack itemstack2 = this.itemStacksSCP914.get(5);

            if (itemstack2.isEmpty())
            {
                this.itemStacksSCP914.set(5, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);

        }
        }
    public  int getBurnTime(ItemStack stack){
        return 200;
    }
        public static int getItemBurnTime(ItemStack stack){
            return 300;
        }
        public static boolean isItemFuel(ItemStack stack){
             return getItemBurnTime(stack)>0;
        }

    }

