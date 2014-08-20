package ultratech.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.oredict.OreDictionary;

public class UT_Utils {

	
	public static List<TileEntity> getTiles(World w,int xCoord, int yCoord, int zCoord){
		List<TileEntity> t = new ArrayList<TileEntity>();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity e = w.getTileEntity(xCoord+d.offsetX, yCoord+d.offsetY, zCoord+d.offsetZ);
			if(e != null)t.add(e);
		}
		return t;
	}

	/**
	 * 
	 * @param a itemstack 1
	 * @param b itemstack 2
	 * @param meta if want detect metadata
	 * @return if are equals using oredictionary
	 */
	public static boolean areEcuals(ItemStack a, ItemStack b, boolean meta){
		if(a == null && b == null)return true;
		if(a != null && b != null && a.getItem() != null && b.getItem() != null){
			if(OreDictionary.itemMatches(a, b, meta))return true;
			int[] c = OreDictionary.getOreIDs(a);
			int[] d = OreDictionary.getOreIDs(b);
			if(c.length > 0 && d.length > 0){
				for(int i : c){
					for(int j : d)
						if(i == j)return true;
				}
			}
		}
		return false;
	}

	public static int RGBtoInt(int r,int g,int b){
		int color = 0;
		color += r*65536;
		color += g * 256;
		color += b;
		return color;
	}

	
	public static TileEntity getRelative(TileEntity from, ForgeDirection d){
		return from.getWorldObj().getTileEntity(from.xCoord + d.offsetX, from.yCoord + d.offsetY, from.zCoord + d.offsetZ);
	}
	
	public static boolean isIn(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){
			if(my > y && my < y+h){
				return true;
			}
		}
		return false;
	}

	public static List<TileEntity> getTiles(TileEntity t) {
		return getTiles(t.getWorldObj(), t.xCoord, t.yCoord, t.zCoord);
	}

	public static void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof IInventory){
			IInventory inventory = (IInventory) tileEntity;
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack item = inventory.getStackInSlot(i);
				if (item != null && item.stackSize > 0) {
					float rx = rand.nextFloat() * 0.8F + 0.1F;
					float ry = rand.nextFloat() * 0.8F + 0.1F;
					float rz = rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityItem = new EntityItem(world,
							x + rx, y + ry, z + rz,
							new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
					if (item.hasTagCompound()) {
						entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
					}
					float factor = 0.05F;
					entityItem.motionX = rand.nextGaussian() * factor;
					entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
					entityItem.motionZ = rand.nextGaussian() * factor;
					world.spawnEntityInWorld(entityItem);
					item.stackSize = 0;
				}
			}
		}
	}

	/**
	 * 
	 * @param block
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @return if the miner can break this block
	 */
	public static boolean canBreak(Block block,World w, int x, int y,int z){
		if(block == Blocks.air)return false;
		if(block instanceof BlockLiquid)return false;
		if(block instanceof BlockFluidBase)return false;
		if(Block.isEqualTo(block,Blocks.mob_spawner))return false;
		if(block == Blocks.portal)return false;
		if(block == Blocks.end_portal)return false;
		if(block == Blocks.end_portal_frame)return false;
		if(block.getBlockHardness(w, x, y, z) == -1)return false;
		return true;
	}
	
	/**
	 * 
	 * @param value
	 * @return the value with only 1 decimal
	 */
	public static float removeDecimals(double value){
		long t = (long) (value*10);
		return ((float)t)/10;
	}
}
