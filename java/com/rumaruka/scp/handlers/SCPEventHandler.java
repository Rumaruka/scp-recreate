package com.rumaruka.scp.handlers;



import net.minecraftforge.fml.common.FMLCommonHandler;


public class SCPEventHandler {
    public static void init() {

        FMLCommonHandler.instance().bus().register(new SCPBlinkHandler());

    }

    private static void registerEntityEventHandler() {}
}
