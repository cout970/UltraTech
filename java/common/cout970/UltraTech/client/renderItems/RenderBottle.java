package common.cout970.UltraTech.client.renderItems;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.client.models.ModelBottle;
import common.cout970.UltraTech.client.textures.ModelResources;
import common.cout970.UltraTech.util.HelperNBT;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderBottle implements IItemRenderer{

	private ModelBottle model;
	private CubeRenderer_Util RF = new CubeRenderer_Util();

	public RenderBottle() {
		this.model = new ModelBottle();
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
            	render(0.0F, -0.6F, 0.0F, 1F,false,item);
                return;
            }
            case EQUIPPED: {
            	GL11.glRotatef(135, 0, 1, 0);
            	render(-0.5F, -0.95F, 0.0F, 2.0F,item);
                return;
            }
            case INVENTORY: {
            	GL11.glRotatef(-45, 0, 1, 0);
            	GL11.glRotatef(30, 0, 0, 1);
                render(0.0F, -0.65F, 0.0F, 1.7F,true,item);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
                render(0.5F, -0.5F, 0.3F, 1.5F,item);
                return;
            }
            default:
                return;
        }
	}
	
	public void render(float x, float y, float z, float scale, ItemStack item){
		render(x, y, z, scale, false,item);
	}
	
	public void render(float x, float y, float z, float scale, boolean rotate90Deg, ItemStack item){
		GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        if(rotate90Deg){
        	GL11.glRotatef(90F, 0, 1, 0);
        }else{
        	GL11.glRotatef(0F, 0, 0, 0);
        }
        GL11.glRotatef(180F, 0, 0, 1);
        GL11.glTranslatef(0, -1f, 0);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ModelResources.BOTTLE);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        FluidStack f = HelperNBT.getFluid(item);
        if(f != null){
        	FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        	IIcon icon = f.getFluid().getIcon();
        	if(icon != null){
        		float k = 1f/16f;
        		GL11.glScalef(0.95f, 0.95f, 0.95f);
        		GL11.glTranslatef(k*4, k*2, -k*2);
        		GL11.glRotatef(-90F, 0, 1, 0);
        		RF.renderBox(icon, 8*k, 8*k, 4*k);
        	}
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}

}
