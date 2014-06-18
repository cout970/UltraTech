package common.cout970.UltraTech.nei;

import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import common.cout970.UltraTech.managers.BlockManager;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class HelpInfo extends TemplateRecipeHandler{

	public String data;
	
	@Override
	public String getRecipeName() {
		return "UltraTech help";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/base.png";
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		if(ingredient != null && ingredient == new ItemStack(BlockManager.Tier3,0,ingredient.stackSize))data = "Miner";
	}
	
	@Override
	public void drawExtras(int recipe)
    {
		
    }
}
