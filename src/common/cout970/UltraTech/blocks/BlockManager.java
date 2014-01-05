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
		 
		 for(UT_Block b : d){
		 GameRegistry.registerBlock(b, UT_itemBlock.class, b.texture);
		 LanguageRegistry.addName(b, "Decorative Block");
		 }
		 int i = 0;
		 int m = 0;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,15),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,0),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,1),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,10),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,4),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,11),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,12),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,2),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,5),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,9),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',new ItemStack(Item.dyePowder,1,14),'x',UltraTech.StoneBlock});i++;
		 m = 0;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,15),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,0),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,1),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,10),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,4),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,11),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,12),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,2),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,5),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,9),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',new ItemStack(Item.dyePowder,1,14),'x',UltraTech.StoneBlock});i++;
		 m = 0;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,15),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,0),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,1),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,10),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,4),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,11),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,12),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,2),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,5),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,9),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlock,'c',new ItemStack(Item.dyePowder,1,14),'x',UltraTech.StoneBlock});i++;
		 m = 0;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,15),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,0),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,1),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,10),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,4),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,11),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,12),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,2),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,5),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,9),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',Block.cobblestone,'c',new ItemStack(Item.dyePowder,1,14),'x',UltraTech.StoneBlock});i++;
		 m = 0;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,15),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,0),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,1),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,10),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,4),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,11),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,12),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,2),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,5),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,9),'x',UltraTech.StoneBlock});m++;
		 GameRegistry.addShapedRecipe(new ItemStack(d.get(i),8,m), new Object[]{"xbx","bcb","xbx",'b',UltraTech.StoneBlockBricks,'c',new ItemStack(Item.dyePowder,1,14),'x',UltraTech.StoneBlock});i++;
		 m = 0;
		 
	}
}
