package com.rumaruka.scp.init;

import com.google.common.base.Strings;
import com.google.common.collect.ObjectArrays;
import com.rumaruka.scp.client.creativetabs.SCPTab;
import com.rumaruka.scp.common.blocks.BlockBlood;
import com.rumaruka.scp.common.blocks.BlockSCP914;
import com.rumaruka.scp.common.blocks.BlockSteel;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.util.RecipesUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Constructor;

public class SCPBlock {


        public static Block blood;
        public static Block reinforced_steel;
        public static Block document_crafter;
        public static Block scp015;
        public static Block scp914;
        public static Block machinery;
        public static Block toilet;
        public static Block acid;
        public static Block flesh;
        public static Block bone;
        public static Block key_slot_1;
        public static Block key_slot_2;
        public static Block key_slot_3;
        public static Block key_slot_omni;
        public static Block desk_wood;
        public static Block desk_stone;
        public static Block chair_wood;
        public static Block chair_stone;
        public static Block light_bulb;
        public static Fluid acidFluid;
    public static void init()
    {
        registerTiles();
        registerTileEntities();

    }
    private static void registerTiles()
    {
        reinforced_steel = new BlockSteel( Material.IRON).setRegistryName("reinforced_steel").setUnlocalizedName("reinforced_steel").setCreativeTab(SCPTab.scpTab);
        scp914 = new BlockSCP914(Material.IRON).setRegistryName("scp914").setUnlocalizedName("scp914").setCreativeTab(SCPTab.scpTab);
        blood = new BlockBlood(Material.CLOTH).setRegistryName("blood").setUnlocalizedName("blood").setCreativeTab(SCPTab.scpTab);
    }

    private static void registerTileEntities()
    {

    }

    public static void registerCraft()
    {
        RecipesUtil.addShapedRecipe(new ItemStack(SCPBlock.reinforced_steel,1,0), new Object[]{
                " a ",
                "a#a",
                " a ",

                '#',  new ItemStack(Blocks.IRON_BLOCK),
                'a',  new ItemStack(Items.IRON_INGOT)

        });
    }


    public static void inGameBlockRegistry() {
        registerBlock(reinforced_steel);
        registerBlock(scp914);

    }
    public static void renderingBlock(){
        registerRender(reinforced_steel);
        registerRender(scp914);
    }


    @Deprecated
    public static Block registerBlock(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        return block;
    }
    @Deprecated
    public static Block registerBlock(Block block, String name)
    {
        if (block.getRegistryName() == null && Strings.isNullOrEmpty(name))
            throw new IllegalArgumentException("Attempted to register a Block with no name: " + block);
        if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name))
            throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
        return registerBlock(block.getRegistryName() != null ? block : block.setRegistryName(name));
    }
    @Deprecated
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, Object... itemCtorArgs)
    {
        if (Strings.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("Attempted to register a block with no name: " + block);
        }
        if (Loader.instance().isInState(LoaderState.CONSTRUCTING))
        {
            FMLLog.warning("The mod %s is attempting to register a block whilst it it being constructed. This is bad modding practice - please use a proper mod lifecycle event.", Loader.instance().activeModContainer());
        }
        try
        {
            assert block != null : "registerBlock: block cannot be null";
            if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name))
                throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
            ItemBlock i = null;
            if (itemclass != null)
            {
                Class<?>[] ctorArgClasses = new Class<?>[itemCtorArgs.length + 1];
                ctorArgClasses[0] = Block.class;
                for (int idx = 1; idx < ctorArgClasses.length; idx++)
                {
                    ctorArgClasses[idx] = itemCtorArgs[idx - 1].getClass();
                }
                Constructor<? extends ItemBlock> itemCtor = itemclass.getConstructor(ctorArgClasses);
                i = itemCtor.newInstance(ObjectArrays.concat(block, itemCtorArgs));
            }
            // block registration has to happen first
            ForgeRegistries.BLOCKS.register(block.getRegistryName() == null ? block.setRegistryName(name) : block);
            if (i != null)
                ForgeRegistries.ITEMS.register(i.setRegistryName(name));
            return block;
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "Caught an exception during block registration");
            throw new LoaderException(e);
        }
    }
    public static void registerRender(Block block)
    {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(LibMisc.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
