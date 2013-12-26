package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class WaterBlockEntity extends TileEntity{

	public WaterBlockEntity(){
		super();
	}
	
	public void updateEntity(){
		List<IFluidHandler> a = getTanks();
		for(IFluidHandler b : a){
			b.fill(null , new FluidStack(FluidRegistry.WATER,100), true);
		}
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
}
