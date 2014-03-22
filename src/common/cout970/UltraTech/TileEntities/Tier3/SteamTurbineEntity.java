package common.cout970.UltraTech.TileEntities.Tier3;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class SteamTurbineEntity extends Machine implements IFluidTank,IFluidHandler,IReactorPart{

	public FluidStack liquid;
	public int capacity = 5000;
	public List<IFluidHandler> Tanks = null;

	public SteamTurbineEntity(){
		super();
		this.tipe = MachineTipe.Output;
		this.tier = MachineTier.Tier3;
	}

	public void updateEntity(){
		if(!this.worldObj.isRemote){			
			if(Tanks == null)getTanks();
			for(IFluidHandler b : Tanks){
				if(this.getFluidAmount() > 500)
					this.drain(b.fill(ForgeDirection.UP , new FluidStack(FluidRegistry.getFluid("steam"),500), true),true);
			}
			if(Reactor != null){
				if(Reactor.steam > 500){
					Reactor.steam -= this.fill(new FluidStack(FluidRegistry.getFluid("steam"),500), true);
				}
			}

			for(int j=0;j < 4 ; j++){
				if(liquid != null && this.getEnergy() <= this.maxEnergy()-GraficCost.SteamTurbineProduct){
					if(liquid.amount >= 80){
						this.drain(80, true);
						this.addEnergy(GraficCost.SteamTurbineProduct);
					}else break;
				}else break;
			}
		}
		super.updateEntity();
	}

	private void getTanks() {
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		Tanks = new ArrayList<IFluidHandler>();
		for(TileEntity te : t){
			if(te instanceof IFluidHandler){
				Tanks.add((IFluidHandler) te);
			}
		}
	}

	//Fluid Tank
	
	@Override
	public FluidStack getFluid() {
		return liquid;
	}

	@Override
	public int getFluidAmount() {
		if(liquid  == null)return 0;
		return liquid.amount;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		if(liquid != null)
			return new FluidTankInfo(liquid, liquid.amount);
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {

		if (resource == null) {
			return 0;
		}
		if(resource.getFluid().getID() != BlockManager.Steam.getID())return 0;
		if (!doFill)
		{
			if (liquid == null)
			{
				return Math.min(capacity, resource.amount);
			}
			if (!liquid.isFluidEqual(resource))
			{
				return 0;
			}
			return Math.min(capacity - liquid.amount, resource.amount);
		}
		if (liquid == null)
		{
			liquid = new FluidStack(resource, Math.min(capacity, resource.amount));
			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));

			return liquid.amount;
		}
		if (!liquid.isFluidEqual(resource))
		{
			return 0;
		}
		int filled = capacity - liquid.amount;
		if (resource.amount < filled)
		{
			liquid.amount += resource.amount;
			filled = resource.amount;
		}
		else{
			liquid.amount = capacity;
		}
		FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));

		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (liquid == null)
		{
			return null;
		}
		int drained = maxDrain;
		if (liquid.amount < drained)
		{
			drained = liquid.amount;
		}
		FluidStack stack = new FluidStack(liquid, drained);
		if (doDrain)
		{
			liquid.amount -= drained;
			if (liquid.amount <= 0)
			{
				liquid = null;
			}
			FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));
		}
		return stack;
	}


	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return this.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null || liquid == null)return null;
		if(resource.fluidID == liquid.fluidID)return this.drain(resource.amount, doDrain);
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{this.getInfo()};
	}
	
	private void setLiquidAmount(int a) {
		if(getFluidAmount() != a){
			if(liquid == null){
				fill(new FluidStack(FluidRegistry.getFluid("steam"),a), true);
			}else{
				liquid.amount = a;
			}
		}
	}

	//Reactor Part

	public boolean ReactorFound = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;


	@Override
	public void SearchReactor() {
		int[] ids = new int[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k);
					if(ids[current] == BlockManager.Reactor.blockID && worldObj.getBlockMetadata(xCoord+i, yCoord+j, zCoord+k) == 0){
						Reactor = (ReactorEntity) worldObj.getBlockTileEntity(xCoord+i,yCoord+j,zCoord+k);
						ReactorFound = true;
						return;
					}
					current++;
				}
			}
		}
		ReactorFound = false;
	}

	@Override
	public ReactorEntity getReactor() {
		return Reactor;
	}

	@Override
	public void onNeighChange() {
		Tanks = null;
		SearchReactor();
		if(ReactorFound){
			checkStructure();
			if(Structure){
				activateBlocks();
			}
		}else{
			Structure = false;
		}	
	}

	@Override
	public void checkStructure() {
		TileEntity[] ids = new TileEntity[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockTileEntity(Reactor.xCoord+i, Reactor.yCoord+j, Reactor.zCoord+k);
					current++;
				}
			}
		}
		Structure = false;
		for(TileEntity t : ids) {
			if(!(t instanceof IReactorPart))return;
		}
		Structure = true;
	}

	@Override
	public void activateBlocks() {
		Reactor.setStructure(true);
		Reactor.activateBlocks();
	}

	@Override
	public void desactivateBlocks() {
		if(Reactor != null){
			Reactor.desactivateBlocks();
		}		
	}

	@Override
	public void setStructure(boolean structure) {
		Structure = structure;		
	}

	@Override
	public void setReactor(ReactorEntity e) {
		Reactor = e;
	}

	@Override
	public boolean isStructure() {
		return Structure;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		Structure = nbtTagCompound.getBoolean("Structure");
		setLiquidAmount(nbtTagCompound.getInteger("Steam"));
	}


	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("Structure", Structure);
		nbtTagCompound.setInteger("Steam", getFluidAmount());
	}
}
