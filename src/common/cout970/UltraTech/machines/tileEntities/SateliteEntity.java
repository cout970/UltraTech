package common.cout970.UltraTech.machines.tileEntities;

import java.util.Random;
import common.cout970.UltraTech.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
public class SateliteEntity extends Machine{
	
	public Machine receptor;
	private boolean hasBase = false;

	public SateliteEntity(){
		super();
	}

	@SideOnly(Side.SERVER)
	@Override
	public void updateEntity(){
		if(Minecraft.getMinecraft().theWorld != null){
			Random r = new Random();
			if(r.nextInt(10)==5)checkBase();
			if(hasBase){
				passEnergy();
			}
			if(Minecraft.getMinecraft().theWorld.isDaytime()){
				int e = this.yCoord/100;
				Energy += e;
			}
		}
	}

	private void passEnergy() {
		if(receptor != null){
			Energy = receptor.gainEnergy(Energy);
			
		}
	}


	public void checkBase(){
		int b;
		for(int a = this.yCoord; a>0 ;a--){
			b = this.worldObj.getBlockId(xCoord, a, zCoord);
			if(b == Reference.IDS || b == Reference.Reciver){
				if(this.worldObj.getBlockTileEntity(xCoord, a, zCoord) instanceof IDSentity){
					receptor = (IDSentity) this.worldObj.getBlockTileEntity(xCoord, a, zCoord);
				}else if(this.worldObj.getBlockTileEntity(xCoord, a, zCoord) instanceof ReciverEntity){
					receptor = (ReciverEntity) this.worldObj.getBlockTileEntity(xCoord, a, zCoord);
				}
				hasBase  = true;
				return;
			}
		}
		hasBase = false;
	}

}
