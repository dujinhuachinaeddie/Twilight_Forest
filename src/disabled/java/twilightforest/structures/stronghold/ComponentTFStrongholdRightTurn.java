package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFStrongholdRightTurn extends StructureTFStrongholdComponent {


	public ComponentTFStrongholdRightTurn() {}

	public ComponentTFStrongholdRightTurn(int i, EnumFacing facing, int x, int y, int z) {
		super(i, facing, x, y, z);
	}

	@Override
	public StructureBoundingBox generateBoundingBox(EnumFacing facing, int x, int y, int z)
	{
		return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 9, 7, 9, facing);
	}
	
	@Override
	public void buildComponent(StructureComponent parent, List list, Random random) {
		super.buildComponent(parent, list, random);
		
		// entrance
		this.addDoor(4, 1, 0);

		// make a random component to the right
		addNewComponent(parent, list, random, 1, -1, 1, 4);
		
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
		placeStrongholdWalls(world, sbb, 0, 0, 0, 8, 6, 8, rand, deco.randomBlocks);

		// clear inside
		fillWithAir(world, sbb, 1, 1, 1, 7, 5, 7);
		
//		// entrance doorway
//		placeDoorwayAt(world, rand, 2, 4, 1, 0, sbb);
//		
//		// right turn doorway
//		placeDoorwayAt(world, rand, 1, 0, 1, 4, sbb);
		
		// statue
		placeCornerStatue(world, 6, 1, 6, 3, sbb);
		
		// doors
		placeDoors(world, rand, sbb);
		
		return true;
	}


}
