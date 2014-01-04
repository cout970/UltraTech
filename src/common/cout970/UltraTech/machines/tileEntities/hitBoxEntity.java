package common.cout970.UltraTech.machines.tileEntities;

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
		NBTTagList tagList = nbt.getTagList("CoordshitBox");
		NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(0);
		x = tagCompound.getInteger("x");
		NBTTagCompound tagCompound2 = (NBTTagCompound) tagList.tagAt(1);
		y = tagCompound2.getInteger("y");
		NBTTagCompound tagCompound3 = (NBTTagCompound) tagList.tagAt(2);
		z = tagCompound3.getInteger("z");
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound = new NBTTagCompound();
		tagCompound.setInteger("x", this.x);
		tagList.appendTag(tagCompound);
		NBTTagCompound tagCompound2 = new NBTTagCompound();
		tagCompound2.setInteger("y", this.y);
		tagList.appendTag(tagCompound2);
		NBTTagCompound tagCompound3 = new NBTTagCompound();
		tagCompound3.setInteger("z", this.z);
		tagList.appendTag(tagCompound3);
		nbt.setTag("CoordshitBox", tagList);
	}
}
