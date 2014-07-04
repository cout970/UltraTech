package common.cout970.UltraTech.TileEntities.electric;

import java.util.ArrayList;
import java.util.List;

import api.cout970.UltraTech.MeVpower.Machine;
import buildcraft.api.transport.IPipeTile;
import buildcraft.api.transport.IPipeTile.PipeType;
import common.cout970.UltraTech.containers.MinerContainer;
import common.cout970.UltraTech.lib.Control;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.misc.MachineWithInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.oredict.OreDictionary;

public class MinerEntity extends MachineWithInventory implements IInventory{

	private ItemStack waiting;
	private int progres = 0;
	public int maxProgres = 10;
	boolean hasEnergy = false ;
	public int current = 0;
	private ArrayList<int[]> mining;
	public boolean hasMine = false;
	public int height = 4;
	public int widht = 4;
	public boolean blocked = false;
	
	public Mode mode = Mode.Horizontal;
	public List<IInventory> ex_Inv;
	//upgrades
	public boolean eject = false;
	public boolean hasSpeedUpgrades;
	public boolean hasRangeUpgrades;
	public boolean hasFortuneUpgrades;
	public int speedUpgrades=0;
	public int rangeUpgrades=0;
	public int fortuneUpgrades=0;
	private boolean mineFinish;
	private int mineSize;
	public boolean hasSilkUpgrade;
	
 	
	public MinerEntity(){
		super(52,"Miner",CostData.Miner);
	}
	

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.worldObj.isRemote)return;
		ex_Inv=null;
		if(ex_Inv == null){
			searchInventories();
		}
		if(eject && !isEmpty()){
			for(int g =0;g<2;g++)expulse();
		}
		
		if(!blocked){
			if(!hasMine){
				CreateMining();
				hasMine = true;
			}
			if(!hasEnergy){
				hasEnergy = this.getEnergy() >= CostData.Miner.use || Control.debug;
			}
			boolean changes = false;
			
			if(hasEnergy && current < mining.size()){
				progres++;
				if(progres >= maxProgres){
					progres = 0;
					changes = true;
					hasEnergy = false;
					BreakNextBlock();
					
				}
			}
			if(Control.debug){
				for(int i = 0;i<25;i++)BreakNextBlock();
			}

			if (changes)
			{
				markDirty();
			}
		}else{
			blocked = !addItemStack(waiting);
		}
	}
	
	private boolean isEmpty() {
		for(int d = getSizeInventory()-1; d >= 0; d--){
			if(this.getStackInSlot(d) != null)return false;
		}
		return true;
	}


	private void BreakNextBlock() {
		for(int r = 0 ; r < 10;r++){
			if(mining.size() > current){
				int x = mining.get(current)[0];
				int y = mining.get(current)[1];
				int z = mining.get(current)[2];
				if(canBreak(worldObj.getBlock(x, y, z))){
					removeEnergy(CostData.Miner.use);
					ArrayList<ItemStack> items = new ArrayList<ItemStack>();
					Block id = worldObj.getBlock(x, y, z);
					int meta = worldObj.getBlockMetadata(x, y, z);
					
					if(!hasSilkUpgrade){
						items = id.getDrops(worldObj, x, y, z, meta, fortuneUpgrades);
					}else{
						if(id.canSilkHarvest(worldObj, Minecraft.getMinecraft().thePlayer , x, y, z, meta)){
							items.add(new ItemStack(id,1,meta));//Block.blocksList[id].damageDropped(Block.blocksList[id].getDamageValue(worldObj, x, y, z))));
						}else{
							items = id.getDrops(worldObj, x, y, z, meta, fortuneUpgrades);
						}
					}
					for(int n = 0; n < items.size();n++){
						blocked = !addItemStack(items.get(n));
					}
					worldObj.setBlockToAir(x, y, z);
					break;
				}
			}else{
				if(!mineFinish){
					mineFinish = true;
					hasMine = false;
					current = 0;
				}
			}
			current++;
		}
	}

	public boolean canBreak(Block block){
		if(block == Blocks.air)return false;
		if(block instanceof BlockLiquid)return false;
		if(block instanceof BlockFluidBase)return false;
		if(Block.isEqualTo(block,Blocks.mob_spawner))return false;
		if(block == Blocks.portal)return false;
		if(block == Blocks.end_portal)return false;
		if(block == Blocks.end_portal_frame)return false;
		if(block.getBlockHardness(getWorldObj(), xCoord, yCoord, zCoord) == -1)return false;
		return true;
	}


	public void CreateMining() {
		mining = new ArrayList<int[]>();
		if(mode == Mode.Horizontal){
			for(int j = this.yCoord-1; j >= 1;j--){
				for(int i = -height;i <= height; i++){
					for(int k = -widht;k <= widht; k++){
						if(canBreak(worldObj.getBlock(this.xCoord + i,j , this.zCoord + k))){
							mining.add(new int[]{this.xCoord + i,j , this.zCoord + k});
						}
					}
				}
			}
		}else if(mode == Mode.VerticalY){
			for(int i = -height;i <= height; i++){
				for(int k = -widht;k <= widht; k++){
					for(int j = this.yCoord-1; j >= 1;j--){
						if(canBreak(worldObj.getBlock(this.xCoord + i,j , this.zCoord + k))){
							mining.add(new int[]{this.xCoord + i,j , this.zCoord + k});
						}
					}
				}
			}
		}else{
			for(int i = -height;i <= height; i++){
				for(int j = this.yCoord-1; j >= 1;j--){
					for(int k = -widht;k <= widht; k++){
						if(canBreak(worldObj.getBlock(this.xCoord + i,j , this.zCoord + k))){
							mining.add(new int[]{this.xCoord + i,j , this.zCoord + k});
						}
					}
				}
			}
		}
		mineSize = mining.size();
	}

	public void expulse(){
		ItemStack b;
		if((b = this.getStack()) != null){
			for(IInventory a : ex_Inv){
				for(int x = 0; x < a.getSizeInventory() ;x++){
					if(a.isItemValidForSlot(x, b) && a.getStackInSlot(x) == null){
						a.setInventorySlotContents(x, b);
						return;
					}else{
						if(OreDictionary.itemMatches(a.getStackInSlot(x), b, true)){
							if((b.stackSize + a.getStackInSlot(x).stackSize) <= a.getInventoryStackLimit() && (b.stackSize + a.getStackInSlot(x).stackSize) <= b.getItem().getItemStackLimit(b)){
								b.stackSize += a.getStackInSlot(x).stackSize;
								a.setInventorySlotContents(x, b);
								return;
							}
						}
					}
				}
			}
			if(b != null)for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
				TileEntity t = worldObj.getTileEntity(xCoord+d.offsetX, yCoord+d.offsetY, zCoord+d.offsetZ);
				if(t instanceof IPipeTile){
					IPipeTile a = (IPipeTile) t;
					if(a.getPipeType() == PipeType.ITEM){
						a.injectItem(b, true, d.getOpposite());
						return;
					}
				}
			}
			this.addItemStack(b);
		}
	}

	private ItemStack getStack() {
		for(int d = getSizeInventory()-1; d >= 0; d--){
			if(inventory[d] != null){
				ItemStack d1 = inventory[d];
				inventory[d] = null;
				return d1;
			}
		}
		return null;
	}

	public void searchInventories(){
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord);

		ex_Inv = new ArrayList<IInventory>();
		for(TileEntity y : t){
			if(y instanceof IInventory)ex_Inv.add((IInventory) y);
		}
	}
	
	public enum Mode{
		VerticalY,Horizontal,VerticalX
	}
	
	public boolean addItemStack(ItemStack i){
		for(int s = 0;s < this.getSizeInventory();s++){	
			if (this.inventory[s] == null)
			{
				this.inventory[s] = i.copy();
				return true;
			}
			else if (this.inventory[s].isItemEqual(i))
			{
				if(inventory[s].stackSize + i.stackSize <= getInventoryStackLimit()){
				inventory[s].stackSize += i.stackSize;
				return true;
				}
			}
		}
		waiting = i;
		return false;
	}
	
	public void ChangeMode(){
		hasMine = false;
		current = 0;
		switch(mode){
		case Horizontal:{
			mode = Mode.VerticalY;
			break;
		}
		case VerticalY:{
			mode = Mode.VerticalX;
			break;
		}
		case VerticalX:{
			mode = Mode.Horizontal;
			break;
		}
		}
	}
	
	public int MinigSize(){
		if(mineSize <= 0)return 0;
		return mineSize;
	}

	
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		
		
		height = nbtTagCompound.getInteger("tam");
		widht = height;
		maxProgres = nbtTagCompound.getInteger("maxProgres");
		eject = nbtTagCompound.getBoolean("eject");
		switch(nbtTagCompound.getInteger("mode")){
		case 0:{
			mode = Mode.Horizontal;
			break;
		}
		case 1:{
			mode = Mode.VerticalY;
			break;
		}
		case 2:{
			mode = Mode.VerticalX;
			break;
		}		
		}
		rangeUpgrades = nbtTagCompound.getInteger("rangeUpgrades");
		speedUpgrades = nbtTagCompound.getInteger("speedUpgrades");
		fortuneUpgrades = nbtTagCompound.getInteger("fortuneUpgrades");
		
		if(speedUpgrades > 0)hasSpeedUpgrades = true;
		if(rangeUpgrades > 0)hasRangeUpgrades = true;
		if(fortuneUpgrades > 0)hasFortuneUpgrades = true;
		hasSilkUpgrade = nbtTagCompound.getBoolean("silk");
		maxProgres = 10 - speedUpgrades*2;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("silk", hasSilkUpgrade);
		
		nbtTagCompound.setInteger("tam", height);
		nbtTagCompound.setInteger("maxProgres", maxProgres);
		nbtTagCompound.setBoolean("eject", eject);
		switch(mode){
		case Horizontal:{
			nbtTagCompound.setInteger("mode", 0);
			break;
		}
		case VerticalY:{
			nbtTagCompound.setInteger("mode", 1);
			break;
		}
		case VerticalX:{
			nbtTagCompound.setInteger("mode", 2);
			break;
		}		
		}
		
		nbtTagCompound.setInteger("rangeUpgrades", rangeUpgrades);
		nbtTagCompound.setInteger("speedUpgrades", speedUpgrades);
		nbtTagCompound.setInteger("fortuneUpgrades", fortuneUpgrades);
	}

	
	//Synchronization

	public void sendGUINetworkData(MinerContainer minerContainer,
			ICrafting iCrafting) {
		super.sendGUINetworkData(minerContainer, iCrafting);
		iCrafting.sendProgressBarUpdate(minerContainer, 2, widht);
		iCrafting.sendProgressBarUpdate(minerContainer, 3, maxProgres);
		iCrafting.sendProgressBarUpdate(minerContainer, 4, mineSize);
		iCrafting.sendProgressBarUpdate(minerContainer, 5, current);
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2){
			widht = value;
			height = value;
		}
		if(id == 3)maxProgres = value;
		if(id == 4)mineSize = value;
		if(id == 5)current = value;
	}
}
