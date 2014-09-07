package common.cout970.UltraTech.items;

import java.util.List;
import java.util.Random;

import ultratech.api.power.interfaces.ISpeeded;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.util.LogHelper;

public class Flamethrower extends UT_Item{
	
	
	public Flamethrower(String name) {
		super(name);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World w, EntityPlayer p){
		Random r = w.rand;
		double x = p.posX;
		double y = p.posY;
		double z = p.posZ;
		double motionX = (double)(-MathHelper.sin(p.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI));
		double motionZ = (double)(MathHelper.cos(p.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI));
		double motionY = (double)(-MathHelper.sin(p.rotationPitch / 180.0F * (float)Math.PI));
		
		for(int i = 0; i < 50; i++){
			w.spawnParticle("flame", x, y-0.25, z, motionX+(0.5-r.nextFloat())*0.3, motionY+(0.5-r.nextFloat())*0.3, motionZ+(0.5-r.nextFloat())*0.3);
		}
		List entities = w.getEntitiesWithinAABBExcludingEntity(p, AxisAlignedBB.getBoundingBox(p.posX-10, p.posY-10, p.posZ-10, p.posX+10, p.posY+10, p.posZ+10));
		for(Object o : entities){
			if(o instanceof Entity){
				boolean isInRange = false;
				if(((Entity) o).getDistance(x+motionX*4, y+motionY*4, z+motionZ*4) < 2){
					isInRange = true;
				}else if(((Entity) o).getDistance(x+motionX*2, y+motionY*2, z+motionZ*2) < 1){
					isInRange = true;
				}else if(((Entity) o).getDistance(x+motionX, y+motionY, z+motionZ) < 0.5f){
					isInRange = true;
				}
				if(isInRange){
					if(o instanceof EntityPlayer){
						if(p.canAttackPlayer((EntityPlayer) o) && !((EntityPlayer) o).capabilities.disableDamage){
							((Entity) o).setFire(10);
							((Entity) o).attackEntityFrom(DamageSource.inFire, 7f);
						}
					}else{
						((Entity) o).setFire(10);
						((Entity) o).attackEntityFrom(DamageSource.inFire, 7f);
					}
				}
			}
		}
		return item;
	}
}