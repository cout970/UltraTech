package common.cout970.UltraTech.TileEntities.intermod;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.mj.IBatteryObject;
import buildcraft.api.mj.MjAPI;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.power.Machine;

public class EngineEntity extends Machine implements IPowerReceptor, IPowerEmitter{

	public ForgeDirection direction = ForgeDirection.UP;
	public float pos = 0;
	public float speed;
	public boolean engOn;
	public PowerHandler power;
	public long oldTime;
	public boolean update;
	public float animation;
	public boolean animationUp = true;
	public double animationTime;

	public EngineEntity(){
		super(2400,2);
	}
	public void updateEntity(){

		if(power == null){
			power = new PowerHandler(this, Type.ENGINE);
			power.configure(1, 300, 1, 5000);
			power.configurePowerPerdition(0, 0);
		}
		if(worldObj.isRemote)return;

		int powerSpace = (int) (power.getMaxEnergyStored()-power.getEnergyStored());
		int toChange = (int) Math.min(32, Math.min(getCharge(), powerSpace));
		if(toChange > 0){
			this.removeCharge(toChange);
			power.addEnergy(toChange);
		}
		if(!update){
			update = true;
			if (!isOrientationValid()){
				switchOrientation();
				worldObj.notifyBlockOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			}
			TileEntity a = getTile(direction);
		}
		if(power.getEnergyStored() >= 50){
			sendPower();
		}
	}
	
	
	///buildcraft code modified
	private void sendPower() {
		TileEntity tile = UT_Utils.getRelative(this, direction);
		if (isPoweredTile(tile, direction)) {
			double extracted = power.useEnergy(50, 200, false);

			if (MjAPI.getMjBattery(tile, MjAPI.DEFAULT_POWER_FRAMEWORK, direction.getOpposite()) != null) {
				IBatteryObject battery = MjAPI.getMjBattery(tile, MjAPI.DEFAULT_POWER_FRAMEWORK, direction.getOpposite());
				double a = battery.addEnergy(extracted);
				power.useEnergy(0, a, true);
			} 
			if(tile instanceof IPowerReceptor){
				PowerReceiver receptor = ((IPowerReceptor) tile).getPowerReceiver(direction.getOpposite());
				if (extracted > 0) {
					double needed = receptor.receiveEnergy(PowerHandler.Type.ENGINE, extracted, direction.getOpposite());
					power.useEnergy(0, needed, true);
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
			if(isPoweredTile(getTile(d), d)){
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
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		if(power == null){
			power = new PowerHandler(this, Type.ENGINE);
			power.configure(1, 300, 100, 5000);
			power.configurePowerPerdition(0, 0);
		}
		return power.getPowerReceiver();
	}

	@Override
	public void doWork(PowerHandler workProvider) {}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		return (side == this.direction) ? true : false;
	}

	public void sendGUINetworkData(Container engineContainer,
			ICrafting iCrafting) {
		super.sendGUINetworkData(engineContainer, iCrafting);
		iCrafting.sendProgressBarUpdate(engineContainer, 2, (int)power.getEnergyStored());
		iCrafting.sendProgressBarUpdate(engineContainer, 3, direction.ordinal());
		iCrafting.sendProgressBarUpdate(engineContainer, 4, engOn ? 1:0);
	}

	public void getGUINetworkData(int i, int j) {
		super.getGUINetworkData(i, j);
			if(i == 2)power.setEnergy(j);
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
