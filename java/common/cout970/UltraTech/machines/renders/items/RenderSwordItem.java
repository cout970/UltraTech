package common.cout970.UltraTech.machines.renders.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.MeVpower.IStorageItem;
import common.cout970.UltraTech.models.ModelDynamo;
import common.cout970.UltraTech.models.ModelSword;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderSwordItem implements IItemRenderer{

	private ModelSword model;
	private ResourceLocation texture = new ResourceLocation("ultratech:textures/misc/animations/sword.png");
	private ResourceLocation texture2 = new ResourceLocation("ultratech:textures/misc/animations/sword_on.png");

	public RenderSwordItem() {
		this.model = new ModelSword();
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
            	render(0.0F, -0.5F, 0.0F, 1.8F,false,item);
                return;
            }
            case EQUIPPED: {
            	GL11.glRotatef(40F, 0, 0, 1);
            	GL11.glRotatef(-40F, 1, 0, 0);
            	render(0.5F, -0.9F, 0.3F, 2.0F,item);
                return;
            }
            case INVENTORY: {
            	GL11.glRotatef(-41F, 1, 0, 1);
                render(0.0F, -1.2F, 0.0F, 1.2F,true,item);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
            	GL11.glRotatef(15F, 1, 0, 0);
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
        GL11.glTranslatef(0, -2f, 0);

        if(item.getItem() instanceof IStorageItem &&((IStorageItem)item.getItem()).getPower(item) > 0){
        	FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture2);
        }else{
        	FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        }

        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
}