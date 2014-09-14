package common.cout970.UltraTech.blocks.models;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import common.cout970.UltraTech.TileEntities.electric.TileEntityPump;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.proxy.ClientProxy;
import common.cout970.UltraTech.util.power.BlockConductor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPump extends BlockConductor{

	public BlockPump(Material p_i45386_1_) {
		super(p_i45386_1_);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("Pump");
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Block_Textures.PARTICLE_COPPER);
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		super.onNeighborBlockChange(w, x, y, z, side);
		IUpdatedEntity i = (IUpdatedEntity) w.getTileEntity(x, y, z);
		if(i != null)i.onNeigUpdate();
	}
	
	@Override
	public void onBlockAdded(World w, int xCoord, int yCoord, int zCoord)
	{
		super.onBlockAdded(w, xCoord, yCoord, zCoord);
		Block b = w.getBlock(xCoord, yCoord-1, zCoord);
		if(b != this || (w.getBlockMetadata(xCoord, yCoord-1, zCoord) == 2)){
			w.setBlock(xCoord, yCoord+1, zCoord, this);
			w.setBlockMetadataWithNotify(xCoord, yCoord+1, zCoord, 1, 3);
			w.setBlock(xCoord, yCoord+2, zCoord, this);
			w.setBlockMetadataWithNotify(xCoord, yCoord+2, zCoord, 2, 3);
		}
	}
	
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		for(int d=1;d<3;d++){
			Block block = par1World.getBlock(par2, par3+d, par4);
			if(block != null && !block.isReplaceable(par1World, par2, par3+d, par4)){
				return false;
			}
		}
		Block block = par1World.getBlock(par2, par3, par4);
		return block == null || block.isReplaceable(par1World, par2, par3, par4);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int m)
	{
		super.breakBlock(world, x, y, z, par5, m);
		int m1 = 2-m;
		for(;m>0;m--){
			world.setBlockToAir(x, y-m, z);
		}
		for(;m1>0;m1--){
			world.setBlockToAir(x, y+m1, z);
		}
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.pumpRenderPass;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int meta) {
		if(meta == 2)return new TileEntityPump();
		return null;
	}

}
