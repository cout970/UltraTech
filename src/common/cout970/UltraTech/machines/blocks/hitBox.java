package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.machines.tileEntities.hitBoxEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class hitBox extends BlockContainer{

	public hitBox(int par1, Material par2Material) {
		super(par1, par2Material);
		setUnlocalizedName("satelite2");
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
	}
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:void");
	}
	public boolean isOpaqueCube()
	{
		return false;
	}


	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
		hitBoxEntity h = (hitBoxEntity) world.getBlockTileEntity(x, y, z);
		if(h != null){
			world.destroyBlock(h.x, h.y, h.z, true);
		}
		super.breakBlock(world, x, y, z, par5, par6);
    }
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new hitBoxEntity();
	}
}
