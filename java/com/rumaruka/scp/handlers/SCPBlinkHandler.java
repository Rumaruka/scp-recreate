package com.rumaruka.scp.handlers;

import com.rumaruka.scp.util.Globals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Int;

import static com.rumaruka.scp.util.Globals.*;

public class SCPBlinkHandler   {

  public static int blink = 5;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onKeyPressed(InputEvent.KeyInputEvent e){
        EntityPlayer player = Minecraft.getMinecraft().player;

        if(player!=null){
            if(SCPKeys.blink.isPressed()){
                Minecraft.getMinecraft().player.sendMessage( new TextComponentString(I18n.format("blinking")));
            }
            if(SCPKeys.activeBlink.isPressed()){
                Minecraft.getMinecraft().player.sendChatMessage( I18n.format("blink.enable"));

               }else {

                Minecraft.getMinecraft().player.sendMessage( new TextComponentString(I18n.format("blink.disable")));
               }
            }
        }





    @SubscribeEvent
    public void onPlayerUpdate(TickEvent.PlayerTickEvent e){
        if(e.player != null){
            EntityPlayer player = e.player;
            EntityDataManager data = player.getDataManager();
            if(data.get(BLINKING)==Integer.valueOf(Globals.TRUE)){
                if(player.isEntityAlive()){

                    int blink = data.get(BLINK);
                    data.set(BLINK, Integer.valueOf(blink-2));
                    if(data.get(BLINK)<=Integer.valueOf(0)){
                        data.set(BLINK,Integer.valueOf(Globals.MAX_BLINK));
                    }
                }

            }
        }

    }


}
