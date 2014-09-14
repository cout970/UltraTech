package common.cout970.UltraTech.blocks.reactor;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Redstone_Entity;

public class Reactor_Redstone_Control_Block extends ReactorPartBaseBlock{

	public Reactor_Redstone_Control_Block(Material m) {
		super(m, "redstone_control");
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		if(m == 1)return new Reactor_Redstone_Entity();
		return null;
	}

}
