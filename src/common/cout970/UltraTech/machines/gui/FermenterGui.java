package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier1.FermenterEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class FermenterGui extends GuiContainer{

	public FermenterEntity entity;
	
	public FermenterGui(Container par1Container, FermenterEntity tile) {
		super(par1Container);
		entity = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/fermenter.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres
		int i1 = (int) entity.progres*24/400;
		this.drawTexturedModalRect(xStart + 107+25-i1, yStart + 33, 176+25-i1, 14, i1+1, 16);

		//water
		FluidStack water = entity.getTankInfo(ForgeDirection.UP)[0].fluid;
		boolean w = water == null;
		if(!w){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/water.png"));
			int a = water.amount*40/entity.getTankInfo(ForgeDirection.UP)[0].capacity;
			this.drawTexturedModalRect(xStart+46, yStart+60-a, 0, 0, 18, a);
		}
		//juice
		FluidStack juice = entity.getTankInfo(ForgeDirection.UP)[1].fluid;
		boolean ju = juice == null;
		if(!ju){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/juice.png"));
			int a = juice.amount*40/entity.getTankInfo(ForgeDirection.UP)[1].capacity;
			this.drawTexturedModalRect(xStart+139, yStart+61-a, 0, 0, 18, a);
		}
		//overlay
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/fermenter.png"));
		if(!w)this.drawTexturedModalRect(xStart+45, yStart+20, 224, 0, 20, 40);
		if(!ju)this.drawTexturedModalRect(xStart+138, yStart+21, 224, 0, 20, 40);
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) entity.getEnergy()*50/entity.maxEnergy();
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	}

}
