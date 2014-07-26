package common.cout970.UltraTech.client.renderItems;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.client.models.ModelCenterPipe;
import common.cout970.UltraTech.client.models.ModelEngine_FT;
import common.cout970.UltraTech.client.models.ModelPipe;
import common.cout970.UltraTech.client.models.ModelPipeIn;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class RenderPipeItem implements IItemRenderer{

	private ModelPipe model;
	private ModelPipeIn in;
	private ModelCenterPipe base;
	
	public RenderPipeItem() {
		model = new ModelPipe();
		in = new ModelPipeIn();
		base = new ModelCenterPipe();
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
		GL11.glTranslatef(0f,-2f,0);
		boolean[] h = {true,true,true,true,true,true};
		//render centeter
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/base.png"));
		base.render(0.0625f, h);
		boolean[] a = {true,true,false,false,true,true};
		for(int j=0;j<a.length;j++)a[j] = !a[j];
		//render pipe conections
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipebaseitem.png"));
		model.render(0.0625f,a);
		//render in
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipein.png"));
		GL11.glColor3f(0.7f, 0.7f, 0.7f);
		in.render(0.0625F,a);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
