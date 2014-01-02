package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.SolarPanelEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SolarPanel extends BlockContainer{

	public SolarPanel(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.0f);
		setStepSound(soundMetalFootstep);
		setResistance(10);
		setUnlocalizedName("SolarPanel");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:SolarPanel");
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new SolarPanelEntity();
	}
	
	@Override
    public int getRenderType() {
            return -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
            return false;
    }
    
    public boolean renderAsNormalBlock() {
            return false;
    }
	

}
