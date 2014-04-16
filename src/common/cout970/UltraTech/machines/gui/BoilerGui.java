package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier1.BoilerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BoilerGui extends GuiContainer{

	public BoilerEntity entity;
	
	public BoilerGui(Container par1Container, BoilerEntity t) {
		super(par1Container);
		entity = t;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/boiler.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) ((((float)entity.getEnergy())*50/entity.maxEnergy()));
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		//fluid 1 liquid
		FluidStack liquid = entity.getTankInfo(ForgeDirection.UP)[0].fluid;
		boolean l = liquid == null;
		if(!l){
			liquid.getFluid().getIcon();
			bindTexture(liquid);
			int a = liquid.amount*40/entity.getTankInfo(ForgeDirection.UP)[0].capacity;
			this.drawTexturedModalRect(xStart+139, yStart+61-a, 0, 0, 18, a);
		}
		//fluid 2 gas
		FluidStack gas = entity.getTankInfo(ForgeDirection.UP)[1].fluid;
		boolean g = gas == null;
		if(!g){
			bindTexture(gas);
			int a = gas.amount*40/entity.getTankInfo(ForgeDirection.UP)[1].capacity;
			this.drawTexturedModalRect(xStart+46, yStart+60-a, 0, 0, 18, a);
		} 
		//overlay
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/boiler.png"));
		if(!g)this.drawTexturedModalRect(xStart+45, yStart+20, 224, 0, 20, 40);
		if(!l)this.drawTexturedModalRect(xStart+138, yStart+21, 224, 0, 20, 40);
		
		//heat
		int h = (int) ((entity.heat-25)*58/125);
		this.drawTexturedModalRect(xStart+74, yStart+14+(58-h), 208, 58-h, 6, h);
		
		String s = ((int)this.entity.heat)+"C";
        this.fontRenderer.drawString(s, xStart+125-fontRenderer.getStringWidth(s), yStart+38, 4210752);

	}

	public static void bindTexture(FluidStack f) {
		if(FluidRegistry.getFluidName(f).equals("water"))Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/water.png"));	
		if(FluidRegistry.getFluidName(f).equals("juice"))Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/juice.png"));
		if(FluidRegistry.getFluidName(f).equals("bioethanol"))Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/ethanol.png"));	
		if(FluidRegistry.getFluidName(f).equals("oil"))Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/oil.png"));	
		if(FluidRegistry.getFluidName(f).equals("fuel"))Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/fuel.png"));	
		if(FluidRegistry.getFluidName(f).equals("gas_oil")){
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/gasoil.png"));
			return;
		}
		if(FluidRegistry.getFluidName(f).contains("gas"))Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/steam.png"));
	}

}
