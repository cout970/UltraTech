package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.fluid.LeadPipeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LeadPipe extends FluidPipeBlock{

	public LeadPipe(Material par2Material) {
		super(par2Material);
		setBlockName("LeadPipe");
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:leadpipe");
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		LeadPipeEntity m = (LeadPipeEntity) w.getTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
		m.tanks = null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world,int a) {
		return new LeadPipeEntity();
	}

}
