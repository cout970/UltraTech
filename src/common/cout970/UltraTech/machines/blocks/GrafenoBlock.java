package common.cout970.UltraTech.machines.blocks;


import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class GrafenoBlock extends Block{
	

	public GrafenoBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(0.2f);
		setStepSound(soundMetalFootstep);
		setResistance(50);
		setUnlocalizedName("GrafenoBlock");
	}
	
	
	@Override
	public void onBlockAdded(World par1World, int x, int y, int z)
    { 
        super.onBlockAdded(par1World, x, y, z);
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
		 return this.blockIcon;

	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:grafeno");
	}

}
