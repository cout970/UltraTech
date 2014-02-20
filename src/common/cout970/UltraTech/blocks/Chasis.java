package common.cout970.UltraTech.blocks;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class Chasis extends Block{

	private Icon blockIcon1;
	private Icon blockIcon2;

	public Chasis(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(25);
		setUnlocalizedName("Chasis");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:chasis");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:chasis1");
		this.blockIcon2 = iconRegister.registerIcon("ultratech:chasis2");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
	{		
		switch(par2){
		case 0:return this.blockIcon;
		case 1:return this.blockIcon1;
		case 2:return this.blockIcon2;
		default:return this.blockIcon;
		}
	}

	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		for (int ix = 0; ix < 3; ix++) {
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
}
