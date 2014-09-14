package common.cout970.UltraTech.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.client.gui.GuiCrafter;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIUltraTechConfig implements IConfigureNEI{

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new CraftingPurifier());
		API.registerRecipeHandler(new CraftingCutter());
		API.registerRecipeHandler(new CraftingCVD());
		API.registerRecipeHandler(new CraftingMolecularAssembly());
		API.registerRecipeHandler(new CraftingLaminator());
		API.registerRecipeHandler(new CraftingChemical());
		API.registerRecipeHandler(new CraftingRefinery());
		API.registerRecipeHandler(new CraftingBoiler());
		API.registerRecipeHandler(new CraftingFermenter());
		
		API.registerUsageHandler(new CraftingPurifier());
		API.registerUsageHandler(new CraftingCutter());
		API.registerUsageHandler(new CraftingCVD());
		API.registerUsageHandler(new CraftingMolecularAssembly());
		API.registerUsageHandler(new CraftingFermenter());
		API.registerUsageHandler(new CraftingLaminator());
		API.registerUsageHandler(new CraftingRefinery());
		API.registerUsageHandler(new CraftingBoiler());
		
		API.registerGuiOverlayHandler(GuiCrafter.class, new CrafterOverlayHandler(), "crafting");
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
