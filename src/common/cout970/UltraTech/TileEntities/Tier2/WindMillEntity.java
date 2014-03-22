package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;
import net.minecraft.util.AxisAlignedBB;
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
			speed -= 0.01;
		}else if(speed < wind){
			speed += 0.005f;
		}
		
		if(timer++ > 200){
			timer = 0;
			rand = (float) Math.random();
			if(isObstruid()){obs = true;}else{obs = false;}
		}
		
		if(worldObj.isRemote)return;
		addEnergy((int)(speed*GraficCost.WindMillProduct));
		
	}

	private boolean isObstruid() {
		for(int x=-1;x<2;x++){
			for(int y=-1;y<2;y++){
				if(!worldObj.isAirBlock(xCoord+x, yCoord+4+y, zCoord+1)){
					return true;
				}
			}
		}
		for(int h=0;h<20;h++){
			if(!worldObj.isAirBlock(xCoord, yCoord+4, zCoord+1+h)){
				return true;
			}
		}
		return false;
	}
	
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
            bb = AxisAlignedBB.getAABBPool().getAABB(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 2+5, zCoord + 2);
        
        return bb;
    }
}
