package common.cout970.UltraTech.multiblocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.Tier1.RefineryInEntity;
import common.cout970.UltraTech.fluid.api.UT_Tank;

public class MultiBlock {

	public List<IRefinery> comp = new ArrayList<IRefinery>();
	private UT_Tank Gas;
	
	public static MultiBlock create(IRefinery base){
		MultiBlock n = new MultiBlock();
		n.comp.add(base);
		return n;
	}
	
	private MultiBlock(){}

	public UT_Tank getGasTank(TileEntity entity) {
		if(Gas == null){
			Gas = new UT_Tank(16000, entity);
		}
		return Gas;
	}

	public void addComponent(IRefinery ref) {
		comp.add(ref);
		ref.setMulti(this);
	}

	public List<RefineryInEntity> getIn() {

		List<RefineryInEntity> found = new ArrayList<RefineryInEntity>();
		for(IRefinery ref : comp){
			if(ref instanceof RefineryInEntity)found.add((RefineryInEntity) ref);
		}
		return found;
	}

	public void mergeWith(MultiBlock s) {
		for(IRefinery r : s.comp) if(!comp.contains(r))addComponent(r);
		Gas = s.Gas;
	}
	
}
