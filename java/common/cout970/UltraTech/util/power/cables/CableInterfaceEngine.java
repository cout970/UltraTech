package common.cout970.UltraTech.util.power.cables;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityEngine;

public class CableInterfaceEngine implements ICable{

	public TileEntityEngine source; 
	
	public CableInterfaceEngine(TileEntityEngine owner){
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
		return source.direction != dir;
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
		return source.direction != side;
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
