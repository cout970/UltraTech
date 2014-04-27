package common.cout970.UltraTech.energy.api;


import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class Machine extends ElectricConductor implements IEnergy{

	public MachineTipe tipe = MachineTipe.Nothing;
	private float Energy = 0;

	public Machine(){
		super();
	}
	
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(tipe == MachineTipe.Output || tipe == MachineTipe.Storage){
			this.emptyMachine();
		}else if(tipe == MachineTipe.Input){
			this.fillMachine();
		}
	}


	/**
	 * this method pass the energy from machine a to machine b
	 * @param a
	 * @param b
	 */
	public static void passEnergy(Machine a, Machine b,boolean Pathfinder){
		if(a == null || b == null)return;
		if(a.equals(b))return;
		if(a.getEnergy() > 0){
			float space = b.maxEnergy()-b.getEnergy();
			float flow = Math.min(a.maxFlow(), b.maxFlow());
			if(space > 0){
				if(new EnergyPathfinder(a,b).canEnergyGoToEnd() || !Pathfinder){
					if(a.getEnergy() >= flow || a.getEnergy() >= space || a.worldObj.getWorldTime()%5 == 0){
						if(a.getEnergy() > flow && space > flow){
							a.removeEnergy(flow);
							b.addEnergy(flow);
						}else if(a.getEnergy() >= space){
							a.removeEnergy(space);
							b.addEnergy(space);
						}else{
							b.addEnergy(a.getEnergy());
							a.removeEnergy(a.getEnergy());
						}
					}
				}
			}
		}
	}

	public void emptyMachine(){

		if(getNetwork() == null)return;
		for(Machine b: getNetwork().getMachines()){
			if(b.tipe != MachineTipe.Output)
				passEnergy(this, b, true);
		}
	}

	public void fillMachine(){
		if(getNetwork() == null)return;
		for(Machine b: getNetwork().getMachines()){
			if(b.tipe == MachineTipe.Output || b.tipe == MachineTipe.Storage)
			passEnergy(b, this, true);
		}
	}
	
	public static enum MachineTipe{
		Output,Input,Nothing,Storage
	}
	
	@Override
	public float addEnergy(float amount) {
		Energy += amount;
		if(Energy > maxEnergy()){
			float aux = maxEnergy() - Energy;
			Energy = maxEnergy();
			return aux;
		}
		if(Energy < 0)Energy = 0;
		return 0;
	}

	@Override
	public void removeEnergy(float amount) {
		if(Energy-amount >= 0){
			Energy -= amount;	
		}
		if(Energy < 0)Energy = 0;
	}

	@Override
	public float getEnergy() {
		if(Energy < 0)Energy = 0;
		return Energy;
	}

	@Override
	public float maxEnergy() {
		return tier.getStorage();
	}
	
	@Override
	public float maxFlow() {
		return tier.getFlow();
	}

	
	public void setEnergy(int e) {
		Energy = e;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);

		NBTTagList tagList = nbtTagCompound.getTagList("Energy_UT");
		if(tagList.tagCount() > 0){
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(0);
			Energy = tagCompound.getFloat("Energy");
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound = new NBTTagCompound();
		tagCompound.setFloat("Energy", this.Energy);
		tagList.appendTag(tagCompound);
		nbtTagCompound.setTag("Energy_UT", tagList);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
    	this.readFromNBT(pkt.data);
    }

	public void sendGUINetworkData(Container cont,
			ICrafting c) {
		c.sendProgressBarUpdate(cont, 1, (int) getEnergy());
	}


	public void getGUINetworkData(int id, int value) {
		if(id == 1)setEnergy(value);
	}
}
