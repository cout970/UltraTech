package common.cout970.UltraTech.multipart.remplace;

import buildcraft.api.tools.IToolWrench;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityEngine;
import common.cout970.UltraTech.blocks.models.BlockFluidPipe;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCopperPipe extends BlockFluidPipe{

	public BlockCopperPipe(Material par2Material) {
		super(par2Material);
		setBlockName("CopperPipe");
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
	{
		if(p.isSneaking())return true;
		TileEntityCopperPipe e = (TileEntityCopperPipe) w.getTileEntity(x, y, z);
		if(e != null){
			if(p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() instanceof IToolWrench){
				e.mode = !e.mode;
				e.lock = e.mode;
				e.sendNetworkUpdate();
			}
		}
		return false;
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Block_Textures.PARTICLE_COPPER);
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		TileEntityCopperPipe m = (TileEntityCopperPipe) w.getTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
		m.onNeighUpdate();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world,int a) {
		return new TileEntityCopperPipe();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.pipeRenderPass;
	}
}
