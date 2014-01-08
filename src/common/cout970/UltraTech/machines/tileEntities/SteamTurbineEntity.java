package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.Energy;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class SteamTurbineEntity extends Machine implements IFluidTank,IFluidHandler,Energy,IReactorPart{

	public FluidStack liquid;
	public int capacity = 5000;
	public boolean update2 = false;
	
	public SteamTurbineEntity(){
		super();
		this.tipe = MachineTipe.Output;
	}

	public void updateEntity(){
		if(!this.worldObj.isRemote){			
			List<IFluidHandler> a = getTanks();
			for(IFluidHandler b : a){
				if(this.getFluidAmount() > 500)
				this.drain(b.fill(ForgeDirection.UP , new FluidStack(FluidRegistry.getFluid("steam"),500), true),true);
			}
			if(Reactor != null){
				if(Reactor.steam > 0){
					Reactor.steam -= this.fill(new FluidStack(UltraTech.Steam,500), true);
				}
			}else if(!update2){
				update = true;
				this.setUp();
			}
			for(int j=0;j < 5 ; j++){
				if(liquid != null && this.Energy <= this.EnergyMax-10){

					if(liquid.amount >= 100){
						this.drain(100, true);
						this.gainEnergy(10);
					}else break;
				}else break;
			}
		}
		super.updateEntity();
	}
	
	public void onNeiChange(){
		setUp();
	}

	
	private List<IFluidHandler> getTanks() {
		List<IFluidHandler> tanks = new ArrayList<IFluidHandler>();
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		for(TileEntity te : t){
			if(te instanceof IFluidHandler){
				tanks.add((IFluidHandler) te);
			}
		}
		return tanks;
	}


	//fluid
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
		if(resource.getFluid().getID() != UltraTech.Steam.getID())return 0;
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
	
	//energy
	@Override
	public int gainEnergy(int energy2) {
		if(Energy+energy2 <= EnergyMax){
			Energy += energy2;
			return 0;
		}else{
			int l =EnergyMax - Energy;
			Energy = EnergyMax;
			return l;
		}

	}


	@Override
	public void loseEnergy(int amount) {
		if(Energy-amount >= 0){
		Energy -= amount;	
		}
	}


	@Override
	public int getEnergy() {
		return Energy;
	}
	

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);
		
		NBTTagList tagList = nbtTagCompound.getTagList("Energy_UT");
		NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(0);
		Energy = tagCompound.getInteger("Energy");
		NBTTagCompound tagCompound2 = (NBTTagCompound) tagList.tagAt(1);
		EnergyMax = tagCompound2.getInteger("EnergyMax");

	}
	

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound = new NBTTagCompound();
		tagCompound.setInteger("Energy", this.Energy);
		tagList.appendTag(tagCompound);
		NBTTagCompound tagCompound2 = new NBTTagCompound();
		tagCompound2.setInteger("EnergyMax", this.EnergyMax);
		tagList.appendTag(tagCompound2);
		nbtTagCompound.setTag("Energy_UT", tagList);
		
	}
	
	//Reactor Part
	
	public boolean found = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;
	
	public void setUp() {
		SearchReactor();
		if(found){
			checkStructure();
			if(Structure){
				activateBlocks();
			}
		}
	}

	@Override
	public void SearchReactor() {
		int[] ids = new int[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k);
					if(ids[current] == UltraTech.Reactor.blockID){
						Reactor = (ReactorEntity) worldObj.getBlockTileEntity(xCoord+i,yCoord+j,zCoord+k);
						found = true;
						return;
					}
					current++;
				}
			}
		}
		found = false;
	}

	@Override
	public ReactorEntity getReactor() {
		return this.Reactor;
	}

	@Override
	public void onNeighChange() {
		setUp();	
	}

	@Override
	public void checkStructure() {
		TileEntity[] ids = new TileEntity[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k);
					current++;
				}
			}
		}
		this.Structure = false;

		for(TileEntity t : ids) {
			if(!(t instanceof IReactorPart))return;
		}
		this.Structure = true;
	}

	@Override
	public void activateBlocks() {
		Reactor.setStructure(true);
		int x,y,z;
		x = Reactor.xCoord;
		y = Reactor.yCoord;
		z = Reactor.zCoord;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(true);
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setReactor(Reactor);
					}
				}
			}
		}
	}

	@Override
	public void desactivateBlocks() {
		if(Structure){
			Structure = false;
			int x,y,z;
			x = Reactor.xCoord;
			y = Reactor.yCoord;
			z = Reactor.zCoord;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
							((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(false);
						}
					}
				}
			}
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
		return this.Structure;
	}
}
