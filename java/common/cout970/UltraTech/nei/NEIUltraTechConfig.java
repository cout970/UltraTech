package common.cout970.UltraTech.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.gui.CrafterGui;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIUltraTechConfig implements IConfigureNEI{

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new PurifierCrafting());
		API.registerRecipeHandler(new CutterCrafting());
		API.registerRecipeHandler(new CVD_Crafting());
		API.registerRecipeHandler(new AssemblyCrafting());
		API.registerRecipeHandler(new LaminatorCrafting());
		API.registerUsageHandler(new PurifierCrafting());
		API.registerUsageHandler(new CutterCrafting());
		API.registerUsageHandler(new CVD_Crafting());
		API.registerUsageHandler(new AssemblyCrafting());
		API.registerUsageHandler(new FermenterCrafting());
		API.registerUsageHandler(new LaminatorCrafting());
		API.registerGuiOverlayHandler(CrafterGui.class, new CrafterOverlayHandler(), "crafting");
	}

	@Override
	public String getName() {
		return "UltraTech plugin";
	}

	@Override
	public String getVersion() {
		return "0.7";
	}
	
	public static boolean matches(ItemStack a,ItemStack b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(b.equals(a)){
			if(a.getItemDamage() == b.getItemDamage()){
				return true;
			}
		}else{
			if(OreDictionary.itemMatches(a, b, true))return true;
		}
		return false;
	}

}
