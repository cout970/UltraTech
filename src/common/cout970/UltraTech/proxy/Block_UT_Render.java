package common.cout970.UltraTech.proxy;

import org.lwjgl.opengl.GL11;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class Block_UT_Render implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if(ClientProxy.renderPass == 0){
		Tessellator t = Tessellator.instance;
		
		t.setColorOpaque(1, 1, 1);
		t.setBrightness(100);
		//texture
		int tipe = block.getRenderType();
		
		t.setTextureUV(tipe, tipe);
		
		//z-
		t.addVertex(x, y, z+0.001);
		t.addVertex(x, y+1, z+0.001);
		t.addVertex(x+1, y+1, z+0.001);
		t.addVertex(x+1, y, z+0.001);
		//z+
		t.addVertex(x+1, y, z+0.999);
		t.addVertex(x+1, y+1, z+0.999);
		t.addVertex(x, y+1, z+0.999);
		t.addVertex(x, y, z+0.999);
		//x-
		t.addVertex(x+0.001, y, z+1);
		t.addVertex(x+0.001, y+1, z+1);
		t.addVertex(x+0.001, y+1, z);
		t.addVertex(x+0.001, y, z);
		//x+
		t.addVertex(x+0.999, y, z);
		t.addVertex(x+0.999, y+1, z);
		t.addVertex(x+0.999, y+1, z+1);
		t.addVertex(x+0.999, y, z+1);
		//y-
		t.addVertex(x+1, y+0.001, z);
		t.addVertex(x+1, y+0.001, z+1);
		t.addVertex(x, y+0.001, z+1);
		t.addVertex(x, y+0.001, z);
		//y+
		t.addVertex(x, y+0.999, z);
		t.addVertex(x, y+0.999, z+1);
		t.addVertex(x+1, y+0.999, z+1);
		t.addVertex(x+1, y+0.999, z);
		return true;
		}else{
			return  renderer.renderStandardBlock(block, x, y, z);	
		}
		
	        
		
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.BlockRenderTipe;
	}

}
