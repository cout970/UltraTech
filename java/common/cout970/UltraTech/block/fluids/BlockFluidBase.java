package common.cout970.UltraTech.block.fluids;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.managers.FluidManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidBase extends BlockFluidClassic{

	@SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;

	public BlockFluidBase(Fluid fluid) {
		super(fluid, Material.water);
		this.setCreativeTab(UltraTech.ResourceTab);
	}
	
	@Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
            stillIcon = register.registerIcon("ultratech:fluids/fluidstill_"+fluidName);
            flowingIcon = stillIcon;
            FluidManager.setIcons(register);
//            flowingIcon = register.registerIcon("ultratech:fluids/fluidflowing_"+fluidName);
    }

}
