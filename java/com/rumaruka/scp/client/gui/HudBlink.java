package com.rumaruka.scp.client.gui;

import com.rumaruka.scp.util.Globals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class HudBlink extends Gui {

    protected static final ResourceLocation blink = new ResourceLocation("scp:textures/misc/blink.png");
    protected static final ResourceLocation gasMask = new ResourceLocation("scp:textures/misc/gasmask.png");
    private static final ResourceLocation icons = new ResourceLocation("scp:textures/gui/icons.png");
    private static Minecraft mc = Minecraft.getMinecraft();

    @SideOnly(Side.CLIENT)
    private static void renderGasMaskBlur(double width, double height)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        mc.getTextureManager().bindTexture(gasMask);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        tessellator.draw();
        bufferbuilder.pos(0.0D,(double) height, -90.0D);
        bufferbuilder.pos(width, height, -90.0D);
        bufferbuilder.pos(width, 0.0D, -90.0D);
        bufferbuilder.pos(0.0D, 0.0D, -90.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    private static void renderBlink(double width, double height)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        mc.getTextureManager().bindTexture(blink);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        tessellator.draw();
        bufferbuilder.pos(0.0D, height, -90.0D);
        bufferbuilder.pos(width, height, -90.0D);
        bufferbuilder.pos(width, 0.0D, -90.0D);
        bufferbuilder.pos(0.0D, 0.0D, -90.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @SubscribeEvent(priority= EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent event)
    {
        if ((event.isCancelable()) || (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE)) {
            return;
        }

        int width = event.getResolution().getScaledWidth();
        int height = event.getResolution().getScaledHeight();

        int k2 = height - 20;
        int blink = Globals.Blink;
        int i4 = width / 2 + 91;
        int bar = MathHelper.ceil((31 + 2) * 10.0D / 400);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        mc.getTextureManager().bindTexture(icons);
        drawTexturedModalRect(i4 - 281, k2 - 1, 0, 18, 83, 11);
        drawTexturedModalRect(i4 - 301, k2 - 1, 83, 18, 17, 11);
        for (int i = 0; i < 10; i++) {
            if (i < bar) {
                drawTexturedModalRect(i4 + i * 8 - 280, k2, 100, 18, 9, 9);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onRenderPumpkin(RenderGameOverlayEvent event)
    {
        double width = event.getResolution().getScaledWidth();
        double height = event.getResolution().getScaledHeight();
        int blink = Globals.Blink;
        if ((blink >= 0) && (blink <= 10)) {
            renderBlink(width, height);
        }
    }
}
