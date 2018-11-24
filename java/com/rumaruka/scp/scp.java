package com.rumaruka.scp;






import com.rumaruka.scp.client.gui.HudBlink;

import com.rumaruka.scp.common.command.CommandJson;
import com.rumaruka.scp.common.config.SCPConfig;
import com.rumaruka.scp.common.entity.player.ExtendedPlayerSCP;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.core.LibProxy;
import com.rumaruka.scp.core.capabilities.ExtendedPlayerSCPStorage;
import com.rumaruka.scp.core.capabilities.IExtendedEntity;
import com.rumaruka.scp.core.capabilities.IExtendedPlayer;
import com.rumaruka.scp.handlers.SCPEventHandler;
import com.rumaruka.scp.init.SCPBlock;
import com.rumaruka.scp.init.SCPItem;
import com.rumaruka.scp.proxy.CommonProxy;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.sql.Wrapper;

@Mod(modid = LibMisc.MODID, name = LibMisc.NAME,version = LibMisc.VERSION,guiFactory = "com.rumaruka.scp.client.gui.SCPGuiFactory")
public class scp {

    @SidedProxy(clientSide = LibProxy.CLIENT_PROXY,serverSide = LibProxy.SERVER_PROXY)
    public static CommonProxy proxy;


    @Mod.Instance
    public static scp instance;


    @CapabilityInject(IExtendedPlayer.class)
    public static  Capability<IExtendedPlayer>  EXTENDED_PLAYER =  null;

    @CapabilityInject(IExtendedEntity.class)
    public static final Capability<IExtendedEntity>  EXTENDED_ENTITY  = null;



    @SideOnly(Side.CLIENT)
    public static boolean hasCape(AbstractClientPlayer player, boolean result)
    {
        return player.getLocationCape() != null;
    }
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        NetworkRegistry.INSTANCE.registerGuiHandler(this,proxy);

        SCPBlock.init();
        SCPBlock.inGameBlockRegistry();

        SCPItem.init();
        SCPItem.inGameRegistry();

        SCPConfig.initCFG(event.getSuggestedConfigurationFile());

        SCPEventHandler.init();
        MinecraftForge.EVENT_BUS.register(new EventListener());

        CapabilityManager.INSTANCE.register(IExtendedPlayer.class,new ExtendedPlayerSCPStorage(),ExtendedPlayerSCP.class);

        proxy.registerModelBakeryVariants();
        proxy.registerKey();


    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
        proxy.registerModelBakeryVariants();


        MinecraftForge.EVENT_BUS.register(new HudBlink());

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.registerModelBakeryVariants();
        proxy.registerBIRenderers();
        SCPBlock.registerCraft();
        SCPItem.initCrafting();



    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandJson());
    }

    public static void syncConfig(Configuration config)
    {
        if (config.hasChanged()) {
            config.save();
        }
    }
}
