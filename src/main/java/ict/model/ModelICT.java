// Date: 6/29/2015 8:25:25 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package ict.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelICT extends ModelBase
{
    //fields
    ModelRenderer Box;
    ModelRenderer c11;
    ModelRenderer c12;
    ModelRenderer c13;
    ModelRenderer c21;
    ModelRenderer c22;
    ModelRenderer c23;
    ModelRenderer c31;
    ModelRenderer c32;
    ModelRenderer c33;
    ModelRenderer c41;
    ModelRenderer c42;
    ModelRenderer c43;

    public ModelICT()
    {
        textureWidth = 64;
        textureHeight = 64;

        Box = new ModelRenderer(this, 0, 0);
        Box.addBox(0F, 0F, 0F, 16, 9, 16);
        Box.setRotationPoint(-8F, 15F, -8F);
        Box.setTextureSize(64, 64);
        Box.mirror = true;
        setRotation(Box, 0F, 0F, 0F);
        c11 = new ModelRenderer(this, 0, 0);
        c11.addBox(0F, 0F, 0F, 4, 4, 4);
        c11.setRotationPoint(-8F, 11F, -8F);
        c11.setTextureSize(64, 64);
        c11.mirror = true;
        setRotation(c11, 0F, 0F, 0F);
        c12 = new ModelRenderer(this, 0, 0);
        c12.addBox(0F, 0F, 0F, 3, 3, 3);
        c12.setRotationPoint(-6F, 9F, -6F);
        c12.setTextureSize(64, 64);
        c12.mirror = true;
        setRotation(c12, 0F, 0F, 0F);
        c13 = new ModelRenderer(this, 0, 0);
        c13.addBox(0F, 0F, 0F, 2, 2, 2);
        c13.setRotationPoint(-4F, 8F, -4F);
        c13.setTextureSize(64, 64);
        c13.mirror = true;
        setRotation(c13, 0F, 0F, 0F);
        c21 = new ModelRenderer(this, 0, 0);
        c21.addBox(0F, 0F, 0F, 4, 4, 4);
        c21.setRotationPoint(4F, 11F, -8F);
        c21.setTextureSize(64, 64);
        c21.mirror = true;
        setRotation(c21, 0F, 0F, 0F);
        c22 = new ModelRenderer(this, 0, 0);
        c22.addBox(0F, 0F, 0F, 3, 3, 3);
        c22.setRotationPoint(3F, 9F, -6F);
        c22.setTextureSize(64, 64);
        c22.mirror = true;
        setRotation(c22, 0F, 0F, 0F);
        c23 = new ModelRenderer(this, 0, 0);
        c23.addBox(0F, 0F, 0F, 2, 2, 2);
        c23.setRotationPoint(2F, 8F, -4F);
        c23.setTextureSize(64, 64);
        c23.mirror = true;
        setRotation(c23, 0F, 0F, 0F);
        c31 = new ModelRenderer(this, 0, 0);
        c31.addBox(0F, 0F, 0F, 4, 4, 4);
        c31.setRotationPoint(4F, 11F, 4F);
        c31.setTextureSize(64, 64);
        c31.mirror = true;
        setRotation(c31, 0F, 0F, 0F);
        c32 = new ModelRenderer(this, 0, 0);
        c32.addBox(0F, 0F, 0F, 3, 3, 3);
        c32.setRotationPoint(3F, 9F, 3F);
        c32.setTextureSize(64, 64);
        c32.mirror = true;
        setRotation(c32, 0F, 0F, 0F);
        c33 = new ModelRenderer(this, 0, 0);
        c33.addBox(0F, 0F, 0F, 2, 2, 2);
        c33.setRotationPoint(2F, 8F, 2F);
        c33.setTextureSize(64, 64);
        c33.mirror = true;
        setRotation(c33, 0F, 0F, 0F);
        c41 = new ModelRenderer(this, 0, 0);
        c41.addBox(0F, 0F, 0F, 4, 4, 4);
        c41.setRotationPoint(-8F, 11F, 4F);
        c41.setTextureSize(64, 64);
        c41.mirror = true;
        setRotation(c41, 0F, 0F, 0F);
        c42 = new ModelRenderer(this, 0, 0);
        c42.addBox(0F, 0F, 0F, 3, 3, 3);
        c42.setRotationPoint(-6F, 9F, 3F);
        c42.setTextureSize(64, 64);
        c42.mirror = true;
        setRotation(c42, 0F, 0F, 0F);
        c43 = new ModelRenderer(this, 0, 0);
        c43.addBox(0F, 0F, 0F, 2, 2, 2);
        c43.setRotationPoint(-4F, 8F, 2F);
        c43.setTextureSize(64, 64);
        c43.mirror = true;
        setRotation(c43, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Box.render(f5);
        c11.render(f5);
        c12.render(f5);
        c13.render(f5);
        c21.render(f5);
        c22.render(f5);
        c23.render(f5);
        c31.render(f5);
        c32.render(f5);
        c33.render(f5);
        c41.render(f5);
        c42.render(f5);
        c43.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderModel(float f)
    {
        Box.render(f);
        c11.render(f);
        c12.render(f);
        c13.render(f);
        c21.render(f);
        c22.render(f);
        c23.render(f);
        c31.render(f);
        c32.render(f);
        c33.render(f);
        c41.render(f);
        c42.render(f);
        c43.render(f);
    }
}