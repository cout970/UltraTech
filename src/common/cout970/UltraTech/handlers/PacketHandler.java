package common.cout970.UltraTech.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import common.cout970.UltraTech.TileEntities.Tier1.CrafterEntity;
import common.cout970.UltraTech.TileEntities.Tier1.Printer3DEntity;
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

			if(tipe == -2){////////tesseract
				int mode;
				String feq,to;
				mode = inputStream.readInt();
				feq = inputStream.readUTF();
				to = inputStream.readUTF();
				if(te instanceof TesseractEntity){
					((TesseractEntity) te).setFrequency(feq);
					((TesseractEntity) te).setDestine(to);
					((TesseractEntity) te).changeMode(T_Mode.getMode(mode));
				}
				
			}else if(tipe == -1){//////////////////printer
				if(te instanceof Printer3DEntity){
					((Printer3DEntity) te).color = inputStream.readInt();
					((Printer3DEntity) te).update = true;
				}
			}else if(tipe == 0){//////////////////crafter
				//setItemStackinSlot
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
					c.update();
				}
			}else if(tipe == 1){//craft()
				
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.craft();
					c.update();
				}
			}else if(tipe == 2){//save recipe
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.saveRecipe();
					c.update();
				}
			}else if(tipe == 3){
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.loadRecipes(inputStream.readInt());
					c.update();
				}
			}else if(tipe == 4){
				if(te instanceof CrafterEntity){
					CrafterEntity c = (CrafterEntity) te;
					c.DellRecipe(inputStream.readInt());
					c.update();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
