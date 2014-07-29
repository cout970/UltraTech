package common.cout970.UltraTech.TileEntities.multiblocks.reactor;

import io.netty.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

import cofh.api.tileentity.IRedstoneControl.ControlMode;
import common.cout970.UltraTech.misc.PowerExchange;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.StorageInterface;
import ultratech.api.reactor.IReactorComponent;
import ultratech.api.reactor.IReactorCoreEntity;
import ultratech.api.reactor.IReactorFuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class Reactor_Core_Entity extends Reactor_Entity_Base implements IReactorCoreEntity,IInventory{

	public byte size;
	public boolean check;
	public UT_Tank steam;
	public UT_Tank water;
	public List<IReactorComponent> components = new ArrayList<IReactorComponent>();
	public ItemStack[] inv;
	public float heat = 25;
	public float maxheat = 100;
	public boolean state = false;
	public int production;
	public ControlMode Mode = ControlMode.LOW;
	public boolean redstoneSignal = false;
	public boolean automation = false;
	
	public Reactor_Core_Entity(){
		inv = new ItemStack[25];
	}
	
	public void updateEntity(){
		if(!check){
			check = true;
			if(size == 0){
				size = (byte) ReactorMultiblockTweak.checkStructure(worldObj, xCoord, yCoord, zCoord);
				Net_Utils.sendUpdate(this);
			}
			updateComponents();
		}
		if(worldObj.isRemote)return;
		boolean empty = true;
		if(automation){
			if(getTank(1).getCapacity()-getTank(1).getFluidAmount() < 100){
				state = false;
			}else{
				state = true;
			}
		}
		production = 0;
		if(state && shouldWork()){
			for(int p=0;p<getSizeInventory();p++){
				if(inv[p] != null){
					if(inv[p].getItem() instanceof IReactorFuel){
						empty = false;
						if(heat < maxheat)heat +=0.1;
						if(heat >= 100 ){
							FluidStack f = new FluidStack(FluidRegistry.getFluid("steam"),((IReactorFuel) inv[p].getItem()).getSteamPerTick());
							int toD =Math.min(1, f.amount/10);
							if(getTank(0).getFluidAmount() >= toD){
								getTank(1).fill(f, true);
								getTank(0).drain(toD, true);
								production += f.amount;
								if(worldObj.getTotalWorldTime()%20 == 0){
									inv[p].setItemDamage(inv[p].getItemDamage()+1);
									if(inv[p].getItemDamage() > inv[p].getMaxDamage()){
										inv[p] = null;
									}
								}
							}
						}
					}
				}
			}
		}
		if(empty && heat > 25)heat -= 0.1;
	}
	
	public boolean shouldWork(){
		if(Mode == ControlMode.DISABLED)return true;
		if(Mode == ControlMode.LOW && !redstoneSignal)return true;
		if(Mode == ControlMode.HIGH && redstoneSignal)return true;
		return false;
	}
	
	@Override
	public void RestaureBlock(){
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
		worldObj.removeTileEntity(xCoord, yCoord, zCoord);
	}
	
	public void updateComponents(){
		int i = 7;
		if(size == 1)i = 3;
		if(size == 2)i = 5;
		if(size == 3)i = 7;
		components.clear();
		for(int x=0;x<i;x++){
			for(int y=0;y<i;y++){
				for(int z=0;z<i;z++){
					TileEntity t = worldObj.getTileEntity(xCoord+x-size, yCoord+y-size, zCoord+z-size);
					if(t instanceof IReactorComponent){
						((IReactorComponent) t).setCore(this);
						components.add((IReactorComponent) t);
					}
				}
			}
		}
	}

	public void writeToNBT(NBTTagCompound NBT){
		super.writeToNBT(NBT);
		NBT.setByte("size", size);
		getTank(0).writeToNBT(NBT, "water");
		getTank(1).writeToNBT(NBT, "steam");
		NBT.setFloat("Heat", heat);
    }
	
	public void readFromNBT(NBTTagCompound NBT){
		super.readFromNBT(NBT);
		size = NBT.getByte("size");
		getTank(0).readFromNBT(NBT, "water");
		getTank(1).readFromNBT(NBT, "steam");
		heat = NBT.getFloat("Heat");
    }
	
	public UT_Tank getTank(int tank){
		if(water == null){
			if(getNumTanks() == 0)updateComponents();
			water = new UT_Tank(getNumTanks()*4000, this);
		}
		if(steam == null)steam = new UT_Tank(32000, this);
		if(tank == 0)return water;
		if(tank == 1)return steam;
		return null;
	}

	private int getNumTanks() {
		int n = 0;
		for(IReactorComponent r : components){
			if(r instanceof Reactor_Tank_Entity)n++;
		}
		return n;
	}

	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		int f = getTank(0).fill(resource,doFill);
		return f;
	}

	public FluidStack drain(ForgeDirection from, FluidStack resource,boolean doDrain) {
		return this.drain(from, resource.amount, doDrain);
	}

	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getTank(1).drain(maxDrain,doDrain);
	}

	public boolean canFill(ForgeDirection from, Fluid fluid) {return false;}
	public boolean canDrain(ForgeDirection from, Fluid fluid) {return false;}

	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{getTank(0).getInfo(),getTank(1).getInfo()};
	}
	
	public void sendGUINetworkData(Container cont, ICrafting c) {
		super.sendGUINetworkData(cont, c);
		if(getTank(0).getFluid() != null){
			c.sendProgressBarUpdate(cont, 1, getTank(0).getFluid().fluidID);
			c.sendProgressBarUpdate(cont, 2, getTank(0).getFluidAmount());
		}
		c.sendProgressBarUpdate(cont, 3, size);
		c.sendProgressBarUpdate(cont, 4, (int) heat);
		c.sendProgressBarUpdate(cont, 5, state ? 1 : 0);
		c.sendProgressBarUpdate(cont, 6, production);
		c.sendProgressBarUpdate(cont, 7, Mode.ordinal());
		c.sendProgressBarUpdate(cont, 8, redstoneSignal ? 1 : 0);
		c.sendProgressBarUpdate(cont, 9, automation ? 1 : 0);
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 1)getTank(0).setFluid(new FluidStack(FluidRegistry.getFluid(value),0));
		if(id == 2)getTank(0).setFluidAmount(value);
		if(id == 3)size = (byte) value;
		if(id == 4)heat = value;
		if(id == 5)state = value == 1;
		if(id == 6)production = value;
		if(id == 7)Mode = ControlMode.values()[value];
		if(id == 8)redstoneSignal = value == 1;
		if(id == 9)automation = value == 1;
	}

	@Override
	public void setSize(int size) {
		this.size = (byte) size;		
	}
	
	//inventary

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {
			if (itemStack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			}
			else {
				itemStack = itemStack.splitStack(amount);
				if (itemStack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		inv[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return "Reactor";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack i) {
		int space = 0;
		if(size == 1)space = 1;
		if(size == 2)space = 9;
		if(size == 3)space = 25;
		if(isSlotinSpace(slot,space)){
			if(i != null){
				if(i.getItem() instanceof IReactorFuel){
					return true;
				}
			}
		}
		return false;
	}

	public boolean isSlotinSpace(int slot, int space) {
		if(space == 1)if(slot == 12)return true;
		if(space == 9){
			if(slot == 6 || slot == 7 || slot == 8)return true;
			if(slot == 11 || slot == 12 || slot == 13)return true;
			if(slot == 16 || slot == 17 || slot == 18)return true;
		}
		if(space == 25)return true;
		return false;
	}

	@Override
	public int getSize() {	
		return size;
	}

	public void applyConfig(int type, int value) {
		if(type == 1){
			if(value == 0)state = true;//on
			if(value == 1)state = false;//off
		}else if(type == 2){
			automation = !automation;
		}else if(type == 3){
			if(value == 0)Mode = ControlMode.HIGH;
			if(value == 1)Mode = ControlMode.DISABLED;
			if(value == 2)Mode = ControlMode.LOW;
		}
	}

	@Override
	public void upadateRedstoneSignal() {
		updateComponents();
		boolean hasSignal = false;
		for(IReactorComponent r : components){
			if(r instanceof Reactor_Redstone_Entity){
				Reactor_Redstone_Entity io = (Reactor_Redstone_Entity) r;
				if(worldObj.isBlockIndirectlyGettingPowered(io.xCoord, io.yCoord, io.zCoord)){
					hasSignal = true;
				}
			}
		}
		redstoneSignal = hasSignal;
		
	}
}
