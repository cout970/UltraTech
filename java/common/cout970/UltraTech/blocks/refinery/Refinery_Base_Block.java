package common.cout970.UltraTech.blocks.refinery;

import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Entity_Base;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Structure_Entity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Refinery_Base_Block extends RefineryPartBaseBlock{

	public Refinery_Base_Block(Material m) {
		super(m, "refinery_base");
		setLightOpacity(0);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int m) {
		if(m == 1)return new Refinery_Structure_Entity();
		return null;
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
