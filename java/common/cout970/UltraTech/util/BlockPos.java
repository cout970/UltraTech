package common.cout970.UltraTech.util;

import net.minecraft.nbt.NBTTagCompound;

public class BlockPos {

	public int x,y,z;
	
	public BlockPos(int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public BlockPos(NBTTagCompound c) {
		this.x = c.getInteger("i");
		this.y = c.getInteger("j");
		this.z = c.getInteger("k");
	}
	
}
