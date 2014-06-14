package api.cout970.UltraTech.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class PacketUpdate extends PacketBase{

	public int x,y,z;
	public NBTTagCompound nbt = new NBTTagCompound();

	public PacketUpdate(){}
	
	public PacketUpdate(int x ,int y ,int z, NBTTagCompound a){
		this.x = x;
		this.y = y;
		this.z = z;
		nbt = a;
	}
	public PacketUpdate(TileEntity e){
		x = e.xCoord;
		y = e.yCoord;
		z = e.zCoord;
		NBTTagCompound a = new NBTTagCompound();
		e.writeToNBT(a);
		nbt = a;
	}
	public void setNBT(NBTTagCompound nbt) {
		this.nbt = nbt;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		PacketBuffer PB = new PacketBuffer(buffer);
        PB.writeInt(x);
        PB.writeShort(y);
        PB.writeInt(z);
        try{
        	PB.writeNBTTagCompoundToBuffer(nbt);
        }catch (Exception e){
        	e.printStackTrace();
        }
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer){
		PacketBuffer PB = new PacketBuffer(buffer);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		try {
			nbt = PB.readNBTTagCompoundFromBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		TileEntity te = player.worldObj.getTileEntity(x, y, z);

		if (te != null)
		{
			te.readFromNBT(nbt);
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player) {

	}
}
