package common.cout970.UltraTech.microparts;

import ultratech.api.power.IPowerConductor;
import ultratech.api.power.PowerUtils;
import ultratech.api.power.SimpleCable;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.Pipe;
import common.cout970.UltraTech.util.power.BlockConductor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CableBlock extends BlockConductor{

	public static float d = 0.0625f;
	public static int p = 4;
	
	public CableBlock(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setStepSound(soundTypeMetal);
		setResistance(10);
		setHardness(1.0f);
		setBlockName("CableBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new Cable_Entity();
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
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:cable");
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		TileEntity te = w.getTileEntity(x, y, z);
		if(te != null){
			if(te instanceof IPowerConductor)
				if(((IPowerConductor) te).getPower().getNetwork() != null)((IPowerConductor) te).getPower().getNetwork().refresh();
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
    	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(x + (d * p), y + (d * p), z + (d * p), (x + 1) - (d * p), (y + 1) - (d * p), (z + 1) - (d * p));
    	
    	boolean renderUp = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y + 1, z));
    	boolean renderDown = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y - 1, z));
    	boolean renderSouth = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z + 1));
    	boolean renderNorth = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z - 1));
    	boolean renderEast = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x + 1, y, z));
    	boolean renderWest = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x - 1, y, z));
    	
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
    	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(x + (d * p), y + (d * p), z + (d * p), (x + 1) - (d * p), (y + 1) - (d * p), (z + 1) - (d * p));
    	
    	boolean renderUp = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y + 1, z));
    	boolean renderDown = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y - 1, z));
    	boolean renderSouth = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z + 1));
    	boolean renderNorth = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z - 1));
    	boolean renderEast = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x + 1, y, z));
    	boolean renderWest = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x - 1, y, z));
    	
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
    	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((d * p),(d * p),(d * p), (1) - (d * p), (1) - (d * p), (1) - (d * p));
    	
    	boolean renderUp = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y + 1, z));
    	boolean renderDown = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y - 1, z));
    	boolean renderSouth = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z + 1));
    	boolean renderNorth = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x, y, z - 1));
    	boolean renderEast = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x + 1, y, z));
    	boolean renderWest = PowerUtils.canConnectTo(w.getTileEntity(x, y, z), w.getTileEntity(x - 1, y, z));
    	
    	if(renderUp)   bb.maxY = 1;
    	if(renderDown) bb.minY = 0;
    	if(renderSouth)bb.maxZ = 1;
    	if(renderNorth)bb.minZ = 0;
    	if(renderEast) bb.maxX = 1;
    	if(renderWest) bb.minX = 0;
    	
    	setBlockBounds((float) bb.minX, (float) bb.minY, (float) bb.minZ, (float) bb.maxX, (float) bb.maxY, (float) bb.maxZ);
    }

}
