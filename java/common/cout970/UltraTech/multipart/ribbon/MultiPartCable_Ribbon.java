package common.cout970.UltraTech.multipart.ribbon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.prefab.CableInterfaceBlock;
import ultratech.api.util.UT_Utils;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.multipart.MultiPartUT;
import common.cout970.UltraTech.multipart.MultiPartRegistry_UT;
import common.cout970.UltraTech.multipart.client.RenderCablePlane;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageMultiPartUpdate;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.power.cables.CableInterfaceRibbonCable;

public abstract class MultiPartCable_Ribbon extends MultiPartUT implements IPowerConductor{

	public MultiPartCable_Ribbon() {
		super(MultiPartRegistry_UT.PlaneCable);
	}
	
	@Override
	public Iterable<Cuboid6> getCollisionBoxes() {
		ArrayList<Cuboid6> OB = new ArrayList<Cuboid6>();
		OB.add(boundingBoxes[0]);//base
		for(ForgeDirection d : getSides()){
			if(connections[getBox(d)]){
				OB.add(boundingBoxes[getBox(d)]);
			}
		}
		return OB;
	}
	
	@Override
	public String getType() {
		return base.getUnlocalizedName()+".down";
	}

	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[0] });
	}
	
	//power
	public PowerInterface cond = null;
	public boolean toUpdate = true;
	
	@Override
	public PowerInterface getPower(){
		if(cond == null){
			cond = new PowerInterface(tile(), new CableInterfaceRibbonCable(this));
		}
		return cond;
	}
	
	public abstract void updateConnections();
	
	@Override
	public void update() {
		super.update();
		if(toUpdate){
			updateConnections();
			toUpdate = false;
		}
		getPower().MachineUpdate();	
	}

	@Override
	public void onNeighborChanged() {
		if(!world().isRemote)Net_Utils.INSTANCE.sendToAll(new MessageMultiPartUpdate(this));
		toUpdate = true;
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
		if(getPower().getNetwork() != null){
			getPower().getNetwork().excludeAndRecalculate(this);
		}
	}

	public int getBox(ForgeDirection orientation) {
		return 0;
	}
	
	public ForgeDirection[] getSides(){
		return new ForgeDirection[]{};
	}
}
