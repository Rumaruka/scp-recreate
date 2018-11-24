package com.rumaruka.scp.common.blocks;

import com.rumaruka.scp.client.creativetabs.SCPTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBlood extends Block {

    public BlockBlood(Material material){
        super(material);
        setCreativeTab(SCPTab.scpTab);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        byte b=0;
        float f = 0.0625f;
        return new AxisAlignedBB(pos.getX(),pos.getY(),pos.getZ(),pos.getZ(),pos.getY()+b*f,pos.getZ());
    }

}
