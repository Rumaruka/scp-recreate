package com.rumaruka.scp.common.potion;

import com.rumaruka.scp.core.LibMisc;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionCrystal extends Potion {
    public PotionCrystal(String name,boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName(name);
        setRegistryName(new ResourceLocation(LibMisc.MODID,name));
        setIconIndex(0,0);
    }
}
