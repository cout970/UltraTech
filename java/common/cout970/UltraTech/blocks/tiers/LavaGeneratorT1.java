package common.cout970.UltraTech.blocks.tiers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import common.cout970.UltraTech.TileEntities.electric.tiers.LavaGeneratorT1_Entity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.power.BlockConductor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LavaGeneratorT1 extends BlockConductor{

	private IIcon blockIcon2;

	public LavaGeneratorT1(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.5f);
		setBlockName("LavaGeneratorT1");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new LavaGeneratorT1_Entity();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR){
		blockIcon = IR.registerIcon(Block_Textures.LAVA_GENERATOR_T1);
		blockIcon2 = IR.registerIcon(Block_Textures.CHASIS_T1);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return blockIcon2;
    	return blockIcon;
    }
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b) {
		IUpdatedEntity i = (IUpdatedEntity) w.getTileEntity(x, y, z);
		i.onNeigUpdate();
	}

}
