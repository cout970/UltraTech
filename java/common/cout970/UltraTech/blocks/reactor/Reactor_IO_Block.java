package common.cout970.UltraTech.blocks.reactor;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_IO_Entity;

public class Reactor_IO_Block extends ReactorPartBase{

	public Reactor_IO_Block(Material m) {
		super(m, "io");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new Reactor_IO_Entity();
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

}
