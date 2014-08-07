package common.cout970.UltraTech.wiki;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public abstract class Page implements IPage{

	public List<TabletButtom> bott = new ArrayList<TabletButtom>();//buttoms in page
	public List<TabletLabel> labb = new ArrayList<TabletLabel>();//labels in page
	private static double zLevel;
	public TabletGui cont;

	public Page(TabletGui c){
		cont = c;
	}
	
	@Override
	public TabletButtom[] getButtoms() {
		TabletButtom[] a = (TabletButtom[]) bott.toArray();
		return a;
	}
	@Override
	public TabletLabel[] getLabels() {
		TabletLabel[] a = (TabletLabel[]) labb.toArray();
		return a;
	}
	
	/**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        int zLevel = 0;
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.draw();
    }
    
    public static void drawTexturedModelRectFromIcon(int x, int y, IIcon icon, int w, int h){
    	Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        int zLevel = 0;
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + h), (double)zLevel, (double)icon.getMinU(), (double)icon.getMaxV());
        tessellator.addVertexWithUV((double)(x + w), (double)(y + h), (double)zLevel, (double)icon.getMaxU(), (double)icon.getMaxV());
        tessellator.addVertexWithUV((double)(x + w), (double)(y + 0), (double)zLevel, (double)icon.getMaxU(), (double)icon.getMinV());
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)zLevel, (double)icon.getMinU(), (double)icon.getMinV());
        tessellator.draw();
    }
    
    public static void drawTextureBox(int x, int y,int w, int h)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + h), (double)Page.zLevel, 0, 1);
        tessellator.addVertexWithUV((double)(x + w), (double)(y + h), (double)Page.zLevel, 1, 1);
        tessellator.addVertexWithUV((double)(x + w), (double)(y + 0), (double)Page.zLevel, 1, 0);
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)Page.zLevel, 0, 0);
        tessellator.draw();
    }
	public static void drawString(String string, int x, int y, int par4) {
		FontRenderer f = Minecraft.getMinecraft().fontRenderer;
		f.drawString(string, x-f.getStringWidth(string)/2, y-f.getStringWidth("1")/2, par4);
	}
}
