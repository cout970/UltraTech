package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.machines.tileEntities.ReactorEntity;

public interface IReactorPart {

	public void checkStructure();
	
	public void SearchReactor();
	
	public ReactorEntity getReactor();
	
	public void onNeighChange();
	
	public void setUp();
	
	public void activateBlocks();
	
	public void desactivateBlocks();
	
	public void setStructure(boolean structure);
	
	public void setReactor(ReactorEntity e);

	public boolean isStructure();
}
