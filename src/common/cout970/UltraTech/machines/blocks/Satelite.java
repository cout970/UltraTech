package common.cout970.UltraTech.machines.blocks;

import java.util.Random;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.SateliteEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class Satelite extends BlockContainer{

	private Icon blockIcon2;
	private Icon blockIcon3;

	public Satelite(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("satelite");		
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new SateliteEntity();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:satelite");
		this.blockIcon2 = iconRegister.registerIcon("ultratech:ids");
		this.blockIcon3 = iconRegister.registerIcon("ultratech:machinechasis");
	}
	
	@Override
	public Icon getIcon(int side,int a)
    {
		return side != 0 ? ((side == 1) ? this.blockIcon : this.blockIcon3) : this.blockIcon2;
    }
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te != null && !w.isRemote){
			if(te instanceof SateliteEntity){
				SateliteEntity r = (SateliteEntity)te;
				r.checkBase();
			}
		}
	}
	
	@Override
	public void onBlockAdded(World worldObj, int xCoord, int yCoord, int zCoord)
	{
		super.onBlockAdded(worldObj, xCoord, yCoord, zCoord);
		TileEntity te = worldObj.getBlockTileEntity(xCoord,yCoord,zCoord);
		if(te != null && !worldObj.isRemote){
			if(te instanceof SateliteEntity){
				SateliteEntity r = (SateliteEntity)te;
				r.checkBase();
			}
		}
	}
	
	public void randomDisplayTick(World worldObj, int xCoord, int yCoord, int zCoord, Random par5Random) {
		TileEntity te = worldObj.getBlockTileEntity(xCoord,yCoord,zCoord);
		if(te != null && !worldObj.isRemote){
			if(te instanceof SateliteEntity){
				SateliteEntity r = (SateliteEntity)te;
				r.checkBase();
			}
		}
	}
}
