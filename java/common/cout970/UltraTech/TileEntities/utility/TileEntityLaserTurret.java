package common.cout970.UltraTech.TileEntities.utility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import common.cout970.UltraTech.entities.EntityLaserShot;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.VecUT;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.util.power.cables.CableInterfaceLaserTurret;
import common.cout970.UltraTech.util.power.cables.CableInterfaceSolarPanel;

public class TileEntityLaserTurret extends Machine implements IUpdatedEntity{

	//for client
	public float curAngleY;//360 degrees
	public float curAngleX;//-10...10 degrees
	
	//for server
	public VecUT target = null;
	
	public TileEntityLaserTurret() {
		super(MachineData.Laser_Turret);
		this.cond.setCable(new CableInterfaceLaserTurret(this));
	}

	public void updateEntity(){
		super.updateEntity();
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord-20, yCoord-2, zCoord-20, xCoord+20, yCoord+2, zCoord+20);
		boolean found = false;
		for(Object o : worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb)){
			if(o instanceof EntityLivingBase && !(o instanceof EntityPlayer)){
				EntityLivingBase e = (EntityLivingBase) o;
				VecUT entPos = new VecUT(e.posX, e.posY, e.posZ);
				if(VecUT.getDistance(new VecUT(this), entPos) <= 20){
					target = entPos;
					found = true;
					break;
				}
			}
		}
		if(!found)target = null;
		if(!worldObj.isRemote && worldObj.getTotalWorldTime() % 10 == 0 && target != null){
			EntityLaserShot e = new EntityLaserShot(worldObj);
			VecUT pos = VecUT.unitVec(VecUT.sumeVec(target, VecUT.inverseVec(new VecUT(xCoord+0.5, yCoord+0.5, zCoord+0.5))));
			e.renderDistanceWeight = 30.0D;
			e.setLocationAndAngles(xCoord+0.5+pos.x, yCoord+0.5+pos.y, zCoord+0.5+pos.z, 90, 90);
			pos = VecUT.VecForScalar(pos, 4);
			e.motionX = pos.x;
			e.motionY = pos.y+0.3;
			e.motionZ = pos.z;
			e.setDamage(10);
			worldObj.spawnEntityInWorld(e);
		}
	}


	@Override
	public void onNeigUpdate() {		
	}
}
