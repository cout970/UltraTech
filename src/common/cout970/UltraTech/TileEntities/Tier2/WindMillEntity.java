package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WindMillEntity extends Machine{

	public float fan;
	public float speed = 0;
	public float wind = 1;
	public int timer;
	public boolean obs = false;
	public long oldTime;
	private float rand;
	public ForgeDirection facing = ForgeDirection.NORTH;

	public WindMillEntity(){
		super();
		this.tipe = MachineTipe.Output;
		this.tier = MachineTier.Tier2;
	}
	
	public void updateEntity()
	{
		super.updateEntity();
		
		float bioma = 1f;
		String b = worldObj.getBiomeGenForCoords(xCoord, zCoord).biomeName;
		if(b == "Desert")bioma = 1.5f;
		if(b == "Ocean")bioma = 1.1f;
		if(b == "Jungle")bioma = 0.8f;
		if(b == "Forest")bioma = 1f;
		if(b == "ExtremeHills")bioma = 1.4f;
		wind = (float) ((((float)yCoord*bioma)/64f)*rand);
		if(worldObj.isRaining())wind += 1;
		if(obs)wind = 0;
		
		if(speed > wind){
			speed -= 0.02;
		}else if(speed < wind){
			speed += 0.01f;
		}
		
		
		if(timer++ > 100){
			timer = 0;
			rand = (float) Math.random();
			if(isObstruid()){obs = true;}else{obs = false;}
		}
		
		if(worldObj.isRemote)return;
		addEnergy(speed*EnergyCosts.WindMillProduct);
		
	}

	private boolean isObstruid() {
		for(int x=-1;x<2;x++){
			for(int y=-1;y<2;y++){
				if(facing == ForgeDirection.SOUTH){
					if(!worldObj.isAirBlock(xCoord+x, yCoord+4+y, zCoord+1)){
						return true;
					}
				}else if(facing == ForgeDirection.WEST){
					if(!worldObj.isAirBlock(xCoord-1, yCoord+4+y, zCoord+x)){
						return true;
					}
				}else if(facing == ForgeDirection.EAST){
					if(!worldObj.isAirBlock(xCoord+1, yCoord+4+y, zCoord+x)){
						return true;
					}
				}else if(facing == ForgeDirection.NORTH){
					if(!worldObj.isAirBlock(xCoord+x, yCoord+4+y, zCoord-1)){
						return true;
					}
				}
			}
		}
		for(int h=1;h<20;h++){
			if(!worldObj.isAirBlock(xCoord+h*facing.offsetX, yCoord+4, zCoord+h*facing.offsetZ)){
				return true;
			}
		}
		return false;
	}
	
	public void switchDirection(){
		if(facing.ordinal() <5 )facing = ForgeDirection.getOrientation(facing.ordinal()+1);
		else facing = ForgeDirection.NORTH;
		UT_Utils.sendPacket(this);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		facing = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Dir", facing.ordinal());
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
            bb = AxisAlignedBB.getAABBPool().getAABB(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 2+5, zCoord + 2);
        
        return bb;
    }
}
