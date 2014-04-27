package common.cout970.UltraTech.multiblocks;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.Tier1.RefineryInEntity;
import common.cout970.UltraTech.TileEntities.Tier1.RefineryOutEntity;

public class MultiBlock {

	public List<IRefinery> comp = new ArrayList<IRefinery>();
	
	public static MultiBlock create(IRefinery base){
		MultiBlock n = new MultiBlock();
		n.comp.add(base);
		return n;
	}
	
	private MultiBlock(){}

	public void addComponent(IRefinery ref) {
		comp.add(ref);
		ref.setMulti(this);
	}

	public RefineryInEntity getIn() {
		for(IRefinery ref : comp){
			if(ref instanceof RefineryInEntity)return (RefineryInEntity) ref;
		}
		return null;
	}

	public void mergeWith(MultiBlock s) {
		for(IRefinery r : s.comp) if(!comp.contains(r))addComponent(r);
	}

	public List<RefineryOutEntity> getOut(int i) {
		List<RefineryOutEntity> f = new ArrayList<RefineryOutEntity>();
		for(IRefinery ref : comp){
			if(ref instanceof RefineryOutEntity){
				if(((int)(ref.getHeight()/3))== i)f.add((RefineryOutEntity) ref);
			}
		}
		return f;
	}
	
}
