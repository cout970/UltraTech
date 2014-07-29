package common.cout970.UltraTech.blocks.reactor;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultratech.api.reactor.IReactorCoreBlock;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Core_Entity;

public class Reactor_Core_Block extends ReactorPartBase implements IReactorCoreBlock{

	public Reactor_Core_Block(Material m) {
		super(m, "core");
	}

	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		if(m == 1)return new Reactor_Core_Entity();
		return null;
	}

	@Override
	public int getLayer() {
		return 0;
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
