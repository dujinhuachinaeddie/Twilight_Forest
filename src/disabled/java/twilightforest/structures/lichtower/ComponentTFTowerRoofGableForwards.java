package twilightforest.structures.lichtower;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;


public class ComponentTFTowerRoofGableForwards extends ComponentTFTowerRoof {

	public ComponentTFTowerRoofGableForwards() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComponentTFTowerRoofGableForwards(int i, ComponentTFTowerWing wing) {
		super(i, wing);
		
		// same facing
		this.setCoordBaseMode(wing.getCoordBaseMode());

		this.size = wing.size + 2; // assuming only square towers and roofs right now.
		this.height = size;
		
		// just hang out at the very top of the tower
		this.makeAttachedOverhangBB(wing);

	}

	/**
	 * Makes a pointy roof out of stuff
	 */
	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
		int slabMeta = 2;
		int slopeChange = slopeChangeForSize(size);
		for (int y = 0; y <= height; y++) {
			int min, max;
			if (y < slopeChange) {
				min = y;
				max = size - y - 1;
			}
			else {
				min = (y + slopeChange) / 2;
				max = size - ((y + slopeChange) / 2) - 1;
			}
			for (int x = 0; x <= size - 2; x++) {
				for (int z = min; z <= max; z++) {
					if (z == min || z == max) {
						setBlockState(world, Blocks.PLANKS, slabMeta, x, y, z, sbb);
					}
					else if (x < size - 2) {
						setBlockState(world, Blocks.PLANKS, slabMeta, x, y, z, sbb);
					}
				}
			}
		}   
		
		// put on the little figurehead-like "cap"
		
		// where is even the top of our roof?
		int top = (size + 1) - slopeChange;
		int zMid = size / 2;
		
		setBlockState(world, Blocks.WOODEN_SLAB, slabMeta | 0xA, size - 1, top - 1, zMid, sbb);
		setBlockState(world, Blocks.WOODEN_SLAB, slabMeta, 0, top, zMid, sbb);
		setBlockState(world, Blocks.WOODEN_SLAB, slabMeta, size - 3, top, zMid, sbb);
		setBlockState(world, Blocks.PLANKS, slabMeta, size - 2, top, zMid, sbb);
		setBlockState(world, Blocks.PLANKS, slabMeta, size - 1, top, zMid, sbb);
		setBlockState(world, Blocks.PLANKS, slabMeta, size - 1, top + 1, zMid, sbb);
		
		return true;
	}

	
	public int slopeChangeForSize(int pSize) {
		if (size > 10) {
			return 3;
		}
		else if (size > 6) {
			return 2;
		}
		else {
			return 1;
		}
	}
}
