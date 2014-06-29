package common.cout970.UltraTech.TileEntities.intermod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import api.cout970.UltraTech.MeVpower.CableType;
import api.cout970.UltraTech.MeVpower.IPowerConductor;
import api.cout970.UltraTech.MeVpower.Machine;
import api.cout970.UltraTech.MeVpower.PowerInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface;
import api.cout970.UltraTech.network.Net_Utils;
import api.cout970.UltraTech.network.SyncTile;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.UT_Utils;

public class DynamoEntity extends SyncTile implements IPowerConductor, IEnergyHandler{

	public ForgeDirection facing = ForgeDirection.UP;
	protected EnergyStorage storage;//RF
	protected StorageInterface inter;//Mev
	public static final int RF = 20;//RF => MeV
	public boolean on = false;
	public IEnergyHandler recep = null;

	public DynamoEntity(){
		super();
		storage = new EnergyStorage((int) (CostData.Dynamo.cap*RF));
		inter = new StorageInterface(this,400,2);
	}

	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(inter.getCharge() >= 4 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= RF*4){
			inter.removeCharge(4d);
			storage.receiveEnergy(RF*4, false);
		}else if(inter.getCharge() >= 1 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= RF){
			inter.removeCharge(1d);
			storage.receiveEnergy(RF, false);
		}
		if(recep != null){
			int send = Math.min(400, storage.getEnergyStored());
			int b = ((IEnergyHandler) recep).receiveEnergy(facing.getOpposite(), send, false);
			if(b > 0){
				int e = this.extractEnergy(facing, b, false);
			}
		}
	}
	
	public void updateReceptor(){
		TileEntity a = UT_Utils.getRelative(this, facing);
		if(a instanceof IEnergyHandler)recep = (IEnergyHandler) a;
	}

	public CableType getConection(ForgeDirection side) {
		if(side == facing.getOpposite())return CableType.BLOCK;
		if(side == facing)return CableType.NOTHING;
		return CableType.RIBBON_BOTTOM;
	}

	public boolean isWorking() {
		return on;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return from == facing;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}
	
	//Save and Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		inter.readFromNBT(nbt);
		facing = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
		on = nbt.getBoolean("On");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		inter.writeToNBT(nbt);
		nbt.setInteger("Direction", facing.ordinal());
		nbt.setBoolean("On", isWorking());
	}

	public void switchOrientation() {
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(UT_Utils.getRelative(this, d) instanceof IEnergyHandler){
				facing = d;
				updateReceptor();
				return;
			}
		}	
	}

	@Override
	public PowerInterface getPower() {
		return inter;
	}
}
