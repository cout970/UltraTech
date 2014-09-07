package common.cout970.UltraTech.multipart.ribbon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.CableType;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.util.UT_Utils;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.render.RenderUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;

import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.multipart.MultipartUtil;
import common.cout970.UltraTech.multipart.client.RenderCablePlane;
import common.cout970.UltraTech.util.render.RenderUtil;

public class MultiPartCable_Ribbon_South extends MultiPartCable_Ribbon{

	@Override
	public String getType() {
		return base.getUnlocalizedName()+".south";
	}
	
	public ForgeDirection[] getSides(){
		return new ForgeDirection[]{ForgeDirection.UP,ForgeDirection.DOWN,ForgeDirection.EAST,ForgeDirection.WEST};
	}
	
	@Override
	public Cuboid6[] setupBoundingBoxes() {
		double w = 0.125;
		float h = pixel*3;
		boundingBoxes = new Cuboid6[6];
		//north
		boundingBoxes[0] = new Cuboid6(0.5-w, 0.5-w,  1-h, 0.5+w, 0.5+w, 1);	//base 
		boundingBoxes[3] = new Cuboid6(0.5-w, 0,  	  1-h,   0.5+w, 0.5-w, 1);	//north 2 == bottom
		boundingBoxes[2] = new Cuboid6(0.5-w, 0.5+w,  1-h, 0.5+w, 1, 1);		//south 3 == top
		boundingBoxes[4] = new Cuboid6(0,     0.5-w,  1-h, 0.5-w, 0.5+w, 1);	//west 4 == west
		boundingBoxes[5] = new Cuboid6(0.5+w, 0.5-w,  1-h, 1,     0.5+w, 1);	//east 5 == east
		return boundingBoxes;
	}
	
	public int getBox(ForgeDirection side){
		if(side == ForgeDirection.UP)return 2;
		if(side == ForgeDirection.DOWN)return 3;
		if(side == ForgeDirection.WEST)return 4;
		if(side == ForgeDirection.EAST)return 5;
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
		if(!MultipartUtil.isSolid(getTile(), ForgeDirection.SOUTH) && !world().isRemote){
			this.tile().dropItems(this.getDrops());
			try{
				this.tile().remPart(this);
			}catch(Exception e){}
			return;
		}
		connections = new boolean[6];
		
		for(ICable c : NetworkManagerRegistry.getConnections(tile())){
			if(c != this.getPower().getCable()){
				ForgeDirection side = c.getInternalConection(CableType.Ribbon_South, getPower().getCable());
				if(side != ForgeDirection.UNKNOWN){
					connections[getBox(side.getOpposite())] = true;
				}
			}
		}
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(d == ForgeDirection.NORTH)continue;
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
