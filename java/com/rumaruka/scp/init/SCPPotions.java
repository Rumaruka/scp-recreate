package com.rumaruka.scp.init;

import com.rumaruka.scp.common.potion.PotionCrystal;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class SCPPotions {
    public static  Potion crystal;
    public static void initPotion(){

    crystal = new PotionCrystal("effect.crystal",true,14804975);
    }

    public static void InGamePotionReg(){
        registerPotion(crystal);
    }

    public static void registerPotion(Potion p){
        ForgeRegistries.POTIONS.register(p);
    }
}
