package common.cout970.UltraTech.nei;

import common.cout970.UltraTech.lib.Reference;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIUltraTechConfig implements IConfigureNEI{

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new PurifierCrafting());
		API.registerRecipeHandler(new CuterCrafting());
		API.registerRecipeHandler(new CVD_Crafting());
		API.registerUsageHandler(new PurifierCrafting());
		API.registerUsageHandler(new CuterCrafting());
		API.registerUsageHandler(new CVD_Crafting());
	}

	@Override
	public String getName() {
		return "UltraTech plugin";
	}

	@Override
	public String getVersion() {
		return Reference.NeiPluginVersion;
	}

}
