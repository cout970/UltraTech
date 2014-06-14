package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.fluid.AluminumPipeEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AluminumPipe extends FluidPipeBlock{

	public AluminumPipe(Material par2Material) {
		super(par2Material);
		setBlockName("AluminumPipe");
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:aluminumpipe");
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		AluminumPipeEntity m = (AluminumPipeEntity) w.getTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
	}

	@Override
	public TileEntity createNewTileEntity(World world,int r) {
		return new AluminumPipeEntity();
	}
}
