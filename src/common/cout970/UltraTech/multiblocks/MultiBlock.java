package common.cout970.UltraTech.multiblocks;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.Tier1.DestileryInEntity;
import common.cout970.UltraTech.TileEntities.Tier1.DestileryOutEntity;

public class MultiBlock {

	public List<IDestilery> comp = new ArrayList<IDestilery>();
	
	public static MultiBlock create(IDestilery base){
		MultiBlock n = new MultiBlock();
		n.comp.add(base);
		return n;
	}
	
	private MultiBlock(){}

	public void addComponent(IDestilery ref) {
		comp.add(ref);
		ref.setMulti(this);
	}

	public DestileryInEntity getIn() {
		for(IDestilery ref : comp){
			if(ref instanceof DestileryInEntity)return (DestileryInEntity) ref;
		}
		return null;
	}

	public void mergeWith(MultiBlock s) {
		for(IDestilery r : s.comp) if(!comp.contains(r))addComponent(r);
	}

	public List<DestileryOutEntity> getOut(int i) {
		List<DestileryOutEntity> f = new ArrayList<DestileryOutEntity>();
		for(IDestilery ref : comp){
			if(ref instanceof DestileryOutEntity){
				if(((int)(ref.getHeight()/3))== i)f.add((DestileryOutEntity) ref);
			}
		}
		return f;
	}
	
}
