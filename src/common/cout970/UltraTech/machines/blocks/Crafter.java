package common.cout970.UltraTech.machines.blocks;

import java.util.Random;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.CrafterEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Crafter extends BlockContainer{

	public Crafter(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("Crafter");
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return false;
		}else{
			TileEntity tile = par1World.getBlockTileEntity(x, y, z);
			if(tile != null){
				par5EntityPlayer.openGui(UltraTech.instance, 12, par1World, x, y, z);
				return true;
			}
		}
		return false;
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:crafter");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new CrafterEntity();
	}
	
	@Override
	 public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		 dropItems(world, x, y, z);
		 super.breakBlock(world, x, y, z, par5, par6);
	 }
	
	 private void dropItems(World world, int x, int y, int z){
		 Random rand = new Random();
		 TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
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
                       new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
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
