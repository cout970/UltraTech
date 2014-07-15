package common.cout970.UltraTech.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

import common.cout970.UltraTech.recipes.Cutter_Recipe;

public class CutterCrafting extends TemplateRecipeHandler {

	List<Cutter_Recipe> recipes = new ArrayList<Cutter_Recipe>();

	@Override
	public String getRecipeName() {
		return "Cutter";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/cuter.png";
	}

	@Override
	public void loadTransferRects() {

		transferRects.add(new RecipeTransferRect(new Rectangle(80, 20, 24, 15), getRecipesID()));
	}

	private String getRecipesID() {
		return "cutter";
	}

	
	
	@Override
    public void loadCraftingRecipes(String outputId, Object... results) {
    
        if (outputId.equals(getRecipesID())) {
            for (Cutter_Recipe recipe : Cutter_Recipe.recipes)
                recipes.add(recipe);
        }  else super.loadCraftingRecipes(outputId, results);
    }

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for(int x = 0; x < Cutter_Recipe.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(result, Cutter_Recipe.recipes.get(x).getOutput())){
				recipes.add(Cutter_Recipe.recipes.get(x));
			}
		}
	}


	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(int x = 0; x < Cutter_Recipe.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(ingredient, Cutter_Recipe.recipes.get(x).getInput())){
				recipes.add(Cutter_Recipe.recipes.get(x));
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
