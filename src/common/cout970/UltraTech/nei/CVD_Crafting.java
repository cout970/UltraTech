package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;

public class CVD_Crafting extends TemplateRecipeHandler {

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
	public void loadCraftingRecipes(ItemStack result)
	{
		for(int x = 0; x < CVD_Recipe.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(result, CVD_Recipe.recipes.get(x).getOutput())){
				recipes.add(CVD_Recipe.recipes.get(x));
			}
		}
	}


	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(int x = 0; x < CVD_Recipe.recipes.size();x++ ){
			if(NEIUltraTechConfig.matches(ingredient, CVD_Recipe.recipes.get(x).getImput1()) || NEIUltraTechConfig.matches(ingredient, CVD_Recipe.recipes.get(x).getImput2())){
				recipes.add(CVD_Recipe.recipes.get(x));
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
		need.add(new PositionedStack(recipes.get(recipe).getImput1(), 47, 22));
		need.add(new PositionedStack(recipes.get(recipe).getImput2(), 83, 22));
		return need;
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

