package common.cout970.UltraTech.blocks;

import java.util.List;

import buildcraft.api.tools.IToolWrench;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.EngineEntity;
import common.cout970.UltraTech.machines.tileEntities.Machine;
import common.cout970.UltraTech.machines.tileEntities.SolarPanelEntity;
import common.cout970.UltraTech.machines.tileEntities.WindMillEntity;
import common.cout970.UltraTech.machines.tileEntities.hitBoxEntity;
import common.cout970.UltraTech.managers.BlockManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModelsBlock extends BlockContainer{

	public int n = 3;
	private Icon blockIcon1;
	private Icon blockIcon2;
	private Icon blockIcon3;
	
	public ModelsBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(20);
		setUnlocalizedName("ModelsBlock");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
	{		
		switch(par2){
		case 0:return this.blockIcon1;
		case 1:return this.blockIcon2;
		case 2:return this.blockIcon3;
		default:return this.blockIcon;
		}
	}
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			return true;
		}else{
			TileEntity tile = world.getBlockTileEntity(i, j, k);
			if(tile != null){ 
				if(tile instanceof EngineEntity){
					if(entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().getItem() instanceof IToolWrench){
						((EngineEntity) tile).Rotate();
					}else{
					entityplayer.openGui(UltraTech.instance, 13, world, i, j, k);
					return true;
					}
				}
			}
		}
		return false;
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		Machine m = (Machine) w.getBlockTileEntity(x, y, z);
		m.updateMachine(w, x, y, z);
		if(m instanceof EngineEntity){
			((EngineEntity) m).canFound = true;
			((EngineEntity) m).c = null;
		}
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:void");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:solarpanel");
		this.blockIcon2 = iconRegister.registerIcon("ultratech:windmill");
		this.blockIcon3 = iconRegister.registerIcon("ultratech:engine");
		
	}
	
	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		for (int ix = 0; ix < n; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if(metadata == 0)return new SolarPanelEntity();
		if(metadata == 1)return new WindMillEntity();
		if(metadata == 2)return new EngineEntity();
		return null;
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public void onBlockAdded(World par1World, int xCoord, int yCoord, int zCoord)
	{
		super.onBlockAdded(par1World, xCoord, yCoord, zCoord);
		if(par1World.getBlockMetadata(xCoord, yCoord, zCoord)==1){
			for(int d=1;d<5;d++){
				par1World.setBlock(xCoord, yCoord+d, zCoord, BlockManager.hitBox.blockID);

			}
			for(int d=1;d<5;d++){
				hitBoxEntity h =((hitBoxEntity)par1World.getBlockTileEntity(xCoord, yCoord+d, zCoord));
				if(h != null){
					h.x = xCoord;
					h.y = yCoord;
					h.z = zCoord;
				}
			}
		}
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		EntityPlayer p = par1World.getClosestPlayer(par2, par3, par4, 10);
		if(p != null && p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItemDamage() == 1){
			for(int d=1;d<5;d++){
				int id = par1World.getBlockId(par2, par3+d, par4);
				Block block = Block.blocksList[id];
				if(block != null && !block.isBlockReplaceable(par1World, par2, par3+d, par4)){
					return false;
				}
			}
		}
		int l = par1World.getBlockId(par2, par3, par4);
		Block block = Block.blocksList[l];
		return block == null || block.isBlockReplaceable(par1World, par2, par3, par4);
	}


    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
    	super.breakBlock(world, x, y, z, par5, par6);
    	if(world.getBlockMetadata(x, y, z)==1){
    		for(int d=1;d<5;d++){
    			world.setBlockToAir(x, y+d, z);
    		}
    	}
    }
}
