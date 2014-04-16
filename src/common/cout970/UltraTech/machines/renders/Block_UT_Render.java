package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.blocks.UT_Block;
import common.cout970.UltraTech.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class Block_UT_Render implements ISimpleBlockRenderingHandler {

	public int tick = 0;

	@Override
	public void renderInventoryBlock(Block par1Block, int metadata, int modelID,
			RenderBlocks renderer) {
		this.renderIn(par1Block, metadata, modelID, renderer);
		this.renderCassing(par1Block, metadata, modelID, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator t = Tessellator.instance;

		//texture
		Icon c = UT_Block.blockIconIn;

		float u = c.getInterpolatedU(0);
		float u1 = c.getInterpolatedU(16);
		float v = c.getInterpolatedV(0);
		float v1 = c.getInterpolatedV(16);

		GL11.glPushMatrix();
		GL11.glColor3f(1, 1, 1);
		t.setColorOpaque_F(1, 1, 1);
		int meta = world.getBlockMetadata(x, y, z);
		this.setColor(meta, t);
		float i = 0f;

		//in
		//y-
		if(block.isBlockSolid(world, x, y, z, 0)){
			t.setTextureUV(u, v);
			t.addVertex(x+1, y+i, z);
			t.setTextureUV(u, v1);
			t.addVertex(x+1, y+i, z+1);
			t.setTextureUV(u1, v1);
			t.addVertex(x, y+i, z+1);
			t.setTextureUV(u1, v);
			t.addVertex(x, y+i, z);
		}
		//y+
		if(block.isBlockSolid(world, x, y, z, 1)){
			t.setTextureUV(u, v);
			t.addVertex(x, y+1-i, z);
			t.setTextureUV(u, v1);
			t.addVertex(x, y+1-i, z+1);
			t.setTextureUV(u1, v1);
			t.addVertex(x+1, y+1-i, z+1);
			t.setTextureUV(u1, v);
			t.addVertex(x+1, y+1-i, z);
		}
		//z-
		if(block.isBlockSolid(world, x, y, z, 2)){
			t.addVertexWithUV(x, y, z+i, u, v);
			t.addVertexWithUV(x, y+1, z+i, u, v1);
			t.addVertexWithUV(x+1, y+1, z+i, u1, v1);
			t.addVertexWithUV(x+1, y, z+i, u1, v);
		}
		//z+
		if(block.isBlockSolid(world, x, y, z, 3)){
			t.setTextureUV(u, v);
			t.addVertex(x+1, y, z+1-i);
			t.setTextureUV(u, v1);
			t.addVertex(x+1, y+1, z+1-i);
			t.setTextureUV(u1, v1);
			t.addVertex(x, y+1, z+1-i);
			t.setTextureUV(u1, v);
			t.addVertex(x, y, z+1-i);
		}
		//x-
		if(block.isBlockSolid(world, x, y, z, 4)){
			t.setTextureUV(u, v);
			t.addVertex(x+i, y, z+1);
			t.setTextureUV(u, v1);
			t.addVertex(x+i, y+1, z+1);
			t.setTextureUV(u1, v1);
			t.addVertex(x+i, y+1, z);
			t.setTextureUV(u1, v);
			t.addVertex(x+i, y, z);
		}
		//x+
		if(block.isBlockSolid(world, x, y, z, 5)){
			t.setTextureUV(u, v);
			t.addVertex(x+1-i, y, z);
			t.setTextureUV(u, v1);
			t.addVertex(x+1-i, y+1, z);
			t.setTextureUV(u1, v1);
			t.addVertex(x+1-i, y+1, z+1);
			t.setTextureUV(u1, v);
			t.addVertex(x+1-i, y, z+1);
		}
		//out
		renderBlockOut(world, x, y, z, block, modelId, renderer, t, meta);

		GL11.glPopMatrix();
		return true;
	}

	private void renderBlockOut(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer, Tessellator t, int meta) {

		GL11.glColor3f(1, 1, 1);
		t.setColorOpaque_F(1, 1, 1);

		Icon c = block.getIcon(meta, 0);
		float i = 0;
		float u = c.getInterpolatedU(0);
		float u1 = c.getInterpolatedU(16);
		float v = c.getInterpolatedV(0);
		float v1 = c.getInterpolatedV(16);
		
		
		//y-
		if(block.isBlockSolid(world, x, y, z, 0)){
			t.setBrightness(block.getMixedBrightnessForBlock(world, x, y-1, z));
			t.setTextureUV(u, v);
			t.addVertex(x+1, y+i, z);
			t.setTextureUV(u, v1);
			t.addVertex(x+1, y+i, z+1);
			t.setTextureUV(u1, v1);
			t.addVertex(x, y+i, z+1);
			t.setTextureUV(u1, v);
			t.addVertex(x, y+i, z);
		}
		//+y
		if(block.isBlockSolid(world, x, y, z, 1)){
			t.setBrightness(block.getMixedBrightnessForBlock(world, x, y+1, z));
			t.addVertexWithUV(x, y+1, z, u, v);
			t.addVertexWithUV(x, y+1, z+1, u, v1);
			t.addVertexWithUV(x+1, y+1, z+1, u1, v1);
			t.addVertexWithUV(x+1, y+1, z, u1, v);
		}
		//z-
		if(block.isBlockSolid(world, x, y, z, 2)){
			t.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z-1));
			t.addVertexWithUV(x, y, z+i, u, v);
			t.addVertexWithUV(x, y+1, z+i, u, v1);
			t.addVertexWithUV(x+1, y+1, z+i, u1, v1);
			t.addVertexWithUV(x+1, y, z+i, u1, v);
		}
		//z+
		if(block.isBlockSolid(world, x, y, z, 3)){
			t.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z+1));
			t.setTextureUV(u, v);
			t.addVertex(x+1, y, z+1-i);
			t.setTextureUV(u, v1);
			t.addVertex(x+1, y+1, z+1-i);
			t.setTextureUV(u1, v1);
			t.addVertex(x, y+1, z+1-i);
			t.setTextureUV(u1, v);
			t.addVertex(x, y, z+1-i);
		}
		//x-
		if(block.isBlockSolid(world, x, y, z, 4)){
			t.setBrightness(block.getMixedBrightnessForBlock(world, x-1, y, z));
			t.setTextureUV(u, v);
			t.addVertex(x+i, y, z+1);
			t.setTextureUV(u, v1);
			t.addVertex(x+i, y+1, z+1);
			t.setTextureUV(u1, v1);
			t.addVertex(x+i, y+1, z);
			t.setTextureUV(u1, v);
			t.addVertex(x+i, y, z);
		}
		//x+
		if(block.isBlockSolid(world, x, y, z, 5)){
			t.setBrightness(block.getMixedBrightnessForBlock(world, x+1, y, z));
			t.setTextureUV(u, v);
			t.addVertex(x+1-i, y, z);
			t.setTextureUV(u, v1);
			t.addVertex(x+1-i, y+1, z);
			t.setTextureUV(u1, v1);
			t.addVertex(x+1-i, y+1, z+1);
			t.setTextureUV(u1, v);
			t.addVertex(x+1-i, y, z+1);
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
		this.setColor(meta, tessellator);

		GL11.glPushMatrix();
		//color
		if(meta == 0)GL11.glColor3f(0.7f, 0.7f, 0.7f);//white
		else if(meta  == 1)GL11.glColor3f(0, 0, 0);//black
		else if(meta  == 10)GL11.glColor3f(1, 0, 0);//red
		else if(meta  == 6)GL11.glColor3f(0, 1, 0);//green
		else if(meta  == 2)GL11.glColor3f(0, 0, 1);//blue
		else if(meta  == 8)GL11.glColor3f(1f, 1f, 0.0f);//yellow
		else if(meta  == 4)GL11.glColor3f(0.0f, 1f, 1f);//light blue
		else if(meta  == 12)GL11.glColor3f(1f, 0.0f, 1f);//purple
		else if(meta  == 7)GL11.glColor3f(0.5f, 1f, 0.0f);//light green
		else if(meta  == 3)GL11.glColor3f(0.0f, 0.5f, 1f);//blue 2
		else if(meta  == 11)GL11.glColor3f(1f, 0.0f, 0.5f);//pink
		else if(meta  == 9)GL11.glColor3f(1f, 0.5f, 0.0f);//orange
		else if(meta  == 13)GL11.glColor3f(0.5f, 0.0f, 1f);//violet
		else if(meta  == 5)GL11.glColor3f(0.0f, 1f, 0.5f);//marine water blue(azul agua marina)
		else GL11.glColor3f(1,1,1);
		tessellator.setBrightness(15728640);//15728640
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

	public void setColor(int meta,Tessellator t){

		if(meta == 0)t.setColorOpaque_F(1, 1, 1);//white
		else if(meta  == 1)t.setColorOpaque_F(0, 0, 0);//black
		else if(meta  == 10)t.setColorOpaque_F(1, 0, 0);//red
		else if(meta  == 6)t.setColorOpaque_F(0, 1, 0);//green
		else if(meta  == 2)t.setColorOpaque_F(0, 0, 1);//blue
		else if(meta  == 8)t.setColorOpaque_F(1f, 1f, 0.0f);//yellow
		else if(meta  == 4)t.setColorOpaque_F(0.0f, 1f, 1f);//light blue
		else if(meta  == 12)t.setColorOpaque_F(1f, 0.0f, 1f);//purple
		else if(meta  == 7)t.setColorOpaque_F(0.5f, 1f, 0.0f);//light green
		else if(meta  == 3)t.setColorOpaque_F(0.0f, 0.5f, 1f);//blue 2
		else if(meta  == 11)t.setColorOpaque_F(1f, 0.0f, 0.5f);//pink
		else if(meta  == 9)t.setColorOpaque_F(1f, 0.5f, 0.0f);//orange
		else if(meta  == 13)t.setColorOpaque_F(0.5f, 0.0f, 1f);//violet
		else if(meta  == 5)t.setColorOpaque_F(0.0f, 1f, 0.5f);//marine water blue(azul agua marina)
		else t.setColorOpaque_F(1,1,1);
		if(Minecraft.getMinecraft() != null && Minecraft.getMinecraft().theWorld != null){
			if(Minecraft.getMinecraft().theWorld.getWorldTime()%24000 > 6000){
				t.setBrightness((int) (Minecraft.getMinecraft().theWorld.getWorldTime()%24000));
			}else{
				t.setBrightness(6000);
			}
		}else{
			t.setBrightness(23999);//18000 night day 15728640 /time set 18000
		}
	}


	
}
