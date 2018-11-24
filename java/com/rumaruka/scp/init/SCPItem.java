package com.rumaruka.scp.init;


import com.google.common.collect.Sets;
import com.rumaruka.scp.client.creativetabs.SCPTab;
import com.rumaruka.scp.common.items.*;
import com.rumaruka.scp.common.items.scp.ItemSCP458;
import com.rumaruka.scp.common.registry.DocumentRegistry;
import com.rumaruka.scp.core.LibMisc;
import com.rumaruka.scp.util.EnumHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Set;

public class SCPItem {
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH,Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE,Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);


    public static Item.ToolMaterial tool1023;
    public static Item scp1023arc;
    public static ItemArmor.ArmorMaterial armorClassD;
    public static Item gas_mask;
    public static Item classd_shirt;
    public static Item classd_pants;
    public static Item classd_boots;
    public static ItemArmor.ArmorMaterial armorSwat;
    public static Item swat_helmet;
    public static Item swat_shirt;
    public static Item swat_pants;
    public static Item swat_shoes;
    public static Item document;
    public static Item pearl;
    public static Item circuit;
    public static Item scp0143_sword;
    public static Item scp0143_pickaxe;
    public static Item wrench;
    public static Item wrench_godly;
    public static Item scp173;
    public static Item scp15;
    public static Item bucket;
    public static Item keycard;
    public static Item scp458;
    public static Item scp063;
    public static Item scp000j;
    public static Item scp500;
    public static void init(){
        initMaterials();
        registerItems();
        initCrafting();
    }



    private static void initMaterials()
    {
        tool1023 = EnumHelper.addToolMaterial("1023",0,-1,0.0f,99996.0f,0);

    }
    private static void registerItems()
    {

        scp1023arc = new ItemSCPKnife(tool1023,"scp1023arc");
        document = new ItemSCPDocument("document");
        keycard = new ItemSCPKeycard("keycard");
        scp458 = new ItemSCP458( "scp458",2, 3.0F, false).setAlwaysEdible();
        scp063 = new ItemSCP063("scp063",1.0f,1.0f, Item.ToolMaterial.DIAMOND,EFFECTIVE_ON);
        scp500 = new ItemSCP500("scp500");



    }
    public static void initCrafting() {

    }


    public static void inGameRegistry() {

        registerItem(scp1023arc);
        registerItem(document);
        registerItem(keycard);
        registerItem(scp458);
        registerItem(scp063);
        registerItem(scp500);



    }



    public static void renderingItems(){

        renderItems(scp1023arc);
        renderItems(scp458);
        renderItems(scp063);
        renderItems(scp500);

        for (int i=0;i< EnumHandler.SCPType.values().length;i++){
            registerRender(document,i,"document_"+ EnumHandler.SCPType.values()[i].getName());
        }
        for (int i=0;i< EnumHandler.SCPType.values().length;i++){
            registerRender(keycard,i,"keycard_"+ EnumHandler.KeyCardType.values()[i].getName());
        }
        for (int i = 0; i< DocumentRegistry.scpList.size(); i++){
            registerRender(pearl,i,"pearl_"+ DocumentRegistry.scpList.get(i).name);
        }
    }





    @Deprecated
    public static void registerItem(Item item /*, String name*/)
    {
        item.setCreativeTab(SCPTab.scpTab);
        ForgeRegistries.ITEMS.register(item);

        /*
        if (item.getRegistryName() == null && Strings.isNullOrEmpty(name))
            throw new IllegalArgumentException("Attempted to register a item with no name: " + item);
        if (item.getRegistryName() != null && !item.getRegistryName().toString().equals(name))
           throw new IllegalArgumentException("Attempted to register a item with conflicting names. Old: " + item.getRegistryName() + " New: " + name);
        ForgeRegistries.ITEMS.register(item.getRegistryName() == null ? item.setRegistryName(name) : item);*/
    }




    public static void renderItems(Item i){

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(
                LibMisc.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));

    }

    public static void registerRender(Item item, int meta, String fileName) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta,
                new ModelResourceLocation(new ResourceLocation(LibMisc.MODID, fileName), "inventory"));
    }
    }

