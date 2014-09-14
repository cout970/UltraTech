package common.cout970.UltraTech.TileEntities.electric;

import cofh.api.energy.IEnergyHandler;
import ultratech.api.util.UT_Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.power.IBatteryBlock;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.util.power.PowerExchange;

public class TileEntityBatteryTier1 extends Machine implements IBatteryBlock{


	public TileEntityBatteryTier1(){
		super(MachineData.Battery_T1);
	}

	private static final int MAX_OUTPUT = 400;
	public boolean[] sides = new boolean[4];
	
	public void updateEntity(){
		super.updateEntity();
		for(int i=0;i<4;i++){
			if(sides[i]){
				moveEnergy(ForgeDirection.getOrientation(i+2));
			}
		}
	}
	
	private void moveEnergy(ForgeDirection d) {
		TileEntity t = UT_Utils.getRelative(this, d);
		if(t instanceof IEnergyHandler){
			IEnergyHandler i = (IEnergyHandler) t;
			if(i.canConnectEnergy(d.getOpposite())){
				int send = Math.min(PowerExchange.QPtoRF(getCharge()), MAX_OUTPUT);
				int rec = i.receiveEnergy(d.getOpposite(), send, false);
				this.removeCharge(PowerExchange.RFtoQP(rec));
			}
		}
	}

	@Override
	public boolean[] getOutputSides() {
		return sides;
	}

	@Override
	public void setSide(ForgeDirection side, boolean value) {
		if(side == ForgeDirection.UP || side == ForgeDirection.DOWN)return;
		sides[side.ordinal()-2] = value;
		sendNetworkUpdate();
	}

	@Override
	public boolean getSide(ForgeDirection side) {
		if(side == ForgeDirection.UP || side == ForgeDirection.DOWN)return false;
		return sides[side.ordinal()-2];
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		sides[0] = nbt.getBoolean("side0");
		sides[1] = nbt.getBoolean("side1");
		sides[2] = nbt.getBoolean("side2");
		sides[3] = nbt.getBoolean("side3");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("side0", sides[0]);
		nbt.setBoolean("side1", sides[1]);
		nbt.setBoolean("side2", sides[2]);
		nbt.setBoolean("side3", sides[3]);
	}

	@Override
	public void saveData(NBTTagCompound nbt) {
		nbt.setDouble("energy", getCharge());
	}

	@Override
	public void loadData(NBTTagCompound nbt) {
		this.cond.setCharge(nbt.getDouble("energy"));
	}
}
