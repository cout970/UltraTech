package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.recipes.Assembly_Recipes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class CraftingMolecularAssembly extends TemplateRecipeHandler{

	List<Assembly_Recipes> recipes = new ArrayList<Assembly_Recipes>();
	
	@Override
	public String getRecipeName() {
		return "Molecular Assembly";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/ma.png";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for(int x = 0; x < Assembly_Recipes.recipes.size();x++ ){
			if(Assembly_Recipes.equals(Assembly_Recipes.recipes.get(x).getOutput(),result)){	
				recipes.add(Assembly_Recipes.recipes.get(x));
			}
		}
	}


	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(int x = 0; x < Assembly_Recipes.recipes.size();x++ ){
			for(int c =0;c < 9;c++){
				if(Assembly_Recipes.equals(Assembly_Recipes.recipes.get(x).getInput(c),ingredient)){
					recipes.add(Assembly_Recipes.recipes.get(x));
					break;
				}
			}
		}
	}
	@Override
	public PositionedStack getResultStack(int recipe)
	{
		return new PositionedStack(recipes.get(recipe).getOutput(),141,23);
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
		int x = 0;
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(0), 54,7));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(1), 72,7));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(2), 90,7));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(3), 54,25));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(4), 72,25));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(5), 90,25));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(6), 54,43));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(7), 72,43));
		if(recipes.get(recipe).getInput(x++) != null)
			need.add(new PositionedStack(recipes.get(recipe).getInput(8), 90,43));

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
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nei:textures/gui/ma.png"));
		this.drawProgressBar(114, 23, 176, 12, 24, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
    }
}











