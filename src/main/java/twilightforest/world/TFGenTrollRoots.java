package twilightforest.world;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import twilightforest.block.BlockTFPlant;
import twilightforest.block.BlockTFTrollRoot;
import twilightforest.block.TFBlocks;



public class TFGenTrollRoots extends TFGenerator
{
    @Override
    public boolean generate(World par1World, Random par2Random, BlockPos pos)
    {
        int copyX = pos.getX();
        int copyZ = pos.getZ();
        
        for (; pos.getY() > 5; pos = pos.down())
        {
            if (par1World.isAirBlock(pos) && BlockTFTrollRoot.canPlaceRootBelow(par1World, pos.up()) && par2Random.nextInt(6) > 0)
            {
            	if (par2Random.nextInt(10) == 0) {
                    par1World.setBlockState(pos, TFBlocks.unripeTrollBer.getDefaultState());
            	} else {
                    par1World.setBlockState(pos, TFBlocks.trollVidr.getDefaultState());
            	}
            }
            else
            {
                pos = new BlockPos(
                        copyX + par2Random.nextInt(4) - par2Random.nextInt(4),
                        pos.getY(),
                        copyZ + par2Random.nextInt(4) - par2Random.nextInt(4)
                );
            }
        }

        return true;
    }
}
