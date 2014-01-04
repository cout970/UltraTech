package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.WindMillEntity;
import common.cout970.UltraTech.machines.tileEntities.hitBoxEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WindMill extends BlockContainer{

	public WindMill(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("MachineChasis");
		this.setBlockBounds(0, 0, 0, 1, 5, 1);
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:windmill");
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new WindMillEntity();
	}

	@Override
    public int getRenderType() {
            return -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
            return false;
    }
    
    public boolean renderAsNormalBlock() {
    	return false;
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	super.onBlockAdded(par1World, par2, par3, par4);
    	for(int d=1;d<5;d++){
    		par1World.setBlock(par2, par3+d, par4, UltraTech.hitBox.blockID);

    	}

    	for(int d=1;d<5;d++){
    		hitBoxEntity h =((hitBoxEntity)par1World.getBlockTileEntity(par2, par3+d, par4));
    		if(h != null){
    			h.x = par2;
    			h.y = par3;
    			h.z = par4;
    		}else
    		System.out.println("null  ");
    	}
    }


    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
    	super.breakBlock(world, x, y, z, par5, par6);
    	for(int d=1;d<5;d++){
    		world.setBlockToAir(x, y+d, z);
    	}
    }
}
