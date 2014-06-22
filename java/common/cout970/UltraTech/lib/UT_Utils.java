package common.cout970.UltraTech.lib;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
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
	 * @return if are equals
	 */
	public static boolean areEcuals(ItemStack a, ItemStack b, boolean meta){
		if(a == null && b == null)return true;
		if(a != null && b != null){
			if(OreDictionary.itemMatches(a, b, meta))return true;
			if(OreDictionary.getOreIDs(a).length != 0 && OreDictionary.getOreIDs(b).length != 0){
				for(int i : OreDictionary.getOreIDs(a)){
					for(int j : OreDictionary.getOreIDs(b))if(i == j)return true;
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

}
