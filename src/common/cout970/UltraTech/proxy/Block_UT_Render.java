package common.cout970.UltraTech.proxy;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.blocks.UT_Block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class Block_UT_Render implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block par1Block, int metadata, int modelID,
			RenderBlocks renderer) {
		this.renderIn(par1Block, metadata, modelID, renderer);
		this.renderCassing(par1Block, metadata, modelID, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if(ClientProxy.renderPass == 0){
		Tessellator t = Tessellator.instance;
//		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/blocks/deco/deco2.png"));
		//bug free
		
		//texture
		
		Icon c = UT_Block.blockIconIn;
	
		float u = c.getInterpolatedU(0);
		float u1 = c.getInterpolatedU(16);
		float v = c.getInterpolatedV(0);
		float v1 = c.getInterpolatedV(16);

		int meta = world.getBlockMetadata(x, y, z);
		this.setColor(meta, t,true);

		//z-
		t.addVertexWithUV(x, y, z+0.001, u, v);
		t.addVertexWithUV(x, y+1, z+0.001, u, v1);
		t.addVertexWithUV(x+1, y+1, z+0.001, u1, v1);
		t.addVertexWithUV(x+1, y, z+0.001, u1, v);
		//z+
		t.setTextureUV(u, v);
		t.addVertex(x+1, y, z+0.999);
		t.setTextureUV(u, v1);
		t.addVertex(x+1, y+1, z+0.999);
		t.setTextureUV(u1, v1);
		t.addVertex(x, y+1, z+0.999);
		t.setTextureUV(u1, v);
		t.addVertex(x, y, z+0.999);
		//x-
		t.setTextureUV(u, v);
		t.addVertex(x+0.001, y, z+1);
		t.setTextureUV(u, v1);
		t.addVertex(x+0.001, y+1, z+1);
		t.setTextureUV(u1, v1);
		t.addVertex(x+0.001, y+1, z);
		t.setTextureUV(u1, v);
		t.addVertex(x+0.001, y, z);
		//x+
		t.setTextureUV(u, v);
		t.addVertex(x+0.999, y, z);
		t.setTextureUV(u, v1);
		t.addVertex(x+0.999, y+1, z);
		t.setTextureUV(u1, v1);
		t.addVertex(x+0.999, y+1, z+1);
		t.setTextureUV(u1, v);
		t.addVertex(x+0.999, y, z+1);
		//y-
		t.setTextureUV(u, v);
		t.addVertex(x+1, y+0.001, z);
		t.setTextureUV(u, v1);
		t.addVertex(x+1, y+0.001, z+1);
		t.setTextureUV(u1, v1);
		t.addVertex(x, y+0.001, z+1);
		t.setTextureUV(u1, v);
		t.addVertex(x, y+0.001, z);
		//y+
		t.setTextureUV(u, v);
		t.addVertex(x, y+0.999, z);
		t.setTextureUV(u, v1);
		t.addVertex(x, y+0.999, z+1);
		t.setTextureUV(u1, v1);
		t.addVertex(x+1, y+0.999, z+1);
		t.setTextureUV(u1, v);
		t.addVertex(x+1, y+0.999, z);
		return true;
		}else{
			return renderer.renderStandardBlock(block, x, y, z);	
		}
		
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.BlockRenderTipe;
	}
	
	public void renderCassing(Block par1Block, int meta, int modelID,
			RenderBlocks renderer){
		Tessellator tessellator = Tessellator.instance;
		Icon f = par1Block.getIcon(0, meta);
		//draw
		par1Block.setBlockBoundsForItemRender();
		GL11.glPushMatrix();
		GL11.glColor3f(1, 1, 1);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float f1 = 0.0F;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addTranslation(0.0F, 0.0F, f1);
        renderer.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(0.0F, 0.0F, -f1);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addTranslation(0.0F, 0.0F, -f1);
        renderer.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(0.0F, 0.0F, f1);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addTranslation(f1, 0.0F, 0.0F);
        renderer.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(-f1, 0.0F, 0.0F);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addTranslation(-f1, 0.0F, 0.0F);
        renderer.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(f1, 0.0F, 0.0F);
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        GL11.glPopMatrix();
        
	}
	
	public void renderIn(Block par1Block, int meta, int modelID,
			RenderBlocks renderer){
		Tessellator tessellator = Tessellator.instance;
		Icon f = UT_Block.blockIconIn;
		//icon
		this.setColor(meta, tessellator,false);
		
		GL11.glPushMatrix();
		//color
		if(meta == 0)tessellator.setColorOpaque_F(1, 1, 1);
		else if(meta  == 1)GL11.glColor3f(0, 0, 0);
		else if(meta  == 2)GL11.glColor3f(1, 0, 0);
		else if(meta  == 3)GL11.glColor3f(0, 1, 0);
		else if(meta  == 4)GL11.glColor3f(0, 0, 1);
		else if(meta  == 5)GL11.glColor3f(1f, 1f, 0);
		else if(meta  == 6)GL11.glColor3f(0, 0.5f, 1f);
		else if(meta  == 7)GL11.glColor3f(0.0f,0.5f, 0.0f);
		else if(meta  == 8)GL11.glColor3f(1f, 0.0f, 1f);
		else if(meta  == 9)GL11.glColor3f(1f, 0.5f, 1f);
		else if(meta  == 10)GL11.glColor3f(1f, 0.5f, 0f);
		else GL11.glColor3f(1,1,1);
		
		tessellator.setBrightness(200000);
		//draw
		par1Block.setBlockBoundsForItemRender();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float f1 = 0.0F;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addTranslation(0.0F, 0.0F, f1);
        renderer.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(0.0F, 0.0F, -f1);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addTranslation(0.0F, 0.0F, -f1);
        renderer.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(0.0F, 0.0F, f1);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addTranslation(f1, 0.0F, 0.0F);
        renderer.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(-f1, 0.0F, 0.0F);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addTranslation(-f1, 0.0F, 0.0F);
        renderer.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, f);
        tessellator.addTranslation(f1, 0.0F, 0.0F);
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        GL11.glPopMatrix();
        GL11.glScalef(1, 1f, 1);
	}
	
	public void setColor(int meta,Tessellator tessellator,boolean bri){
		if(meta == 0)tessellator.setColorOpaque_F(1, 1, 1);
		else if(meta  == 1)tessellator.setColorOpaque_F(0, 0, 0);
		else if(meta  == 2)tessellator.setColorOpaque_F(1, 0, 0);
		else if(meta  == 3)tessellator.setColorOpaque_F(0, 1, 0);
		else if(meta  == 4)tessellator.setColorOpaque_F(0, 0, 1);
		else if(meta  == 5)tessellator.setColorOpaque_F(1f, 1f, 0);
		else if(meta  == 6)tessellator.setColorOpaque_F(0, 0.5f, 1f);
		else if(meta  == 7)tessellator.setColorOpaque_F(0.0f,0.5f, 0.0f);
		else if(meta  == 8)tessellator.setColorOpaque_F(1f, 0.0f, 1f);
		else if(meta  == 9)tessellator.setColorOpaque_F(1f, 0.5f, 1f);
		else if(meta  == 10)tessellator.setColorOpaque_F(1f, 0.5f, 0f);
		else tessellator.setColorOpaque_F(1,1,1);
		tessellator.setBrightness(200000);
	}

}
