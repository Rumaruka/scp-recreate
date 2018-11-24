package com.rumaruka.scp.common.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class SCPConfig {
    public static Configuration cfg;
    public static int BLINK_DW = 31;
    public static boolean BLINK_ENABLED = false;

    public static void initCFG(File f){
        cfg = new Configuration(f);
        syncConfig();

    }

    public static void syncConfig() {


        BLINK_DW = cfg.getInt("Watcher","blink",BLINK_DW,19,31,"1");
        BLINK_ENABLED= cfg.getBoolean("Enable","blink",BLINK_ENABLED,"2");



        if(cfg.hasChanged()){
            cfg.save();
        }
    }
    public static Configuration getConfig()
      {
           return cfg;
       }

}
