package ultratech.api.power;

import java.util.List;

import net.minecraftforge.common.util.ForgeDirection;

public interface INetworkManager {

	public void list(PowerInterface t);
	public List<IPowerConductor> getFinding();
	public boolean canGoToTheEnd();
	public IPowerConductor getEnd();
	public IPowerConductor getBase();
	public void search(IPowerConductor base, IPowerConductor end);
	public void clear();
	public void excludeAndRecalculate(IPowerConductor p);
	public void iterate(PowerInterface p);
	public boolean canConnectOnThisSide(PowerInterface from, ForgeDirection fromDir, PowerInterface to);
}
