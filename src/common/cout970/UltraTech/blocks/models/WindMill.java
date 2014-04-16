package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.Tier1.hitBoxEntity;
import common.cout970.UltraTech.TileEntities.Tier2.WindMillEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.energy.api.EnergyUtils;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WindMill extends BlockContainer{

	public WindMill(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(0.5f);
		setStepSound(soundMetalFootstep);
		setResistance(20);
		setUnlocalizedName("WindMill");
		setBlockBounds(0f, 0f, 0f, 1f, 6f, 1f);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new WindMillEntity();	
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		Machine m = (Machine) w.getBlockTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:windmill");
	}
	
	@Override
	public void onBlockAdded(World par1World, int xCoord, int yCoord, int zCoord)
	{
		EnergyUtils.onBlockAdded( par1World, xCoord, yCoord, zCoord);
		super.onBlockAdded(par1World, xCoord, yCoord, zCoord);
		for(int d=1;d<5;d++){
			par1World.setBlock(xCoord, yCoord+d, zCoord, BlockManager.Misc.blockID);
			par1World.setBlockMetadataWithNotify(xCoord, yCoord+d, zCoord, BlockManager.Misc.blockID,3);
		}
		for(int d=1;d<5;d++){
			hitBoxEntity h =((hitBoxEntity)par1World.getBlockTileEntity(xCoord, yCoord+d, zCoord));
			if(h != null){
				h.x = xCoord;
				h.y = yCoord;
				h.z = zCoord;
			}
		}
	}

	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		EnergyUtils.onBlockPreDestroy(w, x, y, z, meta);
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		for(int d=1;d<5;d++){
			int id = par1World.getBlockId(par2, par3+d, par4);
			Block block = Block.blocksList[id];
			if(block != null && !block.isBlockReplaceable(par1World, par2, par3+d, par4)){
				return false;
			}
		}
		int l = par1World.getBlockId(par2, par3, par4);
		Block block = Block.blocksList[l];
		return block == null || block.isBlockReplaceable(par1World, par2, par3, par4);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		super.breakBlock(world, x, y, z, par5, par6);
		for(int d=1;d<5;d++){
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
		return ClientProxy.windmillRenderPass;
	}
}
