package ultratech.api.power.interfaces;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.PowerInterface;

public interface ICable{

	public IPowerConductor getConductor();
	public PowerInterface getPower();

	//this machine can be connected on this side
	//without check the connection
	public boolean isOpenSide(ForgeDirection dir);
	
	//this is used to perform the path finder
	public World getWorldObj();
	public int[] getCoords();
	
	//the very important thing
	public boolean shouldConnectWithThis(ICable c, ForgeDirection side);
	
	public CableType getType();
	//this is for connection between multipart in the same block
	public ForgeDirection getInternalConection(CableType big, ICable cable);
}
