package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.logistics.TileEntityInfiniteSupply;
import common.cout970.UltraTech.managers.UltraTech;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInfiniteSupply extends BlockContainer{

	public BlockInfiniteSupply() {
		super(Material.iron);
		setBlockName("wip");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityInfiniteSupply();
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, w, x, y, z);
        return true;
    }

}
