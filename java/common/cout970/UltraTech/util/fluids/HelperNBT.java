package common.cout970.UltraTech.util.fluids;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class HelperNBT {

	public static void setFluid(ItemStack item, Fluid fluid, int i) {
		refresh(item);
		if(fluid == null)return;
		item.stackTagCompound.setString("Fluid", fluid.getName());
		item.stackTagCompound.setInteger("Amount", i);
	}
	
	public static FluidStack getFluid(ItemStack item){
		refresh(item);
		if(item.stackTagCompound.hasKey("Fluid")){
			return new FluidStack(FluidRegistry.getFluid(item.stackTagCompound.getString("Fluid")),item.stackTagCompound.getInteger("Amount"));
		}
		return null;
	}

	public static void refresh(ItemStack item) {
		if(item.stackTagCompound == null){
			item.stackTagCompound = new NBTTagCompound();
		}
		
	}

	public static void clear(ItemStack i) {
		i.stackTagCompound = new NBTTagCompound();
	}

	public static void setEnergy(ItemStack drop, double energy) {
		refresh(drop);
		drop.stackTagCompound.setDouble("Power", energy);
	}

}
