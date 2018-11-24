package com.rumaruka.scp.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;


public class BlockSteel extends Block {
    public BlockSteel(Material mat) {
        super(mat);

        setHardness(3.0f);
        setSoundType(SoundType.METAL);
        setResistance(20.0f);
    }
}
