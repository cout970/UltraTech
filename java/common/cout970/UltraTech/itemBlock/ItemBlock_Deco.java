package common.cout970.UltraTech.itemBlock;

import java.util.List;

import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.wiki.WikiResources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBlock_Deco extends ItemBlockUT{

	public final static String[] subNames = {
		"White","Black","Blue","Steal Blue","Cyan","Sea Green","Green","Light Green","Yellow","Orange","Red","Purple","Pink","Blue Violet"
	};
	
	public ItemBlock_Deco(Block b) {
		super(b, "deco");
		for(int v=1;v<9;v++){
			WikiResources.decoblack[v-1] = new ResourceLocation("ultratech","textures/blocks/decoration/black/deco"+v+".png");
			WikiResources.decowhite[v-1] = new ResourceLocation("ultratech","textures/blocks/decoration/white/deco"+v+".png");
		}
		WikiResources.decoblock[0] = new ResourceLocation("ultratech","textures/blocks/decoration/white/stoneblock.png");
		WikiResources.decoblock[1] = new ResourceLocation("ultratech","textures/blocks/decoration/black/stoneblock.png");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean par4) {
		super.addInformation(is, p, l, par4);
		try{
			l.add("Use a 3D painter to change the color");
		}catch(Exception e){}
	}

}
