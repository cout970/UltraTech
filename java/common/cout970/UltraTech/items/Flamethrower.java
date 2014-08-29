package common.cout970.UltraTech.items;

import ultratech.api.power.interfaces.ISpeeded;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
		for(int i = 0; i < 50; i++){
			double x = p.posX + (w.rand.nextGaussian()/5);
			double y = p.posY + (w.rand.nextGaussian()/5);
			double z = p.posZ + (w.rand.nextGaussian()/5);
			Vec3 v = Vec3.createVectorHelper(
					Math.cos(Math.toRadians(p.rotationYawHead+90f)),
					Math.cos(Math.toRadians(p.prevRotationPitch+90f)),
					Math.sin(Math.toRadians(p.rotationYawHead+90f)));
			float a = w.rand.nextFloat()*0.2f;
			float b = w.rand.nextFloat()*0.2f;
			float c = w.rand.nextFloat()*0.2f;
		w.spawnParticle("flame", x+v.xCoord, y+v.yCoord, z+v.zCoord, v.xCoord+a, v.yCoord+b, v.zCoord+c);
		}
		return item;
    }
}