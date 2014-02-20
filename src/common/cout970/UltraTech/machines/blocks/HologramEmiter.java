package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.HologramEmiterEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class HologramEmiter extends BlockContainer{

	private Icon blockIcon1;

	public HologramEmiter(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("HologramEmiter");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:hologram");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:chasis2");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
    {
        return (par1 == 1) ? this.blockIcon : blockIcon1;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new HologramEmiterEntity();
	}

}
