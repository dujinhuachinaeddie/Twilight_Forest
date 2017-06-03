package twilightforest.structures.minotaurmaze;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeRoomVault extends ComponentTFMazeRoom {
	public ComponentTFMazeRoomVault() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ComponentTFMazeRoomVault(int i, Random rand, int x, int y, int z) {
		super(i, rand, x, y, z);
		
		// specify a non-existant high spawn list value to stop actual monster spawns
		this.spawnListIndex = Integer.MAX_VALUE;
	}

	
	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
		// fill room with bricks
		fillWithBlocks(world, sbb, 0, 1, 0, 15, 4, 15, TFBlocks.mazestone, 3, AIR, false);
		fillWithBlocks(world, sbb, 0, 2, 0, 15, 3, 15, TFBlocks.mazestone, 1, AIR, false);

		// 4x4 room in the middle
		fillWithAir(world, sbb, 6, 2, 6, 9, 3, 9);
		
		// pressure plates, sand & tnt
		fillWithBlocks(world, sbb, 6, 2, 5, 9, 2, 5, Blocks.WOODEN_PRESSURE_PLATE, 0, AIR, false);
		fillWithBlocks(world, sbb, 6, 2, 10, 9, 2, 10, Blocks.WOODEN_PRESSURE_PLATE, 0, AIR, false);
		fillWithBlocks(world, sbb, 5, 2, 6, 5, 2, 9, Blocks.WOODEN_PRESSURE_PLATE, 0, AIR, false);
		fillWithBlocks(world, sbb, 10, 2, 6, 10, 2, 9, Blocks.WOODEN_PRESSURE_PLATE, 0, AIR, false);
		
		// unfair sand
		fillWithBlocks(world, sbb, 6, 4, 5, 9, 4, 5, Blocks.SAND, 0, AIR, false);
		fillWithBlocks(world, sbb, 6, 4, 10, 9, 4, 10, Blocks.SAND, 0, AIR, false);
		fillWithBlocks(world, sbb, 5, 4, 6, 5, 4, 9, Blocks.SAND, 0, AIR, false);
		fillWithBlocks(world, sbb, 10, 4, 6, 10, 4, 9, Blocks.SAND, 0, AIR, false);

		fillWithBlocks(world, sbb, 6, 0, 5, 9, 0, 5, Blocks.TNT, 0, AIR, false);
		fillWithBlocks(world, sbb, 6, 0, 10, 9, 0, 10, Blocks.TNT, 0, AIR, false);
		fillWithBlocks(world, sbb, 5, 0, 6, 5, 0, 9, Blocks.TNT, 0, AIR, false);
		fillWithBlocks(world, sbb, 10, 0, 6, 10, 0, 9, Blocks.TNT, 0, AIR, false);
		
		// LEWTZ!
		this.setBlockState(world, Blocks.CHEST, 0, 7, 2, 6, sbb);
		this.placeTreasureAtCurrentPosition(world, rand, 8, 2, 6, TFTreasure.labyrinth_vault, sbb);
		this.setBlockState(world, Blocks.CHEST, 0, 8, 2, 9, sbb);
		this.placeTreasureAtCurrentPosition(world, rand, 7, 2, 9, TFTreasure.labyrinth_vault, sbb);
		this.setBlockState(world, Blocks.CHEST, 0, 6, 2, 7, sbb);
		this.placeTreasureAtCurrentPosition(world, rand, 6, 2, 8, TFTreasure.labyrinth_vault, sbb);
		this.setBlockState(world, Blocks.CHEST, 0, 9, 2, 8, sbb);
		this.placeTreasureAtCurrentPosition(world, rand, 9, 2, 7, TFTreasure.labyrinth_vault, sbb);


		// mazebreaker!
		
		
		return true;
	}
}