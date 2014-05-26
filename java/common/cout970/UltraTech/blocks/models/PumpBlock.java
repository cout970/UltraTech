package common.cout970.UltraTech.blocks.models;

import api.cout970.UltraTech.FTpower.BlockConductor;
import common.cout970.UltraTech.TileEntities.Tier1.hitBoxEntity;
import common.cout970.UltraTech.TileEntities.Tier2.PumpEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PumpBlock extends BlockConductor{

	public PumpBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
		setCreativeTab(UltraTech.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("Pump");
		setBlockBounds(0f, 0f, 0f, 1f, 3f, 1f);
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:pump");
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		super.onNeighborBlockChange(w, x, y, z, side);
		TileEntity te = w.getTileEntity(x, y, z);
		if(te != null){
			if(te instanceof PumpEntity)
				((PumpEntity) te).tanks = null;
			((PumpEntity) te).currentTank =-1;
		}
	}
	
	@Override
	public void onBlockAdded(World par1World, int xCoord, int yCoord, int zCoord)
	{
		super.onBlockAdded(par1World, xCoord, yCoord, zCoord);
		for(int d=1;d<3;d++){
			par1World.setBlock(xCoord, yCoord+d, zCoord, BlockManager.Misc);
			par1World.setBlockMetadataWithNotify(xCoord, yCoord+d, zCoord, 3,3);
		}
		for(int d=1;d<3;d++){
			hitBoxEntity h =((hitBoxEntity)par1World.getTileEntity(xCoord, yCoord+d, zCoord));
			if(h != null){
				h.x = xCoord;
				h.y = yCoord;
				h.z = zCoord;
			}
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
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		super.breakBlock(world, x, y, z, par5, par6);
		for(int d=1;d<3;d++){
			world.setBlockToAir(x, y+d, z);
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
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new PumpEntity();
	}

}
