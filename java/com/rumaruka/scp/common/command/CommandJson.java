package com.rumaruka.scp.common.command;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rumaruka.scp.util.SCPCell;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandJson extends CommandBase {

   private List<String> aliases;
    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);
   public CommandJson(){
       this.aliases=new ArrayList<>();
       this.aliases.add("json");
       this.aliases.add("js");
   }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getName() {
        return "json";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "json <x> <y> <z> <width> <height> <depth> <name>";
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
       if(args.length==7){
           toJson(sender,args);
       }else if(args.length==1){
           toStructure(sender,args[0]);

       }else {
           throw new CommandException(getUsage(sender),new Object[0]);
       }

    }

    private void toStructure(ICommandSender sender, String json) {

        File f = new File(json);
        Gson g = new Gson();
        World w = sender.getEntityWorld();
        int x = sender.getPosition().getX();
        int y = sender.getPosition().getY();
        int z = sender.getPosition().getZ();
        try {
            BufferedReader reader = Files.newReader(f, Charsets.UTF_8);
            SCPCell cell = g.fromJson(reader, SCPCell.class);
            int xCoord = 0;
            int yCoord = 0;
            int zCoord = 0;
            for (int xstart = 0; xstart < cell.content.length; xstart++) {
                for (int ystart = 0; ystart < cell.content[xstart].length; ystart++) {
                    for (int zstart = 0; zstart < cell.content[xstart][ystart].length; zstart++) {
                        String uuid = (String) cell.blocks.get(cell.content[xstart][ystart][zstart]);
                        String[] parts = uuid.split(":");
                        String domain = parts[0];
                        String name =parts[1];
                        int meta = Integer.valueOf(parts[2]).intValue();
                        Block b = Block.REGISTRY.getObject(new ResourceLocation(domain,name));
                        IBlockState state = w.getBlockState(new BlockPos(x+xstart,y+ystart,z+zstart));

                        w.setBlockState(new BlockPos(x+xstart,y+ystart,z+zstart), (IBlockState) b);
                        w.setBlockState(new BlockPos(x+xstart,y+ystart,z+zstart), state.withProperty(LEVEL,meta),3);

                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void  toJson(ICommandSender sender, String[] argv){
    int[] start = new int[3];
    start[0] = Integer.valueOf(argv[0]).intValue();
    start[1] = Integer.valueOf(argv[1]).intValue();
    start[2] = Integer.valueOf(argv[2]).intValue();
    int xsize = Integer.valueOf(argv[3]).intValue();
    int ysize = Integer.valueOf(argv[4]).intValue();
    int zsize = Integer.valueOf(argv[5]).intValue();
    int[][][] content = new int[xsize][ysize][zsize];

    List blocks = new ArrayList<>();
    for(int z=start[2];z<start[2]+zsize;z++){
        for(int y=start[1];y<start[1]+ysize;y++){
            for(int x=start[0];x<start[0]+xsize;x++){

                String uuid = getBlockUUID(sender.getEntityWorld(),new BlockPos(x,y,z));
                int index = blocks.indexOf(uuid);
                if(index<0){
                    blocks.add(uuid);
                }
                content[(x - start[0])][(y - start[1])][(z - start[2])]=blocks.indexOf(uuid);

            }
        }
    }
    SCPCell cell = new SCPCell();
    cell.setStart(start[0],start[1],start[2]);
    cell.setBlocks(blocks);
    cell.setContent(content);
    Gson gson = new GsonBuilder().create();
    String s = gson.toJson(cell);
    File f = new File(argv[6]+".json");
    BufferedWriter writer = null;
    try {
        writer= Files.newWriter(f, Charsets.UTF_8);
        writer.write(s);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        IOUtils.closeQuietly(writer);
    }

}
    private String getBlockUUID(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        ResourceLocation name = state.getBlock().getRegistryName();
        int meta =  state.getBlock().getMetaFromState(state);
        return name + ":" + meta;
    }
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return false;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
