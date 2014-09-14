package common.cout970.UltraTech.blocks.tiers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.interfaces.IStorageItem;
import buildcraft.api.tools.IToolWrench;

import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier1;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier2;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier3;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier4;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;
import common.cout970.UltraTech.util.power.BlockConductor;
import common.cout970.UltraTech.util.power.IBatteryBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlocksBattery extends BlockConductor{

	private static final String DATA_KEY = "data";
	public int n = 4;
	private IIcon blockIcon1;
	private IIcon blockIcon2;

	public BlocksBattery(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.techTab);
		setStepSound(soundTypeMetal);
		setHardness(2.5f);
		setBlockName("StorageBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		switch(metadata){
		case 0: return new TileEntityBatteryTier1();
		case 1: return new TileEntityBatteryTier2();
		case 2: return new TileEntityBatteryTier3();
		case 3: return new TileEntityBatteryTier4();
		}
		return new TileEntityBatteryTier1();
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune){
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		if(world.isRemote)return ret;
		if(!world.isRemote){
			IBatteryBlock b = (IBatteryBlock) world.getTileEntity(x, y, z);
			if(b != null){
				ItemStack drop = new ItemStack(this, 1, metadata);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean(DATA_KEY, true);
				b.saveData(nbt);
				drop.stackTagCompound = nbt;
				ret.add(drop);
			}
		}
		return ret;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{		
		return this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.batteryRenderPass;
	}

	@SuppressWarnings("unchecked")
	public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
	{	
		for (int ix = 0; ix < n; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			ItemStack it = entityplayer.getCurrentEquippedItem();
			if(it != null){ 
				if(it.getItem() instanceof IToolWrench) {
					onBlockHarvested(world, i, j, k, world.getBlockMetadata(i, j, k), entityplayer);
					removedByPlayer(world, entityplayer, i, j, k, false);
					return true;
				}
			}
		}else{
			TileEntity tile = world.getTileEntity(i, j, k);
			if(tile != null){ 
				ItemStack it = entityplayer.getCurrentEquippedItem();
				if(it != null){
					if(it.getItem() instanceof IStorageItem){
						return false;
					}else if(it.getItem() instanceof IToolWrench){
						IBatteryBlock b = (IBatteryBlock) tile;
						boolean var = !b.getSide(ForgeDirection.getOrientation(side));
						b.setSide(ForgeDirection.getOrientation(side), var);
						return false;
					}else{
						entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
						return true;
					}
				}else{
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
				}
			}
		}
		return false;
	}

	public void onBlockHarvested(World w, int x, int y, int z, int meta, EntityPlayer p) {

		this.dropBlockAsItem(w, x, y, z, meta, 0);
		super.onBlockHarvested(w, x, y, z, meta, p);
	}

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack item) {
		if(item.stackTagCompound != null){
			if(item.stackTagCompound.hasKey(DATA_KEY)){
				IBatteryBlock b = (IBatteryBlock) w.getTileEntity(x, y, z);
				if(b != null){
					b.loadData(item.stackTagCompound);
				}
			}
		}
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		IPowerConductor m = (IPowerConductor) w.getTileEntity(x, y, z);
		if(m.getPower().getNetwork() != null)m.getPower().getNetwork().refresh();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Block_Textures.CHASIS_T2);
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
