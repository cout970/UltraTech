package common.cout970.UltraTech.blocks;

import java.util.List;

import api.cout970.UltraTech.FTpower.BlockConductor;
import api.cout970.UltraTech.FTpower.IPowerConductor;
import api.cout970.UltraTech.FTpower.IStorageItem;
import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.StorageInterface;
import common.cout970.UltraTech.TileEntities.Tier1.StorageTier1;
import common.cout970.UltraTech.TileEntities.Tier2.StorageTier2;
import common.cout970.UltraTech.TileEntities.Tier3.StorageTier3;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class StorageBlock extends BlockConductor{

	public int n = 3;
	private IIcon blockIcon1;
	private IIcon blockIcon2;
	
	public StorageBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundTypeMetal);
		setHardness(2.5f);
		setBlockName("StorageBlock");
	}
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{		
		switch(meta){
		case 0:return this.blockIcon;
		case 1:return this.blockIcon1;
		case 2:return this.blockIcon2;
		}
		return this.blockIcon;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		switch(metadata){
		case 0: return new StorageTier1();
		case 1: return new StorageTier2();
		case 2: return new StorageTier3();
		}
		return new StorageTier1();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:storage0");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:storage1");
		this.blockIcon2 = iconRegister.registerIcon("ultratech:storage2");
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {	
		for (int ix = 0; ix < n; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			return true;
		}else{
			TileEntity tile = world.getTileEntity(i, j, k);
			if(tile != null){ 
				ItemStack it = entityplayer.getCurrentEquippedItem();
				if(it != null && it.getItem() instanceof IStorageItem){
					((StorageInterface) ((IPowerConductor)tile).getPower()).addCharge(((IStorageItem)it.getItem()).getPower(it));
					((IStorageItem)it.getItem()).removePower(it, ((IStorageItem)it.getItem()).getPower(it));
					return true;
				}else{
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
				return true;
				}
			}
		}
		return false;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		IPowerConductor m = (IPowerConductor) w.getTileEntity(x, y, z);
		if(m.getPower().getNetwork() != null)m.getPower().getNetwork().refresh();
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.batteryRenderPass;
	}
}
