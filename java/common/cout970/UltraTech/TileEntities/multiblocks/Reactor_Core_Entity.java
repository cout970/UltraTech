package common.cout970.UltraTech.TileEntities.multiblocks;

import net.minecraft.nbt.NBTTagCompound;

public class Reactor_Core_Entity extends Reactor_Entity_Base{

	public static byte size;

	public void writeToNBT(NBTTagCompound NBT){
		super.writeToNBT(NBT);
		NBT.setByte("size", size);
    }
	
	public void readFromNBT(NBTTagCompound NBT){
		super.readFromNBT(NBT);
		size = NBT.getByte("size");
    }
}
