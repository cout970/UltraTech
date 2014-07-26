package common.cout970.UltraTech.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageCrafter implements IMessage, IMessageHandler<MessageCrafter, IMessage> {

	 public int x, y, z;
	 public int tipe;
	 public ItemStack item;
	 public int slot;
	 public int mode;
	 public boolean option;

	 public MessageCrafter(){}
	 
	 public MessageCrafter(TileEntity e,boolean craft){//craft or empty craft
			x = e.xCoord;
			y = e.yCoord;
			z = e.zCoord;
			if(craft)tipe = 0;
			else tipe = 1;
		}

		public MessageCrafter(CrafterEntity e, ItemStack i,int slot) {//slot change
			x = e.xCoord;
			y = e.yCoord;
			z = e.zCoord;
			tipe = 2;
			item = i;
			this.slot = slot;
		}

		public MessageCrafter(CrafterEntity e, int i,int slot) {//save or load or dell crafting saves
			x = e.xCoord;
			y = e.yCoord;
			z = e.zCoord;
			tipe = 3;
			mode = i;
			this.slot = slot;
		}

		public MessageCrafter(CrafterEntity e, int i, boolean b) {
			x = e.xCoord;
			y = e.yCoord;
			z = e.zCoord;
			tipe = i;
			option = b;
		}
		
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer pb = new PacketBuffer(buf);
		tipe = pb.readInt();
		x = pb.readInt();
		y = pb.readShort();
		z = pb.readInt();
		if(tipe == 3){ mode = pb.readInt(); slot = pb.readShort();}
		if(tipe == 2){ slot = pb.readShort(); boolean b = pb.readBoolean(); if(b){try{ item = pb.readItemStackFromBuffer();}catch(Exception e){e.printStackTrace();}}}
		if(tipe == 4){ option = pb.readBoolean();}
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer pb = new PacketBuffer(buf);
		pb.writeInt(tipe);
		pb.writeInt(x);
		pb.writeShort(y);
		pb.writeInt(z);
		if(tipe == 2){ pb.writeShort(slot); try { pb.writeBoolean(item != null); if(item != null)pb.writeItemStackToBuffer(item); }catch (Exception e) {e.printStackTrace();}}
		if(tipe == 3){ pb.writeInt(mode); pb.writeShort(slot);}
		if(tipe == 4){ pb.writeBoolean(option);}
	}

	@Override
	public IMessage onMessage(MessageCrafter message, MessageContext ctx) {
		TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if(tileEntity instanceof CrafterEntity){
			CrafterEntity ce = (CrafterEntity) tileEntity;
			if(message.tipe == 0)ce.craft();
			if(message.tipe == 1)ce.emptyCraft();
			if(message.tipe == 2){ce.craft.setInventorySlotContents(message.slot, message.item);}
			if(message.tipe == 3){
				if(message.mode == 0)ce.saveRecipe();
				if(message.mode == 1)ce.loadRecipes(message.slot);
				if(message.mode == 2)ce.DellRecipe(message.slot);
			}
			if(message.tipe == 4){ce.restrictMode = message.option;}
			ce.update();
		}
		return null;
	}

}
