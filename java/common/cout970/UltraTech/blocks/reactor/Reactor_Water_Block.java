package common.cout970.UltraTech.blocks.reactor;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Water_Entity;

public class Reactor_Water_Block extends ReactorPartBase{

	public Reactor_Water_Block(Material m) {
		super(m, "water_provider");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new Reactor_Water_Entity();
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
