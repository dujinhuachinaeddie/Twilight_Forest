package twilightforest.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTFSlimeProjectile extends EntityThrowable {

	public EntityTFSlimeProjectile(World par1World) {
		super(par1World);
	}

	public EntityTFSlimeProjectile(World par1World, EntityLivingBase par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
        makeTrail();
	}

	@Override
    protected float getGravityVelocity()
    {
		return 0.006F;
    }

	private void makeTrail() {
		for (int i = 0; i < 2; i++) {
			double dx = posX + 0.5 * (rand.nextDouble() - rand.nextDouble()); 
			double dy = posY + 0.5 * (rand.nextDouble() - rand.nextDouble()); 
			double dz = posZ + 0.5 * (rand.nextDouble() - rand.nextDouble()); 
			world.spawnParticle(EnumParticleTypes.SLIME, dx, dy, dz, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
    	super.attackEntityFrom(damagesource, i);
		die();
        return true;
     }

	@Override
	protected void onImpact(RayTraceResult target) {
		// only damage living things
		if (!world.isRemote && target.entityHit instanceof EntityLivingBase)
		{
			target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 8);
			// TODO: damage armor?
		}

		die();
	}

	private void die() {
		if (!this.world.isRemote)
		{
			this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
			this.setDead();
		} else
		{
			for (int i = 0; i < 8; ++i)
			{
				this.world.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, rand.nextGaussian() * 0.05D, rand.nextDouble() * 0.2D, rand.nextGaussian() * 0.05D);
			}
		}
	}


}
