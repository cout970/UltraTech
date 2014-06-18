package api.cout970.UltraTech.microparts;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import common.cout970.UltraTech.core.UltraTech;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JItemMultiPart;
import codechicken.multipart.TMultiPart;

public class ItemPlaneCableMultipart extends JItemMultiPart {
    
    public ItemPlaneCableMultipart() {
        super();
        setUnlocalizedName("UT_Plane_cable");
        setCreativeTab(UltraTech.techTab);
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
	public TMultiPart newPart(ItemStack arg0, EntityPlayer arg1, World arg2,
			BlockCoord arg3, int arg4, Vector3 arg5) {
		return new MicroCablePlane();
	}
    
}