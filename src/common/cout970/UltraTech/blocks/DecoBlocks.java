package common.cout970.UltraTech.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockDeco;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockStone;
import common.cout970.UltraTech.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class DecoBlocks {

	public static List<UT_Block> d = new ArrayList<UT_Block>();
	public static Block stoneblock;
	
	public static void initBlocks(){
		//black 
		d.add(new UT_Block(Reference.Deco,"deco",true));
		d.add(new UT_Block(Reference.Deco2,"deco2",true));
		d.add(new UT_Block(Reference.Deco3,"deco3",true));
		d.add(new UT_Block(Reference.Deco4,"deco4",true));
		d.add(new UT_Block(Reference.Deco5,"deco5",true));
		d.add(new UT_Block(Reference.Deco6,"deco6",true));
		d.add(new UT_Block(Reference.Deco7,"deco7",true));
		d.add(new UT_Block(Reference.Deco8,"deco8",true));
		//white
		d.add(new UT_Block(Reference.Decow,"deco",false));
		d.add(new UT_Block(Reference.Deco2w,"deco2",false));
		d.add(new UT_Block(Reference.Deco3w,"deco3",false));
		d.add(new UT_Block(Reference.Deco4w,"deco4",false));
		d.add(new UT_Block(Reference.Deco5w,"deco5",false));
		d.add(new UT_Block(Reference.Deco6w,"deco6",false));
		d.add(new UT_Block(Reference.Deco7w,"deco7",false));
		d.add(new UT_Block(Reference.Deco8w,"deco8",false));

		stoneblock = new StoneBlock(Reference.StoneBlock, Material.rock);

		for(UT_Block b : d){
			GameRegistry.registerBlock(b, UT_ItemBlockDeco.class, b.texture+"_"+(b.black ? "w" : "b"));
			LanguageRegistry.addName(b, "Decorative Block");
		}
		//stoneblock
		GameRegistry.registerBlock(stoneblock, UT_ItemBlockStone.class, "StoneBlock");
		LanguageRegistry.addName(stoneblock, "Purified Stone");

		//black
		GameRegistry.addShapedRecipe(new ItemStack(d.get(0),8,0), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',Item.redstone,'x',new ItemStack(stoneblock,1,0)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(1),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,1),'c',Item.redstone,'x',new ItemStack(stoneblock,1,1)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(2),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,1),'c',Item.redstone,'x',new ItemStack(stoneblock,1,0)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(3),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,0),'c',Item.redstone,'x',Block.stone});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(4),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,0),'c',Item.redstone,'x',Block.stoneBrick});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(5),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,0),'c',Item.redstone,'x',new ItemStack(stoneblock,1,0)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(6),8,0), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',Item.redstone,'x',new ItemStack(stoneblock,1,0)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(7),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,0),'c',Item.redstone,'x',new ItemStack(stoneblock,1,1)});
		
		//white
		GameRegistry.addShapedRecipe(new ItemStack(d.get(8),8,0), new Object[]{"xbx","bcb","xbx",'b',Block.stone,'c',Item.redstone,'x',new ItemStack(stoneblock,1,2)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(9),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,3),'c',Item.redstone,'x',new ItemStack(stoneblock,1,3)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(10),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,3),'c',Item.redstone,'x',new ItemStack(stoneblock,1,2)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(11),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,2),'c',Item.redstone,'x',Block.stone});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(12),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,2),'c',Item.redstone,'x',Block.stoneBrick});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(13),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,2),'c',Item.redstone,'x',new ItemStack(stoneblock,1,2)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(14),8,0), new Object[]{"xbx","bcb","xbx",'b',Block.stoneBrick,'c',Item.redstone,'x',new ItemStack(stoneblock,1,2)});
		GameRegistry.addShapedRecipe(new ItemStack(d.get(15),8,0), new Object[]{"xbx","bcb","xbx",'b',new ItemStack(stoneblock,1,2),'c',Item.redstone,'x',new ItemStack(stoneblock,1,3)});

	}
}
