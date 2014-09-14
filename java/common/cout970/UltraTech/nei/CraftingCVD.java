package common.cout970.UltraTech.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import ultratech.api.recipes.CVD_Recipe;
import ultratech.api.recipes.Purifier_Recipe;
import ultratech.api.recipes.RecipeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;

public class CraftingCVD extends TemplateRecipeHandler {

	public ArrayList<CVD_Recipe> recipes = new ArrayList<CVD_Recipe>();

	@Override
	public String getRecipeName() {
		return "CVD";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/cvd.png";
	}

	@Override
	public void loadTransferRects() {

		transferRects.add(new RecipeTransferRect(new Rectangle(108, 20, 24, 15), getRecipesID()));
	}

	private String getRecipesID() {
		return "cvd";
	}
	
	@Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(getRecipesID())) {
            for (CVD_Recipe recipe : RecipeRegistry.cvd)
                recipes.add(recipe);
        }  else super.loadCraftingRecipes(outputId, results);
    }

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for(CVD_Recipe a : RecipeRegistry.cvd){
			if(NEIUltraTechConfig.matches(result, a.getResult())){
				recipes.add(a);
			}
		}
	}


	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(CVD_Recipe a : RecipeRegistry.cvd){
			if(NEIUltraTechConfig.matches(ingredient, a.getInput(0)) || NEIUltraTechConfig.matches(ingredient, a.getInput(1))){
				recipes.add(a);
			}
		}
	}
	@Override
	public int numRecipes()
	{
		return recipes.size();
	}
	@Override
	public List<PositionedStack> getIngredientStacks(int recipe)
	{
		List<PositionedStack> need = new ArrayList<PositionedStack>();
		need.add(new PositionedStack(recipes.get(recipe).getInput(0), 47, 22));
		need.add(new PositionedStack(recipes.get(recipe).getInput(1), 83, 22));
		return need;
	}
	@Override
	public PositionedStack getResultStack(int recipe)
	{
		return new PositionedStack(recipes.get(recipe).getResult(),137,22);
	}
	@Override
	public List<PositionedStack> getOtherStacks(int recipe)
	{
		return new ArrayList<PositionedStack>();
	}
	@Override
	public void drawExtras(int recipe)
	{
		int ticks2 = 500;
		int ticks = 100;
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		this.drawProgressBar(8, 4, 0, 0, 25, 50, 1f-(cycleticks % ticks2 / (float)ticks2), 3);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nei:textures/gui/cvd.png"));
		this.drawProgressBar(108, 22, 176, 14, 24, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
	}
}

