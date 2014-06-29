package common.cout970.UltraTech.blocks.models;

import buildcraft.api.tools.IToolWrench;
import api.cout970.UltraTech.MeVpower.BlockConductor;
import api.cout970.UltraTech.MeVpower.Machine;
import common.cout970.UltraTech.TileEntities.electric.BoilerEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Dynamo extends BlockConductor{

	public Dynamo(Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(UltraTech.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("Dynamo");
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
	{
		if(p.isSneaking())return true;
		DynamoEntity e = (DynamoEntity) w.getTileEntity(x, y, z);
		if(e != null){
			if(p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() instanceof IToolWrench){
				e.switchOrientation();
				return true;
			}
			p.openGui(UltraTech.instance, 13, w, x, y, z);
		}
		return true;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block side){
		DynamoEntity m = (DynamoEntity) w.getTileEntity(x, y, z);
		if(m.getPower().getNetwork() != null)m.getPower().getNetwork().refresh();
		m.updateReceptor();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world,int b) {
		return new DynamoEntity();
	}
	
	public void onBlockAdded(World w, int x, int y, int z) {
		super.onBlockAdded(w, x, y, z);
		DynamoEntity d = (DynamoEntity) w.getTileEntity(x, y, z);
		d.switchOrientation();
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:chasis1");
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
		return ClientProxy.dynamoRenderPass;
	}
	

}
