package com.rumaruka.scp.common.potion;

import com.rumaruka.scp.init.SCPPotions;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionTypeRegistry {
    public static final PotionType CRYSTAL = new PotionType("crystal", new PotionEffect[] {new PotionEffect(SCPPotions.crystal, 2400)}).setRegistryName("crystal");

    public static void registerPotionTypes(){
        regPT(CRYSTAL);
    }

    public static void regPT(PotionType pt){
        ForgeRegistries.POTION_TYPES.register(pt);
    }
}
