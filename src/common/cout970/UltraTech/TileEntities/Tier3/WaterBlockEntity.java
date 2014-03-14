package common.cout970.UltraTech.TileEntities.Tier3;

import java.util.HashMap;
import java.util.Map;

import common.cout970.UltraTech.lib.GraficCost;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class WaterBlockEntity extends TileEntity implements IReactorPart{

	Map<ForgeDirection,IFluidHandler> tanks;

	public WaterBlockEntity(){
		super();
	}

	public void updateEntity(){
		if(tanks == null)updateTanks();
		if(FluidRegistry.WATER != null)
			for(ForgeDirection f : ForgeDirection.VALID_DIRECTIONS){
				if(tanks.containsKey(f))tanks.get(f).fill(f , new FluidStack(FluidRegistry.WATER,GraficCost.WaterBlockProduct), true);
			}
	}

	private void updateTanks() {
		tanks = new HashMap<ForgeDirection,IFluidHandler>();
		for(TileEntity te : UT_Utils.getTiles(worldObj, xCoord, yCoord, zCoord)){
			if(te instanceof IFluidHandler){
				tanks.put(getDirection(te),(IFluidHandler) te);
			}
		}
	}

	private ForgeDirection getDirection(TileEntity te) {
		int x,y,z;
		x = te.xCoord - xCoord;
		y = te.yCoord - yCoord;
		z = te.xCoord - zCoord;
		if(y != 0){
			if(y == 1)return ForgeDirection.UP;
			if(y == -1)return ForgeDirection.DOWN;
		}
		if(x != 0){
			if(x == 1)return ForgeDirection.WEST;
			if(x == -1)return ForgeDirection.EAST;
		}
		if(z != 0){
			if(z == 1)return ForgeDirection.NORTH;
			if(z == -1)return ForgeDirection.SOUTH;
		}
		return ForgeDirection.UP;
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
}
