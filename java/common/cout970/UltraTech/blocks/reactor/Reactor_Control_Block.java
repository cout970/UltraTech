package common.cout970.UltraTech.blocks.reactor;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_Control_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_Core_Entity;
import common.cout970.UltraTech.TileEntities.utility.ReactorControllerEntity;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.misc.IReactorPart;
import common.cout970.UltraTech.util.LogHelper;

public class Reactor_Control_Block extends ReactorPartBase{

	public Reactor_Control_Block(Material m) {
		super(m, "control");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int m) {
		if(m == 1)return new Reactor_Control_Entity();
		return null;
	}
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		super.onBlockActivated(world, i, j, k, entityplayer, par6,par7,par8,par9);
		if(!entityplayer.isSneaking()){
			TileEntity tl = world.getTileEntity(i, j, k);
			if(tl instanceof Reactor_Control_Entity){
				Reactor_Control_Entity th = (Reactor_Control_Entity) tl;
				if(th.getCore() != null && th.isFormed()){
					Reactor_Core_Entity ce = (Reactor_Core_Entity) th.getCore();
					entityplayer.openGui(UltraTech.instance, 97, world, ce.xCoord, ce.yCoord, ce.zCoord);
				}
			}
		}
		return true;
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
