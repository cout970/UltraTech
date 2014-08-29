package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cofh.api.tileentity.IRedstoneControl.ControlMode;
import ultratech.api.power.interfaces.IPower;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Core_Entity;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageReactorConfig;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class Reactor_Gui extends MachineGuiBase{

	public Reactor_Core_Entity tile;
	
	public Reactor_Gui(Container par1Container, InventoryPlayer inventory, TileEntity te) {
		super(par1Container, te);
		ySize = 242;
		tile = (Reactor_Core_Entity) te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//draw temp var
		int h = (int) (tile.heat*58/tile.maxheat);
		this.drawTexturedModalRect(xStart+9, yStart+39+(58-h), 176, 58-h, 5, h);
		
		if(!tile.state){
			this.drawTexturedModalRect(xStart+8, yStart+10, 235, 0, 15, 22);
			this.drawTexturedModalRect(xStart+24, yStart+10, 235, 69, 15, 22);
		}else{
			this.drawTexturedModalRect(xStart+8, yStart+10, 235, 23, 15, 22);
			this.drawTexturedModalRect(xStart+24, yStart+10, 235, 46, 15, 22);
		}
		
		if(tile.Mode == ControlMode.LOW){
			this.drawTexturedModalRect(xStart+57, yStart+10, 235, 138, 15, 22);
		}else if(tile.Mode == ControlMode.HIGH){
			this.drawTexturedModalRect(xStart+57, yStart+10, 235, 115, 15, 22);
		}else{
			this.drawTexturedModalRect(xStart+57, yStart+10, 235, 92, 15, 22);
		}
		
		if(tile.automation){
			this.drawTexturedModalRect(xStart+41, yStart+10, 235, 184, 15, 22);
		}else{
			this.drawTexturedModalRect(xStart+41, yStart+10, 235, 161, 15, 22);
		}
		
		//lock slots
		if(tile.getSize() < 3){
			for(int x=0;x<5;x++){
				this.drawTexturedModalRect(xStart+78+x*18, yStart+10, 203, 0, 16, 16);	
			}
			for(int y=0;y<5;y++){
				this.drawTexturedModalRect(xStart+78+y*18, yStart+82, 203, 0, 16, 16);	
			}
			for(int z=0;z<3;z++){
				this.drawTexturedModalRect(xStart+78, yStart+28+z*18, 203, 0, 16, 16);	
			}
			for(int z=0;z<3;z++){
				this.drawTexturedModalRect(xStart+150, yStart+28+z*18, 203, 0, 16, 16);	
			}
		}
		if(tile.getSize() < 2){
			for(int z=0;z<3;z++){
				this.drawTexturedModalRect(xStart+96+z*18, yStart+28, 203, 0, 16, 16);	
			}
			for(int z=0;z<3;z++){
				this.drawTexturedModalRect(xStart+96+z*18, yStart+64, 203, 0, 16, 16);	
			}
			this.drawTexturedModalRect(xStart+96, yStart+46, 203, 0, 16, 16);
			this.drawTexturedModalRect(xStart+132, yStart+46, 203, 0, 16, 16);
		}
		
		//draw fluids
		if(tile.getTank(0).getFluidAmount() > 0){
			UT_Tank t = tile.getTank(0);
			if(t.getCapacity() > 0){
			this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon i1 = FluidRegistry.WATER.getIcon();
			int p = t.getFluidAmount()*60/t.getCapacity();
			if(p>=60)p=60;
			this.drawTexturedModelRectFromIcon(xStart+26, yStart+38+(60-p), i1, 20, p);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+26, yStart+37, 182, 0, 20, 60);
			}
		}
		if(tile.getTank(1).getFluidAmount() > 0){
			UT_Tank t = tile.getTank(1);
			if(t.getCapacity() > 0){
			this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon i1 = FluidRegistry.getFluid("steam").getIcon();
			int p = t.getFluidAmount()*60/t.getCapacity();
			if(p>=60)p=60;
			this.drawTexturedModelRectFromIcon(xStart+52, yStart+38+(60-p), i1, 20, p);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+52, yStart+37, 182, 0, 20, 60);
			}
		}
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        xStart = (width - xSize) / 2;
		yStart = (height - ySize) / 2;
		
		String line1 = "Reactor State: ";
		if(tile.state && tile.shouldWork()){
			line1 += "Running";
		}else{
			line1 += "Stoped";
		}

		this.drawString(fontRendererObj, line1, 10, 105, UT_Utils.RGBtoInt(255, 255, 255));	
		String line2 = "Reactor Production: "+tile.production+" mB/t";
		this.drawString(fontRendererObj, line2, 10, 117, UT_Utils.RGBtoInt(255, 255, 255));	
		
		String line3 = "Redstone input: "+tile.redstoneSignal;
		this.drawString(fontRendererObj, line3, 10, 129, UT_Utils.RGBtoInt(255, 255, 255));	
	
		String line4 = "Automatic start and stop: "+tile.automation;
		this.drawString(fontRendererObj, line4, 10, 141, UT_Utils.RGBtoInt(255, 255, 255));	
	
		
        if(UT_Utils.isIn(x, y, xStart+26, yStart+38, 20, 60)){
        	List<String> info = new ArrayList<String>();
        	info.add("Water: "+tile.getTank(0).getFluidAmount()+" / "+tile.getTank(0).getCapacity());
        	this.drawHoveringText(info, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
        if(UT_Utils.isIn(x, y, xStart+52, yStart+38, 20, 60)){
        	List<String> info = new ArrayList<String>();
        	info.add("Steam: "+tile.getTank(1).getFluidAmount()+" / "+tile.getTank(1).getCapacity());
        	this.drawHoveringText(info, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
        if(UT_Utils.isIn(x, y, xStart+7, yStart+38, 14, 60)){
        	List<String> info = new ArrayList<String>();
        	info.add("Heat: "+((int)tile.heat)+" / "+((int)tile.maxheat));
        	this.drawHoveringText(info, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}
	
	@Override
	protected void mouseClicked(int mx, int my, int b)
	{
		super.mouseClicked(mx, my, b);
		
		xStart = (width - xSize) / 2;
		yStart = (height - ySize) / 2;
		
		if(UT_Utils.isIn(mx, my, xStart+8, yStart+10, 15, 22)){
			
			Net_Utils.INSTANCE.sendToServer(new MessageReactorConfig(tile,1,0));//on
		}if(UT_Utils.isIn(mx, my, xStart+24, yStart+10, 15, 22)){
			
			Net_Utils.INSTANCE.sendToServer(new MessageReactorConfig(tile,1,1));//off
		}if(UT_Utils.isIn(mx, my, xStart+41, yStart+10, 15, 22)){
			
			Net_Utils.INSTANCE.sendToServer(new MessageReactorConfig(tile,2,0));//off
		}if(UT_Utils.isIn(mx, my, xStart+57, yStart+10, 15, 22)){
			
			Net_Utils.INSTANCE.sendToServer(new MessageReactorConfig(tile,3,tile.Mode.ordinal()));//off
		}
	}

}
