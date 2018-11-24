package com.rumaruka.scp.client.gui;


import com.rumaruka.scp.common.inventory.ContainerSCP914;
import com.rumaruka.scp.common.tileentity.TileEntitySCP914;
import com.rumaruka.scp.core.LibMisc;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiSCP914 extends GuiContainer {
    private static final ResourceLocation SCP914_GUI = new ResourceLocation(LibMisc.MODID,"textures/gui/0914.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
        private TileEntitySCP914 scp014Inventory;
    public GuiSCP914(InventoryPlayer inventoryPlayer, TileEntitySCP914 scp914) {
        super(new ContainerSCP914(inventoryPlayer,scp914));
        this.playerInventory=inventoryPlayer;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(SCP914_GUI);
        int j = (this.width-this.xSize)/2;
        int k = (this.height - this.ySize) / 2;
        int i1 = this.scp014Inventory.getCookProgressScaled(24);
        drawTexturedModalRect(j + 76, k + 40, 176, 14, i1 + 1, 16);

    }
}
