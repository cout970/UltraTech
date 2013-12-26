package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.ReactorTankEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ReactorTank extends BlockContainer{

	public ReactorTank(int par1, Material par2Material) {
		super(par1, par2Material);
		setHardness(2.0f);
		setStepSound(soundGlassFootstep);
		setCreativeTab(UltraTech.techTab);
		setResistance(30);
		setUnlocalizedName("UTReactorTank");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:reactortank");
	}
	public int getRenderBlockPass()
	{
		return 0;
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
		((ReactorTankEntity)world.getBlockTileEntity(x, y, z)).dell();
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public void onBlockAdded(World worldObj, int xCoord, int yCoord, int zCoord)
	{
		((ReactorTankEntity)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord)).work();
		super.onBlockAdded(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return true;
		}else{
			if(!par1World.isRemote){
				ReactorTankEntity tile = (ReactorTankEntity)par1World.getBlockTileEntity(x, y, z);
				if(tile != null){ 
					if(tile.multi){
						par5EntityPlayer.openGui(UltraTech.instance, 13, par1World, tile.x, tile.y, tile.z);
					}else{
						tile.work();
					}
					return true;
				}
			}
		}
		return true;
	}


	@Override
	public TileEntity createNewTileEntity(World world) {
		return new ReactorTankEntity();
	}
}
