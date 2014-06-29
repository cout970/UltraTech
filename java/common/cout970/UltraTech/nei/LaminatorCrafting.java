package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

import common.cout970.UltraTech.lib.recipes.Laminator_Recipe;

public class LaminatorCrafting extends TemplateRecipeHandler{

	List<Laminator_Recipe> recipes = new ArrayList<Laminator_Recipe>();
	
	@Override
	public String getRecipeName() {
		return "Laminator";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/laminator.png";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for(int x = 0; x < Laminator_Recipe.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(result, Laminator_Recipe.recipes.get(x).getOutput())){
				recipes.add(Laminator_Recipe.recipes.get(x));
			}
		}
	}


	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(int x = 0; x < Laminator_Recipe.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(ingredient, Laminator_Recipe.recipes.get(x).getInput())){
				recipes.add(Laminator_Recipe.recipes.get(x));
			}
		}
	}
	@Override
	public PositionedStack getResultStack(int recipe)
	{
		return new PositionedStack(recipes.get(recipe).getOutput(),137,22);
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
		need.add(new PositionedStack(recipes.get(recipe).getInput(), 47, 22));
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
		this.drawProgressBar(8, 4, 0, 0, 25, 50, 1f-(cycleticks % ticks2 / (float)ticks2), 3);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nei:textures/gui/laminator.png"));
		this.drawProgressBar(87, 22, 176, 14, 24, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
    }

}
