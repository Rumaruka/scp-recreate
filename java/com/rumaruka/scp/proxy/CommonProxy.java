package com.rumaruka.scp.proxy;

import com.rumaruka.scp.client.EnumGui;
import com.rumaruka.scp.client.gui.GuiSCP914;
import com.rumaruka.scp.common.inventory.ContainerSCP914;
import com.rumaruka.scp.common.tileentity.TileEntitySCP914;
import com.rumaruka.scp.handlers.SCPKeys;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CommonProxy implements IGuiHandler {
    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap();

    public void init() {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID== EnumGui.SCP914.ordinal()){
            TileEntitySCP914 scp914= (TileEntitySCP914) world.getTileEntity(new BlockPos(x,y,z));
          return new ContainerSCP914(player.inventory,scp914);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID== EnumGui.SCP914.ordinal()){
            TileEntitySCP914 scp914= (TileEntitySCP914) world.getTileEntity(new BlockPos(x,y,z));
            return new GuiSCP914(player.inventory,scp914);
        }

        return null;
    }


    public static NBTTagCompound getEntityData(String name) {
        return (NBTTagCompound) extendedEntityData.remove(name);
    }


    public void registerKey() {

    }

    public void registerRenderers() {
    }
    public void registerBIRenderers() {
    }
    public void registerModelBakeryVariants(){}
}