package common.cout970.UltraTech.block.fluids;

import common.cout970.UltraTech.managers.UT_Tabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockFluidFin extends BlockFluidFinite{

	@SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;
	
	public BlockFluidFin(Fluid fluid) {
		super(fluid, Material.water);
		this.setCreativeTab(UT_Tabs.ResourceTab);
	}
	
	@Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
            stillIcon = register.registerIcon("ultratech:fluids/fluidstill_"+fluidName.toLowerCase());
            flowingIcon = stillIcon;
            FluidRegistry.getFluid(fluidName).setIcons(stillIcon, flowingIcon);
//            flowingIcon = register.registerIcon("ultratech:fluids/fluidflowing_"+fluidName);
    }

}
