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
import common.cout970.UltraTech.multipart.MultiPartCable_Ribbon_Down;

public class CableInterfaceRibbonCable implements ICable{

		public MultiPartCable_Ribbon_Down source; 
		
		public CableInterfaceRibbonCable(MultiPartCable_Ribbon_Down owner){
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
			return dir != ForgeDirection.UP;
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
			if(source.tile().canAddPart(new NormallyOccludedPart(source.boundingBoxes[side.ordinal()]))){
				if(c.getType() == CableType.Block || c.getType() == CableType.Ribbon)return true;
			}
			return false;
		}

		@Override
		public CableType getType() {
			return CableType.Ribbon;
		}

		@Override
		public ForgeDirection getInternalConection(CableType big, ICable cable) {
			if(big == CableType.Big)return ForgeDirection.UP;
			return ForgeDirection.UNKNOWN;
		}
	}