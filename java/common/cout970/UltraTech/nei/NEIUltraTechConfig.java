package common.cout970.UltraTech.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.client.gui.Crafter_Gui;
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
		API.registerRecipeHandler(new ChemicalCrafting());
		API.registerRecipeHandler(new RefineryCrafting());
		API.registerRecipeHandler(new BoilerCrafting());
		API.registerRecipeHandler(new FermenterCrafting());
		
		API.registerUsageHandler(new PurifierCrafting());
		API.registerUsageHandler(new CutterCrafting());
		API.registerUsageHandler(new CVD_Crafting());
		API.registerUsageHandler(new AssemblyCrafting());
		API.registerUsageHandler(new FermenterCrafting());
		API.registerUsageHandler(new LaminatorCrafting());
		API.registerUsageHandler(new RefineryCrafting());
		API.registerUsageHandler(new BoilerCrafting());
		
		API.registerGuiOverlayHandler(Crafter_Gui.class, new CrafterOverlayHandler(), "crafting");
	}

	@Override
	public String getName() {
		return "UltraTech plugin";
	}

	@Override
	public String getVersion() {
		return "0.9.1";
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
