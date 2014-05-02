package common.cout970.UltraTech.blocks.models;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.multiblocks.refinery.BaseRef;
import common.cout970.UltraTech.multiblocks.refinery.CoreRefinery;
import common.cout970.UltraTech.multiblocks.refinery.TileGag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class RefineryBlock extends BlockContainer{

	private Icon[] icons;

	public RefineryBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(2.5f);
		setStepSound(soundMetalFootstep);
		setResistance(20);
		setUnlocalizedName("Refinery");
		setLightValue(1);
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		super.onNeighborBlockChange(w, x, y, z, side);
		TileEntity e = w.getBlockTileEntity(x, y, z);
		if(e instanceof BaseRef){
			((BaseRef) e).refresh();
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		super.breakBlock(world, x, y, z, par5, par5);
		if(world.getBlockTileEntity(x, y, z) instanceof TileGag){
			TileGag t = (TileGag) world.getBlockTileEntity(x, y, z);	
				CoreRefinery c = (CoreRefinery) world.getBlockTileEntity(t.x, t.y, t.z);
				if(c != null)c.removeStructure(t.x, t.y, t.z);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister IR){
		icons = new Icon[4];
		icons[0] = IR.registerIcon("ultratech:refinery/base");
		icons[1] = IR.registerIcon("ultratech:refinery/struc");
		icons[2] = IR.registerIcon("ultratech:refinery/base");
		icons[3] = IR.registerIcon("ultratech:void");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		if(meta == 0)return icons[0];
		if(meta == 1)return icons[1];
		if(meta == 2)return icons[2];
		if(meta == 3)return icons[3];
		return icons[0];
	}
	
	@Override
	public TileEntity createNewTileEntity(World world){return null;}
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if(metadata == 0)return new BaseRef();
		if(metadata == 2)return new CoreRefinery();
		if(metadata == 3)return new TileGag();
		return null;
		//0 base
		//1 struc
		//2 core
		//3 invs
	}
	
	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
			subItems.add(new ItemStack(this, 1, 0));
			subItems.add(new ItemStack(this, 1, 1));
//			subItems.add(new ItemStack(this, 1, 2));//r
//			subItems.add(new ItemStack(this, 1, 3));//r
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	@Override
	public int damageDropped (int metadata) {return metadata;}
}
