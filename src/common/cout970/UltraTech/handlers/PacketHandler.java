package common.cout970.UltraTech.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import common.cout970.UltraTech.TileEntities.Tier1.CrafterEntity;
import common.cout970.UltraTech.TileEntities.Tier1.Printer3DEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ClimateEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorControllerEntity;
import common.cout970.UltraTech.TileEntities.Tier3.TesseractEntity;
import common.cout970.UltraTech.TileEntities.Tier3.TesseractEntity.T_Mode;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		
		if(!packet.channel.equals("UltraTech"))return;
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		int tipe = 0;
		int x,y,z;
		int slot;
		int id=0, amount=0, meta=0;
		boolean null_Item = false;
		try{
			//read
			tipe = inputStream.readInt();

			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();

			//getTile
			TileEntity te;
			if(player instanceof EntityPlayerMP){
				EntityPlayerMP playerSP = (EntityPlayerMP)player;
				te = playerSP.worldObj.getBlockTileEntity(x, y, z);
			}else{
				EntityPlayerSP playerSP = (EntityPlayerSP)player;
				te = playerSP.worldObj.getBlockTileEntity(x, y, z);
			}

			switch(tipe)
			{case -2:{//tesseract
				int mode;
				int feq;
				mode = inputStream.readInt();
				feq = inputStream.readInt();
				if(te instanceof TesseractEntity){
					((TesseractEntity) te).setFrequency(feq);
					((TesseractEntity) te).changeMode(T_Mode.getMode(mode));
				}break;
			}case -1:{//3Dprinter
				if(te instanceof Printer3DEntity){
					((Printer3DEntity) te).color = inputStream.readInt();
					((Printer3DEntity) te).update = true;
				}break;
			}case 0:{//crater slot
				slot = inputStream.readInt();
				id = inputStream.readInt();
				if(id == 0){
					null_Item = true;
				}else{
					amount = inputStream.readInt();
					meta = inputStream.readInt();
				}

				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					ItemStack item;
					if(null_Item){
						item = null;
					}else{
						item = new ItemStack(id, amount, meta);
					}
					c.craft.setInventorySlotContents(slot, item);
					c.onInventoryChanged();
				}break;
			}case 1:{//crafter craft
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.craft();
					c.onInventoryChanged();
				}break;
			}case 2:{//crafter save recipe
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.saveRecipe();
					c.onInventoryChanged();
				}break;
			}case 3:{
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.loadRecipes(inputStream.readInt());
					c.onInventoryChanged();
				}break;
			}case 4:{
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.DellRecipe(inputStream.readInt());
					c.onInventoryChanged();
				}break;
			}case 5:{
				if(te instanceof ReactorControllerEntity){
					((ReactorControllerEntity) te).useHeat = inputStream.readInt() == 1;
				}break;
			}case 6:{
				if(te instanceof ClimateEntity){
					((ClimateEntity) te).setClimate(inputStream.readInt());
				}break;
			}case 7:{
				if(te instanceof CrafterEntity){
					for(int f=0;f<9;f++)((CrafterEntity) te).craft.setInventorySlotContents(f, null);
					((CrafterEntity) te).onInventoryChanged();
				}break;
			}
			}
//			System.out.println("packet received type: "+tipe);
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
