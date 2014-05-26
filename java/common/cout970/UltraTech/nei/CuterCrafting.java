package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class CuterCrafting extends TemplateRecipeHandler {

	List<Cuter_Recipes> recipes = new ArrayList<Cuter_Recipes>();

	@Override
	public String getRecipeName() {
		return "Cuter";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/cuter.png";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for(int x = 0; x < Cuter_Recipes.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(result, Cuter_Recipes.recipes.get(x).getOutput())){
				recipes.add(Cuter_Recipes.recipes.get(x));
			}
		}
	}


	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(int x = 0; x < Cuter_Recipes.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(ingredient, Cuter_Recipes.recipes.get(x).getInput())){
				recipes.add(Cuter_Recipes.recipes.get(x));
			}
		}
	}
	@Override
	public PositionedStack getResultStack(int recipe)
	{
		return new PositionedStack(recipes.get(recipe).getOutput(),112,21);
	}
	@Override
	public List<PositionedStack> getOtherStacks(int recipe)
	{
		return new ArrayList<PositionedStack>();
	}
	@Override
	public List<PositionedStack> getIngredientStacks(int recipe)
	{
		List<PositionedStack> need = new ArrayList<PositionedStack>();
		need.add(new PositionedStack(recipes.get(recipe).getInput(), 56,21));
		return need;
	}

	@Override
	public int numRecipes()
	{
		return recipes.size();
	}
	
	@Override
	public void drawExtras(int recipe)
    {
		int ticks = 100;
		int ticks2 = 500;
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		this.drawProgressBar(9, 4, 0, 0, 25, 50, 1f-(cycleticks % ticks2 / (float)ticks2), 3);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nei:textures/gui/cuter.png"));
		this.drawProgressBar(80, 20, 178, 14, 24, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
    }

}
