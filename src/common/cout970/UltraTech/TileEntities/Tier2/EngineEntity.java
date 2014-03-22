package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class EngineEntity extends Machine implements IPowerReceptor, IPowerEmitter{

	public ForgeDirection direction = ForgeDirection.UP;
	public float pos = 0;
	public float speed;
	public boolean engOn;
	public PowerHandler power;
	public PowerReceiver c;
	public boolean update;
	public long oldTime;

	public EngineEntity(){
		super();
		this.tier = MachineTier.Tier2;
	}
	public void updateEntity(){

		if(power == null){
			power = new PowerHandler(this, Type.ENGINE);
			power.configure(1, 300, 1, 5000);
			power.configurePowerPerdition(0, 0);
		}
		if(worldObj.isRemote)return;

		if(getEnergy() >= GraficCost.Engine_EnergyConsume && power.getEnergyStored()+GraficCost.Engine_MJ_Produced <= power.getMaxEnergyStored()){
			if(!engOn)UT_Utils.sendPacket(this);
			engOn = true;
		}else{
			if(engOn)UT_Utils.sendPacket(this);
			engOn = false;
		}
		if(engOn){
			this.removeEnergy(GraficCost.Engine_EnergyConsume);
			power.addEnergy(GraficCost.Engine_MJ_Produced);
		}
		if(!update){
			update = true;
			if (!isOrientationValid()){
				switchOrientation();
			}
			TileEntity a = getTile(direction);
			if(a instanceof IPowerReceptor){
				IPowerReceptor b = (IPowerReceptor) a;
				c = b.getPowerReceiver(direction.getOpposite());
			}
		}
		if(c != null){
			c.receiveEnergy(Type.ENGINE, power.useEnergy(100, 1000, true), direction.getOpposite());
		}
	}

	public void switchOrientation() {
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(getTile(d) instanceof IPowerReceptor){
				direction = d;
				UT_Utils.sendPacket(this);
				return;
			}
		}
	}

	private boolean isOrientationValid() {
		if(getTile(direction) instanceof IPowerReceptor)return true;
		return false;
	}

	private TileEntity getTile(ForgeDirection dir) {
		return worldObj.getBlockTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
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
		update = true;
		engOn = nbtTagCompound.getBoolean("On");
	}
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Dir", direction.ordinal());
		nbtTagCompound.setBoolean("On", engOn);
	}
}
