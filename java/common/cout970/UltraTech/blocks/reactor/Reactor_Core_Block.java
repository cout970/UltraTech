package common.cout970.UltraTech.blocks.reactor;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultratech.api.reactor.IReactorCoreBlock;
import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_Core_Entity;

public class Reactor_Core_Block extends ReactorPartBase implements IReactorCoreBlock{

	public Reactor_Core_Block(Material m) {
		super(m, "core");
	}

	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		if(m == 1)return new Reactor_Core_Entity();
		return null;
	}

	@Override
	public int getLayer() {
		return 0;
	}
}
