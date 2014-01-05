package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.WaterBlockEntity;
import common.cout970.UltraTech.misc.IReactorPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class WaterBlock extends BlockContainer{

	private Icon blockIconTop;

	public WaterBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("WaterBlock");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:waterblock");
		this.blockIconTop = iconRegister.registerIcon("ultratech:machinechasis");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 || par1 == 0 ? this.blockIconTop : this.blockIcon;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new WaterBlockEntity();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileEntity t = world.getBlockTileEntity(x, y, z);
		((IReactorPart)t).desactivateBlocks();
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public void onBlockAdded(World worldObj, int xCoord, int yCoord, int zCoord)
	{
		TileEntity t = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
		((IReactorPart)t).onNeighChange();
		super.onBlockAdded(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return true;
		}else{
			if(!par1World.isRemote){
				TileEntity tile = par1World.getBlockTileEntity(x, y, z);
				if(tile != null){ 
					IReactorPart p = (IReactorPart) tile;
					if(p.isStructure() && p.getReactor() != null){
						par5EntityPlayer.openGui(UltraTech.instance, 13, par1World, p.getReactor().xCoord, p.getReactor().yCoord, p.getReactor().zCoord);
					}else{
						p.onNeighChange();
					}
					return true;
				}
			}
		}
		return true;
	}
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te != null && !w.isRemote){
			if(te instanceof IReactorPart){
				IReactorPart r = (IReactorPart)te;
				r.onNeighChange();
			}
		}
	}
}
