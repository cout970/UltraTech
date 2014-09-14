package common.cout970.UltraTech.util.power.cables;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntitySolarPanelT1;
import common.cout970.UltraTech.TileEntities.utility.TileEntityLaserTurret;


public class CableInterfaceLaserTurret implements ICable{
		
		public TileEntityLaserTurret source; 
		
		public CableInterfaceLaserTurret(TileEntityLaserTurret tileEntityLaserTurret){
			source = tileEntityLaserTurret;
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
			if(side != ForgeDirection.DOWN){
				if(c.getType() == CableType.Ribbon_Down || c.getType() == CableType.Block){
					return true;
				}else return false;
			}
			return true;
		}

		@Override
		public CableType getType() {
			return CableType.Ribbon_Down;
		}

		@Override
		public ForgeDirection getInternalConection(CableType big, ICable cable) {
			return ForgeDirection.UNKNOWN;
		}
	}