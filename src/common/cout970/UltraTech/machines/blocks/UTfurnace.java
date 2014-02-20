package common.cout970.UltraTech.machines.blocks;

import java.util.Random;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.UTfurnaceEntity;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class UTfurnace extends BlockContainer{

    @SideOnly(Side.CLIENT)
    private Icon furnaceIconFront;
	private Icon furnaceIconOn;
    
	public UTfurnace(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("utf");
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return true;
		}else{
			if(!par1World.isRemote){
				UTfurnaceEntity tile = (UTfurnaceEntity)par1World.getBlockTileEntity(x, y, z);
				if(tile != null){ 
					par5EntityPlayer.openGui(UltraTech.instance, 8, par1World, x, y, z);
					return true;
				}
			}
		}
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new UTfurnaceEntity();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:chasis1");
		this.furnaceIconFront = iconRegister.registerIcon("ultratech:utfurnacefront");
		this.furnaceIconOn = iconRegister.registerIcon("ultratech:utfurnaceon");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 || par1 == 0 ? this.blockIcon : (par2 == 0 ? this.furnaceIconFront : this.furnaceIconOn);
    }
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
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
		 if(tileEntity instanceof ISpeedUpgradeabel){
			 float rx = rand.nextFloat() * 0.8F + 0.1F;
			 float ry = rand.nextFloat() * 0.8F + 0.1F;
			 float rz = rand.nextFloat() * 0.8F + 0.1F;
			 ItemStack upgrades = ((ISpeedUpgradeabel)tileEntity).getDrop();
			 if(upgrades != null){
			 EntityItem entityItem = new EntityItem(world,
                    x + rx, y + ry, z + rz, upgrades);

			 float factor = 0.05F;
			 entityItem.motionX = rand.nextGaussian() * factor;
			 entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			 entityItem.motionZ = rand.nextGaussian() * factor;
			 world.spawnEntityInWorld(entityItem);
			 }
		 }
	 }
}
