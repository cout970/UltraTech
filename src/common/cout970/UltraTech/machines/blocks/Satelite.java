package common.cout970.UltraTech.machines.blocks;



import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.SateliteEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Satelite extends BlockContainer{

	public Satelite(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("sat");
		setBlockBounds(-2, 0, 0, 3, 1, 1);//Sets the bounds of the block.  minX, minY, minZ, maxX, maxY, maxZ
		
	}

	@Override
	 public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		 
		world.destroyBlock(x+1, y, z, false);
		world.destroyBlock(x-1, y, z, false); 
		world.destroyBlock(x+2, y, z, false);
		world.destroyBlock(x-2, y, z, false);
		 super.breakBlock(world, x, y, z, par5, par6);
	 }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
    {
		world.setBlock(x+2, y, z, UltraTech.SateliteBox.blockID);
		world.setBlock(x+1, y, z, UltraTech.SateliteBox.blockID);
		 world.setBlock(x-1, y, z, UltraTech.SateliteBox.blockID);
		 world.setBlock(x-2, y, z, UltraTech.SateliteBox.blockID);
        super.onBlockAdded(world, x, y, z);
    }
	
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new SateliteEntity();
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	@Override
	public int getRenderType() {
		return -1;
	}
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:satelite");
	}
	

	


}
