package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.electric.LavaGeneratorEntity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.power.BlockConductor;
import common.cout970.UltraTech.util.render.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LavaGenerator extends BlockConductor{

	private IIcon blockIcon2;

	public LavaGenerator(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.5f);
		setBlockName("LavaGenerator");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new LavaGeneratorEntity();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR){
		blockIcon = IR.registerIcon(Block_Textures.LAVA_GENERATOR);
		blockIcon2 = IR.registerIcon(Block_Textures.CHASIS_T2);
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
