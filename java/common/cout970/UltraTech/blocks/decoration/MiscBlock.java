package common.cout970.UltraTech.blocks.decoration;

import java.util.List;
import java.util.Random;

import common.cout970.UltraTech.TileEntities.utility.hitBoxEntity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.UT_Tabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MiscBlock extends BlockContainer{

	private IIcon blockIconLast;
	private IIcon blockIcon1;

	public MiscBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.techTab);
		setBlockName("Misc");
		setStepSound(soundTypeMetal);
		setResistance(10);
		setHardness(2.5f);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return (metadata == 3) ? null : new hitBoxEntity();
	}
	
	public IIcon getIcon(int side,int meta){
		
		if(meta == 0)return this.blockIcon;
		if(meta == 1)return this.blockIcon1;
		return this.blockIconLast;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIconLast = iconRegister.registerIcon(Block_Textures.VOID);
		this.blockIcon = iconRegister.registerIcon(Block_Textures.RADIONITE_BLOCK);
		this.blockIcon1 = iconRegister.registerIcon(Block_Textures.GRAFENO_BLOCK);
	}
	
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		TileEntity t = world.getTileEntity(x, y, z);
		if(t instanceof hitBoxEntity){
			hitBoxEntity h = (hitBoxEntity) t;			
			world.func_147480_a(h.x, h.y, h.z,true);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		if(p_149650_1_ == 3)return null;
		
        return Item.getItemFromBlock(this);
    }
	
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {		for (int ix = 0; ix < 2; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
}
