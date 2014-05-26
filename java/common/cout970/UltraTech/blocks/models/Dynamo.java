package common.cout970.UltraTech.blocks.models;

import api.cout970.UltraTech.FTpower.BlockConductor;
import api.cout970.UltraTech.FTpower.Machine;
import common.cout970.UltraTech.TileEntities.Tier1.BoilerEntity;
import common.cout970.UltraTech.TileEntities.Tier2.DynamoEntity;
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
		TileEntity e = w.getTileEntity(x, y, z);
		if(e != null){
			p.openGui(UltraTech.instance, 13, w, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world,int b) {
		return new DynamoEntity();
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:dynamo");
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
