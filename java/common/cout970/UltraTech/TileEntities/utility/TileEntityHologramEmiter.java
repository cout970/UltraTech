package common.cout970.UltraTech.TileEntities.utility;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.misc.ItemDisplay;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.render.IHologram;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityHologramEmiter extends SyncTile implements IUpdatedEntity{

	public boolean update = false;
	public IHologram rend;
	public float mX=0f, mY=2f, mZ=0f;
	public ForgeDirection facing = ForgeDirection.NORTH;

	public TileEntityHologramEmiter(){
		super();
	}
	
	public void updateEntity(){
		if(rend == null){			
			rend = new ItemDisplay(worldObj, xCoord, yCoord, zCoord);
		}
		rend.update();
	}
	
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox(){
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }

	@Override
	public void onNeigUpdate() {
		if(rend != null)rend.onNeigUpdate();
	}
	
	public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        if(rend != null)rend.readNBT(nbt);
        mX = nbt.getFloat("mX");
        mY = nbt.getFloat("mY");
        mZ = nbt.getFloat("mZ");
        facing = ForgeDirection.getOrientation(nbt.getInteger("Dir"));
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
    	 super.writeToNBT(nbt);
    	 if(rend != null)rend.writeNBT(nbt);
    	 nbt.setFloat("mX", mX);
    	 nbt.setFloat("mY", mY);
    	 nbt.setFloat("mZ", mZ);
    	 nbt.setInteger("Dir", facing.ordinal());
    }
}
