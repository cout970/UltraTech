package common.cout970.UltraTech.blocks;

import java.util.List;
import java.util.Random;

import buildcraft.api.tools.IToolWrench;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.Tier3.ClimateEntity;
import common.cout970.UltraTech.TileEntities.Tier3.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.Tier3.MinerEntity;
import common.cout970.UltraTech.TileEntities.Tier3.MolecularAssemblyEntity;
import common.cout970.UltraTech.TileEntities.Tier3.TesseractEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.energy.api.EnergyUtils;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.managers.ItemManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Tier3Block extends BlockContainer{

	public Icon icons[];
	public int numBlocks = 5;
	
	public Tier3Block(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundMetalFootstep);
		setResistance(50);
		setHardness(2.0f);
		setUnlocalizedName("UT_Tier3Block");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister IR){
		icons = new Icon[9];
		icons[0] = IR.registerIcon("ultratech:chasis2");
		icons[1] = IR.registerIcon("ultratech:miner");
		icons[2] = IR.registerIcon("ultratech:hologram");
		icons[3] = IR.registerIcon("ultratech:assembly");
		icons[4] = IR.registerIcon("ultratech:climate1");
		icons[5] = IR.registerIcon("ultratech:climate2");
		icons[6] = IR.registerIcon("ultratech:climate3");
		icons[7] = IR.registerIcon("ultratech:tesseract");
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if(metadata == 0)return new MinerEntity();
		if(metadata == 1)return new HologramEmiterEntity();
		if(metadata == 2)return new MolecularAssemblyEntity();
		if(metadata == 3)return new ClimateEntity();
		if(metadata == 4)return new TesseractEntity();
		return null;
	}

	@Override
	public Icon getIcon(int side, int meta) {
		switch(meta){
		case 0:{
			if(side != 1 && side != 0)return icons[1];
			return icons[0];
		}
		case 1:{
			if(side == 1)return icons[2];
			return icons[0];
		}
		case 2:{
			if(side != 1 && side != 0)return icons[3];
			return icons[0];
		}
		case 3:{
			if(side == 1 || side == 0)return icons[6];
			if(side == 3)return icons[5];
			if(side == 2)return icons[5];
			return icons[4];
		}
		case 4:{
			return icons[7];
		}
		default:return icons[0];
		}
	}

	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			return true;
		}else{
			TileEntity tile = world.getBlockTileEntity(i, j, k);
			if(tile != null){ 
				if(tile instanceof MinerEntity){
					if(entityplayer.getCurrentEquippedItem() != null){
						if(entityplayer.getCurrentEquippedItem().getItem() instanceof IToolWrench){
							((MinerEntity) tile).ChangeMode();
							return true;
						}
					}
				}
				entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
			}
		}
		return true;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te instanceof Machine){
			if(((Machine)te).getNetwork() != null)((Machine)te).getNetwork().refresh();
		}
		if(te instanceof MinerEntity){
			((MinerEntity)te).searchInventories();
		}
		if(te instanceof ClimateEntity){
			((ClimateEntity) te).restoneUpdate(w.isBlockIndirectlyGettingPowered(x, y, z));
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		for (int ix = 0; ix < numBlocks; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
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
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		TileEntity t = world.getBlockTileEntity(x, y, z);
		if(t instanceof IInventory){	
			dropItems(world, x, y, z);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z){
		Random rand = new Random();
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

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
		 if (!(tileEntity instanceof MinerEntity)) {
			 return;
		 }
		 MinerEntity me = (MinerEntity) tileEntity;
		 if(me.eject){
			 float rx = rand.nextFloat() * 0.8F + 0.1F;
			 float ry = rand.nextFloat() * 0.8F + 0.1F;
			 float rz = rand.nextFloat() * 0.8F + 0.1F;
			 EntityItem entityItem = new EntityItem(world,
                   x + rx, y + ry, z + rz,
                   new ItemStack(ItemManager.ItemName.get("AutoEjectUpgrade").itemID, 1, 0));
			 float factor = 0.05F;
			 entityItem.motionX = rand.nextGaussian() * factor;
			 entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			 entityItem.motionZ = rand.nextGaussian() * factor;
			 world.spawnEntityInWorld(entityItem);
		 }
		 if(me.hasSpeedUpgrades){
			 for(int d = me.speedUpgrades;d > 0;d--){
				 float rx = rand.nextFloat() * 0.8F + 0.1F;
				 float ry = rand.nextFloat() * 0.8F + 0.1F;
				 float rz = rand.nextFloat() * 0.8F + 0.1F;
				 EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(ItemManager.ItemName.get("MiningUpgrade").itemID, 1, 0));
				 float factor = 0.05F;
				 entityItem.motionX = rand.nextGaussian() * factor;
				 entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				 entityItem.motionZ = rand.nextGaussian() * factor;
				 world.spawnEntityInWorld(entityItem);
			 }
		 }
		 if(me.hasRangeUpgrades){
			 for(int d = me.rangeUpgrades;d > 0;d--){
			 float rx = rand.nextFloat() * 0.8F + 0.1F;
			 float ry = rand.nextFloat() * 0.8F + 0.1F;
			 float rz = rand.nextFloat() * 0.8F + 0.1F;
			 EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(ItemManager.ItemName.get("RangeUpgrade").itemID, 1, 0));
			 float factor = 0.05F;
			 entityItem.motionX = rand.nextGaussian() * factor;
			 entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			 entityItem.motionZ = rand.nextGaussian() * factor;
			 world.spawnEntityInWorld(entityItem);
			 }
		 }
		 if(me.hasFortuneUpgrades){
			 for(int d = me.fortuneUpgrades;d > 0;d--){
			 float rx = rand.nextFloat() * 0.8F + 0.1F;
			 float ry = rand.nextFloat() * 0.8F + 0.1F;
			 float rz = rand.nextFloat() * 0.8F + 0.1F;
			 EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(ItemManager.ItemName.get("FortuneUpgrade").itemID, 1, 0));
			 float factor = 0.05F;
			 entityItem.motionX = rand.nextGaussian() * factor;
			 entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			 entityItem.motionZ = rand.nextGaussian() * factor;
			 world.spawnEntityInWorld(entityItem);
			 }
		 }
	}

	@Override
	public void onBlockAdded(World w, int x, int y, int z) {
		EnergyUtils.onBlockAdded(w, x, y, z);
	}
	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		if(w.getBlockTileEntity(x, y, z) instanceof TesseractEntity)TesseractEntity.tes.clear();
		EnergyUtils.onBlockPreDestroy(w, x, y, z, meta);
	}
}
