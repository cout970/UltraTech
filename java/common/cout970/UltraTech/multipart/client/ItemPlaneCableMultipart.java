package common.cout970.UltraTech.multipart.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.multipart.MultipartUtil;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Down;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_East;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_North;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_South;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Up;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_West;
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
			BlockCoord pos, int side, Vector3 hit){
		if(!MultipartUtil.isSolid(w, pos.x, pos.y, pos.z, ForgeDirection.getOrientation(side).getOpposite()))return null;
		if(side == ForgeDirection.UP.getOpposite().ordinal())return new MultiPartCable_Ribbon_Up();
		if(side == ForgeDirection.NORTH.getOpposite().ordinal())return new MultiPartCable_Ribbon_North();
		if(side == ForgeDirection.SOUTH.getOpposite().ordinal())return new MultiPartCable_Ribbon_South();
		if(side == ForgeDirection.EAST.getOpposite().ordinal())return new MultiPartCable_Ribbon_East();
		if(side == ForgeDirection.WEST.getOpposite().ordinal())return new MultiPartCable_Ribbon_West();
		return new MultiPartCable_Ribbon_Down();
	}
    
}