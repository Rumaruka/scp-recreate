package com.rumaruka.scp.common.entity.player;

import com.rumaruka.scp.common.entity.SCPEntityAttributes;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.core.capabilities.IExtendedPlayer;
import com.rumaruka.scp.scp;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ExtendedPlayerSCP  implements IExtendedPlayer {
    public static Map<EntityPlayer, ExtendedPlayerSCP> clientExtendedPlayers = new HashMap<EntityPlayer, ExtendedPlayerSCP>();
    public static Map<String, NBTTagCompound> backupNBTTags = new HashMap<String, NBTTagCompound>();
    public EntityPlayer player;
    private boolean seen096;
    private boolean heard513;
    private double time;
    private double blinkSpeed;
    private ResourceLocation locationCape;
    public ExtendedPlayerSCP(){
        getPlayer();
        this.time=0;
        this.blinkSpeed=2;
        this.seen096=false;
        this.heard513=false;
        this.setupCustomSkin();
    }
protected void setupCustomSkin(){

        this.locationCape=getLocationCape("Rumaruka");
}
    public static float getMaxBlink(){
        return (float) SCPEntityAttributes.MAX_BLINK.getDefaultValue();
    }
    public static float getBlink(){
        return (float) SCPEntityAttributes.BLINK.getDefaultValue();
    }

    public ResourceLocation getLocationCape(){
        return this.locationCape;
    }
    public static ResourceLocation getLocationCape(String name){
        return new ResourceLocation(LibMisc.MODID+"scpcape.png");
    }
    public static ExtendedPlayerSCP getForPlayer(EntityPlayer player){
        if(player==null){
            return null;
        }
        if(player.getEntityWorld()!=null&&player.getEntityWorld().isRemote){
            if(clientExtendedPlayers.containsKey(player)){
                ExtendedPlayerSCP extendedPlayerSCP = clientExtendedPlayers.get(player);
                extendedPlayerSCP.setPlayer(player);
                return extendedPlayerSCP;
            }
            ExtendedPlayerSCP extendedPlayer = new ExtendedPlayerSCP();
            extendedPlayer.setPlayer(player);
            clientExtendedPlayers.put(player,extendedPlayer);
        }
        IExtendedPlayer iExtendedPlayer = player.getCapability(scp.EXTENDED_PLAYER,null);
        if(!(iExtendedPlayer instanceof ExtendedPlayerSCP)){
            return null;
        }
        ExtendedPlayerSCP extendedPlayerSCP = (ExtendedPlayerSCP) iExtendedPlayer;
        if(extendedPlayerSCP.getPlayer()!=player) {
            extendedPlayerSCP.setPlayer(player);
        }
            return extendedPlayerSCP;

    }

    public void setPlayer(EntityPlayer player) {
        this.player=player;
        if(this.player.getEntityWorld()==null||this.player.getEntityWorld().isRemote){
            return;
        }
        if(backupNBTTags.containsKey(this.player.getName())){
            this.readNBT(backupNBTTags.get(this.player.getName()));
            backupNBTTags.remove(this.player.getName());
        }
    }

    public void readNBT(NBTTagCompound compound) {
        NBTTagCompound tagCompound = compound.getCompoundTag("SCPPlayer");
        if(tagCompound.hasKey("Time")){
            tagCompound.getDouble("Time");
        }
        if(tagCompound.hasKey("Blink")){
            tagCompound.getDouble("Blink");
        }
        if(tagCompound.hasKey("Blinking")){
            tagCompound.getDouble("Blinking");
        }
        if(tagCompound.hasKey("MAX_BLINK")){
            tagCompound.getDouble("MAX_BLINK");
        }
        if(tagCompound.hasKey("BlinkSpeed")){
            tagCompound.getDouble("BlinkSpeed");
        }
        if(tagCompound.hasKey("Seen096")){
            tagCompound.getBoolean("Seen096");
        }
        if(tagCompound.hasKey("Heard513")){
            tagCompound.getBoolean("Heard513");
        }

    }
    public NBTTagCompound writeNBT(NBTTagCompound compound){
        NBTTagCompound tagCompound=compound.getCompoundTag("ExtendedPlayerSCP");
        tagCompound.setDouble("Time", this.time);
        tagCompound.setDouble("Blink",SCPEntityAttributes.BLINK.getDefaultValue());
        tagCompound.setDouble("Blinking",SCPEntityAttributes.BLINKING.getDefaultValue());
        tagCompound.setDouble("MAX_BLINK",SCPEntityAttributes.MAX_BLINK.getDefaultValue());
        tagCompound.setDouble("BlinkSpeed", this.blinkSpeed);
        tagCompound.setBoolean("Seen096",this.seen096);
        tagCompound.setBoolean("Heard513",this.heard513);
        return compound;
    }
    public EntityPlayer getPlayer() {
        return this.player;
    }

    public void backupPlayer() {
        if(this.player != null) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            this.writeNBT(nbtTagCompound);
            backupNBTTags.put(this.player.getName(), nbtTagCompound);
        }

    }


    public double getBlinkSpeed() {
        return blinkSpeed;
    }

    public void setBlinkSpeed(double blinkSpeed) {
        this.blinkSpeed = blinkSpeed;
    }
    public boolean hasHeard513()
    {
        return this.heard513;
    }

    public void hear513(boolean bool)
    {
        this.heard513 = bool;
    }

    public boolean hasSeen0096()
    {
        return this.seen096;
    }

    public void see0096(boolean bool)
    {
        this.seen096 = bool;
    }
}
