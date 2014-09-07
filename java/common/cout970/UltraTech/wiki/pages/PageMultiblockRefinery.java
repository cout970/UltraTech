package common.cout970.UltraTech.wiki.pages;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.wiki.Page;
import common.cout970.UltraTech.wiki.TabletButtom;
import common.cout970.UltraTech.wiki.TabletGui;
import common.cout970.UltraTech.wiki.TabletLabel;

public class PageMultiblockRefinery extends Page{

	public TabletLabel description;
	public TabletButtom material;
	public TabletButtom building;
	public int layer = 0;
	
	public PageMultiblockRefinery(TabletGui c) {
		super(c);
		c.maxpages = 2;
		description = new TabletLabel(this,8,20,130,12);
		description.allLine("Multiblock Refinery");
		description.allLine(" ");
		description.allLine("Description:");
		description.allLine("The Refinery uses hot ");
		description.allLine("gases  to  create cold");
		description.allLine("liquids e.g. gas_oil is");
		description.allLine("used to create gasoline,");
		description.allLine("fuel and liquid plastic.");
	
		material = new TabletButtom(this,8,20,130,12) {
			
			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				Tessellator.instance.setColorOpaque_F(1, 1, 1);
				GL11.glDisable(GL11.GL_LIGHTING);
				Page.drawString("Refinery Materials", x+posX+65, y+posY, 0, true);
				int h = 18;
				Page.drawString("     1 Refinery Core", x+posX, y+posY+h, 0, false);
				RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(BlockManager.Refinery_Core), x+posX, y+posY+h-4);
				Page.drawString("     4 Refinery IO", x+posX, y+posY+18+h, 0, false);
				RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(BlockManager.Refinery_IO), x+posX, y+posY+13+h);
				Page.drawString("     13 Refinery Base", x+posX, y+posY+36+h, 0, false);
				RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(BlockManager.Refinery_Base), x+posX, y+posY+31+h);
				Page.drawString("     72 Refinery Structure", x+posX, y+posY+54+h, 0, false);
				RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(BlockManager.Refinery_Structure), x+posX, y+posY+49+h);
			}
			
			@Override
			public void doSomething() {}
		};
		building = new TabletButtom(this,8,20,130,12) {
			
			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				GL11.glPushMatrix();
				float size = 1.2f;
				GL11.glScaled(size, size, size);
				GL11.glTranslatef(x+posX, y+posY, 0);
				GL11.glRotated(45, 0, 1, 0);
				GL11.glRotated(30, 1, 0, 0);
				GL11.glDisable(GL11.GL_LIGHTING);
				RenderItem(new ItemStack(BlockManager.Refinery_Base),0, 0);
				GL11.glPopMatrix();
			}
			
			private void RenderItem(ItemStack itemStack, int i, int j) {
				RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, itemStack, i, j);
			}

			@Override
			public void doSomething(){
				layer++;
				if(layer == 10)layer = 0;
			}
		};
		
	}

	@Override
	public void renderPage(TextureManager t, int x, int y) {
		
		if(this.cont.page == 1)
			description.drawButtom(t, x, y);
		else if(this.cont.page == 2)material.drawButtom(t, x, y);
		else building.drawButtom(t, x, y);
		
	}

	@Override
	public void mouseClick(int x, int y) {
		building.checkClick(x, y);
	}
}
