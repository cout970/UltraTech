// Date: 01/05/2014 16:28:08
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package common.cout970.UltraTech.machines.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBattery extends ModelBase
{
  //fields
    ModelRenderer down;
    ModelRenderer top;
    ModelRenderer l;
    ModelRenderer r;
    ModelRenderer f;
    ModelRenderer b;
    ModelRenderer corner1;
    ModelRenderer corner2;
    ModelRenderer corner3;
    ModelRenderer corner4;
  
  public ModelBattery()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      down = new ModelRenderer(this, 0, 0);
      down.addBox(-8F, 0F, -8F, 16, 1, 16);
      down.setRotationPoint(0F, 23F, 0F);
      down.setTextureSize(128, 64);
      down.mirror = true;
      setRotation(down, 0F, 0F, 0F);
      top = new ModelRenderer(this, 0, 0);
      top.addBox(-8F, 0F, -8F, 16, 1, 16);
      top.setRotationPoint(0F, 8F, 0F);
      top.setTextureSize(128, 64);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
      l = new ModelRenderer(this, 0, 17);
      l.addBox(0F, 0F, 0F, 1, 14, 14);
      l.setRotationPoint(6F, 9F, -7F);
      l.setTextureSize(128, 64);
      l.mirror = true;
      setRotation(l, 0F, 0F, 0F);
      r = new ModelRenderer(this, 0, 17);
      r.addBox(0F, 0F, 0F, 1, 14, 14);
      r.setRotationPoint(-7F, 9F, -7F);
      r.setTextureSize(128, 64);
      r.mirror = true;
      setRotation(r, 0F, 0F, 0F);
      f = new ModelRenderer(this, 0, 45);
      f.addBox(0F, 0F, 0F, 12, 14, 1);
      f.setRotationPoint(-6F, 9F, -7F);
      f.setTextureSize(128, 64);
      f.mirror = true;
      setRotation(f, 0F, 0F, 0F);
      b = new ModelRenderer(this, 0, 45);
      b.addBox(0F, 0F, 0F, 12, 14, 1);
      b.setRotationPoint(-6F, 9F, 6F);
      b.setTextureSize(128, 64);
      b.mirror = true;
      setRotation(b, 0F, 0F, 0F);
      corner1 = new ModelRenderer(this, 64, 0);
      corner1.addBox(0F, 0F, 0F, 1, 14, 1);
      corner1.setRotationPoint(7F, 9F, -8F);
      corner1.setTextureSize(128, 64);
      corner1.mirror = true;
      setRotation(corner1, 0F, 0F, 0F);
      corner2 = new ModelRenderer(this, 64, 0);
      corner2.addBox(0F, 0F, 0F, 1, 14, 1);
      corner2.setRotationPoint(7F, 9F, 7F);
      corner2.setTextureSize(128, 64);
      corner2.mirror = true;
      setRotation(corner2, 0F, 0F, 0F);
      corner3 = new ModelRenderer(this, 64, 0);
      corner3.addBox(0F, 0F, 0F, 1, 14, 1);
      corner3.setRotationPoint(-8F, 9F, 7F);
      corner3.setTextureSize(128, 64);
      corner3.mirror = true;
      setRotation(corner3, 0F, 0F, 0F);
      corner4 = new ModelRenderer(this, 64, 0);
      corner4.addBox(0F, 0F, 0F, 1, 14, 1);
      corner4.setRotationPoint(-8F, 9F, -8F);
      corner4.setTextureSize(128, 64);
      corner4.mirror = true;
      setRotation(corner4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f0, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f0, f1, f2, f3, f4, f5);
    setRotationAngles(f0, f1, f2, f3, f4, f5,entity);
    down.render(f5);
    top.render(f5);
    l.render(f5);
    r.render(f5);
    f.render(f5);
    b.render(f5);
    corner1.render(f5);
    corner2.render(f5);
    corner3.render(f5);
    corner4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

}
