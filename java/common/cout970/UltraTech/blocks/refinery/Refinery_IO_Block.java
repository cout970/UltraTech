package common.cout970.UltraTech.blocks.refinery;

import common.cout970.UltraTech.TileEntities.multiblocks.refinery.RefineryMultiblockTweak;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_IO_Entity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UltraTech;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Refinery_IO_Block extends RefineryPartBase{

	public Refinery_IO_Block(Material m) {
		super(m, "refinery_io");
		setLightOpacity(0);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		return new Refinery_IO_Entity();
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(p.isSneaking()){
			TileEntity t = w.getTileEntity(x, y, z);
			if(t instanceof Refinery_IO_Entity){
				int m = ((Refinery_IO_Entity) t).getMode();
				if(m == 1)((Refinery_IO_Entity) t).setMode(2);
				if(m == 2)((Refinery_IO_Entity) t).setMode(3);
				if(m == 3)((Refinery_IO_Entity) t).setMode(4);
				if(m == 4)((Refinery_IO_Entity) t).setMode(1);
			}
		}else{
			p.openGui(UltraTech.instance, 14, w, x, y, z);
		}
		return false;
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[5];
		icons[0] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_off");
		icons[1] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_in");
		icons[2] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_out");
		icons[3] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_out1");
		icons[4] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_out2");
	}
	
	public IIcon getIcon(int side, int meta){
		if(meta < 5)return icons[meta];
		return icons[0];
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
