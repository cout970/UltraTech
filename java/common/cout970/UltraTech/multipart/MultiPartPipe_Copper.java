package common.cout970.UltraTech.multipart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ultratech.api.util.UT_Utils;
import buildcraft.api.tools.IToolWrench;
import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.ISidedHollowConnect;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.multipart.client.RenderPipeCopper;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageMultiPartUpdate;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import common.cout970.UltraTech.util.fluids.TankConection;

public class MultiPartPipe_Copper extends MultiPartPipe implements ISidedHollowConnect,IFluidHandler,IFluidTransport{

	public MultiPartPipe_Copper() {
		super(MultiPartRegistry_UT.CopperPipe);
	}

	@Override
	public Cuboid6[] setupBoundingBoxes() {
		boundingBoxes = new Cuboid6[7];
		double w = 0.2;
		boundingBoxes[6] = new Cuboid6(0.5-w,0.5-w,0.5-w,0.5+w,0.5+w,0.5+w);//base
		boundingBoxes[0] = new Cuboid6(0.5-w, 0, 0.5-w, 0.5+w, 0.5-w, 0.5+w);//up
		boundingBoxes[1] = new Cuboid6(0.5-w, 0.5+w, 0.5-w, 0.5+w, 1, 0.5+w);//down
		boundingBoxes[2] = new Cuboid6(0.5-w, 0.5-w, 0, 0.5+w, 0.5+w, 0.5-w);//north
		boundingBoxes[3] = new Cuboid6(0.5-w, 0.5-w, 0.5+w, 0.5+w, 0.5+w, 1);//south
		boundingBoxes[4] = new Cuboid6(0, 0.5-w, 0.5-w, 0.5-w, 0.5+w, 0.5+w);//west
		boundingBoxes[5] = new Cuboid6(0.5+w, 0.5-w, 0.5-w, 1, 0.5+w, 0.5+w);//east
		return boundingBoxes;
	}

	@Override
	public Iterable<IndexedCuboid6> getSubParts() {
		Iterable<Cuboid6> boxList = getCollisionBoxes();
		LinkedList<IndexedCuboid6> partList = new LinkedList<IndexedCuboid6>();
		for (Cuboid6 c : boxList)
			partList.add(new IndexedCuboid6(0, c));
		((ArrayList<Cuboid6>) boxList).clear();
		return partList;
	}

