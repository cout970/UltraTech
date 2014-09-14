package common.cout970.UltraTech.util.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public interface IBatteryBlock {

	public boolean[] getOutputSides();//only 4 sides (without up and down)
	
	public void setSide(ForgeDirection side, boolean value);

	public boolean getSide(ForgeDirection d);

	public void saveData(NBTTagCompound nbt);

	public void loadData(NBTTagCompound nbt);
}
