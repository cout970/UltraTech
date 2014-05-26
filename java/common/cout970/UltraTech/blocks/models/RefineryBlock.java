package common.cout970.UltraTech.blocks.models;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.multiblocks.refinery.BaseRef;
import common.cout970.UltraTech.multiblocks.refinery.CoreRefinery;
import common.cout970.UltraTech.multiblocks.refinery.RF_Utils;
import common.cout970.UltraTech.multiblocks.refinery.OutRef;
import common.cout970.UltraTech.multiblocks.refinery.TileGag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class RefineryBlock extends BlockContainer{

	private IIcon[] icons;

	public RefineryBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("Refinery");
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block b){
		super.onNeighborBlockChange(w, x, y, z, b);
		TileEntity e = w.getTileEntity(x, y, z);
		if(w.getBlockMetadata(x, y, z) == 0){
			if(RF_Utils.refreshRefinery(w, x, y, z)){
				int[] a = RF_Utils.getCenter(w, x, y, z);
				RF_Utils.setRefinery(w, x+a[0], y+a[1], z+a[2]);
			}
		}else if(e instanceof TileGag){
			TileGag t = (TileGag) e;
			if(w.getBlockMetadata(t.x, t.y, t.z) != 2){
				RF_Utils.removeRefinery(w, t.x, t.y-1, t.z);
			}
		}else if(w.getBlockMetadata(x, y, z) == 1){
			for(int j = 0;j<8;j++){
				if(Block.isEqualTo(w.getBlock(x, y-j, z),BlockManager.Refinery) && w.getBlockMetadata(x, y-j, z) == 0){
					this.onNeighborBlockChange(w, x, y-j, z, b);
					break;
				}
			}
		} 
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
		icons = new IIcon[6];
		icons[0] = IR.registerIcon("ultratech:chasis1");
		icons[1] = IR.registerIcon("ultratech:refinery/struc");
		icons[2] = IR.registerIcon("ultratech:refinery/base");
		icons[3] = IR.registerIcon("ultratech:void");
		icons[4] = IR.registerIcon("ultratech:refinery/out");
		icons[5] = IR.registerIcon("ultratech:void");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta == 0)return icons[0];
		if(meta == 1)return icons[1];
		if(meta == 2)return icons[2];
		if(meta == 3)return icons[3];
		if(meta == 4)return icons[4];
		if(meta == 5)return icons[5];
		return icons[0];
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if(metadata == 2)return new CoreRefinery();
		if(metadata == 3)return new TileGag();
		if(metadata == 4)return new OutRef();
		if(metadata == 5)return new BaseRef();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
			subItems.add(new ItemStack(this, 1, 0));
			subItems.add(new ItemStack(this, 1, 1));
			subItems.add(new ItemStack(this, 1, 4));
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	@Override
	public int damageDropped (int metadata) {
		if(metadata == 2)return 1;
		if(metadata == 3)return 1;
		if(metadata == 4)return 4;
		if(metadata == 5)return 0;
		return metadata;
		}
}
