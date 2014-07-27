package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.blocknormal.Deco_Block;
import common.cout970.UltraTech.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class DecoBlocksRender implements ISimpleBlockRenderingHandler{


	@Override
	public int getRenderId() {
		return ClientProxy.BlockRenderTipe;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block block, int modelId, RenderBlocks r) {
		Tessellator t = Tessellator.instance;
		//texture
		IIcon c = block.getIcon(-1, 0);
		GL11.glPushMatrix();
		int m = w.getBlockMetadata(x, y, z);
		setColorT(m);
		block.setLightLevel(1f);
		t.setBrightness(block.getMixedBrightnessForBlock(w, x, y, z));
		block.setLightLevel(0f);
		
//		//in		
		if(block.shouldSideBeRendered(w, x, y+1, z, 0))r.renderFaceYPos(block, x, y, z, c);
		if(block.shouldSideBeRendered(w, x, y-1, z, 0))r.renderFaceYNeg(block, x, y, z, c);
		if(block.shouldSideBeRendered(w, x, y, z+1, 0))r.renderFaceZPos(block, x, y, z, c);
		if(block.shouldSideBeRendered(w, x, y, z-1, 0))r.renderFaceZNeg(block, x, y, z, c);
		if(block.shouldSideBeRendered(w, x+1, y, z, 0))r.renderFaceXPos(block, x, y, z, c);
		if(block.shouldSideBeRendered(w, x-1, y, z, 0))r.renderFaceXNeg(block, x, y, z, c);
		//out
		r.renderStandardBlock(block, x, y, z);
		GL11.glPopMatrix();
		return true;
	}

	@Override
	public void renderInventoryBlock(Block b, int m, int modelID, RenderBlocks r) {
		Tessellator t = Tessellator.instance;
		IIcon f = Deco_Block.Base;
		setColorGL(m);
		
		//interior
		GL11.glPushMatrix();
//		t.setBrightness(15728640);//temp
		RenderHelper.enableStandardItemLighting();
		b.setBlockBoundsForItemRender();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		t.startDrawingQuads();
		t.setNormal(0.0F, -1.0F, 0.0F);
		r.renderFaceYNeg(b, 0.0D, 0.0D, 0.0D, f);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		r.renderFaceYPos(b, 0.0D, 0.0D, 0.0D, f);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, -1.0F);
		t.addTranslation(0.0F, 0.0F, 0);
		r.renderFaceZNeg(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0.0F, 0.0F, 0);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, 1.0F);
		t.addTranslation(0.0F, 0.0F, 0);
		r.renderFaceZPos(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0.0F, 0.0F, 0);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(-1.0F, 0.0F, 0.0F);
		t.addTranslation(0, 0.0F, 0.0F);
		r.renderFaceXNeg(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0, 0.0F, 0.0F);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(1.0F, 0.0F, 0.0F);
		t.addTranslation(0, 0.0F, 0.0F);
		r.renderFaceXPos(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0, 0.0F, 0.0F);
		t.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glScalef(1, 1f, 1);
		GL11.glPopMatrix();
		
		//exterior
		f = b.getIcon(0, m);
		GL11.glPushMatrix();
		setColorGL(0);
		t.setBrightness(15728640);//temp
		b.setBlockBoundsForItemRender();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		t.startDrawingQuads();
		t.setNormal(0.0F, -1.0F, 0.0F);
		r.renderFaceYNeg(b, 0.0D, 0.0D, 0.0D, f);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		r.renderFaceYPos(b, 0.0D, 0.0D, 0.0D, f);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, -1.0F);
		t.addTranslation(0.0F, 0.0F, 0);
		r.renderFaceZNeg(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0.0F, 0.0F, 0);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, 1.0F);
		t.addTranslation(0.0F, 0.0F, 0);
		r.renderFaceZPos(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0.0F, 0.0F, 0);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(-1.0F, 0.0F, 0.0F);
		t.addTranslation(0, 0.0F, 0.0F);
		r.renderFaceXNeg(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0, 0.0F, 0.0F);
		t.draw();
		t.startDrawingQuads();
		t.setNormal(1.0F, 0.0F, 0.0F);
		t.addTranslation(0, 0.0F, 0.0F);
		r.renderFaceXPos(b, 0.0D, 0.0D, 0.0D, f);
		t.addTranslation(0, 0.0F, 0.0F);
		t.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glScalef(1, 1f, 1);
		GL11.glPopMatrix();
	}
	
	public void setColorGL(int meta){
		float[] rgb = getColorByMetadata(meta);
		GL11.glColor3f(rgb[0],rgb[1],rgb[2]);
	}
	
	public void setColorT(int meta){
		float[] rgb = getColorByMetadata(meta);
		Tessellator.instance.setColorOpaque_F(rgb[0],rgb[1],rgb[2]);
	}
	
	public static float[] getColorByMetadata(int meta){
		
		if(meta == 0)return new float[]{1f,1f,1f};//white
		if(meta == 1)return new float[]{0f,0f,0f};//black
		if(meta == 2)return new float[]{0f,0f,1f};//blue
		if(meta == 3)return new float[]{0f,0.5f,1f};//steal blue
		if(meta == 4)return new float[]{0f,1f,1f};//cian
		if(meta == 5)return new float[]{0f,1f,0.5f};//sea green
		if(meta == 6)return new float[]{0f,1f,0f};//green
		if(meta == 7)return new float[]{0.5f,1f,0f};//light green
		if(meta == 8)return new float[]{1f,1f,0f};//yellow
		if(meta == 9)return new float[]{1f,0.5f,0f};//orange
		if(meta == 10)return new float[]{1f,0f,0f};//red
		if(meta == 11)return new float[]{1f,0f,1f};//purple
		if(meta == 12)return new float[]{1f,0f,0.5f};//pink
		if(meta == 13)return new float[]{0.5f,0f,1f};//blue violet

		return new float[]{1f,1f,1f};
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

}
