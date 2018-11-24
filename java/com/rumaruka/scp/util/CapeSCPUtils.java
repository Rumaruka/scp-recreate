package com.rumaruka.scp.util;

import com.mojang.authlib.GameProfile;
import com.rumaruka.scp.core.LibMisc;
import com.sun.javafx.scene.control.skin.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CapeSCPUtils {



    private static ResourceLocation SCP_CAPES = UtilsSCP.getResource(LibMisc.MODID + ":textures/scpcape.png");


    public static ResourceLocation getCape(GameProfile profile) {
            if(Loader.isModLoaded(LibMisc.MODID)){
                return SCP_CAPES;
            }

            else
                return null;
    }
}
