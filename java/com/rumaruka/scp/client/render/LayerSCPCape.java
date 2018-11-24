package com.rumaruka.scp.client.render;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.rumaruka.scp.util.CapeSCPUtils;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.Map;

public class LayerSCPCape implements LayerRenderer<AbstractClientPlayer> {

    private final Field playerInfoField = ReflectionHelper.findField(AbstractClientPlayer.class, "playerInfo", "field_175157_a");
    private final Field playerTexturesField = ReflectionHelper.findField(NetworkPlayerInfo.class, "playerTextures", "field_187107_a");

    public LayerSCPCape() {
        playerInfoField.setAccessible(true);
        playerTexturesField.setAccessible(true);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doRenderLayer(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entity.getLocationCape() != null)
            return;
        ResourceLocation cape = CapeSCPUtils.getCape(entity.getGameProfile());
        if (cape == null)
            return;

        try {
            NetworkPlayerInfo playerInfo = (NetworkPlayerInfo) playerInfoField.get(entity);
            if (playerInfo != null) {
                Map<Type, ResourceLocation> playerTextures = (Map<Type, ResourceLocation>) playerTexturesField.get(playerInfo);
                playerTextures.put(Type.CAPE, cape);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}