package common.cout970.UltraTech.TileEntities.electric;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.misc.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import api.cout970.UltraTech.Wpower.CableType;
import api.cout970.UltraTech.Wpower.Machine;
import api.cout970.UltraTech.Wpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.fluids.TankConection;

public class PumpEntity extends Machine{

	public List<TankConection> tanks;
	public int currentTank=-1;
	private Fluid fluid;
	
	public PumpEntity(){
		super(2400,2,MachineTipe.Nothing,true);
	}
	
	public CableType getConection(ForgeDirection side) {
		if(side == ForgeDirection.DOWN)return CableType.BLOCK;
		return CableType.RIBBON_BOTTOM;
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(tanks == null)getTanks();
		if(worldObj.getTotalWorldTime()% 20 != 0)return;
		if(fluid == null){
			for(int y=yCoord;y>0;y--){
				if(getFluid(worldObj.getBlock(xCoord, y, zCoord)) != null){
					fluid = getFluid(worldObj.getBlock(xCoord, y, zCoord));
					break;
				}
			}
		}
		if(getEnergy() >= EnergyCosts.PumpCost && hasSpace() ){
			boolean found = false;
			for(int y=yCoord-1;y>0;y--){
				if(getFluid(worldObj.getBlock(xCoord, y, zCoord)) != null){
					boolean stop = false;
					for(int x=-20;x<21;x++){
						for(int z=-20;z<21;z++){
							if(x != 0 || z != 0){
								Block b = worldObj.getBlock(xCoord+x, y, zCoord+z);
								if(getFluid(b,worldObj.getBlockMetadata(xCoord+x, y, zCoord+z)) == fluid){
									if(fill(new BlockPos(xCoord+x, y, zCoord+z)))removeEnergy(EnergyCosts.PumpCost);
									stop = true;
									found = true;
								}
							}if(stop)break;
						}if(stop)break;
					}
					if(!found && getFluid(worldObj.getBlock(xCoord, y, zCoord),worldObj.getBlockMetadata(xCoord, y, zCoord)) != null){
						if(fill(new BlockPos(xCoord, y, zCoord)))removeEnergy(EnergyCosts.PumpCost);
					}
					found = true;
					break;
				}
			}if(!found){
				fluid = null;
			}
		}
	}

	private Fluid getFluid(Block block,int meta) {
		Fluid fluid = FluidRegistry.lookupFluidForBlock(block);
		if(fluid != null){
			if(meta == 0)return fluid;
		}
		return null;
	}
	
	private Fluid getFluid(Block block) {
		Fluid fluid = FluidRegistry.lookupFluidForBlock(block);
		return fluid;
	}

	private void getTanks() {
		tanks = new ArrayList<TankConection>();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(UT_Utils.getRelative(this, d) instanceof IFluidHandler)tanks.add(new TankConection((IFluidHandler) UT_Utils.getRelative(this, d), d.getOpposite()));
		}
	}

	private boolean fill(BlockPos p) {
		if(p == null)return false;
		Fluid g = getFluid(worldObj.getBlock(p.x, p.y, p.z),worldObj.getBlockMetadata(p.x, p.y, p.z));
		if(g != null){
			FluidStack f = new FluidStack(g, 1000);
			worldObj.setBlockToAir(p.x, p.y, p.z);
			TankConection t = tanks.get(currentTank);
			t.tank.fill(t.side, f, true);
			if(t.tank.fill(t.side, f, false) != 1000)currentTank = -1;
			return true;
		}
		return false;
	}

	private boolean hasSpace(){
		if(tanks.size() == 0)return false;
		if(fluid == null)return false;
		if(currentTank == -1){
			for(TankConection f : tanks){
				if(f.tank.fill(f.side,new FluidStack(fluid,1000),false) == 1000){
					currentTank = tanks.indexOf(f);
					break;
				}
			}
			if(currentTank == -1)return false;
		}
		return true;
	}

}
