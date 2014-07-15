package common.cout970.UltraTech.blocks.reactor;

import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_Chamber_Entity;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Reactor_Chamber_Block extends ReactorPartBase{

	public Reactor_Chamber_Block(Material m) {
		super(m, "chamber");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int m) {
		if(m == 1)return new Reactor_Chamber_Entity();
		return null;
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
