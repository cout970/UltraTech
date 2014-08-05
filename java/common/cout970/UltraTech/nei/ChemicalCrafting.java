package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ultratech.api.recipes.Chemical_Recipe;
import ultratech.api.recipes.Fermenter_Recipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import common.cout970.UltraTech.managers.FluidManager;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.render.RenderUtil;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class ChemicalCrafting extends TemplateRecipeHandler{


	private List<Chemical_Recipe> recipes = new ArrayList<Chemical_Recipe>();
	private double zLevel;

	@Override
	public String getRecipeName() {
		return "Chemical Plant";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/chemical.png";
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result){
		for(Chemical_Recipe c : Chemical_Recipe.recipes){
			if(UT_Utils.areEcuals(result, c.out_1, true)){recipes.add(c);}
			if(UT_Utils.areEcuals(result, c.out_2, true)){recipes.add(c);}
			if(UT_Utils.areEcuals(result, c.out_3, true)){recipes.add(c);}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient){}
	
	@Override
	public PositionedStack getResultStack(int recipe)
	{
		return null;
	}
	@Override
	public List<PositionedStack> getOtherStacks(int recipe)
	{
		Chemical_Recipe r = recipes.get(recipe);
		ArrayList<PositionedStack> res = new ArrayList<PositionedStack>();
		res.add(new PositionedStack(r.out_1, 140, 4));
		res.add(new PositionedStack(r.out_2, 140, 22));
		res.add(new PositionedStack(r.out_3, 140, 40));
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
		int ticks2 = 500;
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		this.drawProgressBar(9, 4, 0, 0, 25, 50, 1f-(cycleticks % ticks2 / (float)ticks2), 3);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nei:textures/gui/chemical.png"));
		this.drawProgressBar(115, 3, 177, 0, 25, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
		this.drawProgressBar(115, 21, 177, 0, 25, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
		this.drawProgressBar(115, 39, 177, 0, 25, 16, 1f-(cycleticks % ticks / (float)ticks), 4);

		//fluid
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		IIcon ic = FluidRegistry.getFluid("plastic").getStillIcon();
		if(ic == null)return;
		int a = (int) (40f*(1f-(cycleticks % 200 / (float)200)));
		this.drawTexturedModelRectFromIcon(41, 50-a, ic, 18, a);
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/gui/chemical.png"));
		this.drawProgressBar(40, 9, 224, 0, 20, 42, 0f, 4);
		//text
		Minecraft.getMinecraft().fontRenderer.drawString("1000mb of Plastic", 45, 55, UT_Utils.RGBtoInt(255, 255, 255), true);
		
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
