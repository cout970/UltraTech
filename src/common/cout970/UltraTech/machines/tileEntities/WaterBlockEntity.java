package common.cout970.UltraTech.machines.tileEntities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class WaterBlockEntity extends TileEntity implements IReactorPart{

	List<IFluidHandler> tanks;

	public WaterBlockEntity(){
		super();
	}

	public void updateEntity(){
		if(tanks == null)updateTanks();
		if(FluidRegistry.WATER != null)
			for(IFluidHandler b : tanks){
				b.fill(ForgeDirection.UP , new FluidStack(FluidRegistry.WATER,50), true);
			}
	}

	private void updateTanks() {
		tanks = new ArrayList<IFluidHandler>();
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		for(TileEntity te : t){
			if(te instanceof IFluidHandler){
				tanks.add((IFluidHandler) te);
			}
		}
	}


	public boolean found = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;


	public void setUp(){

		SearchReactor();
		if(found){
			checkStructure();
			if(Structure){
				activateBlocks();
			}
		}else{
			Structure = false;
		}
	}

	@Override
	public void SearchReactor() {
		int[] ids = new int[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k);
					if(ids[current] == BlockManager.Reactor.blockID && worldObj.getBlockMetadata(xCoord+i, yCoord+j, zCoord+k) == 0){
						Reactor = (ReactorEntity) worldObj.getBlockTileEntity(xCoord+i,yCoord+j,zCoord+k);
						found = true;
						return;
					}
					current++;
				}
			}
		}
		found = false;
	}

	@Override
	public ReactorEntity getReactor() {
		return this.Reactor;
	}

	@Override
	public void onNeighChange() {
		setUp();	
	}

	@Override
	public void checkStructure() {
		TileEntity[] ids = new TileEntity[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockTileEntity(Reactor.xCoord+i, Reactor.yCoord+j, Reactor.zCoord+k);
					current++;
				}
			}
		}

		this.Structure = false;

		for(TileEntity t : ids) {
			if(!(t instanceof IReactorPart))return;
		}
		this.Structure = true;
	}

	@Override
	public void activateBlocks() {
		Reactor.setStructure(true);
		int x,y,z;
		x = Reactor.xCoord;
		y = Reactor.yCoord;
		z = Reactor.zCoord;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(true);
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setReactor(Reactor);
						worldObj.markBlockForRenderUpdate(x+i, y+j, z+k);
					}
				}
			}
		}
	}

	@Override
	public void desactivateBlocks() {
		if(Reactor != null){
			Structure = false;
			int x,y,z;
			x = Reactor.xCoord;
			y = Reactor.yCoord;
			z = Reactor.zCoord;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
							((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(false);
							worldObj.markBlockForUpdate(x+i, y+j, z+k);
							ByteArrayOutputStream bytes = new ByteArrayOutputStream();
							DataOutputStream data = new DataOutputStream(bytes);
							try {
								data.writeInt(x+i);
								data.writeInt(y+j);
								data.writeInt(z+k);
								data.writeInt(0);
								data.writeInt(this.isStructure() ? 1 : 0);
							} catch (IOException e) {
								e.printStackTrace();
							}
							PacketDispatcher.sendPacketToAllPlayers(PacketDispatcher.getPacket("UltraTech1", bytes.toByteArray()));
							
						}
					}
				}
			}
		}		
	}

	@Override
	public void setStructure(boolean structure) {
		Structure = structure;		
	}

	@Override
	public void setReactor(ReactorEntity e) {
		Reactor = e;
	}

	@Override
	public boolean isStructure() {
		return this.Structure;
	}
}
