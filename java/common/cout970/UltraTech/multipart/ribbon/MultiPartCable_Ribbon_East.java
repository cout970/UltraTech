package common.cout970.UltraTech.multipart.ribbon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.util.UT_Utils;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;

import common.cout970.UltraTech.multipart.MultipartUtil;
import common.cout970.UltraTech.multipart.client.RenderCablePlane;

public class MultiPartCable_Ribbon_East  extends MultiPartCable_Ribbon{

	@Override
	public String getType() {
		return base.getUnlocalizedName()+".east";
	}
	
	public ForgeDirection[] getSides(){
		return new ForgeDirection[]{ForgeDirection.UP,ForgeDirection.DOWN,ForgeDirection.NORTH,ForgeDirection.SOUTH};
	}
	
	@Override
	public Cuboid6[] setupBoundingBoxes() {
		double w = 0.125;
		float h = pixel*3;
		boundingBoxes = new Cuboid6[6];
		boundingBoxes[0] = new Cuboid6(1-h, 0.5-w,  0.5-w, 1,  0.5+w,  0.5+w);	//base 
		boundingBoxes[3] = new Cuboid6(1-h, 0,      0.5-w, 1,  0.5-w,  0.5+w);	//north 2 == bottom
		boundingBoxes[2] = new Cuboid6(1-h, 0.5+w,  0.5-w, 1,  1, 	   0.5+w);	//south 3 == top
		boundingBoxes[4] = new Cuboid6(1-h, 0.5-w,  0,     1,  0.5+w,  0.5-w);	//west 4 == west
		boundingBoxes[5] = new Cuboid6(1-h, 0.5-w,  0.5+w, 1,  0.5+w,  1);		//east 5 == east
		return boundingBoxes;
	}
	
	public int getBox(ForgeDirection side){
		if(side == ForgeDirection.UP)return 2;
		if(side == ForgeDirection.DOWN)return 3;
		if(side == ForgeDirection.NORTH)return 4;
		if(side == ForgeDirection.SOUTH)return 5;
		return 0;
	}
	
	@Override
    public Iterable<IndexedCuboid6> getSubParts() {
        Iterable<Cuboid6> boxList = getCollisionBoxes();
        LinkedList<IndexedCuboid6> partList = new LinkedList<IndexedCuboid6>();
        for (Cuboid6 c : boxList)
            partList.add(new IndexedCuboid6(0, c));
        ((ArrayList<Cuboid6>) boxList).clear();
        return partList;
    }
	
	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[0] });
	}

	private RenderCablePlane render;

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (pass == 0) {
			
			if (render == null) render = new RenderCablePlane();{
				render.render(this, pos);
			}
		}
	}
	
	public void updateConnections(){
		if(tile() == null)return;
		if(!MultipartUtil.isSolid(getTile(), ForgeDirection.EAST) && !world().isRemote){
			this.tile().dropItems(this.getDrops());
			try{
				this.tile().remPart(this);
			}catch(Exception e){}
			return;
		}
		connections = new boolean[6];
		
		for(ICable c : NetworkManagerRegistry.getConnections(tile())){
			if(c != this.getPower().getCable()){
				ForgeDirection side = c.getInternalConection(CableType.Ribbon_East, getPower().getCable());
				if(side != ForgeDirection.UNKNOWN){
					connections[getBox(side.getOpposite())] = true;
				}
			}
		}
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(d == ForgeDirection.WEST)continue;
			TileEntity tile = UT_Utils.getRelative(tile(), d);
			for(ICable c : NetworkManagerRegistry.getConnections(tile)){
				if(getPower().getCable().shouldConnectWithThis(c, d)){
					if(c.shouldConnectWithThis(getPower().getCable(), d.getOpposite())){
						connections[getBox(d)] = true;
					}
				}
			}
		}
	}
}
