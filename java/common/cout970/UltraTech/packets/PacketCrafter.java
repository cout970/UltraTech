package common.cout970.UltraTech.packets;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.cout970.UltraTech.network.PacketBase;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.core.UltraTech;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketCrafter extends PacketBase{

	/**old
	 * packet tipes:
	 * -2 tesseract name change
	 * -1 printer change color
	 * 0 crafter slot change
	 * 1 crafter crat a item
	 * 2 crafter save a craft
	 * 3 crafter load a save 
	 * 4 crafter delete a save
	 * 5 controller change mode
	 * 6 climate station
	 * 7 crafter clear grid
	 */
	
	/**
	 * new
	 * 0 craft item
	 * 1 clear crafting grid
	 * 2 set itemstack in slot
	 * 3 saves
	 * 
	 */
	
	public int x,y,z;
	public int tipe;
	public ItemStack item;
	public int slot;
	public int mode;
	
	public PacketCrafter(){}
	
	public PacketCrafter(TileEntity e,boolean craft){//craft or empty craft
		x = e.xCoord;
		y = e.yCoord;
		z = e.zCoord;
		if(craft)tipe = 0;
		else tipe = 1;
	}

	public PacketCrafter(CrafterEntity e, ItemStack i,int slot) {//slot change
		x = e.xCoord;
		y = e.yCoord;
		z = e.zCoord;
		tipe = 2;
		item = i;
		this.slot = slot;
	}

	/**
	 * 0 save 1 load 2 clear
	 */
	public PacketCrafter(CrafterEntity e, int i,int slot) {//save or load or dell crafting saves
		x = e.xCoord;
		y = e.yCoord;
		z = e.zCoord;
		tipe = 3;
		mode = i;
		this.slot = slot;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer){
		PacketBuffer pb = new PacketBuffer(buffer);
		pb.writeInt(tipe);
		pb.writeInt(x);
		pb.writeShort(y);
		pb.writeInt(z);
		if(tipe == 2){ pb.writeShort(slot); try { pb.writeBoolean(item != null); if(item != null)pb.writeItemStackToBuffer(item); }catch (IOException e) {e.printStackTrace();}}
		if(tipe == 3){ pb.writeInt(mode); pb.writeShort(slot);}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		PacketBuffer pb = new PacketBuffer(buffer);
		tipe = pb.readInt();
		x = pb.readInt();
		y = pb.readShort();
		z = pb.readInt();
		if(tipe == 3){ mode = pb.readInt(); slot = pb.readShort();}
		if(tipe == 2){ slot = pb.readShort(); boolean b = pb.readBoolean(); if(b){try{ item = pb.readItemStackFromBuffer();}catch(IOException e){e.printStackTrace();}}}
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		CrafterEntity ce = (CrafterEntity) player.worldObj.getTileEntity(x, y, z);
		if(tipe == 0)ce.craft();
		if(tipe == 1)ce.emptyCraft();
		if(tipe == 2){ce.craft.setInventorySlotContents(slot, item);}
		if(tipe == 3){
			if(mode == 0)ce.saveRecipe();
			if(mode == 1)ce.loadRecipes(slot);
			if(mode == 2)ce.DellRecipe(slot);
		}
		ce.update();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {}
}
