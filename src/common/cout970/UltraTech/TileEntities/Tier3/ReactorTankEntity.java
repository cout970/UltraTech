package common.cout970.UltraTech.TileEntities.Tier3;


import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class ReactorTankEntity extends TileEntity implements IFluidHandler,IFluidTank,IReactorPart{

	public FluidStack liquid;
	private final int capacity;
	private int last = 69;
	

	public ReactorTankEntity(){
		super();
		this.capacity = 16000;
	}

	@Override
	public void updateEntity(){
		if(liquid != null){
			if(liquid.amount != last){
				UT_Utils.sendPacket(this, false);
				last = liquid.amount;
			}
		}
	}

	//Fluid Tank

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

		if (resource == null) {
			return 0;
		}
		if(resource.getFluid().getBlockID() != Block.waterStill.blockID)return 0;
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
		else
		{
			liquid.amount = capacity;
		}


		FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));

		return filled;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
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
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
			return null;
		if (!resource.isFluidEqual(liquid))
			return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection direction) {

		return new FluidTankInfo[]{new FluidTankInfo(liquid, capacity)};
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	public int getFluidLightLevel() {
		FluidStack tankFluid = liquid;
		return tankFluid == null ? 0 : tankFluid.getFluid().getLuminosity(tankFluid);
	}

	public FluidStack getFluid() {
		return liquid;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public int getFluidAmount() {
		if(liquid == null)return 0;
		return liquid.amount;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(liquid, capacity);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return this.fill(null, resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return this.drain(null, maxDrain, doDrain);
	}

	//Save & Load

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);

		int amount = nbtTagCompound.getInteger("Amount");
		if(amount != 0){
			liquid = new FluidStack(nbtTagCompound.getInteger("ID"),amount);
		}
		Structure = nbtTagCompound.getBoolean("Structure");
	}


	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);

		if(liquid == null){
			nbtTagCompound.setInteger("Amount", 0);
			nbtTagCompound.setInteger("ID", 0);
		}else{
			nbtTagCompound.setInteger("Amount", liquid.amount);
			nbtTagCompound.setInteger("ID", liquid.fluidID);
		}
		nbtTagCompound.setBoolean("Structure", Structure);
	}


	//Reactor Part

	public boolean found = false;
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
		SearchReactor();
		if(found){
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

		this.Structure = false;

		for(TileEntity t : ids) {
			if(!(t instanceof IReactorPart))return;
		}
		this.Structure = true;
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
		return this.Structure;
	}

	//Synchronization

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.data);
	}
}