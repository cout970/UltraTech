package common.cout970.UltraTech.blocks;

import common.cout970.UltraTech.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockManager {

	public static UT_Block b;
	
	public static void initBlocks(){
		 b = new UT_Block(Reference.Deco,"deco");
		 GameRegistry.registerBlock(b, "deco_UT");
		 LanguageRegistry.addName(b, "Decorative Block");
	}
}
