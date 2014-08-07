package common.cout970.UltraTech.client.renderItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.client.models.ModelEngine;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderEngineItem implements IItemRenderer{

	private ModelEngine model;

	public RenderEngineItem() {
		this.model = new ModelEngine();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
            case ENTITY: {
            	render(0.0F, 0.0F, 0.0F, 1.0F,false);
                return;
            }
            case EQUIPPED: {
                render(0.5F, -0.5F, 0.5F, 1.0F);
                return;
            }
            case INVENTORY: {
                render(0.0F, -1F, 0.0F, 1.0F,true);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
                render(0.5F, -0.5F, 0.5F, 1.0F);
                return;
            }
            default:
                return;
        }
	}
	
	public void render(float x, float y, float z, float scale){
		render(x, y, z, scale, false);
	}
	
	public void render(float x, float y, float z, float scale, boolean rotate90Deg){
		GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180F, 0, 0, 1);
        GL11.glTranslatef(0, -2f, 0);

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ResourcesLocations.ENGINE);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glTranslatef(0, 10*0.0625F, 0);
        model.renderMove(0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
}
