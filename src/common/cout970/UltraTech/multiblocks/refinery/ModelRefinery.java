// Date: 29/04/2014 22:39:44
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package common.cout970.UltraTech.multiblocks.refinery;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRefinery extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Tower;
    ModelRenderer out1;
    ModelRenderer out2;
    ModelRenderer out3;
  
  public ModelRefinery()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(-24F, 0F, -24F, 48, 16, 48);
      Base.setRotationPoint(0F, 8F, 0F);
      Base.setTextureSize(256, 256);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Tower = new ModelRenderer(this, 0, 65);
      Tower.addBox(-22F, -96F, -22F, 44, 112, 44);
      Tower.setRotationPoint(0F, -8F, 0F);
      Tower.setTextureSize(256, 256);
      Tower.mirror = true;
      setRotation(Tower, 0F, 0F, 0F);
      out1 = new ModelRenderer(this, 194, 0);
      out1.addBox(22F, -8F, -8F, 2, 16, 16);
      out1.setRotationPoint(0F, -16F, 0F);
      out1.setTextureSize(256, 256);
      out1.mirror = true;
      setRotation(out1, 0F, 0F, 0F);
      out2 = new ModelRenderer(this, 194, 0);
      out2.addBox(22F, -8F, -8F, 2, 16, 16);
      out2.setRotationPoint(0F, -48F, 0F);
      out2.setTextureSize(256, 256);
      out2.mirror = true;
      setRotation(out2, 0F, 0F, 0F);
      out3 = new ModelRenderer(this, 194, 0);
      out3.addBox(22F, -8F, -8F, 2, 16, 16);
      out3.setRotationPoint(0F, -80F, 0F);
      out3.setTextureSize(256, 256);
      out3.mirror = true;
      setRotation(out3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Tower.render(f5);
    out1.render(f5);
    out2.render(f5);
    out3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity t)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, t);
  }

}
