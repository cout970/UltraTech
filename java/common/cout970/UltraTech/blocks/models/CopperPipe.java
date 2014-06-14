package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CopperPipe extends FluidPipeBlock{

	public CopperPipe(Material par2Material) {
		super(par2Material);
		setBlockName("CopperPipe");
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:copperpipe");
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		CopperPipeEntity m = (CopperPipeEntity) w.getTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world,int a) {
		return new CopperPipeEntity();
	}

}
