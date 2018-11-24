package com.rumaruka.scp.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class Globals extends Entity {

    public static int FALSE = 0;
    public static int TRUE = 1;
    public static int KEY_BLINKING = 0;
    public static int KEY_BLINK = 1;
    public static int KEY_REMOTE = 2;
    public static int MAX_BLINK = 400;
    public static final DataParameter<Integer> BLINK = EntityDataManager.<Integer>createKey(Globals.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> BLINKING =EntityDataManager.<Integer>createKey(Globals.class, DataSerializers.VARINT);
    public static int RENDER_PIPE = -1;
    public static int RENDER_KEYCARD = -1;
    public static int RENDER_DESK = -1;
    public static int RENDER_CHAIR = -1;
    public static int Blink = 31;
    public static int Blinking = 30;

    public Globals(World worldIn) {
        super(worldIn);
    }
public Globals(World w,int blink,int blinking,int key_blink,int key_blinking){
    super(w);
    Blink=blink;
    Blinking=blinking;
    KEY_BLINK=key_blink;
    KEY_BLINKING=key_blinking;

}
    @Override
    public void entityInit() {
        this.dataManager.register(BLINK,Blink);
        this.dataManager.register(BLINKING,Blinking);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        Blink = compound.getInteger("Blink");
        Blinking = compound.getInteger("Blinking");
        KEY_BLINK = compound.getInteger("KEYBLINK");
        KEY_BLINKING = compound.getInteger("KEYBLINKING");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("Blink", Blink);
        compound.setInteger("Blinking", Blinking);
        compound.setInteger("KEYBLINK", KEY_BLINK);
        compound.setInteger("KEYBLINKING", KEY_BLINKING);
    }
}



