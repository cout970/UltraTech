package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.fluid.api.FluidUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class FluidPipeBlock extends BlockContainer{

	public FluidPipeBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundMetalFootstep);
//		setResistance(10);
		setHardness(0.5f);
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
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public void onBlockAdded(World w, int x, int y, int z) {
		super.onBlockAdded(w, x, y, z);
		FluidUtils.onBlockAdded(w, x, y, z);
	}
	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		super.onBlockPreDestroy(w, x, y, z, meta);
		FluidUtils.onBlockPreDestroy(w, x, y, z, meta);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
    	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(x + (0.0625 * 5), y + (0.0625 * 5), z + (0.0625 * 5), (x + 1) - (0.0625 * 5), (y + 1) - (0.0625 * 5), (z + 1) - (0.0625 * 5));
    	
    	boolean renderUp = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y + 1, z));
    	boolean renderDown = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y - 1, z));
    	boolean renderSouth = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y, z + 1));
    	boolean renderNorth = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y, z - 1));
    	boolean renderEast = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x + 1, y, z));
    	boolean renderWest = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x - 1, y, z));
    	
    	if(renderUp)   bb.maxY = y + 1;
    	if(renderDown) bb.minY = y;
    	if(renderSouth)bb.maxZ = z + 1;
    	if(renderNorth)bb.minZ = z;
    	if(renderEast) bb.maxX = x + 1;
    	if(renderWest) bb.minX = x;
    	
    	return bb;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
    	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(x + (0.0625 * 5), y + (0.0625 * 5), z + (0.0625 * 5), (x + 1) - (0.0625 * 5), (y + 1) - (0.0625 * 5), (z + 1) - (0.0625 * 5));
    	
    	boolean renderUp = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y + 1, z));
    	boolean renderDown = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y - 1, z));
    	boolean renderSouth = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y, z + 1));
    	boolean renderNorth = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y, z - 1));
    	boolean renderEast = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x + 1, y, z));
    	boolean renderWest = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x - 1, y, z));
    	
    	if(renderUp)   bb.maxY = y + 1;
    	if(renderDown) bb.minY = y;
    	if(renderSouth)bb.maxZ = z + 1;
    	if(renderNorth)bb.minZ = z;
    	if(renderEast) bb.maxX = x + 1;
    	if(renderWest) bb.minX = x;
    	
    	return bb;
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z) {
    	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((0.0625 * 5),(0.0625 * 5),(0.0625 * 5), (1) - (0.0625 * 5), (1) - (0.0625 * 5), (1) - (0.0625 * 5));
    	
    	boolean renderUp = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y + 1, z));
    	boolean renderDown = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y - 1, z));
    	boolean renderSouth = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y, z + 1));
    	boolean renderNorth = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x, y, z - 1));
    	boolean renderEast = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x + 1, y, z));
    	boolean renderWest = FluidUtils.CanPassFluid(w.getBlockTileEntity(x, y, z), w.getBlockTileEntity(x - 1, y, z));
    	
    	if(renderUp)   bb.maxY = 1;
    	if(renderDown) bb.minY = 0;
    	if(renderSouth)bb.maxZ = 1;
    	if(renderNorth)bb.minZ = 0;
    	if(renderEast) bb.maxX = 1;
    	if(renderWest) bb.minX = 0;
    	
    	setBlockBounds((float) bb.minX, (float) bb.minY, (float) bb.minZ, (float) bb.maxX, (float) bb.maxY, (float) bb.maxZ);
    }

}
