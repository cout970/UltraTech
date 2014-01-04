package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.machines.tileEntities.ReactorEntity;

public interface IReactorPart {

	public void checkStructure();
	
	public ReactorEntity getReactor();
	
	public void onNeighChange();
}
