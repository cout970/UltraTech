package common.cout970.UltraTech.TileEntities.multiblocks.refinery;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import ultratech.api.recipes.Refinery_Recipe;
import ultratech.api.refinery.IRefineryCoreEntity;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Refinery_Core_Entity extends Refinery_Entity_Base implements IRefineryCoreEntity{

	public List<Refinery_IO_Entity> IO;
	public UT_Tank in;
	public UT_Tank out1;
	public UT_Tank out2;
	public UT_Tank out3;
	public Refinery_Recipe recipe;
	public float progres1;
	public float progres2;
	public float progres3;
	public static final int speed = 2;
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 9, zCoord + 2);
		return bb;
	}

	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(IO == null)updateComponents();
		if(recipe != null && in != null && out1 != null && out2 != null && out3 != null){
			if(in.getFluid() != null && in.getFluid().fluidID != recipe.input.fluidID){
				recipe = Refinery_Recipe.getResult(in);
				return;
			}
			if(in.getFluidAmount() >= recipe.input.amount*speed){
				if(recipe.out1 == null || out1.fill(new FluidStack(recipe.out1, (int)recipe.amount1*speed), false) >= (int)recipe.amount1*speed){
					if(recipe.out2 == null || out2.fill(new FluidStack(recipe.out2, (int)recipe.amount2*speed), false) >= (int)recipe.amount2*speed){
						if(recipe.out3 == null || out3.fill(new FluidStack(recipe.out3, (int)recipe.amount3*speed), false) >= (int)recipe.amount3*speed){
							Progres();
						}
					}
				}
			}
		}
	}

	private void Progres() {
		progres1 += recipe.amount1*speed;
		progres2 += recipe.amount2*speed;
		progres3 += recipe.amount3*speed;
		in.drain(recipe.input.amount*speed, true);
		
		while(progres1 >= 1){
			progres1--;
			out1.fill(new FluidStack(recipe.out1, 1),true);
		}
		while(progres2 >= 1){
			progres2--;
			out2.fill(new FluidStack(recipe.out2, 1),true);
		}
		while(progres3 >= 1){
			progres3--;
			out3.fill(new FluidStack(recipe.out3, 1),true);
		}
	}

	public void updateComponents(){
		getBase();
		boolean[] found = new boolean[4];

		for(Refinery_IO_Entity b : IO){
			if(b.mode == 1 && !found[0]){
				found[0] = true;
				in = b.getTank();
			}else if(b.mode == 2 && !found[1]){
				found[1] = true;
				out1 = b.getTank();
			}else if(b.mode == 3 && !found[2]){
				found[2] = true;
				out2 = b.getTank();
			}else if(b.mode == 4 && !found[3]){
				found[3] = true;
				out3 = b.getTank();
			}
		}
		if(in != null)recipe = Refinery_Recipe.getResult(in);
	}


	public void getBase() {
		IO = new ArrayList<Refinery_IO_Entity>();
		for(int j=0;j<3;j++){
			for(int i=0;i<3;i++){
				for(int k=0;k<3;k++){
					TileEntity t = worldObj.getTileEntity(xCoord+i-1, yCoord-j+1, zCoord+k-1);
					if(t instanceof Refinery_IO_Entity){
						IO.add((Refinery_IO_Entity) t);
					}
				}
			}
		}
	}
}
