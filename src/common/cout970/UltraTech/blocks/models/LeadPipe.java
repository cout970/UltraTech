package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.Tier1.LeadPipeEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LeadPipe extends FluidPipeBlock{

	public LeadPipe(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:leadpipe");
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		LeadPipeEntity m = (LeadPipeEntity) w.getBlockTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
		m.tanks = null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new LeadPipeEntity();
	}

}
