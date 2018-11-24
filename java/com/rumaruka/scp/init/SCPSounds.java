package com.rumaruka.scp.init;

import com.rumaruka.scp.handlers.RegisterHandlers;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SCPSounds {


    public SCPSounds(){

    }

    public static void registerSounds(){

    }
    private static SoundEvent getRegisteredSoundEvent(String soundNameIn)
    {
        ResourceLocation resource = new ResourceLocation(soundNameIn);
        SoundEvent sound = new SoundEvent(resource).setRegistryName(soundNameIn);
        RegisterHandlers.Sounds.SOUNDS.add(sound);
        return sound;
    }
}
