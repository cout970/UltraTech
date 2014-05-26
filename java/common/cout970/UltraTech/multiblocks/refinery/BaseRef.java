package common.cout970.UltraTech.multiblocks.refinery;

import java.util.ArrayList;
import java.util.List;

import api.cout970.UltraTech.fluids.UT_Tank;
import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import common.cout970.UltraTech.managers.BlockManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;


public class BaseRef extends TileGag implements IFluidHandler{

	public UT_Tank input;
	public List<TileGag> mb;
	public List<OutRef> out;

	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(mb == null){
			mb = new ArrayList<TileGag>();
			int index[] = RF_Utils.getPos(worldObj,x,y,z,BlockManager.Refinery);
			for(int k=-index[0];k<3-index[0];k++){
				for(int j=0;j<8;j++){
					for(int i=-index[1];i<3-index[1];i++){
						TileEntity t = worldObj.getTileEntity(x+i, y+j, z+k);
						if(t instanceof TileGag){
							mb.add((TileGag) t);
						}
					}
				}
			}
		}
		if(out == null){
			out = new ArrayList<OutRef>();
			for(TileGag t : mb){
				if(t instanceof OutRef){
					out.add((OutRef) t);
					((OutRef) t).height = getHeight((OutRef) t);
				}
			}
		}
			int ini = Cooling_Recipes.getInit(getTank().getFluid());
			FluidStack[] res = Cooling_Recipes.getResult(getTank().getFluid());
			if(ini != 0 && res != null){
				if(getTank().getFluid().amount < ini)return;
				int a1 = 0,a2 = 0,a3 = 0;
				if(res[0]!= null){
					boolean c = false;
					for(OutRef o : getOut(0)){
						if(o.getTank().getCapacity()-o.getTank().getFluidAmount() >= res[0].amount){
							c = true;
							a1 = getOut(0).indexOf(o);
						}
					}
					if(!c)return;
				}
				if(res[1]!= null){
					boolean c = false;
					for(OutRef o : getOut(1)){
						if(o.getTank().getCapacity()-o.getTank().getFluidAmount() >= res[1].amount){
							c = true;
							a2 = getOut(1).indexOf(o);
						}
					}
					if(!c)return;
				}
				if(res[2]!= null){
					boolean c = false;
					for(OutRef o : getOut(2)){
						if(o.getTank().getCapacity()-o.getTank().getFluidAmount() >= res[2].amount){
							c = true;
							a3 = getOut(2).indexOf(o);
						}
					}
					if(!c)return;
				}
				//all good
					FluidStack g = getTank().drain(ini, true);
					if(g != null && g.amount > 0){
					if(getOut(0).size()>0)getOut(0).get(a1).getTank().fill(res[0], true);
					if(getOut(1).size()>0)getOut(1).get(a2).getTank().fill(res[1], true);
					if(getOut(2).size()>0)getOut(2).get(a3).getTank().fill(res[2], true);
				}
			}
		}
	
	private List<OutRef> getOut(int i) {
		List<OutRef> a = new ArrayList<OutRef>();
		for(OutRef f : out){
			if(f.height == -1)f.height = getHeight(f);
			if(((int)(((float)f.height+1)/2f)) == i)a.add(f);
		}
		return a;
	}

	
	private int getHeight(OutRef t) {
		for(int j=0;j<8;j++){
			if(worldObj.getBlock(t.xCoord, t.yCoord-j, t.zCoord) == BlockManager.Refinery){
				if(worldObj.getBlockMetadata(t.xCoord, t.yCoord-j, t.zCoord) == 5){
					return j;
				}
			}
		}
		return -1;
	}

	public UT_Tank getTank(){
		if(input == null)input = new UT_Tank(2500, this);
		return input;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(Cooling_Recipes.hasRecipe(resource))return getTank().fill(resource, doFill);
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
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}
	
}
