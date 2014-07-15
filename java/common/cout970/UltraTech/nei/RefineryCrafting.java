package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import common.cout970.UltraTech.recipes.Chemical_Recipe;
import common.cout970.UltraTech.recipes.Cooling_Recipes;
import common.cout970.UltraTech.util.RenderUtil;
import common.cout970.UltraTech.util.UT_Utils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class RefineryCrafting extends TemplateRecipeHandler{

	private List<Cooling_Recipes> recipes = new ArrayList<Cooling_Recipes>();
	private double zLevel;

	@Override
	public String getRecipeName() {
		return "Refinery Multiblock";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/refinery.png";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result){
		for(Cooling_Recipes c : Cooling_Recipes.recipes){
			for(int g=0;g<3;g++){
				if(c.product[g] != null && c.product[g].getFluid() != null && c.product[g].getFluid().getBlock() != null){
					if(UT_Utils.areEcuals(new ItemStack(c.product[g].getFluid().getBlock()),result,false)){
						recipes.add(c);
					}
				}
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(Cooling_Recipes c : Cooling_Recipes.recipes){
			if(c.input != null && c.input.getFluid() != null && c.input.getFluid().getBlock() != null){
				if(UT_Utils.areEcuals(new ItemStack(c.input.getFluid().getBlock()),ingredient,false)){
					recipes.add(c);
				}
			}
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
		return recipes.size();
	}
	
	@Override
	public void drawExtras(int recipe)
    {
		int ticks = 100;
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nei:textures/gui/refinery.png"));
		this.drawProgressBar(57, 22, 177, 0, 25, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
		
		Cooling_Recipes r = recipes.get(recipe);
		
		//fluids
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		IIcon ic2 = r.input.getFluid().getStillIcon();
		int b = (int) (40f*(1f-(cycleticks % 200 / (float)200)));
		this.drawTexturedModelRectFromIcon(35, 50-b, ic2, 18, b);

		
		for(int f=0;f<3;f++){
			if(r.product[f] == null)continue;
			IIcon ic = r.product[f].getFluid().getStillIcon();
			if(ic == null)continue;
			int amount = r.product[f].amount;
			amount = 100-amount;
			amount *= 5;
			int a = (int) (40f*((cycleticks % amount / (float)amount)));
			this.drawTexturedModelRectFromIcon(84+25*f, 50-a, ic, 18, a);
		}
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/gui/refinery.png"));
		this.drawProgressBar(83, 10, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(108, 10, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(133, 10, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(34, 10, 224, 0, 20, 42, 0f, 4);
		//text
		String in = "Input: "+r.input.amount+" of "+r.input.getFluid().getName();
		Minecraft.getMinecraft().fontRenderer.drawString(in, 5, 55, UT_Utils.RGBtoInt(255, 255, 255), true);
		if(r.product[0] != null){
			String out1 = "1-Output: "+r.product[0].amount+" of "+r.product[0].getFluid().getName();
			Minecraft.getMinecraft().fontRenderer.drawString(out1, 5, 68, UT_Utils.RGBtoInt(255, 255, 255), true);
		}
		if(r.product[1] != null){
			String out2 = "2-Output: "+r.product[1].amount+" of "+r.product[1].getFluid().getName();
			Minecraft.getMinecraft().fontRenderer.drawString(out2, 5, 81, UT_Utils.RGBtoInt(255, 255, 255), true);
		}
		if(r.product[2] != null){
			String out2 = "2-Output: "+r.product[2].amount+" of "+r.product[2].getFluid().getName();
			Minecraft.getMinecraft().fontRenderer.drawString(out2, 5, 94, UT_Utils.RGBtoInt(255, 255, 255), true);
		}
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
