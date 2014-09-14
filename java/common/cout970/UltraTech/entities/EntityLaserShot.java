package common.cout970.UltraTech.entities;

import common.cout970.UltraTech.managers.BlockManager;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLaserShot extends EntityUT implements IProjectile{

	public int damage = 2;
	public int count = 0;
	
	public EntityLaserShot(World w) {
		super(w);
		setSize(0.5F, 0.5F);
	}

	@Override
	public void setThrowableHeading(double x, double y,double z, float rY, float rF) {
		
	}

	public void setDamage(int i) {
		damage = i;
	}
	
	public void onUpdate()
    {
        super.onUpdate();
        if(worldObj.isRemote)return;
        Block block = this.worldObj.getBlock((int)posX, (int)posY, (int)posZ);

        if (block.getMaterial() != Material.air){
        	
            block.setBlockBoundsBasedOnState(this.worldObj, (int)posX, (int)posY, (int)posZ);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, (int)posX, (int)posY, (int)posZ);
            
            if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ))){
                setDead();
            }
        }
        
        for(Object o : worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(posX-1, posY-1, posZ-1, posX+1, posY+1, posZ+1))){
        	if(o instanceof EntityLivingBase){
        		EntityLivingBase e = (EntityLivingBase) o;
        		e.attackEntityFrom(DamageSource.generic, damage);
        		setDead();
        		return;
        	}
        }
        
//        worldObj.setBlock((int)posX, (int)posY, (int)posZ, BlockManager.DiamondGlass);
        moveEntity(motionX, motionY, motionZ);
        count++;
        if(count > 40)this.attackEntityFrom(DamageSource.generic, 10);
    }

}
