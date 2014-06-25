package common.cout970.UltraTech.blocks.models;

import java.util.Random;

import common.cout970.UltraTech.TileEntities.electric.LaminatorEntity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.core.UltraTech;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
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
import api.cout970.UltraTech.Wpower.BlockConductor;

public class Laminator extends BlockConductor{

	private IIcon blockIcon2;

	public Laminator(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.5f);
		setBlockName("Laminator");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new LaminatorEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR){
		blockIcon = IR.registerIcon("ultratech:laminator");
		blockIcon2 = IR.registerIcon("ultratech:chasis");
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		if(side == 0 || side == 1)return blockIcon2;
    	return blockIcon;
    }
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			return true;
		}else{
			TileEntity tile = world.getTileEntity(i, j, k);
			if(tile != null){ 
				entityplayer.openGui(UltraTech.instance, 15, world, i, j, k);
			}
		}
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

	private void dropItems(World world, int x, int y, int z){
		Random rand = new Random();
		TileEntity tileEntity = world.getTileEntity(x, y, z);

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
