package common.cout970.UltraTech.TileEntities.Tier3;

import java.util.HashMap;
import java.util.Map;

import common.cout970.UltraTech.fluid.api.FluidUtils;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class WaterBlockEntity extends TileEntity implements IReactorPart,IFluidHandler{

	Map<ForgeDirection,IFluidHandler> tanks;

	public WaterBlockEntity(){
		super();
	}

	public void updateEntity(){
		if(tanks == null)updateTanks();
		if(FluidRegistry.WATER != null)
			for(ForgeDirection f : ForgeDirection.VALID_DIRECTIONS){
				if(tanks.containsKey(f))tanks.get(f).fill(f.getOpposite(), new FluidStack(FluidRegistry.WATER,EnergyCosts.WaterBlockProduct), true);
			}
	}

	private void updateTanks() {
		tanks = new HashMap<ForgeDirection,IFluidHandler>();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity te = FluidUtils.getRelative(this, d);
			if(te instanceof IFluidHandler){
				tanks.put(d,(IFluidHandler) te);
			}
		}
	}

	//Reactor
	
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
		tanks = null;
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
	
	//fluids

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{};
	}
}
