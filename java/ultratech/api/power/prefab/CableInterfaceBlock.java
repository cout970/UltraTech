package ultratech.api.power.prefab;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;

public class CableInterfaceBlock implements ICable{

	public IPowerConductor source; 
	
	public CableInterfaceBlock(IPowerConductor owner){
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
		return true;
	}

	@Override
	public CableType getType() {
		return CableType.Block;
	}

	@Override
	public ForgeDirection getInternalConection(CableType big, ICable cable) {
		return ForgeDirection.UNKNOWN;
	}
}
