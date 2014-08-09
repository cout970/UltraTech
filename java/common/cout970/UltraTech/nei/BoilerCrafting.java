package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import common.cout970.UltraTech.util.render.RenderUtil;
import ultratech.api.recipes.Boiler_Recipes;
import ultratech.api.recipes.RecipeRegistry;
import ultratech.api.recipes.Refinery_Recipe;
import ultratech.api.util.UT_Utils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class BoilerCrafting extends TemplateRecipeHandler{

	public String input;
	public String output;
	private double zLevel;
	
	@Override
	public String getRecipeName() {
		return "Boiler";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/boiler.png";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result){
		FluidStack f = FluidContainerRegistry.getFluidForFilledItem(result);
		if(f != null && RecipeRegistry.boiler.containsValue(f.getFluid().getName())){
			output = f.getFluid().getName();
			for(String s :RecipeRegistry.boiler.keySet()){
				if(RecipeRegistry.boiler.get(s) == output)input = s;
			}
			
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		FluidStack f = FluidContainerRegistry.getFluidForFilledItem(ingredient);
		if(f != null && RecipeRegistry.boiler.containsKey(f.getFluid().getName())){
			input = f.getFluid().getName();
			output = RecipeRegistry.boiler.get(input);
		}
	}
	
	@Override
	public PositionedStack getResultStack(int recipe)
	{
		return null;
	}
	@Override
	public List<PositionedStack> getOtherStacks(int recipe)
	{
		ArrayList<PositionedStack> res = new ArrayList<PositionedStack>();
		return res;
	}
	
	@Override
	public List<PositionedStack> getIngredientStacks(int recipe)
	{
		return new ArrayList<PositionedStack>();
	}

	@Override
	public int numRecipes()
	{
		if(input != null)return 1;
		return 0;
	}
	
	@Override
	public void drawExtras(int recipe)
    {
		int ticks = 100;
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(getGuiTexture()));
		this.drawProgressBar(69, 41, 208, 40, 5, 20, 0f, 4);
		Minecraft.getMinecraft().fontRenderer.drawString("100C", 95, 27, UT_Utils.RGBtoInt(255, 255, 255));

		//fluids
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		Fluid f = FluidRegistry.getFluid(input);
		Fluid o = FluidRegistry.getFluid(output);
		if(f == null)return;
		if(o == null)return;
		IIcon ic2 = f.getStillIcon();
		if(ic2 != null){
			int b = (int) (40f*(1f-(cycleticks % 200 / (float)200)));
			this.drawTexturedModelRectFromIcon(41, 50-b, ic2, 18, b);
		}
		
		
		IIcon ic = o.getStillIcon();
		if(ic != null){
			int b = (int) (40f*(1f-(cycleticks % 200 / (float)200)));
			this.drawTexturedModelRectFromIcon(134, 50-b, ic, 18, b);
		}

		RenderUtil.bindTexture(new ResourceLocation(getGuiTexture()));
		this.drawProgressBar(40, 9, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(133, 10, 224, 0, 20, 42, 0f, 4);
		
		String in = "Input: "+input;
		Minecraft.getMinecraft().fontRenderer.drawString(in, 5, 65, UT_Utils.RGBtoInt(255, 255, 255), true);
		
		String out1 = "Output: "+output;
		Minecraft.getMinecraft().fontRenderer.drawString(out1, 5, 78, UT_Utils.RGBtoInt(255, 255, 255), true);

    }
	
	public void drawTexturedModelRectFromIcon(int par1, int par2, IIcon par3Icon, int par4, int par5)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par5), (double)this.zLevel, (double)par3Icon.getMinU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + par5), (double)this.zLevel, (double)par3Icon.getMaxU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + 0), (double)this.zLevel, (double)par3Icon.getMaxU(), (double)par3Icon.getMinV());
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)par3Icon.getMinU(), (double)par3Icon.getMinV());
        tessellator.draw();
    }

}
