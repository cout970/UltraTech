package common.cout970.UltraTech.TileEntities.electric;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ultratech.api.power.StorageInterface.PowerIO;

import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.BlockPos;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import common.cout970.UltraTech.util.power.Machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PumpEntity extends Machine implements IFluidHandler,IUpdatedEntity{

	//for the animation
	public long animationTime;
	public boolean up;
	public float p;
	
	//for the pump
	private UT_Tank tank;
	public int height;
	public LinkedList<BlockPos> layer = new LinkedList<BlockPos>();
	private boolean FluidUpdate;
	
	
	public PumpEntity(){
		super(3200,2,PowerIO.Nothing,true);
		height = yCoord-3;
	}
	
	public UT_Tank getTank(){
		if(tank == null)tank = new UT_Tank(8000, this);
		return tank;
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(worldObj.getTotalWorldTime()% 10 != 0)return;
		if(FluidUpdate && worldObj.getTotalWorldTime()% 40 == 0){
			FluidUpdate = false;
			sendNetworkUpdate();
		}
		if(getCharge() >= MachineData.Pump.use){
			if(layer.isEmpty() && worldObj.getTotalWorldTime()% 40 == 0){
				if(height<=0)height = yCoord-3;
				for(;height>0;){
					if(worldObj.isAirBlock(xCoord, height, zCoord) || getFluid(worldObj.getBlock(xCoord, height, zCoord), 0) != null){
						if(layer.isEmpty()){
							loadLayer(getFluid(worldObj.getBlock(xCoord, height, zCoord), 0));
							if(layer.isEmpty())height -= 1;
							else break;
						}else break;
					}else{
						height = yCoord-3;
						break;
					}
				}
			}else if(!layer.isEmpty() && getTank().getCapacity()-getTank().getFluidAmount() >= 1000){
				for(int u=0;u<5;u++){
					BlockPos b = layer.pollFirst();
					if(b != null){
						Fluid f = getFluid(worldObj.getBlock(b.x, b.y, b.z), worldObj.getBlockMetadata(b.x, b.y, b.z));
						if(f != null){
							if((b.x != xCoord || b.z != zCoord) && f == FluidRegistry.LAVA)worldObj.setBlock(b.x, b.y, b.z, Blocks.stone, 0, 6);
							else worldObj.setBlockToAir(b.x, b.y, b.z);
							this.fill(null,new FluidStack(f, 1000), true);
							removeCharge(MachineData.Pump.use);
							break;
						}
					}
				}
			}
		}
	}

	private void loadLayer(Fluid d) {
		layer.clear();
		if(getFluid(worldObj.getBlock(xCoord, height, zCoord), 0)!= null)
			for(int x=-100;x<100;x++){for(int z=-100;z<100;z++){
				Fluid f = getFluid(worldObj.getBlock(xCoord+x, height, zCoord+z), worldObj.getBlockMetadata(xCoord+x, height, zCoord+z));
				if(f != null && f == d &&(getTank().getFluid() == null  || getTank().getFluid().getFluid() == f)){
					layer.add(new BlockPos(xCoord+x, height, zCoord+z));
					if(layer.size() > 1000)break;
				}
			}
			if(layer.size() > 1000)break;
			}
	}

	private Fluid getFluid(Block block,int meta) {
		Fluid fluid = FluidRegistry.lookupFluidForBlock(block);
		if(fluid != null){
			if(meta == 0)return fluid;
		}
		return null;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		int f = getTank().fill(resource, doFill);
		if(f > 0 && doFill)
			FluidUpdate = true;
		return f;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack f = getTank().drain(maxDrain, doDrain);
		if(f != null && f.amount > 0 && doDrain){
			FluidUpdate = true;
		}
		return f;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return getTank().fill(new FluidStack(fluid, 1), false) != 0;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return getTank().drain(1, false) != null;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		getTank().readFromNBT(nbtTagCompound, "liquid");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		getTank().writeToNBT(nbtTagCompound, "liquid");
	}

	@Override
	public void onNeigUpdate() {
		
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord-2, zCoord, xCoord+1, yCoord+1, zCoord+1);
        return bb;
    }

}
