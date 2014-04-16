package common.cout970.UltraTech.blocks.models;

import buildcraft.api.tools.IToolWrench;
import common.cout970.UltraTech.TileEntities.Tier2.EngineEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.energy.api.EnergyUtils;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Engine extends BlockContainer{

	public Engine(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(0.5f);
		setStepSound(soundMetalFootstep);
		setResistance(20);
		setUnlocalizedName("Engine");
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
	{
		if(p.isSneaking())return true;
		EngineEntity e = (EngineEntity) w.getBlockTileEntity(x, y, z);
		if(e != null){
			if(p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() instanceof IToolWrench){
				e.switchOrientation();
				return true;
			}
			p.openGui(UltraTech.instance, 13, w, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new EngineEntity();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:engine");
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		EngineEntity m = (EngineEntity) w.getBlockTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
		m.update = false;
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
		return ClientProxy.engineRenderPass;
	}

	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		EnergyUtils.onBlockPreDestroy(w, x, y, z, meta);
	}

	@Override
	public void onBlockAdded(World par1World, int xCoord, int yCoord, int zCoord)
	{
		EnergyUtils.onBlockAdded( par1World, xCoord, yCoord, zCoord);
		super.onBlockAdded(par1World, xCoord, yCoord, zCoord);
	}
}
