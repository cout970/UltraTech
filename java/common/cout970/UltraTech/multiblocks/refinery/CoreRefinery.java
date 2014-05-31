package common.cout970.UltraTech.multiblocks.refinery;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import common.cout970.UltraTech.managers.BlockManager;
import api.cout970.UltraTech.fluids.UT_Tank;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CoreRefinery extends TileGag{
	
	public List<BaseRef> in = new ArrayList<BaseRef>();
	public List<OutRef> out = new ArrayList<OutRef>();
	public List<TileGag> MultiBlock;
	public UT_Tank input;
	public UT_Tank[] output = new UT_Tank[3];
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(MultiBlock == null){
			MultiBlock = new ArrayList<TileGag>();
			in.clear();
			out.clear();
			for(int k=-1;k<2;k++){
				for(int j=-1;j<7;j++){
					for(int i=-1;i<2;i++){
						TileEntity t = worldObj.getTileEntity(i+xCoord, j+yCoord, k+zCoord);
						if(t instanceof TileGag){MultiBlock.add((TileGag) t);
							if(t instanceof BaseRef){in.add((BaseRef) t);}
							if(t instanceof OutRef){out.add((OutRef) t);
							((OutRef) t).height = ((OutRef) t).getHeight((OutRef) t);}
							((TileGag) t).setMulti(this);
						}
					}
				}
			}
		}
		
		int ini = Cooling_Recipes.getInit(getTank().getFluid());
		FluidStack[] res = Cooling_Recipes.getResult(getTank().getFluid());
		if(ini != 0 && res != null){
			if(getTank().getFluid().amount < ini)return;
			boolean[] a = new boolean[3];
			for(int i=0;i<3;i++){
				if(res[i]!= null){
					if((getTank(i).getCapacity()-getTank(i).getFluidAmount()) >= res[i].amount){
						a[i] = true;					
					}
				}else{
					a[i]= true;
				}
			}
			
			//all good
			if(a[0] && a[1] && a[2]){
				FluidStack g = getTank().drain(ini, true);
				if(g != null && g.amount > 0){
					
					getTank(0).fill(res[0], true);
					getTank(1).fill(res[1], true);
					getTank(2).fill(res[2], true);
				}
			}
		}
	}

	public UT_Tank getTank() {
		if(input == null)input = new UT_Tank(8000, this);
		return input;
	}
	public UT_Tank getTank(int level) {
		if(output[level] == null)
			output[level] = new UT_Tank(8000, this);
		return output[level];
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }
}
