package common.cout970.UltraTech.blocks;

import java.util.Random;

import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class UT_TileBlock extends BlockContainer{

	public IIcon[] icons;
	private String name;
	
	public UT_TileBlock(Material m,String n) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName(n);
		name = n.toLowerCase();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[1];
		icons[0] = IR.registerIcon(Block_Textures.MOD_FOLDER+name);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[0];
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block block){
		TileEntity t =  w.getTileEntity(x, y, z);
		if(t instanceof IUpdatedEntity)((IUpdatedEntity) t).onNeigUpdate();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6){
		TileEntity t = world.getTileEntity(x, y, z);
		if(t instanceof IInventory){	
			dropItems(world, x, y, z);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	public void dropItems(World world, int x, int y, int z){
		Random rand = new Random();
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof IInventory){
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
	}
	
}
