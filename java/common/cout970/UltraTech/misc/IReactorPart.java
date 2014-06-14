package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.TileEntities.utility.ReactorEntity;

public interface IReactorPart {

	public void checkStructure();
	
	public void SearchReactor();
	
	public ReactorEntity getReactor();
	
	public void onNeighChange();
		
	public void activateBlocks();
	
	public void desactivateBlocks();
	
	public void setStructure(boolean structure);
	
	public void setReactor(ReactorEntity e);

	public boolean isStructure();
}
