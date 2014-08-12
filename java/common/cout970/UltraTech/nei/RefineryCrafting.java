package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.recipes.Chemical_Recipe;
import ultratech.api.recipes.RecipeRegistry;
import ultratech.api.recipes.Refinery_Recipe;
import ultratech.api.util.UT_Utils;
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
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class RefineryCrafting extends TemplateRecipeHandler{

	private List<Refinery_Recipe> recipes = new ArrayList<Refinery_Recipe>();
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
		FluidStack f = FluidContainerRegistry.getFluidForFilledItem(result);
		if(f == null)return;
		for(Refinery_Recipe c : RecipeRegistry.refinery){
			if(c.out1 != null){
				if(FluidContainerRegistry.containsFluid(result, new FluidStack(c.out1,1))){
					recipes.add(c);
				}else if(c.out1.getBlock() != null){
					if(UT_Utils.areEcuals(new ItemStack(c.out1.getBlock()),result,false)){
						recipes.add(c);
					}
				}
			}
			if(c.out2 != null){
				if(FluidContainerRegistry.containsFluid(result, new FluidStack(c.out2,1))){
					recipes.add(c);
				}else if(c.out2.getBlock() != null){
					if(UT_Utils.areEcuals(new ItemStack(c.out2.getBlock()),result,false)){
						recipes.add(c);
					}
				}
			}
			if(c.out3 != null){
				if(FluidContainerRegistry.containsFluid(result, new FluidStack(c.out3,1))){
					recipes.add(c);
				}else if(c.out3.getBlock() != null){
					if(UT_Utils.areEcuals(new ItemStack(c.out3.getBlock()),result,false)){
						recipes.add(c);
					}
				}
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(Refinery_Recipe c : RecipeRegistry.refinery){
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
		
		Refinery_Recipe r = recipes.get(recipe);
		
		//fluids
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		IIcon ic2 = r.input.getFluid().getStillIcon();
		int b = (int) (40f*(1f-(cycleticks % 200 / (float)200)));
		this.drawTexturedModelRectFromIcon(35, 50-b, ic2, 18, b);


		if(r.out1 != null){
			IIcon ic = r.out1.getStillIcon();
			if(ic != null){
				float amount = r.amount1*10;
				int a = (int) (40f*amount / 100);
				this.drawTexturedModelRectFromIcon(84+25*0, 50-a, ic, 18, a);
			}
		}
		
		if(r.out2 != null){
			IIcon ic = r.out2.getStillIcon();
			if(ic != null){
				float amount = r.amount2*10;
				int a = (int) (40f*amount / 100);
				this.drawTexturedModelRectFromIcon(84+25*1, 50-a, ic, 18, a);
			}
		}

		if(r.out3 != null){
			IIcon ic = r.out3.getStillIcon();
			if(ic != null){
				float amount = r.amount3*10;
				int a = (int) (40f*amount / 100);
				this.drawTexturedModelRectFromIcon(84+25*2, 50-a, ic, 18, a);
			}
		}
		
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/gui/refinery.png"));
		this.drawProgressBar(83, 10, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(108, 10, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(133, 10, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(34, 10, 224, 0, 20, 42, 0f, 4);
		//text
		String in = "Input: "+r.input.amount+" of "+r.input.getFluid().getName();
		Minecraft.getMinecraft().fontRenderer.drawString(in, 5, 55, UT_Utils.RGBtoInt(255, 255, 255), true);
		if(r.out1 != null){
			String out1 = "1-Output: "+r.amount1+" of "+r.out1.getName();
			Minecraft.getMinecraft().fontRenderer.drawString(out1, 5, 68, UT_Utils.RGBtoInt(255, 255, 255), true);
		}
		if(r.out2 != null){
			String out2 = "2-Output: "+r.amount2+" of "+r.out2.getName();
			Minecraft.getMinecraft().fontRenderer.drawString(out2, 5, 81, UT_Utils.RGBtoInt(255, 255, 255), true);
		}
		if(r.out3 != null){
			String out2 = "2-Output: "+r.amount3+" of "+r.out3.getName();
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
