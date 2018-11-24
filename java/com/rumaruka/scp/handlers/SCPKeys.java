package com.rumaruka.scp.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;
import scala.collection.parallel.ParIterableLike;

public class SCPKeys {

    public static SCPKeys instance;
    public Minecraft mc;
    public static final String descBlinking =  "key.blinking.desc";
    public static final String descBlink =  "key.blink.desc";
    public static KeyBinding activeBlink = new KeyBinding(descBlinking,Keyboard.KEY_B,"SCPCraft");
    public static KeyBinding blink = new KeyBinding(descBlink,Keyboard.KEY_Z,"SCPCraft");


    public SCPKeys(Minecraft mc){
        this.mc=mc;
        instance=this;
        ClientRegistry.registerKeyBinding(activeBlink);
        ClientRegistry.registerKeyBinding(blink);
    }

}
