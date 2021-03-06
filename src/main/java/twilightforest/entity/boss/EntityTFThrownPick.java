package twilightforest.entity.boss;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTFThrownPick extends EntityThrowable  {
	private static final float PROJECTILE_DAMAGE = 3;

	public EntityTFThrownPick(World par1World, EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
        this.setSize(0.5F, 0.5F);
	}

	public EntityTFThrownPick(World par1World) {
		super(par1World);
        this.setSize(0.5F, 0.5F);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
        if (result.entityHit instanceof EntityTFKnightPhantom)
        {
            return;
        }

        if (!world.isRemote)
        {
            if (result.entityHit != null)
            {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), PROJECTILE_DAMAGE);
            }

            setDead();
        } else
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
	}
	
    @Override
	public boolean canBeCollidedWith()
    {
        return true;
    }
    
    @Override
	public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.015F;
    }
}
