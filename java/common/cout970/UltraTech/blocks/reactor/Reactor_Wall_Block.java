package common.cout970.UltraTech.blocks.reactor;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Wall_Entity;

public class Reactor_Wall_Block extends ReactorPartBaseBlock{

	public Reactor_Wall_Block(Material m) {
		super(m, "wall");
	}

	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		if(m == 1)return new Reactor_Wall_Entity();
		return null;
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

}
