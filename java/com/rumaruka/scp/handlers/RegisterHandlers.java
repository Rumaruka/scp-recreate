package com.rumaruka.scp.handlers;

import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.init.SCPSounds;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.LinkedList;
import java.util.List;

public class RegisterHandlers {

    @Mod.EventBusSubscriber(modid = LibMisc.MODID)
    public static class Sounds
    {
        public static final List<SoundEvent> SOUNDS = new LinkedList<>();

        static void add(SoundEvent sound)
        {
            SOUNDS.add(sound);
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<SoundEvent> event)
        {
            SOUNDS.stream().forEach(sound -> event.getRegistry().register(sound));
        }
    }

    public static void init(){
        SCPSounds.registerSounds();
    }
}
