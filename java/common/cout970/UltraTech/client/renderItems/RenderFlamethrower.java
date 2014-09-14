package common.cout970.UltraTech.client.renderItems;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL44;

import common.cout970.UltraTech.client.models.ModelBottle;
import common.cout970.UltraTech.client.models.ModelFlamethrower;
import common.cout970.UltraTech.client.textures.ModelResources;
import common.cout970.UltraTech.util.HelperNBT;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderFlamethrower implements IItemRenderer{

	private ModelFlamethrower model;

	public RenderFlamethrower() {
		this.model = new ModelFlamethrower();
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
            	render(0.2F, -0.8F, 0.0F, 1F,false,item);
                return;
            }
            case EQUIPPED: {
            	GL11.glRotatef(-140, 0, 1, 0);
            	GL11.glRotatef(75, 1, 0, 0);
            	render(0.1F, -1.5F, -0.3F, 1.5F,item);
                return;
            }
            case INVENTORY: {
                render(-0.4F, -0.8F, 0.0F, 0.9F,true,item);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
            	GL11.glRotatef(150, 0, 1, 0);
            	GL11.glRotatef(-20, 1, 0, 0);
            	GL11.glRotatef(-5, 0, 0, 1);
                render(-0.3F, -0.2F, -0.0F, 1.0F,item);
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
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ModelResources.FLAMETHROWER);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
	
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.bow;
    }

}