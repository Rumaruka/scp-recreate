package com.rumaruka.scp.proxy;



import com.rumaruka.scp.client.render.LayerSCPCape;
import com.rumaruka.scp.common.registry.DocumentRegistry;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.handlers.SCPKeys;
import com.rumaruka.scp.init.SCPBlock;
import com.rumaruka.scp.init.SCPItem;
import com.rumaruka.scp.util.EnumHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;


public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
        render();
    }

    private void render() {

    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);

    }

    @Override
    public void registerModelBakeryVariants() {
        for (int i = 0; i < EnumHandler.SCPType.values().length; i++) {
            ModelBakery.registerItemVariants(SCPItem.document, new ResourceLocation(LibMisc.MODID, "document_"+ EnumHandler.SCPType.values()[i].getName()));
        }
        for (int i = 0; i < EnumHandler.KeyCardType.values().length; i++) {
            ModelBakery.registerItemVariants(SCPItem.document, new ResourceLocation(LibMisc.MODID, "keycard_"+ EnumHandler.KeyCardType.values()[i].getName()));
        }
        for (int i = 0; i < DocumentRegistry.scpList.size(); i++) {
            ModelBakery.registerItemVariants(SCPItem.pearl, new ResourceLocation(LibMisc.MODID, "pearl_"+ DocumentRegistry.scpList.get(i).name));
        }
    }
    @Override
    public void registerBIRenderers() {
        SCPBlock.renderingBlock();

        SCPItem.renderingItems();
        registerModelBakeryVariants();


        LayerSCPCape customCapeRenderer = new LayerSCPCape();
        for (RenderPlayer renderer : Minecraft.getMinecraft().getRenderManager().getSkinMap().values())
            renderer.addLayer(customCapeRenderer);


    }

    @Override
    public void registerKey() {
        FMLCommonHandler.instance().bus().register(new SCPKeys(Minecraft.getMinecraft()));
    }
}
