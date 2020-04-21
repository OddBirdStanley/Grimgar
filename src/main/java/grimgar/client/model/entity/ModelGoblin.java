package grimgar.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGoblin extends ModelBase {
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer leftEar;
	public ModelRenderer rightEar;
	public ModelRenderer leftArm;
	public ModelRenderer rightArm;
	public ModelRenderer rightLeg;
	public ModelRenderer leftLeg;

	public ModelGoblin() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 0);
		body.setRotationPoint(0.0F, 17.0F, 0.0F);
		body.addBox(-5.5F, -14.0F, -2.5F, 11, 14, 5);

		head = new ModelRenderer(this, 0, 19);
		head.setRotationPoint(0.0F, -14.0F, 0.0F);
		head.addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7);
		body.addChild(head);

		leftEar = new ModelRenderer(this, 16, 33);
		leftEar.setRotationPoint(-3.5F, -3.5F, -0.5F);
		leftEar.addBox(-5.0F, -1.5F, -0.5F, 5, 3, 1);
		leftEar.rotateAngleX = 0.0F;
		leftEar.rotateAngleY = -0.2618F;
		leftEar.rotateAngleZ = 0.2618F;
		head.addChild(leftEar);

		rightEar = new ModelRenderer(this, 16, 37);
		rightEar.setRotationPoint(3.5F, -3.5F, -0.5F);
		rightEar.addBox(0.0F, -1.5F, -0.5F, 5, 3, 1);
		rightEar.rotateAngleX = 0.0F;
		rightEar.rotateAngleY = 0.2618F;
		rightEar.rotateAngleZ = -0.2618F;
		head.addChild(rightEar);

		leftArm = new ModelRenderer(this, 32, 0);
		leftArm.setRotationPoint(5.5F, -13.0F, 0.0F);
		leftArm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4);
		body.addChild(leftArm);

		rightArm = new ModelRenderer(this, 28, 28);
		rightArm.setRotationPoint(-5.5F, -13.0F, 0.0F);
		rightArm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4);
		body.addChild(rightArm);

		rightLeg = new ModelRenderer(this, 0, 33);
		rightLeg.setRotationPoint(-3.0F, 17.0F, 0.0F);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4);

		leftLeg = new ModelRenderer(this, 28, 15);
		leftLeg.setRotationPoint(3.0F, 17.0F, 0.0F);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4);
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.body.rotateAngleY = 0.0F;
        this.rightArm.rotationPointZ = 0.0F;
        this.rightArm.rotationPointX = -5.0F;
        this.leftArm.rotationPointZ = 0.0F;
        this.leftArm.rotationPointX = 5.0F;
        float f = 1.0F;
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
        this.rightLeg.rotateAngleZ = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;
        this.rightArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleZ = 0.0F;
		this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
	    this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
	    this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	    this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		body.render(scale);
		leftLeg.render(scale);
		rightLeg.render(scale);
	}

}