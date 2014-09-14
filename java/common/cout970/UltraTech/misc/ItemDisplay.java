package common.cout970.UltraTech.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import ultratech.api.util.UT_Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.TileEntities.utility.TileEntityHologramEmiter;
import common.cout970.UltraTech.client.textures.ModelResources;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.render.IHologram;
import common.cout970.UltraTech.wiki.Page;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ItemDisplay implements IHologram{

	public List<ItemStack> items = new ArrayList<ItemStack>();
	public World w;
	public int x,y,z;
	private boolean updated;

	public ItemDisplay(World worldObj, int xCoord, int yCoord, int zCoord) {
		w = worldObj;
		x = xCoord;
		y = yCoord;
		z = zCoord;
	}

	@Override
	public void render(float f5) {
		if(!items.isEmpty()){
			GL11.glTranslatef(0, 0.8f, 0);
			for(int j=0;j<(10);j++){
				GL11.glPushMatrix();
				GL11.glTranslatef(0, -j/6f, 0);
				for(int i=0;i<9;i++){
					int slot = j*9+i;
					if(slot < items.size()){
						GL11.glPushMatrix();
						GL11.glScalef(1/9f, 1/9f, 1/9f);
						GL11.glTranslatef(i, 0, 0);
						GL11.glTranslatef(0.5f, 0, 0);
						renderItemStack(items.get(slot));
						GL11.glPopMatrix();
					}
				}
				GL11.glPopMatrix();
			}
		}
	}



	@Override
	public void renderPart(float f5, int part) {}

	@Override
	public boolean enableBlend() {
		return false;
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return TextureMap.locationItemsTexture;
	}

	@Override
	public void setColor() {
		Tessellator.instance.setColorOpaque_F(1, 1, 1);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  224,  240);
	}

	@Override
	public void setRotation() {

	}

	private void renderItemStack(ItemStack itemStack) {
		//item
		GL11.glPushMatrix();
		float s = 0.05f;
		GL11.glScalef(s, s, s);
		GL11.glRotated(180, 1, 0, 0);
		GL11.glTranslatef(0,0,10);
		if(isCubic(itemStack)){//without block angle
			GL11.glTranslatef(4,0,-10);
			GL11.glRotated(90f+45f, 0, 1, 0);
			GL11.glRotated(30f, 1, 0, 0);
		}
		GL11.glTranslatef(-7, -20, -56);
		GL11.glDisable(GL11.GL_CULL_FACE);
		RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, itemStack, 0, 0);
		GL11.glPopMatrix();

		//number
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScalef(0.03f, 0.03f, 0.03f);
		GL11.glRotated(180, 1, 0, 0);
		GL11.glColor3f(1, 1, 1);
		Page.drawString(""+itemStack.stackSize, 0, 0, UT_Utils.RGBtoInt(255, 255, 255), true);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}

	private boolean isCubic(ItemStack itemStack) {
		if(itemStack.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemStack.getItem()).getRenderType()))return true;
		return false;
	}

	@Override
	public void update() {
		if(w.isRemote)return;
		if(w.getTotalWorldTime() % 20 == 1){
			updated = false;
		}
		if(!updated && w.getTileEntity(x, y, z) != null){// 
			updated = true;
			items.clear();
			for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
				TileEntity t = UT_Utils.getRelative(w.getTileEntity(x, y, z), d);
				if(t instanceof IInventory){
					IInventory i = (IInventory) t;
					for(int slot=0; slot < i.getSizeInventory();slot++){
						if(i.getStackInSlot(slot) != null){
							items.add(i.getStackInSlot(slot));
						}
					}
				}
			}
			((TileEntityHologramEmiter)w.getTileEntity(x, y, z)).sendNetworkUpdate();
		}
	}

	@Override
	public void onNeigUpdate() {
		updated = false;
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		NBTTagList list = nbt.getTagList("Inv", 10);
		items.clear();
		if(list.tagCount() > 0){
			for(int i = 0; i < list.tagCount(); i++){
				ItemStack a = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(i));
				if(a != null)items.add(a);
			}
		}
	}

	@Override
	public void writeNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for(ItemStack i : items){
			if(i != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("pos", items.indexOf(i));
				i.writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		nbt.setTag("Inv", list);
	}
}
