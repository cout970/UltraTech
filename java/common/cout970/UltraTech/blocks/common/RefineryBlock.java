package common.cout970.UltraTech.blocks.common;

import java.util.List;
import java.util.Random;

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
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.multiblocks.refinery.RF_Utils;
import common.cout970.UltraTech.multiblocks.refinery.RefineryBase;
import common.cout970.UltraTech.multiblocks.refinery.RefineryCore;
import common.cout970.UltraTech.multiblocks.refinery.TileGag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RefineryBlock extends BlockContainer{

	private IIcon[] icons;

	public RefineryBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("Refinery");
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
	{
		if(p.isSneaking()){
			int meta = w.getBlockMetadata(x, y, z);
			if(meta == 4){
				RefineryBase b = (RefineryBase) w.getTileEntity(x, y, z);
				b.changeMode();
				b.onNeigUpdate();
				w.markBlockForUpdate(x, y, z);
				return true;
			}
		}else{
			int meta = w.getBlockMetadata(x, y, z);
			if(meta == 4){
				p.openGui(UltraTech.instance, 14, w, x, y, z);
			}
		}
		return false;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block b){
		super.onNeighborBlockChange(w, x, y, z, b);
		TileEntity e = w.getTileEntity(x, y, z);
		int meta = w.getBlockMetadata(x, y, z);
		if(meta == 0 && w.getBlock(x, y-1, z) != BlockManager.Refinery){
			if(RF_Utils.refreshRefinery(w, x, y, z)){
				int[] a = RF_Utils.getCenter(w, x, y, z);
				RF_Utils.setRefinery(w, x+a[0], y+a[1], z+a[2]);
			}
		}

		if(meta == 1){
			for(int j = 0;j<8;j++){
				if(Block.isEqualTo(w.getBlock(x, y-j, z),BlockManager.Refinery) && w.getBlockMetadata(x, y-j, z) == 0){
					this.onNeighborBlockChange(w, x, y-j-1, z, b);
					break;
				}
			}
		} 
		if(meta == 4){
			((RefineryBase)e).onNeigUpdate();
		}
	}
	
	 @SideOnly(Side.CLIENT)
	    public IIcon getIcon(IBlockAccess w, int x, int y, int z, int side)
	    {
		 	if(w.getBlockMetadata(x, y, z) == 4){
		 		RefineryBase b = (RefineryBase) w.getTileEntity(x, y, z);
		 		if(side == 0 || side == 1){
					return icons[0];
				}else{
		 		if(b.mode == 0)return icons[2];
		 		if(b.mode == 1)return icons[4];
		 		if(b.mode == 2)return icons[5];
		 		if(b.mode == 3)return icons[6];
		 		if(b.mode == 4)return icons[7];
				}
		 	}
	        return super.getIcon(w, x, y, z, side);
	    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6){
		if(world.getTileEntity(x, y, z) instanceof TileGag){
			TileGag t = (TileGag) world.getTileEntity(x, y, z);	
			RF_Utils.removeRefinery(world, t.x, t.y-1, t.z);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[8];
		icons[0] = IR.registerIcon(Block_Textures.CHASIS_T2);
		icons[1] = IR.registerIcon(Block_Textures.REFINERY_STRUCTURE);
		icons[2] = IR.registerIcon(Block_Textures.REFINERY_BASE);
		icons[3] = IR.registerIcon(Block_Textures.VOID);
		icons[4] = IR.registerIcon(Block_Textures.REFINERY_INPUT);
		icons[5] = IR.registerIcon(Block_Textures.REFINERY_OUTPUT_1);
		icons[6] = IR.registerIcon(Block_Textures.REFINERY_OUTPUT_2);
		icons[7] = IR.registerIcon(Block_Textures.REFINERY_OUTPUT_3);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta == 0)return icons[0];
		if(meta == 4){
			if(side == 0 || side == 1){
				return icons[0];
			}else return icons[2];
		}
		if(meta == 1)return icons[1];
		if(meta == 2)return icons[3];
		return icons[1];
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if(metadata == 4)return new RefineryBase();
		if(metadata == 1)return new TileGag();
		if(metadata == 2)return new TileGag();
		if(metadata == 3)return new RefineryCore();
		return null;
	}

	@SuppressWarnings("unchecked")
	public void getSubBlocks(Item unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
	
	@Override
	public int damageDropped (int metadata) {
		if(metadata == 0)return 0;
		if(metadata == 1)return 1;
		if(metadata == 2)return 1;
		if(metadata == 3)return 1;
		return metadata;
	}
}
