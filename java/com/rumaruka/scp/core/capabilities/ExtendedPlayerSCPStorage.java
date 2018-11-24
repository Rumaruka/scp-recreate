package com.rumaruka.scp.core.capabilities;

import com.rumaruka.scp.common.entity.player.ExtendedPlayerSCP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ExtendedPlayerSCPStorage implements Capability.IStorage<IExtendedPlayer> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IExtendedPlayer> capability, IExtendedPlayer instance, EnumFacing side) {

        if(!(instance instanceof ExtendedPlayerSCP))
            return null;
        ExtendedPlayerSCP extendedPlayerSCP = (ExtendedPlayerSCP)instance;
        NBTTagCompound extTagCompound = new NBTTagCompound();
        extendedPlayerSCP.writeNBT(extTagCompound);
        return extTagCompound;


    }

    @Override
    public void readNBT(Capability<IExtendedPlayer> capability, IExtendedPlayer instance, EnumFacing side, NBTBase nbt) {
        if(!(instance instanceof ExtendedPlayerSCP) || !(nbt instanceof NBTTagCompound))
            return;
        ExtendedPlayerSCP extendedPlayer = (ExtendedPlayerSCP)instance;
        NBTTagCompound extTagCompound = (NBTTagCompound)nbt;
        extendedPlayer.readNBT(extTagCompound);

    }
}
