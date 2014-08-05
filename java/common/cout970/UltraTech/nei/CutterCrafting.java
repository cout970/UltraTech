package common.cout970.UltraTech.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import ultratech.api.recipes.Cutter_Recipe;
import ultratech.api.recipes.RecipeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

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
            for (Cutter_Recipe recipe : RecipeRegistry.cutter)
                recipes.add(recipe);
        }  else super.loadCraftingRecipes(outputId, results);
    }

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for(int x = 0; x < RecipeRegistry.cutter.size();x++ ){
			if(NEIUltraTechConfig.matches(result, RecipeRegistry.cutter.get(x).getResult(0)) || NEIUltraTechConfig.matches(result, RecipeRegistry.cutter.get(x).getResult(1))){
				recipes.add(RecipeRegistry.cutter.get(x));
			}
		}
	}


	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(int x = 0; x < RecipeRegistry.cutter.size();x++ ){
			if(NEIUltraTechConfig.matches(ingredient, RecipeRegistry.cutter.get(x).getInput(0))){
				recipes.add(RecipeRegistry.cutter.get(x));
			}
		}
	}
	@Override
	public PositionedStack getResultStack(int recipe)
	{
		return new PositionedStack(recipes.get(recipe).getResult(),112,21);
	}
	@Override
	public List<PositionedStack> getOtherStacks(int recipe)
	{
		List<PositionedStack> ps = new ArrayList<PositionedStack>();
		if(recipes.get(recipe).getResult(1) != null)
		ps.add(new PositionedStack(recipes.get(recipe).getResult(1),132,21));
		return ps;
	}
	@Override
	public List<PositionedStack> getIngredientStacks(int recipe)
	{
		List<PositionedStack> need = new ArrayList<PositionedStack>();
		need.add(new PositionedStack(recipes.get(recipe).getInput(0), 56,21));
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
