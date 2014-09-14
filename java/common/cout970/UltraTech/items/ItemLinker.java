package common.cout970.UltraTech.items;

import buildcraft.api.tools.IToolWrench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLinker extends ItemUT implements IToolWrench{


	public ItemLinker(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		return false;
	}
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
	}

	@Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
		return true;
	}

	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z) {}
}
