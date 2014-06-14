package common.cout970.UltraTech.blocks;

import java.util.List;
import java.util.Random;

import api.cout970.UltraTech.Vpower.BlockConductor;
import api.cout970.UltraTech.Vpower.Machine;
import common.cout970.UltraTech.TileEntities.electric.CVD_Entity;
import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.TileEntities.electric.GeneratorEntity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.TileEntities.utility.Printer3DEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.managers.BlockManager;
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

public class Tier1Block extends BlockConductor{

	public IIcon icons[];
	public int numBlocks = 6;

	public Tier1Block(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.5f);
		setBlockName("UT_Tier1Block");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR){
		BlockManager.Juice.setIcons(IR.registerIcon("ultratech:juice"));
		BlockManager.Steam.setIcons(IR.registerIcon("ultratech:steam"));
		BlockManager.Gas_etanol.setIcons(IR.registerIcon("ultratech:steam"));
		BlockManager.Etanol.setIcons(IR.registerIcon("ultratech:etanol"));
		BlockManager.Gas_Oil.setIcons(IR.registerIcon("ultratech:gasoil"));
		BlockManager.Gasoline.setIcons(IR.registerIcon("ultratech:gas"));
		icons = new IIcon[9];
		icons[0] = IR.registerIcon("ultratech:chasis");
		icons[1] = IR.registerIcon("ultratech:crafter");
		icons[2] = IR.registerIcon("ultratech:generator");
		icons[3] = IR.registerIcon("ultratech:generatoron");
		icons[4] = IR.registerIcon("ultratech:cvd");
		icons[5] = IR.registerIcon("ultratech:printer");
		icons[6] = IR.registerIcon("ultratech:chargestation");
		icons[7] = IR.registerIcon("ultratech:fermenter");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if(metadata == 0)return new CrafterEntity();
		if(metadata == 1)return new GeneratorEntity();
		if(metadata == 2)return new CVD_Entity();
		if(metadata == 3)return new Printer3DEntity();
		if(metadata == 4)return new ChargeStationEntity();
		if(metadata == 5)return new FermenterEntity();
		return null;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		switch(meta){
		case 0:{
			if(side == 1)return icons[1];
			return icons[0];
		}
		case 1:{
			if(side != 1 && side != 0)return icons[2];
			return icons[0];
		}
		case 2:{
			return icons[4];
		}
		case 3:{
			return icons[5];
		}
		case 4:{
			return icons[6];
		}
		case 5:{
			return icons[7];
		}
		case 6:{
			return icons[8];
		}
		default:return icons[0];
		}
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess BA, int x, int y, int z, int side)
    {
		int meta = BA.getBlockMetadata(x, y, z);
		if(meta == 1){
			if(side == 1 || side == 0)return icons[0];
			TileEntity t = BA.getTileEntity(x, y, z);
			if(t instanceof GeneratorEntity){
				if(((GeneratorEntity)t).on){
					return icons[3];
				}else{
					return icons[2];
				}
			}else{
				return icons[2];
			}
		}else{
			return getIcon(side, meta);
		}
	}

	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			return true;
		}else{
			TileEntity tile = world.getTileEntity(i, j, k);
			if(tile != null){ 
				if(tile instanceof CrafterEntity){
					((CrafterEntity) tile).update();
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
				}
				if(tile instanceof GeneratorEntity){
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
				}
				if(tile instanceof CVD_Entity){
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
				}
				if(tile instanceof Printer3DEntity){
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
				}
				if(tile instanceof ChargeStationEntity){
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
				}
				if(tile instanceof FermenterEntity){
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
				}
			}
		}

		return false;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getTileEntity(x, y, z);
		if(te instanceof Machine){
			if(((Machine)te).getNetwork() != null)((Machine)te).getNetwork().refresh();
		}if(te instanceof CrafterEntity){
			((CrafterEntity) te).update();
		}
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
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
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
