// Date: 22/06/2014 18:58:10
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package common.cout970.UltraTech.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPipeIn extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer Down;
    ModelRenderer Up;
    ModelRenderer Front;
    ModelRenderer Back;
    ModelRenderer Left;
    ModelRenderer Right;
  
  public ModelPipeIn()
  {
    textureWidth = 32;
    textureHeight = 16;
    
      base = new ModelRenderer(this, 0, 0);
      base.addBox(-2F, -2F, -2F, 4, 4, 4);
      base.setRotationPoint(0F, 16F, 0F);
      base.setTextureSize(32, 16);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      Down = new ModelRenderer(this, 0, 0);
      Down.addBox(-2F, 2F, -2F, 4, 6, 4);
      Down.setRotationPoint(0F, 16F, 0F);
      Down.setTextureSize(32, 16);
      Down.mirror = true;
      setRotation(Down, 0F, 0F, 0F);
      Up = new ModelRenderer(this, 0, 0);
      Up.addBox(-2F, -8F, -2F, 4, 6, 4);
      Up.setRotationPoint(0F, 16F, 0F);
      Up.setTextureSize(32, 16);
      Up.mirror = true;
      setRotation(Up, 0F, 0F, 0F);
      Front = new ModelRenderer(this, 0, 0);
      Front.addBox(-2F, -2F, -8F, 4, 4, 6);
      Front.setRotationPoint(0F, 16F, 0F);
      Front.setTextureSize(32, 16);
      Front.mirror = true;
      setRotation(Front, 0F, 0F, 0F);
      Back = new ModelRenderer(this, 0, 0);
      Back.addBox(-2F, -2F, 2F, 4, 4, 6);
      Back.setRotationPoint(0F, 16F, 0F);
      Back.setTextureSize(32, 16);
      Back.mirror = true;
      setRotation(Back, 0F, 0F, 0F);
      Left = new ModelRenderer(this, 0, 0);
      Left.addBox(2F, -2F, -2F, 6, 4, 4);
      Left.setRotationPoint(0F, 16F, 0F);
      Left.setTextureSize(32, 16);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      Right = new ModelRenderer(this, 0, 0);
      Right.addBox(-8F, -2F, -2F, 6, 4, 4);
      Right.setRotationPoint(0F, 16F, 0F);
      Right.setTextureSize(32, 16);
      Right.mirror = true;
      setRotation(Right, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    base.render(f5);
    Down.render(f5);
    Up.render(f5);
    Front.render(f5);
    Back.render(f5);
    Left.render(f5);
    Right.render(f5);
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

public void render(float f5, boolean[] a) {
	if(a == null)return;
	base.render(f5);
    if(a[0])Down.render(f5);
    if(a[1])Up.render(f5);
    if(a[2])Front.render(f5);
    if(a[3])Back.render(f5);
    if(a[4])Left.render(f5);
    if(a[5])Right.render(f5);
}

}
