package common.cout970.UltraTech.blocks.models;

import api.cout970.UltraTech.fluids.FluidUtils;
import api.cout970.UltraTech.fluids.Pipe;
import common.cout970.UltraTech.TileEntities.electric.PumpEntity;
import common.cout970.UltraTech.core.UltraTech;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class FluidPipeBlock extends BlockContainer{

	public FluidPipeBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundTypeMetal);
		setResistance(10);
		setHardness(1.5f);
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
		return -1;
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		TileEntity te = w.getTileEntity(x, y, z);
		if(te != null){
			if(te instanceof Pipe)
				if(((Pipe) te).getNetwork() != null)((Pipe) te).getNetwork().refresh();
		}
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
    	
    	boolean renderUp = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y + 1, z));
    	boolean renderDown = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y - 1, z));
    	boolean renderSouth = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z + 1));
    	boolean renderNorth = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z - 1));
    	boolean renderEast = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x + 1, y, z));
    	boolean renderWest = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x - 1, y, z));
    	
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
    	
    	boolean renderUp = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y + 1, z));
    	boolean renderDown = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y - 1, z));
    	boolean renderSouth = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z + 1));
    	boolean renderNorth = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z - 1));
    	boolean renderEast = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x + 1, y, z));
    	boolean renderWest = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x - 1, y, z));
    	
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
    	
    	boolean renderUp = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y + 1, z));
    	boolean renderDown = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y - 1, z));
    	boolean renderSouth = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z + 1));
    	boolean renderNorth = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z - 1));
    	boolean renderEast = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x + 1, y, z));
    	boolean renderWest = FluidUtils.CanPassFluid(w.getTileEntity(x, y, z), w.getTileEntity(x - 1, y, z));
    	
    	if(renderUp)   bb.maxY = 1;
    	if(renderDown) bb.minY = 0;
    	if(renderSouth)bb.maxZ = 1;
    	if(renderNorth)bb.minZ = 0;
    	if(renderEast) bb.maxX = 1;
    	if(renderWest) bb.minX = 0;
    	
    	setBlockBounds((float) bb.minX, (float) bb.minY, (float) bb.minZ, (float) bb.maxX, (float) bb.maxY, (float) bb.maxZ);
    }

}
