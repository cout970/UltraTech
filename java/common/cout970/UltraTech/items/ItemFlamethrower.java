package common.cout970.UltraTech.items;

import java.util.List;
import java.util.Random;

import ultratech.api.power.interfaces.ISpeeded;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import common.cout970.UltraTech.TileEntities.electric.TileEntityMiner;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.HelperNBT;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlamethrower extends ItemBow{
	
	
	public ItemFlamethrower() {
		super();
		setCreativeTab(UT_Tabs.ResourceTab);
		setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setUnlocalizedName("flamethrower");
	}
	
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer p, List l, boolean flag) {
		FluidStack f = HelperNBT.getFluid(item);
		if(f == null){
			l.add("0/1000 mB");
		}else{
			l.add(f.amount+"/1000 mB");
		}
	}
	
	public void onUsingTick(ItemStack item, EntityPlayer p, int count)
    {
		FluidStack f = HelperNBT.getFluid(item);
		if(f == null && p.isSneaking()){
			for(int k=0; k<p.inventory.getSizeInventory(); k++){
				ItemStack i = p.inventory.getStackInSlot(k);
				if(i != null && i.getItem() instanceof ItemBottle){
					FluidStack in = HelperNBT.getFluid(i);
					if(in == null)continue;
					if(FluidRegistry.getFluidStack("gasoline", 1).fluidID == in.fluidID){
						i.splitStack(1);
						HelperNBT.setFluid(item, FluidRegistry.getFluid("gasoline"), 1000);
						ItemStack cont = new ItemStack(ItemManager.ItemName.get("Bottle"));
						if(!this.addItemStack(cont, p.inventory)){
							if(!p.worldObj.isRemote){
								p.dropPlayerItemWithRandomChoice(cont, true);
							}
							break;
						}
					}
				}
			}
			return ;
		}

		if(f != null){
			if(!p.capabilities.isCreativeMode){
				if(p.worldObj.getTotalWorldTime()%100 == 0)
				HelperNBT.removeFluid(item, 20);
			}
			Random r = p.worldObj.rand;
			double x = p.posX;
			double y = p.posY;
			double z = p.posZ;
			double motionX = (double)(-MathHelper.sin(p.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI));
			double motionZ = (double)(MathHelper.cos(p.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI));
			double motionY = (double)(-MathHelper.sin(p.rotationPitch / 180.0F * (float)Math.PI));

			double FposX = (double)(-MathHelper.sin((p.rotationYaw+10) / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI))*1.4f;
			double FposZ = (double)(MathHelper.cos((p.rotationYaw+10) / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI))*1.4f;
			double FposY = (double)(-MathHelper.sin(p.rotationPitch / 180.0F * (float)Math.PI)) -0.25;
			p.worldObj.spawnParticle("smoke", x+FposX, y+FposY, z+FposZ, 0,0,0);
			for(int i = 0; i < 10; i++){
				p.worldObj.spawnParticle("flame", x+FposX, y+FposY, z+FposZ, motionX+(0.5-r.nextFloat())*0.3, motionY+(0.5-r.nextFloat())*0.3, motionZ+(0.5-r.nextFloat())*0.3);
			}
			List entities = p.worldObj.getEntitiesWithinAABBExcludingEntity(p, AxisAlignedBB.getBoundingBox(p.posX-10, p.posY-10, p.posZ-10, p.posX+10, p.posY+10, p.posZ+10));
			for(Object o : entities){
				if(o instanceof Entity){
					boolean isInRange = false;
					if(((Entity) o).getDistance(x+FposX+motionX*4, y+FposY+motionY*4, z+FposZ+motionZ*4) < 2){
						isInRange = true;
					}else if(((Entity) o).getDistance(x+motionX*2, y+motionY*2, z+motionZ*2) < 1){
						isInRange = true;
					}else if(((Entity) o).getDistance(x+motionX, y+motionY, z+motionZ) < 0.5f){
						isInRange = true;
					}
					if(isInRange){
						if(o instanceof EntityPlayer){
							if(p.canAttackPlayer((EntityPlayer) o) && !((EntityPlayer) o).capabilities.disableDamage){
								((Entity) o).setFire(10);
								((Entity) o).attackEntityFrom(DamageSource.inFire, 5f);
							}
						}else if(!(o instanceof EntityItem)){
							((Entity) o).setFire(10);
							((Entity) o).attackEntityFrom(DamageSource.inFire, 5f);
						}
					}
				}
			}
		}
    }

	@Override
	public ItemStack onItemRightClick(ItemStack item, World w, EntityPlayer p){
		p.setItemInUse(item, this.getMaxItemUseDuration(item));
		return item;
	}
	
	public boolean addItemStack(ItemStack i, IInventory inv){
		if(i == null)return false;
		for(int s = 0;s < inv.getSizeInventory();s++){	
			if (inv.getStackInSlot(s) == null)
			{
				inv.setInventorySlotContents(s, i.copy());
				return true;
			}
			else if (inv.getStackInSlot(s).isItemEqual(i) && i.getMaxStackSize() >= inv.getStackInSlot(s).stackSize+i.stackSize)
			{
				if(inv.getStackInSlot(s).stackSize + i.stackSize <= inv.getInventoryStackLimit()){
					ItemStack stack = inv.getStackInSlot(s).copy();
					stack.stackSize += i.stackSize;
					inv.setInventorySlotContents(s, stack);
				return true;
				}
			}
		}
		return false;
	}
	
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 72000;
    }
	
	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
	{
		return p_77654_1_;
	}

	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.bow;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister IR)
    {
    }

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int p_94599_1_)
    {
        return this.itemIcon;
    }
}