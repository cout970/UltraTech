package common.cout970.UltraTech.util.power;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultratech.api.power.ISpeeded;
import ultratech.api.power.PowerConductorBlock;
import ultratech.api.util.UT_Utils;

import common.cout970.UltraTech.managers.ItemManager;
/**
 * 
 * @author Cout970
 *
 */
public abstract class BlockConductor extends PowerConductorBlock{

	public BlockConductor(Material m) {
		super(m);
	}

	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		super.onBlockPreDestroy(w, x, y, z, meta);
		if(w.isRemote)return;
		dropItems(w, x, y, z);		
	}
	
	public void dropItems(World world, int x, int y, int z){
		UT_Utils.dropItems(world, x, y, z);
		Random rand = new Random();
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity instanceof ISpeeded){
			float rx = rand.nextFloat() * 0.8F + 0.1F;
			float ry = rand.nextFloat() * 0.8F + 0.1F;
			float rz = rand.nextFloat() * 0.8F + 0.1F;
			int upgrades = ((ISpeeded)tileEntity).getUpgrades();
			if(upgrades > 0){
				ItemStack item = new ItemStack(ItemManager.ItemName.get("SpeedUpgrade"),upgrades);
				EntityItem entityItem = new EntityItem(world,
						x + rx, y + ry, z + rz, item);

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
			}
		}
	}

}
