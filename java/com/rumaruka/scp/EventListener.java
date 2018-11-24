package com.rumaruka.scp;

import com.rumaruka.scp.common.entity.player.ExtendedPlayerSCP;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.core.capabilities.IExtendedEntity;
import com.rumaruka.scp.core.capabilities.IExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventListener {


    public EventListener() {

    }

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        /*if(event.getObject() instanceof EntityLivingBase) {
            event.addCapability(new ResourceLocation(LibMisc.MODID, "IExtendedEntity"), new ICapabilitySerializable<NBTTagCompound>() {
                IExtendedEntity instance = scp.EXTENDED_ENTITY.getDefaultInstance();

                @Override
                public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                    return capability == scp.EXTENDED_ENTITY;
                }

                @Override
                public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                    return capability == scp.EXTENDED_ENTITY ? scp.EXTENDED_ENTITY.<T>cast(this.instance) : null;
                }

                @Override
                public NBTTagCompound serializeNBT() {
                    return (NBTTagCompound) scp.EXTENDED_ENTITY.getStorage().writeNBT(scp.EXTENDED_ENTITY, this.instance, null);
                }

                @Override
                public void deserializeNBT(NBTTagCompound nbt) {
                    scp.EXTENDED_ENTITY.getStorage().readNBT(scp.EXTENDED_ENTITY, this.instance, null, nbt);
                }
            });
        }*/

        if(event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation(LibMisc.MODID, "IExtendedPlayer"), new ICapabilitySerializable<NBTTagCompound>() {
                IExtendedPlayer instance = scp.EXTENDED_PLAYER.getDefaultInstance();

                @Override
                public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                    return capability == scp.EXTENDED_PLAYER;
                }

                @Override
                public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                    return capability == scp.EXTENDED_PLAYER ? scp.EXTENDED_PLAYER.<T>cast(this.instance) : null;
                }

                @Override
                public NBTTagCompound serializeNBT() {
                    return (NBTTagCompound) scp.EXTENDED_PLAYER.getStorage().writeNBT(scp.EXTENDED_PLAYER, this.instance, null);
                }

                @Override
                public void deserializeNBT(NBTTagCompound nbt) {
                    scp.EXTENDED_PLAYER.getStorage().readNBT(scp.EXTENDED_PLAYER, this.instance, null, nbt);
                }
            });
        }
    }


    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        ExtendedPlayerSCP extendedPlayer = ExtendedPlayerSCP.getForPlayer(event.getOriginal());
        if(extendedPlayer != null)
            extendedPlayer.backupPlayer();
    }


}
//}
