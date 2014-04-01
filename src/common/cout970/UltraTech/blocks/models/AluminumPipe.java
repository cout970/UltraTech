package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.Tier1.AluminumPipeEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AluminumPipe extends FluidPipeBlock{

	public AluminumPipe(int par1, Material par2Material) {
		super(par1, par2Material);
		setUnlocalizedName("AluminumPipe");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:aluminumpipe");
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		AluminumPipeEntity m = (AluminumPipeEntity) w.getBlockTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new AluminumPipeEntity();
	}
}
