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
import common.cout970.UltraTech.multipart.MultiPartCable_Big;

public class CableInterfaceBigCable implements ICable{

		public MultiPartCable_Big source; 
		
		public CableInterfaceBigCable(MultiPartCable_Big owner){
			source = owner;
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
			if(source.tile().canAddPart(new NormallyOccludedPart(source.boundingBoxes[side.ordinal()])) && c.isOpenSide(side.getOpposite())){
				return true;
			}
			return false;
		}

		@Override
		public CableType getType() {
			return CableType.Big;
		}

		@Override
		public ForgeDirection getInternalConection(CableType big, ICable cable) {
			if(big == CableType.Ribbon_Down)return ForgeDirection.DOWN;
			if(big == CableType.Ribbon_Up)return ForgeDirection.UP;
			if(big == CableType.Ribbon_North)return ForgeDirection.NORTH;
			if(big == CableType.Ribbon_South)return ForgeDirection.SOUTH;
			if(big == CableType.Ribbon_East)return ForgeDirection.EAST;
			if(big == CableType.Ribbon_West)return ForgeDirection.WEST;
			return ForgeDirection.UNKNOWN;
		}
	}