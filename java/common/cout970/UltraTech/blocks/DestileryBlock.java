package common.cout970.UltraTech.blocks;

import java.util.List;

import common.cout970.UltraTech.TileEntities.fluid.DestileryEntity;
import common.cout970.UltraTech.TileEntities.fluid.DestileryInEntity;
import common.cout970.UltraTech.TileEntities.fluid.DestileryOutEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.multiblocks.IDestilery;
import common.cout970.UltraTech.multiblocks.TileDestilery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DestileryBlock extends BlockContainer{

	public IIcon icons[];
	public int numBlocks = 3;
	
	public DestileryBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.5f);
		setBlockName("UT_Destilery");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR)
	{
		icons = new IIcon[6];
		icons[0] = IR.registerIcon("ultratech:refinery");
		icons[1] = IR.registerIcon("ultratech:refinery-in");
		icons[2] = IR.registerIcon("ultratech:refinery-out");
		icons[3] = IR.registerIcon("ultratech:ref");
		icons[4] = IR.registerIcon("ultratech:ref-in");
		icons[5] = IR.registerIcon("ultratech:ref-out");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta == 0)return icons[0];
		if(meta == 1)return icons[1];
		if(meta == 2)return icons[2];
		return null;
	}
	
	@Override
	public IIcon getIcon(IBlockAccess BA, int x, int y, int z, int side)
	{
		int meta = BA.getBlockMetadata(x, y, z);
		TileEntity t = BA.getTileEntity(x, y, z);
		if(t instanceof IDestilery){
				if(((IDestilery) t).isMulti()){
					if(meta == 0)return icons[3];
					if(meta == 1)return icons[4];
					if(meta == 2)return icons[5];
				}else return getIcon(side, meta);
		}
		return getIcon(side, meta);
	}


	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getTileEntity(x, y, z);
		if(te instanceof TileDestilery)((TileDestilery)te).update = false;
		w.markBlockForUpdate(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if(metadata == 0)return new DestileryEntity();
		if(metadata == 1)return new DestileryInEntity();
		if(metadata == 2)return new DestileryOutEntity();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {		for (int ix = 0; ix < numBlocks; ix++) {
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
	public void onBlockAdded(World w, int x, int y, int z) {
		((TileDestilery)w.getTileEntity(x, y, z)).searchMulti();
	}
	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		((TileDestilery)w.getTileEntity(x, y, z)).DellMulti();
	}
}
