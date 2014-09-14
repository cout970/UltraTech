package common.cout970.UltraTech.blocks.models;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityTesseract;
import common.cout970.UltraTech.blocks.common.BlockUT;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnergyTesseract extends BlockUT{

	public BlockEnergyTesseract(Material m) {
		super(m, "Tesseract");
		setLightLevel(1f);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6){
		super.breakBlock(world, x, y, z, par5, par6);
		TileEntityTesseract.tes.clear();
	}

	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[1];
		icons[0] = IR.registerIcon(Block_Textures.VOID);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTesseract();
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.tesseractRenderPass;
	}

}
