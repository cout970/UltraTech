package common.cout970.UltraTech.machines.blocks;


import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class GrafenoBlock extends Block{
	
	private Icon blockIcon2, blockIcon3, blockIcon4, blockIcon5, blockIcon6, blockIcon7, blockIcon8, blockIcon9, blockIcon10, blockIcon11;

	public GrafenoBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(0.2f);
		setStepSound(soundGlassFootstep);
		setResistance(50);
		setLightValue(0.9f);
		setLightOpacity(0);
		setUnlocalizedName("GrafenoBlock");
	}
	
	
//	@SuppressWarnings("unchecked")
//	@SideOnly(Side.CLIENT)
//	@Override
//	public void getSubBlocks(int par1, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems) {
//		for (int ix = 0; ix < 11; ix++) {
//			subItems.add(new ItemStack(this, 1, ix));
//		}
//	}
	
	@Override
	public void onBlockAdded(World par1World, int x, int y, int z)
    { 
        super.onBlockAdded(par1World, x, y, z);
//        if (!par1World.isRemote)
//        {
//        	int meta = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItemDamage();
//        	par1World.setBlockMetadataWithNotify(x, y, z, meta, 2);
//        }
    }

	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	public Icon getIcon(int side,int metadata){
		switch(metadata){
		case 1:
			return this.blockIcon2;
		case 2:
			return this.blockIcon3;
		case 3:
			return this.blockIcon4;
		case 4:
			return this.blockIcon5;
		case 5:
			return this.blockIcon6;
		case 6:
			return this.blockIcon7;
		case 7:
			return this.blockIcon8;
		case 8:
			return this.blockIcon9;
		case 9:
			return this.blockIcon10;
		case 10:
			return this.blockIcon11;
		default: return this.blockIcon2;
		}

	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:decorative/hexagon");
		this.blockIcon2 = iconRegister.registerIcon("ultratech:decorative/hexagon2");
		this.blockIcon3 = iconRegister.registerIcon("ultratech:decorative/hexagon3");
		this.blockIcon4 = iconRegister.registerIcon("ultratech:decorative/hexagon4");
		this.blockIcon5 = iconRegister.registerIcon("ultratech:decorative/hexagon5");
		this.blockIcon6 = iconRegister.registerIcon("ultratech:decorative/hexagon6");
		this.blockIcon7 = iconRegister.registerIcon("ultratech:decorative/hexagon7");
		this.blockIcon8 = iconRegister.registerIcon("ultratech:decorative/hexagon8");
		this.blockIcon9 = iconRegister.registerIcon("ultratech:decorative/hexagon9");
		this.blockIcon10 = iconRegister.registerIcon("ultratech:decorative/hexagon10");
		this.blockIcon11 = iconRegister.registerIcon("ultratech:decorative/hexagon11");
	}

}
