package twilightforest.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import twilightforest.entity.EntityTFRedcap;

public abstract class EntityAITFRedcapBase extends EntityAIBase
{
	protected final EntityTFRedcap entityObj;

	protected EntityAITFRedcapBase(EntityTFRedcap entity) {
		this.entityObj = entity;
	}

	/**
	 * Fairly straightforward.  Returns true in a 120 degree arc in front of the target's view.
	 */
	public boolean isTargetLookingAtMe(EntityLivingBase attackTarget) {
	    	// find angle of approach
	    	double dx = entityObj.posX - attackTarget.posX;
	    	double dz = entityObj.posZ - attackTarget.posZ;
	    	float angle = (float)((Math.atan2(dz, dx) * 180D) / Math.PI) - 90F;
	
	    	float difference = MathHelper.abs((attackTarget.rotationYaw - angle) % 360);

	    	return difference < 60 || difference > 300;
	    }

	public BlockPos findBlockTNTNearby(int range) {
	    BlockPos entityPos = new BlockPos(entityObj);
	    
	    for (int x = -range; x <= range; x++)
	    {
	        for (int y = -range; y <= range; y++)
	        {
	            for (int z = -range; z <= range; z++)
	            {
	            	if (entityObj.world.getBlockState(entityPos.add(x, y, z)).getBlock() == Blocks.TNT)
	            	{
	            		return entityPos.add(x, y, z);
	            	}
	            }
	        }
	    }
	    
	    return null;
	}

	public boolean isLitTNTNearby(int range)
	{
		AxisAlignedBB expandedBox = entityObj.getEntityBoundingBox().expand(range, range, range);
	    return !entityObj.world.getEntitiesWithinAABB(EntityTNTPrimed.class, expandedBox).isEmpty();
	}

}
