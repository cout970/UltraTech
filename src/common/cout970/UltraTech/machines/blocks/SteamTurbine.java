package common.cout970.UltraTech.machines.blocks;


import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.SteamTurbineEntity;
import common.cout970.UltraTech.misc.IReactorPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SteamTurbine extends BlockContainer{

	private Icon IconFront;

	public SteamTurbine(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("SteamTurbine");
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
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:turbine");
		this.IconFront = iconRegister.registerIcon("ultratech:machinechasis");
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
		return (side == 0 || side ==1)? this.blockIcon : this.IconFront;
		
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 || par1 == 0 ? this.blockIcon : this.IconFront;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new SteamTurbineEntity();
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
}
