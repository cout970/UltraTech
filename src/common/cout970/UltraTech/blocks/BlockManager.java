package common.cout970.UltraTech.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockManager {

	public static List<UT_Block> d = new ArrayList<UT_Block>();
	
	public static void initBlocks(){
		 d.add(new UT_Block(Reference.Deco,"deco"));
		 d.add(new UT_Block(Reference.Deco2,"deco2"));
		 d.add(new UT_Block(Reference.Deco3,"deco3"));
		 d.add(new UT_Block(Reference.Deco4,"deco4"));
		 d.add(new UT_Block(Reference.Deco5,"deco5"));
		 d.add(new UT_Block(Reference.Deco6,"deco6"));
		 d.add(new UT_Block(Reference.Deco7,"deco7"));
		 
		 for(UT_Block b : d){
		 GameRegistry.registerBlock(b, UT_itemBlock.class, b.texture);
		 LanguageRegistry.addName(b, "Decorative Block");
		 }
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(0),8,0), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',Item.redstone,'x',UltraTech.StoneBlock});
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(1),8,0), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',Item.redstone,'x',UltraTech.StoneBlockBricks});
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(2),8,0), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',Item.redstone,'x',UltraTech.StoneBlock});
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(3),8,0), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',Item.redstone,'x',Block.stone});
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(4),8,0), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',Item.redstone,'x',Block.stoneBrick});
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(5),8,0), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',Item.redstone,'x',UltraTech.StoneBlock});
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(6),8,0), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',Item.redstone,'x',Block.stoneBrick});
	}
}
