package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.WindMillEntity;
import common.cout970.UltraTech.machines.tileEntities.hitBoxEntity;
import net.minecraft.block.Block;
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
		setUnlocalizedName("WindMill");
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
    public void onBlockAdded(World par1World, int xCoord, int yCoord, int zCoord)
    {
    	super.onBlockAdded(par1World, xCoord, yCoord, zCoord);
    	for(int d=1;d<5;d++){
    		par1World.setBlock(xCoord, yCoord+d, zCoord, UltraTech.hitBox.blockID);

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
    
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
    	for(int d=1;d<5;d++){
    		int id = par1World.getBlockId(par2, par3+d, par4);
    		Block block = Block.blocksList[id];
    		if(block != null && !block.isBlockReplaceable(par1World, par2, par3+d, par4)){
    			return false;
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
    	for(int d=1;d<5;d++){
    		world.setBlockToAir(x, y+d, z);
    	}
    }
}
