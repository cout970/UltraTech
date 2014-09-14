package common.cout970.UltraTech.util.power.cables;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.prefab.CableInterfaceBlock;
import codechicken.multipart.NormallyOccludedPart;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Down;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_East;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_North;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_South;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Up;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_West;

public class CableInterfaceRibbonCable implements ICable{

		public MultiPartCable_Ribbon source; 
		
		public CableInterfaceRibbonCable(MultiPartCable_Ribbon multiPartCable_Ribbon){
			source = multiPartCable_Ribbon;
		}
		
		@Override
		public IPowerConductor getConductor() {
			return source;
		}

		@Override
		public PowerInterface getPower() {
			return source.getPower();
		}

		@Override
		public boolean isOpenSide(ForgeDirection dir) {
			return true;
		}

		@Override
		public World getWorldObj() {
			return source.getPower().getParent().getWorldObj();
		}

		@Override
		public int[] getCoords() {
			TileEntity t = source.getPower().getParent();
			return new int[]{t.xCoord,t.yCoord,t.zCoord};
		}

		@Override
		public boolean shouldConnectWithThis(ICable c, ForgeDirection side) {
			if(source.tile() == null)return false;
			if(getSidePlaced() == side)return true;
			if(source.boundingBoxes[source.getBox(side)] == null)return false;
			if(source.tile().canAddPart(new NormallyOccludedPart(source.boundingBoxes[source.getBox(side)]))){
				if(c.getType() == CableType.Block || c.getType() == getType())return true;
			}
			return false;
		}

		@Override
		public CableType getType() {
			if(source instanceof MultiPartCable_Ribbon_Down)return CableType.Ribbon_Down;
			if(source instanceof MultiPartCable_Ribbon_Up)return CableType.Ribbon_Up;
			if(source instanceof MultiPartCable_Ribbon_North)return CableType.Ribbon_North;
			if(source instanceof MultiPartCable_Ribbon_South)return CableType.Ribbon_South;
			if(source instanceof MultiPartCable_Ribbon_West)return CableType.Ribbon_West;
			if(source instanceof MultiPartCable_Ribbon_East)return CableType.Ribbon_East;
			return CableType.Ribbon_Down;
		}

		@Override
		public ForgeDirection getInternalConection(CableType big, ICable cable) {
			if(big == CableType.Big)return getSidePlaced().getOpposite();
			if(big != getOppositeType(big) && big != getOppositeType(getType()))return getSidePlaced().getOpposite();
			return ForgeDirection.UNKNOWN;
		}
		
		private CableType getOppositeType(CableType big) {
			if(big == CableType.Ribbon_Down)return CableType.Ribbon_Up;
			if(big == CableType.Ribbon_Up)return CableType.Ribbon_Down;
			if(big == CableType.Ribbon_North)return CableType.Ribbon_South;
			if(big == CableType.Ribbon_South)return CableType.Ribbon_North;
			if(big == CableType.Ribbon_East)return CableType.Ribbon_West;
			if(big == CableType.Ribbon_West)return CableType.Ribbon_East;
			return big;
		}

		public ForgeDirection getSidePlaced(){
			CableType big = getType();
			if(big == CableType.Ribbon_Down)return ForgeDirection.DOWN;
			if(big == CableType.Ribbon_Up)return ForgeDirection.UP;
			if(big == CableType.Ribbon_North)return ForgeDirection.NORTH;
			if(big == CableType.Ribbon_South)return ForgeDirection.SOUTH;
			if(big == CableType.Ribbon_East)return ForgeDirection.EAST;
			if(big == CableType.Ribbon_West)return ForgeDirection.WEST;
			return ForgeDirection.DOWN;
		}
	}