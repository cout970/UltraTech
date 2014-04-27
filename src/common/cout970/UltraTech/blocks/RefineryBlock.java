package common.cout970.UltraTech.blocks;

import java.util.List;

import common.cout970.UltraTech.TileEntities.Tier1.RefineryEntity;
import common.cout970.UltraTech.TileEntities.Tier1.RefineryInEntity;
import common.cout970.UltraTech.TileEntities.Tier1.RefineryOutEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.multiblocks.IRefinery;
import common.cout970.UltraTech.multiblocks.TileRefinery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RefineryBlock extends BlockContainer{

	public Icon icons[];
	public int numBlocks = 3;
	
	public RefineryBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundMetalFootstep);
		setResistance(50);
		setHardness(2.5f);
		setUnlocalizedName("UT_RefineryBlock");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister IR){
		icons = new Icon[6];
		icons[0] = IR.registerIcon("ultratech:refinery");
		icons[1] = IR.registerIcon("ultratech:refinery-in");
		icons[2] = IR.registerIcon("ultratech:refinery-out");
		icons[3] = IR.registerIcon("ultratech:ref");
		icons[4] = IR.registerIcon("ultratech:ref-in");
		icons[5] = IR.registerIcon("ultratech:ref-out");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		if(meta == 0)return icons[0];
		if(meta == 1)return icons[1];
		if(meta == 2)return icons[2];
		return null;
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess BA, int x, int y, int z, int side)
	{
		int meta = BA.getBlockMetadata(x, y, z);
		TileEntity t = BA.getBlockTileEntity(x, y, z);
		if(t instanceof IRefinery){
				if(((IRefinery) t).isMulti()){
					if(meta == 0)return icons[3];
					if(meta == 1)return icons[4];
					if(meta == 2)return icons[5];
				}else return getIcon(side, meta);
		}
		return getIcon(side, meta);
	}


	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te instanceof TileRefinery)((TileRefinery)te).update = false;
		w.markBlockForRenderUpdate(x, y, z);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if(metadata == 0)return new RefineryEntity();
		if(metadata == 1)return new RefineryInEntity();
		if(metadata == 2)return new RefineryOutEntity();
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
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	@Override
	public void onBlockAdded(World w, int x, int y, int z) {
		((TileRefinery)w.getBlockTileEntity(x, y, z)).searchMulti();
	}
	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		((TileRefinery)w.getBlockTileEntity(x, y, z)).DellMulti();
	}

}
