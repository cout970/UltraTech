package common.cout970.UltraTech.blocks.refinery;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultratech.api.refinery.IRefineryCoreBlock;

import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Core_Entity;

public class Refinery_Core_Block extends RefineryPartBaseBlock implements IRefineryCoreBlock{

	public Refinery_Core_Block(Material m) {
		super(m, "refinery_core");
		setLightOpacity(0);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int m) {
		if(m == 1)return new Refinery_Core_Entity();
		return null;
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
