package ultratech.api.power;

import java.util.List;

public interface IPathFinder {

	public void list(PowerInterface t);
	public List<IPowerConductor> getFinding();
	public boolean canGoToTheEnd();
	public IPowerConductor getEnd();
	public IPowerConductor getBase();
	public void search(IPowerConductor base, IPowerConductor end);
	public void clear();
	public void excludeAndRecalculate(IPowerConductor p);
}
