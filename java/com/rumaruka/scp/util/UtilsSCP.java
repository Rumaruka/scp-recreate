package com.rumaruka.scp.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UtilsSCP {

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(path);
    }

    public static ResourceLocation getResource(String mod, String path) {
        return new ResourceLocation(mod, path);
    }
    public static final void breakBlockWithParticles(World world, BlockPos pos, int meta) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock().isAir(state, world, pos))
            return;
        world.setBlockToAir(pos);
        doBreakParticles(world, pos, state.getBlock(), meta);
    }

    public static final void doBreakParticles(World world, BlockPos pos, Block block, int meta) {
        world.playEvent(null, 2001, pos, Block.getIdFromBlock(block) + (meta << 12));
    }
}
