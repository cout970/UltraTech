package common.cout970.UltraTech.blocks;

import java.util.List;

import common.cout970.UltraTech.TileEntities.Tier1.hitBoxEntity;
import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class MiscBlock extends BlockContainer{

	private Icon blockIconLast;
	private Icon blockIcon1;

	public MiscBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setUnlocalizedName("Misc");
		setStepSound(soundMetalFootstep);
		setResistance(10);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return (metadata == 3) ? null : new hitBoxEntity();
	}
	
	public Icon getIcon(int side,int meta){
		
		if(meta == 0)return this.blockIcon;
		if(meta == 1)return this.blockIcon1;
		return this.blockIconLast;
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIconLast = iconRegister.registerIcon("ultratech:void");
		this.blockIcon = iconRegister.registerIcon("ultratech:radioniteblock");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:grafeno");

	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileEntity t = world.getBlockTileEntity(x, y, z);
		if(t instanceof hitBoxEntity){
			hitBoxEntity h = (hitBoxEntity) t;
			world.destroyBlock(h.x, h.y, h.z, true);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
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
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		for (int ix = 0; ix < 2; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
}
