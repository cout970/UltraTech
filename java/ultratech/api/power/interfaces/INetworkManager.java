package ultratech.api.power.interfaces;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.power.PowerInterface;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public interface INetworkManager {

	public void list(ICable t);
	public List<PowerInterface> getFinding();
	public boolean canGoToTheEnd();
	public IPowerConductor getEnd();
	public IPowerConductor getBase();
	public void search(IPowerConductor base, IPowerConductor end);
	public void clear();
	public void excludeAndRecalculate(IPowerConductor p);
	public void iterate(PowerInterface p);
	public List<ICable> getConnections(TileEntity t);
}
