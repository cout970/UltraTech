package common.cout970.UltraTech.blocks.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import api.cout970.UltraTech.MeVpower.BlockConductor;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Heater extends BlockConductor{

	private IIcon blockIcon1;

	public Heater(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("Heater");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new Heater_Entity();
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:heater");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:heater_on");
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		if(meta == 1)return blockIcon1;
        return this.blockIcon;
    }
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b) {
		IUpdatedEntity i = (IUpdatedEntity) w.getTileEntity(x, y, z);
		i.onNeigUpdate();
	}

}
