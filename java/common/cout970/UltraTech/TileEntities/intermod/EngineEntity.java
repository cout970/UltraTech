package common.cout970.UltraTech.TileEntities.intermod;

import ultratech.api.util.UT_Utils;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.mj.IBatteryObject;
import buildcraft.api.mj.MjAPI;
import buildcraft.api.mj.MjBattery;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.util.power.PowerExchange;
import common.cout970.UltraTech.util.power.cables.CableInterfaceEngine;

public class EngineEntity extends Machine implements IPowerEmitter{

	//render
	public ForgeDirection direction = ForgeDirection.UP;
	public float animation = 0.625f;
	public boolean animationUp;
	public boolean engOn;	
	public long oldTime;
	//work
	@MjBattery(maxCapacity = 800)
	public double MJ;
	public boolean update;

	public EngineEntity(){
		super(MachineData.Engine);
		getPower().setCable(new CableInterfaceEngine(this));
	}
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;

		int powerSpace = (int) (800-MJ);
		int toChange = (int) Math.min(MachineData.Engine.use, Math.min(PowerExchange.QPtoMJ(getCharge()), powerSpace));
		if(toChange > 0){
			this.removeCharge(PowerExchange.MJtoQP(toChange));
			MJ += toChange;
		}
		if(worldObj.getTotalWorldTime()%10 == 1){
			if(engOn && toChange <= 0){
				engOn = false;
				sendNetworkUpdate();
			}

			if(!engOn && toChange > 0){
				engOn = true;
				sendNetworkUpdate();
			}
		}
		if(!update){
			update = true;
			if (!isOrientationValid()){
				switchOrientation();
			}
		}
		if(MJ >= 50){
			sendPower();
		}else if(!engOn && MJ > 0){
			sendPower();
		}
	}
	
	
	///buildcraft code modified
	
	private void sendPower() {
		TileEntity tile = UT_Utils.getRelative(this, direction);
		if (isPoweredTile(tile, direction)) {
			double extracted = Math.min(100, MJ);
			if (extracted > 0) {
				if (MjAPI.getMjBattery(tile, MjAPI.DEFAULT_POWER_FRAMEWORK, direction.getOpposite()) != null) {
					IBatteryObject battery = MjAPI.getMjBattery(tile, MjAPI.DEFAULT_POWER_FRAMEWORK, direction.getOpposite());
					double a = battery.addEnergy(extracted);
					MJ -= a;
				}else if(tile instanceof IPowerReceptor){
					PowerReceiver receptor = ((IPowerReceptor) tile).getPowerReceiver(direction.getOpposite());
					double needed = receptor.receiveEnergy(PowerHandler.Type.ENGINE, extracted, direction.getOpposite());
					MJ -= needed;
				}
			}
		}
	}
	
	public boolean isPoweredTile(TileEntity tile, ForgeDirection side) {
		if (tile == null) {
			return false;
		} else if (tile instanceof IPowerReceptor) {
			return ((IPowerReceptor) tile).getPowerReceiver(side.getOpposite()) != null;
		} else {
			return MjAPI.getMjBattery(tile, MjAPI.DEFAULT_POWER_FRAMEWORK, direction.getOpposite()) != null;
		}
	}

	//end of buildcraft code

	public void switchOrientation() {
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(!(getTile(d) instanceof EngineEntity) && isPoweredTile(getTile(d), d)){
				direction = d;
				Net_Utils.sendUpdate(this);
				return;
			}
		}
	}

	private boolean isOrientationValid() {
		if(getTile(direction) instanceof IPowerReceptor)return true;
		return false;
	}

	private TileEntity getTile(ForgeDirection dir) {
		return worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
	}

	
	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		return (side == this.direction) ? true : false;
	}

	public void sendGUINetworkData(Container engineContainer,
			ICrafting iCrafting) {
		super.sendGUINetworkData(engineContainer, iCrafting);
		iCrafting.sendProgressBarUpdate(engineContainer, 2, (int)MJ);
		iCrafting.sendProgressBarUpdate(engineContainer, 3, direction.ordinal());
		iCrafting.sendProgressBarUpdate(engineContainer, 4, engOn ? 1:0);
	}

	public void getGUINetworkData(int i, int j) {
		super.getGUINetworkData(i, j);
			if(i == 2)MJ = j;
			if(i == 3)direction = ForgeDirection.getOrientation(j);
			if(i == 4)engOn = (j == 1) ? true : false;
	}

	public void Rotate() {
		if(direction.ordinal() >= 5){
			direction = ForgeDirection.getOrientation(0);
		}else
			direction = ForgeDirection.getOrientation(direction.ordinal()+1);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		direction = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));
		engOn = nbtTagCompound.getBoolean("On");
	}
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Dir", direction.ordinal());
		nbtTagCompound.setBoolean("On", engOn);
	}
}
