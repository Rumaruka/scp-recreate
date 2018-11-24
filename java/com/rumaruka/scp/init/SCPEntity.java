package com.rumaruka.scp.init;

import com.rumaruka.scp.common.registry.DocumentRegistry;
import com.rumaruka.scp.scp;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static com.rumaruka.scp.scp.*;

public class SCPEntity {

    public static void init(){

    }
    public static void registerSCP(ResourceLocation regName, Class<? extends Entity> scp, String name, int idSCP, int back, int fore, int level) {
        if ((back != -1) && (fore != -1)) {
            EntityRegistry.registerModEntity(regName, scp, name, idSCP, instance, back, fore, true);

            if (level >= 0) {
                DocumentRegistry.registerDocument(name, level);
            }
            EntityRegistry.registerModEntity(regName, scp, name, idSCP + 300, instance, 80, 3, true);

        }
    }
}
