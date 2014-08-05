package common.cout970.UltraTech.multiblocks.refinery;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.recipes.Cooling_Recipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RefineryCore extends TileGag{
	
	public List<RefineryBase> IO;
	public UT_Tank in;
	public UT_Tank out1;
	public UT_Tank out2;
	public UT_Tank out3;
	public Cooling_Recipes recipe;

	public void restaureBlock(){
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		worldObj.removeTileEntity(xCoord, yCoord, zCoord);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(IO == null)getBase();
		
		if(recipe != null && in != null && out1 != null && out2 != null && out3 != null){
			if(in.getFluidAmount() >= recipe.input.amount){
				if(recipe.product[0] == null || out1.fill(recipe.product[0], false) >= recipe.product[0].amount)
					if(recipe.product[1] == null || out2.fill(recipe.product[1], false) >= recipe.product[1].amount)
						if(recipe.product[2] == null || out3.fill(recipe.product[2], false) >= recipe.product[2].amount){
							craft();
						}
			}
		}


	}
	
	public void update(){
		boolean[] found = new boolean[4];

		for(RefineryBase b : IO){
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
		if(in != null)recipe = Cooling_Recipes.getResult(in);
	}


	private void getBase() {
		IO = new ArrayList<RefineryBase>();
		for(int j=0;j<3;j++){
			for(int i=0;i<3;i++){
				for(int k=0;k<3;k++){
					TileEntity t = worldObj.getTileEntity(xCoord+i-1, yCoord-j, zCoord+k-1);
					if(t instanceof RefineryBase){
						IO.add((RefineryBase) t);
						((RefineryBase) t).main = this;
					}
				}
			}
		}
		this.update();
	}

	public void craft(){
		in.drain(recipe.input.amount, true);
		out1.fill(recipe.product[0],true);
		out2.fill(recipe.product[1],true);
		out3.fill(recipe.product[2],true);
	}



	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }
}
