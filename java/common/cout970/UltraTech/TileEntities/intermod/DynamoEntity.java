package common.cout970.UltraTech.TileEntities.intermod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import api.cout970.UltraTech.Wpower.CableType;
import api.cout970.UltraTech.Wpower.IPowerConductor;
import api.cout970.UltraTech.Wpower.Machine;
import api.cout970.UltraTech.Wpower.PowerInterface;
import api.cout970.UltraTech.Wpower.StorageInterface;
import api.cout970.UltraTech.network.Net_Utils;
import api.cout970.UltraTech.network.SyncTile;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.UT_Utils;

public class DynamoEntity extends SyncTile implements IPowerConductor, IEnergyHandler{

	public ForgeDirection facing = ForgeDirection.UP;
	protected EnergyStorage storage;//RF
	protected StorageInterface inter;//w
	public static final int RF = 80;//RF => MeV
	public boolean on = false;
	public IEnergyHandler recep = null;

	public DynamoEntity(){
		super();
		storage = new EnergyStorage((int) (CostData.Dynamo.cap*RF));
		inter = new StorageInterface(this,400,2);
	}

	public void updateEntity(){
		super.updateEntity();
		
		if(inter.getCharge() >= 1 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= RF){
			inter.removeCharge(1d);
			storage.receiveEnergy(RF, false);
			if(!on){
			on = true;
			Sync();
			}
		}else{
			if(on){
			on = false;
			Sync();
			}
		}
		 
		if(!worldObj.isRemote && recep != null){
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
		Sync();
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
