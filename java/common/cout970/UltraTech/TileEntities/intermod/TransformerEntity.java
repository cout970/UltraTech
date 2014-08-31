package common.cout970.UltraTech.TileEntities.intermod;

import ultratech.api.power.StorageInterface.PowerIO;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.info.Info;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.util.power.PowerExchange;
import cpw.mods.fml.common.FMLCommonHandler;

public class TransformerEntity extends Machine implements IEnergySource,IEnergySink{

	public double EU;//ic2 energy
	public double maxEU = 4000;
	public double limit = 32;//limit of energy output per tick
	public int tier = 1;//1 = LV, 2 = MV, 3 = HV, 4 = EV etc.
	private boolean addedToEnet;
	
	public TransformerEntity() {
		super(MachineData.Transformer);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!addedToEnet) onLoaded();
		if(worldObj.isRemote)return;
		double euCharge = PowerExchange.EUtoQP(EU);//operations in QP
		double qpCharge = getCharge();
		if(euCharge > qpCharge){
			double transfer = Math.min(euCharge-qpCharge, getCapacity()-getCharge());
			if(transfer > 0){
				EU -= PowerExchange.QPtoEU(transfer);
				addCharge(transfer);
			}
		}else if(qpCharge > euCharge){
			double transfer = Math.min(qpCharge-euCharge, PowerExchange.EUtoQP(maxEU-EU));
			if(transfer > 0){
				EU += PowerExchange.QPtoEU(transfer);
				removeCharge(transfer);
			}
		}
	}

	public void onLoaded() {
		if (!addedToEnet && !FMLCommonHandler.instance().getEffectiveSide().isClient() && Info.isIc2Available()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			addedToEnet = true;
		}
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		onChunkUnload();
	}
	
	@Override
	public void onChunkUnload() {
		if (addedToEnet && Info.isIc2Available()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			addedToEnet = false;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound data = tag.getCompoundTag("IC2Energy");
		EU = data.getDouble("energy");
	}

	/**
	 * Forward for the base TileEntity's writeToNBT(), used for saving the state.
	 * 
	 * @param tag Compound tag as supplied by TileEntity.writeToNBT()
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagCompound data = new NBTTagCompound();
		data.setDouble("energy", EU);
		tag.setTag("IC2Energy", data);
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter,
			ForgeDirection direction) {
		return true;
	}

	@Override
	public double getDemandedEnergy() {
		return maxEU-EU;
	}

	@Override
	public int getSinkTier() {
		return tier;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount,
			double voltage) {
		EU += amount;
		if(EU > maxEU){
			double buffer = EU - maxEU;
			EU = maxEU;
			return buffer;
		}
		return 0;
	}

	@Override
	public double getOfferedEnergy() {
		return Math.min(EU, limit);
	}

	@Override
	public void drawEnergy(double amount) {}

	@Override
	public int getSourceTier() {
		return tier;
	}
}
