package common.cout970.UltraTech.TileEntities.Tier1;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class hitBoxEntity extends TileEntity{

	public int x,y,z;
	
	public hitBoxEntity(){
		super();
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		x = nbt.getInteger("x0");
		y = nbt.getInteger("y0");
		z = nbt.getInteger("z0");
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("x0", this.x);
		nbt.setInteger("y0", this.y);
		nbt.setInteger("z0", this.z);
	}
}
