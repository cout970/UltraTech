package common.cout970.UltraTech.machines.tileEntities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.machines.containers.EngineContainer;
import cpw.mods.fml.common.network.PacketDispatcher;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class EngineEntity extends Machine implements IPowerReceptor, IPowerEmitter{

	public ForgeDirection direction = ForgeDirection.UP;
	public final int EnergyConsume = 15;
	public final int MJ_Produced = 32;
	public float speed = 0;
	public float engPos = 0;
	public boolean engOut;
	private float speedMax;
	public PowerHandler power;
	public PowerReceiver c;
	public boolean canFound = true;
	private boolean checkOrienation = true;
	public boolean working;
	public boolean sync = false;
	private boolean DirUpdate;
	
	public void updateEntity(){
		
		if(working)speedMax = 2.5f ;else speedMax = 0;
		if(speed != speedMax){
			if(speed < speedMax){
				speed+=0.005f;
			}else{
				speed-=0.005f;
				if(speed < 0)speed = 0;
			}
		}
		if(power == null){
			power = new PowerHandler(this, Type.ENGINE);
			power.configure(1, 300, 1, 5000);
			power.configurePowerPerdition(0, 0);
		}
		if(worldObj.isRemote)return;
		
		if(DirUpdate){
			DirUpdate = false;
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			DataOutputStream data = new DataOutputStream(bytes);
			try {
				data.writeInt(xCoord);
				data.writeInt(yCoord);
				data.writeInt(zCoord);
				data.writeInt(1);
				data.writeInt(direction.ordinal());
			} catch (IOException e) {
				e.printStackTrace();
			}
			PacketDispatcher.sendPacketToAllPlayers(PacketDispatcher.getPacket("UltraTech1", bytes.toByteArray()));
			sync = false;
		}
		if (checkOrienation) {
			checkOrienation = false;
			if (!isOrientationValid()){
				switchOrientation();
			}
		}


		if(!sync){
			sync = true;
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			DataOutputStream data = new DataOutputStream(bytes);
			try {
				data.writeInt(xCoord);
				data.writeInt(yCoord);
				data.writeInt(zCoord);
				data.writeInt(0);
				data.writeInt((working) ? 1 : 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PacketDispatcher.sendPacketToAllPlayers(PacketDispatcher.getPacket("UltraTech1", bytes.toByteArray()));
		}
		if(getEnergy() >= EnergyConsume && power.getEnergyStored() <= power.getMaxEnergyStored()-MJ_Produced){
			this.removeEnergy(EnergyConsume);
			power.addEnergy(MJ_Produced);
			if(!working){
				working = true;
				sync = false;
			}
		}else{
			if(working){
				working = false;
				sync = false;
			}
		}
		if(c == null){
			if(canFound ){
				TileEntity a = getTileOpposite();
				if(a != null && a instanceof IPowerReceptor){
					IPowerReceptor b = (IPowerReceptor) a;
					c = b.getPowerReceiver(direction.getOpposite());
					if(c == null)canFound = false;
				}
			}
		}else{
			if(c.powerRequest() > 0)
				c.receiveEnergy(Type.ENGINE, power.useEnergy(100, 1000, true), direction.getOpposite());
		}
	}

	public void switchOrientation() {

		if(worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord) instanceof IPowerReceptor){
			direction = ForgeDirection.DOWN;
			return;
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1) instanceof IPowerReceptor){
			direction = ForgeDirection.NORTH;
			return;
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1) instanceof IPowerReceptor){
			direction = ForgeDirection.SOUTH;
			return;
		}
		if(worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord) instanceof IPowerReceptor){
			direction = ForgeDirection.EAST;
			return;
		}
		if(worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord) instanceof IPowerReceptor){
			direction = ForgeDirection.WEST;
			return;
		}
	}


	private boolean isOrientationValid() {
		if(getTileOpposite() instanceof IPowerReceptor)return true;
		return false;
	}


	private TileEntity getTileOpposite() {
		switch(direction){
		case UP: return worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		case DOWN: return worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		case NORTH: return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		case SOUTH: return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		case EAST: return worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		case WEST: return worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		default : return null;
		}
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
	public void doWork(PowerHandler workProvider) {

	}


	@Override
	public World getWorld() {
		return this.worldObj;
	}


	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		return (side == this.direction) ? true : false;
	}

	public void sendGUINetworkData(EngineContainer engineContainer,
			ICrafting iCrafting) {
		iCrafting.sendProgressBarUpdate(engineContainer, 0, (working)? 1 : 0);
//		iCrafting.sendProgressBarUpdate(engineContainer, 1, Energy);
		iCrafting.sendProgressBarUpdate(engineContainer, 2, (int)power.getEnergyStored());
		iCrafting.sendProgressBarUpdate(engineContainer, 3, direction.ordinal());
	}

	public void getGUINetworkData(int i, int j) {
		switch(i){
		case 0:{
			if(j == 0){
				working = false;
			}else{
				working = true;
			}
		}
		case 1:{
//			Energy = j;
			break;
		}
		case 2:{
			power.setEnergy(j);
			break;
		}
		case 3:{
			direction = ForgeDirection.getOrientation(j);
			break;
		}
		}
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
		this.checkOrienation = false;
		DirUpdate = true;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Dir", direction.ordinal());
		
	}
    public boolean receiveClientEvent(int id, int value){
    	super.receiveClientEvent(id, value);
    	if(id == 0){
    		if(value == 1)working=true;else working=false;
    	}else if(id == 1){
    		direction = ForgeDirection.getOrientation(value);
    	}
    	return false;
    }
}
