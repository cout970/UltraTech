package common.cout970.UltraTech.wiki;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.Tessellator;

public abstract class Page implements IPage{

	public List<TabletButtom> bott = new ArrayList<TabletButtom>();//buttoms in page
	public List<TabletLabel> labb = new ArrayList<TabletLabel>();//labels in page
	private double zLevel;

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
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.draw();
    }
    
    public void drawTextureBox(int x, int y,int w, int h)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + h), (double)this.zLevel, 0, 1);
        tessellator.addVertexWithUV((double)(x + w), (double)(y + h), (double)this.zLevel, 1, 1);
        tessellator.addVertexWithUV((double)(x + w), (double)(y + 0), (double)this.zLevel, 1, 0);
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)this.zLevel, 0, 0);
        tessellator.draw();
    }
}