	@Override
	public Iterable<Cuboid6> getCollisionBoxes() {
		ArrayList<Cuboid6> t2 = new ArrayList<Cuboid6>();
		t2.add(boundingBoxes[6]);
		for(int i=0; i<6; i++){
			if(connections[i]){// && side[i] != ConnectionMode.NOTHING){
				t2.add(boundingBoxes[i]);
			}
		}
		return t2;
	}

	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[6] });
	}

	@Override
	public int getHollowSize(int arg0) {
		return 6;
	}

	private RenderPipeCopper render;

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (pass == 0) {
			if (render == null) render = new RenderPipeCopper();
			render.render(this, pos);
		}
	}

	public static final int MAX_ACCEPT = 100;
	public static final int MAX_EXTRACT = 100;
	public Map<ForgeDirection,TankConection> tanks = new HashMap<ForgeDirection,TankConection>();
	public ConnectionMode[] side = {ConnectionMode.OUTPUT,ConnectionMode.OUTPUT,ConnectionMode.OUTPUT,ConnectionMode.OUTPUT,ConnectionMode.OUTPUT,ConnectionMode.OUTPUT};//sides input and output
	public boolean[] locked = new boolean[6];
	public boolean toUpdate = true;


	public void updateConnections(){
		if(tile() == null)return;
		tanks.clear();
		connections = new boolean[6];//connection cable
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){

			TileEntity t = UT_Utils.getRelative(tile(), d);
			if(FluidUtils.isPipe(t)){
				if(this.canConectOnSide(d) && FluidUtils.getFluidTransport(t).canConectOnSide(d.getOpposite()))
					connections[d.ordinal()] = true;
			}else if(t instanceof IFluidHandler){
				if(this.canConectOnSide(d)){
					connections[d.ordinal()] = true;
					tanks.put(d,new TankConection((IFluidHandler) t, d.getOpposite()));
				}
			}
		}
	}

	@Override
	public void update() {
		super.update();
		if(toUpdate){
			updateConnections();
			boolean redstone = world().isBlockIndirectlyGettingPowered(x(), y(), z());
			for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
				if(!locked[d.ordinal()] && connections[d.ordinal()]){
					if(redstone)side[d.ordinal()] = ConnectionMode.INPUT;
					else side[d.ordinal()] = ConnectionMode.OUTPUT;
				}
			}
			toUpdate = false;
		}
		if(world().isRemote)return;
		if(world().getTotalWorldTime()%20 == 3)sendDescUpdate();
		if(getNetwork() == null)return;
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(connections[d.ordinal()]){
				if(side[d.ordinal()] == ConnectionMode.OUTPUT){
					int total = getNetwork().getFluidAmount();//amount in total
					int toD = Math.min(MAX_ACCEPT, total);//amount allowed to transfer
					if(toD <= 0)continue;
					if(getNetwork().getFluid() == null)continue;
					TankConection t = tanks.get(d);//get the tank
					if(t != null){
						toD = Math.min(toD, t.tank.fill(t.side, new FluidStack(getNetwork().fluid, toD), false));//min (this can transfer and tank can transfer)
						if(toD > 0){
							FluidStack df = drain(t.side.getOpposite(), toD, true);//drain from the network
							if(df != null){
								t.tank.fill(t.side, df, true);//fill the tank
							}
						}
					}
				}else if(side[d.ordinal()] == ConnectionMode.INPUT){
					TankConection t = tanks.get(d);//get tank
					if(t != null){
						FluidStack f = t.tank.drain(t.side, MAX_EXTRACT, false);//simulated extraction 
						if(f != null && (getNetwork().getFluid() == null || getNetwork().getFluid().getID() == f.fluidID)){
							int space = getNetwork().getCapacity()-getNetwork().getFluidAmount();//space for fluid in the network
							int toD = Math.min(f.amount, space);//min space, fluid
							if(toD > 0){
								FluidStack c = t.tank.drain(t.side, toD, false);
								int filled = fill(null, c, true);
								t.tank.drain(t.side, filled, true);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(getNetwork() == null)return 0;
		return getNetwork().manager.fill(from, resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		if(getNetwork() == null)return null;
		return getNetwork().manager.drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(getNetwork() == null)return null;
		return getNetwork().manager.drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null)return false;
		return getNetwork().manager.canFill(from, fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null)return false;
		return getNetwork().manager.canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}

	public void save(NBTTagCompound nbt)
	{
		super.save(nbt);
		for(int d=0; d<6;d++){
			nbt.setInteger("s"+d, side[d].ordinal());
		}
		for(int d=0; d<6;d++){
			nbt.setBoolean("l"+d, locked[d]);
		}
	}

	public void load(NBTTagCompound nbt)
	{
		super.load(nbt);
		for(int d=0; d<6;d++){
			side[d] = ConnectionMode.values()[nbt.getInteger("s"+d)];
		}
		for(int d=0; d<6;d++){
			locked[d] = nbt.getBoolean("l"+d);
		}
	}

	public void writeDesc(MCDataOutput p){
		super.writeDesc(p);
		for(int d=0; d<6;d++){
			p.writeInt(side[d].ordinal());
		}
		for(int d=0; d<6;d++){
			p.writeBoolean(locked[d]);
		}
	}

	public void readDesc(MCDataInput p){
		super.readDesc(p);
		for(int d=0; d<6;d++){
			side[d] = ConnectionMode.values()[p.readInt()];
		}
		for(int d=0; d<6;d++){
			locked[d] = p.readBoolean();
		}
	}

	@Override
	public void onNeighborChanged() {
		if(!world().isRemote)Net_Utils.INSTANCE.sendToAll(new MessageMultiPartUpdate(this));
		toUpdate = true;
		if(getNetwork() != null){
			getNetwork().refresh();
		}
	}

	@Override
	public void onPartChanged(TMultiPart part) {
		onNeighborChanged();
	}

	@Override
	public void onAdded() {
		onNeighborChanged();
	}

	@Override
	public void onRemoved() {
		if(getNetwork() != null)
			getNetwork().excludeAndRecalculate(this);
	}

	public boolean activate(EntityPlayer player, MovingObjectPosition hit, ItemStack item){
		if(item != null && item.getItem() instanceof IToolWrench) {
			Vector3 v = new Vector3(hit.hitVec.xCoord-hit.blockX, hit.hitVec.yCoord-hit.blockY, hit.hitVec.zCoord-hit.blockZ);
			for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
				if(connections[d.ordinal()]){
					if(MultipartUtil.isHited(boundingBoxes[d.ordinal()], v)){
						side[d.ordinal()] = ConnectionMode.getNext(side[d.ordinal()]);
						locked[d.ordinal()] = true;
						if(!world().isRemote)this.sendDescUpdate();
					}
				}
			}
		}
		return false;
	}

	public enum ConnectionMode{
		OUTPUT,INPUT,NOTHING;

		public static ConnectionMode getNext(ConnectionMode c) {
			if(c == null)return OUTPUT;
			if(c == OUTPUT)return INPUT;
			if(c == INPUT)return NOTHING;
			return OUTPUT;
		}
	}

	@Override
	public boolean canConectOnSide(ForgeDirection d) {
		return tile().canAddPart(new NormallyOccludedPart(boundingBoxes[d.ordinal()]));
	}
}
