package common.cout970.UltraTech.microparts;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.LogHelper;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JItemMultiPart;
import codechicken.multipart.TMultiPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlaneCableMultipart extends JItemMultiPart {
    
    public ItemPlaneCableMultipart() {
        super();
        setUnlocalizedName("UT_Plane_cable");
        setCreativeTab(UT_Tabs.techTab);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("ultratech:cable");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z, int side, float f, float f2, float f3) {
    
        if (super.onItemUse(stack, player, w, x, y, z, side, f, f2, f3)) {
            w.playSoundEffect(x + 0.5, y + 0.5, z + 0.5,
                    Block.soundTypeMetal.getBreakSound(),
                    Block.soundTypeMetal.getVolume() * 5.0F,
                    Block.soundTypeMetal.getPitch() * .9F);
            return true;
        }
        return false;
    }
    
	@Override
	public TMultiPart newPart(ItemStack item, EntityPlayer p, World w,
			BlockCoord pos, int side, Vector3 hit) {
		MicroCablePlane c = new MicroCablePlane();
		return c;
	}
    
}