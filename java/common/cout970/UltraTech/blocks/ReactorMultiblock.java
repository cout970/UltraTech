package common.cout970.UltraTech.blocks;

import java.util.List;
import java.util.Random;

import common.cout970.UltraTech.TileEntities.Tier3.ReactorControllerEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorTankEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorWallEntity;
import common.cout970.UltraTech.TileEntities.Tier3.SteamExtractorEntity;
import common.cout970.UltraTech.TileEntities.Tier3.WaterBlockEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.IReactorPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ReactorMultiblock extends BlockContainer{

	public IIcon icons[][];
	public int numBlocks = 6;
	
	public ReactorMultiblock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.5f);
		setBlockName("UT_ReactorMultiblock");
	}
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			if(world.getTileEntity(i, j, k) instanceof ReactorControllerEntity)entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
			return true;
		}else{
			TileEntity tile = world.getTileEntity(i, j, k);
			if(tile != null){ 
				if(tile instanceof IReactorPart){
					IReactorPart p = (IReactorPart) tile;	
					p.onNeighChange();
					if(p.isStructure() && p.getReactor() != null){
						entityplayer.openGui(UltraTech.instance, 13, world, p.getReactor().xCoord, p.getReactor().yCoord, p.getReactor().zCoord);
					}
					return true;
				}
			}
		}
		return true;
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		TileEntity te = w.getTileEntity(x, y, z);
		if(te != null){
			if(te instanceof IReactorPart)
				((IReactorPart)te).onNeighChange();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[numBlocks][];
		icons[0] = new IIcon[1];
		icons[1] = new IIcon[2];
		icons[2] = new IIcon[1];
		icons[3] = new IIcon[2];
		icons[4] = new IIcon[1];
		icons[5] = new IIcon[1];
		icons[0][0] = IR.registerIcon("ultratech:reactor/reactor");
		icons[1][0] = IR.registerIcon("ultratech:reactor/reactorwall");
		icons[1][1] = IR.registerIcon("ultratech:reactor/reactorwallmulti");
		icons[2][0] = IR.registerIcon("ultratech:reactor/reactortank");
		icons[3][0] = IR.registerIcon("ultratech:reactor/reactorcontrolleroff");
		icons[3][1] = IR.registerIcon("ultratech:reactor/reactorcontrolleron");
		icons[4][0] = IR.registerIcon("ultratech:reactor/waterblock");
		icons[5][0] = IR.registerIcon("ultratech:reactor/turbine");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
	    if(meta == 0){
	    	return icons[0][0];
	    }if(meta == 1){
	    	return icons[1][0];
	    }if(meta == 2){
	    	return icons[2][0];
	    }if(meta == 3){
	    	return icons[3][0];
	    }if(meta == 4){
	    	return icons[4][0];
	    }if(meta == 5){
	    	return icons[5][0];
	    }
	    return icons[0][0];
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess BA, int x, int y, int z, int side)
    {
		TileEntity t = BA.getTileEntity(x, y, z);
		if(t instanceof ReactorWallEntity){
			if(((ReactorWallEntity) t).isStructure())return icons[1][1];
		}if(t instanceof ReactorControllerEntity){
			if(((ReactorControllerEntity) t).meta == 1){return icons[3][1];}else{return icons[3][0];}
		}
		return this.getIcon(side, BA.getBlockMetadata(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World w,int metadata) {
		if(metadata == 0)return new ReactorEntity();
		if(metadata == 1)return new ReactorWallEntity();
		if(metadata == 2)return new ReactorTankEntity();
		if(metadata == 3)return new ReactorControllerEntity();
		if(metadata == 4)return new WaterBlockEntity();
		if(metadata == 5)return new SteamExtractorEntity();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {
		for (int ix = 0; ix < numBlocks; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	@Override
	public void onBlockAdded(World worldObj, int xCoord, int yCoord, int zCoord)
	{
		super.onBlockAdded(worldObj, xCoord, yCoord, zCoord);
		TileEntity t = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		if(t instanceof IReactorPart){
			((IReactorPart)t).onNeighChange();
		}
	}
	
	public void onBlockPreDestroy(World worldObj, int xCoord, int yCoord, int zCoord, int meta) {
		super.onBlockPreDestroy(worldObj, xCoord, yCoord, zCoord, meta);
		TileEntity t = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		if(t instanceof IReactorPart)((IReactorPart) t).onNeighChange();
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6){
		dropItems(world, x, y, z);
		TileEntity t = world.getTileEntity(x, y, z);
		if(t instanceof IReactorPart){	
			((IReactorPart)t).desactivateBlocks();
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	 private void dropItems(World world, int x, int y, int z){
		 Random rand = new Random();
		 TileEntity tileEntity = world.getTileEntity(x, y, z);
		 if (!(tileEntity instanceof IInventory)) {
			 return;
		 }
		 IInventory inventory = (IInventory) tileEntity;
		 for (int i = 0; i < inventory.getSizeInventory(); i++) {
			 ItemStack item = inventory.getStackInSlot(i);
			 if (item != null && item.stackSize > 0) {
				 float rx = rand.nextFloat() * 0.8F + 0.1F;
				 float ry = rand.nextFloat() * 0.8F + 0.1F;
				 float rz = rand.nextFloat() * 0.8F + 0.1F;
				 EntityItem entityItem = new EntityItem(world,
                      x + rx, y + ry, z + rz,
                      new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
				 if (item.hasTagCompound()) {
					 entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				 }
				 float factor = 0.05F;
				 entityItem.motionX = rand.nextGaussian() * factor;
				 entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				 entityItem.motionZ = rand.nextGaussian() * factor;
				 world.spawnEntityInWorld(entityItem);
				 item.stackSize = 0;
			 }
		 }
	 }

	 public int getRenderBlockPass()
	 {
		 return 0;
	 }

	 public boolean isOpaqueCube()
	 {
		 return false;
	 }

	 public boolean renderAsNormalBlock()
	 {
		 return true;
	 }
}
