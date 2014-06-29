package common.cout970.UltraTech.Tiers.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public interface IBlockMachine {

	public void registerBlockIcons(IIconRegister IR);
	public IIcon getIcon(int side, int meta);
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d);
	public void onNeighborBlockChange(World w, int x, int y, int z, Block block);
}
