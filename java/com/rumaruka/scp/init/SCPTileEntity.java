package com.rumaruka.scp.init;

import com.rumaruka.scp.common.tileentity.TileEntitySCP914;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SCPTileEntity {

    public static void loadTileEntity(){
        GameRegistry.registerTileEntity(TileEntitySCP914.class,"TileEntitySCP914");
    }
}
