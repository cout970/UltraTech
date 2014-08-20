package common.cout970.UltraTech.util.render;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public interface IHologram {

	public void render(float f5);
	
	public void renderPart(float f5, int part);
	
	public boolean enableBlend();
	
	public ResourceLocation getResourceLocation();

	public void setColor();
	
	public void setRotation();

	public void update();

	public void onNeigUpdate();

	public void readNBT(NBTTagCompound nbt);

	public void writeNBT(NBTTagCompound nbt);
}
