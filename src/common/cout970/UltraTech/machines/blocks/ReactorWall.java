package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.ReactorWallEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ReactorWall extends BlockContainer{


	private Icon blockIconR;


	public ReactorWall(int par1, Material par2Material) {
		super(par1, par2Material);
		setHardness(2.0f);
		setStepSound(soundGlassFootstep);
		setCreativeTab(UltraTech.techTab);
		setResistance(30);
		setUnlocalizedName("UTReactorWall");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:reactorwall");
		this.blockIconR = iconRegister.registerIcon("ultratech:reactorwallmulti");
	}

	@Override
	public Icon getBlockTexture(IBlockAccess BA, int x, int y, int z, int side)
    {
		
		int a,b,c,d,e,f;
		a = BA.getBlockId(x, y+1, z);
		b = BA.getBlockId(x, y-1, z);
		c = BA.getBlockId(x, y, z+1);
		d = BA.getBlockId(x, y, z-1);
		e = BA.getBlockId(x+1, y, z);
		f = BA.getBlockId(x-1, y, z);
		int r = UltraTech.Reactor.blockID;
		if(a == r || b == r || c == r || d == r || e == r || f == r){
			return this.blockIcon;
		}
		return this.blockIconR;
		
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		((ReactorWallEntity)world.getBlockTileEntity(x, y, z)).dell();
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public void onBlockAdded(World worldObj, int xCoord, int yCoord, int zCoord)
	{
		((ReactorWallEntity)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord)).work();
		super.onBlockAdded(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return false;
		}else{
			if(!par1World.isRemote){
				ReactorWallEntity tile = (ReactorWallEntity)par1World.getBlockTileEntity(x, y, z);
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
		return new ReactorWallEntity();
	}


}
