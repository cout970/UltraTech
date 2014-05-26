package common.cout970.UltraTech.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.lib.recipes.Fermenter_Recipes;
import common.cout970.UltraTech.managers.BlockManager;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FermenterCrafting extends TemplateRecipeHandler{

	public Map<Item,Integer> recipes = new HashMap<Item,Integer>();
	public Map<Integer,Item> inverse = new HashMap<Integer,Item>();
	private double zLevel;
	@Override
	public String getRecipeName() {
		return "Fermenter";
	}

	@Override
	public String getGuiTexture() {
		return "nei:textures/gui/fermenter.png";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result){}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		if(ingredient != null && Fermenter_Recipes.recipes.containsKey(ingredient.getItem())){
			recipes.put(ingredient.getItem(),Fermenter_Recipes.recipes.get(ingredient));
			inverse.put(0, ingredient.getItem());
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
		return new ArrayList<PositionedStack>();
	}
	
	@Override
	public List<PositionedStack> getIngredientStacks(int recipe)
	{
		List<PositionedStack> need = new ArrayList<PositionedStack>();
		need.add(new PositionedStack(new ItemStack(inverse.get(recipe),1), 77,22));
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
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nei:textures/gui/fermenter.png"));
		this.drawProgressBar(104, 22, 178, 14, 25, 16, 1f-(cycleticks % ticks / (float)ticks), 4);
		//water
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		IIcon ic = FluidRegistry.WATER.getStillIcon();
		int a = (int) (40f*(1f-(cycleticks % 200 / (float)200)));
		this.drawTexturedModelRectFromIcon(41, 50-a, ic, 18, a);
		//juice
		ic = BlockManager.Juice.getStillIcon();
		a = (int) (40f*((cycleticks % 200 / (float)200)));
		this.drawTexturedModelRectFromIcon(134, 51-a, ic, 18, a);
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/gui/fermenter.png"));
		this.drawProgressBar(40, 9, 224, 0, 20, 42, 0f, 4);
		this.drawProgressBar(133, 10, 224, 0, 20, 42, 0f, 4);
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
