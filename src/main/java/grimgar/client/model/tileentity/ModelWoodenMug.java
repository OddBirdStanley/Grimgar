package grimgar.client.model.tileentity;

import grimgar.core.block.BlockWoodenMug.EnumMugContent;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelWoodenMug extends ModelBase {
	public ModelRenderer sideNorth;
	public ModelRenderer sideSouth;
	public ModelRenderer sideEast;
	public ModelRenderer sideWest;
	public ModelRenderer sideBottom;
	public ModelRenderer handleUpper;
	public ModelRenderer handleMiddle;
	public ModelRenderer handleLower;
	
	public ModelRenderer liquid;
	
	public ModelWoodenMug() {
		textureHeight = 64;
		textureWidth = 64;
		
		sideNorth = new ModelRenderer(this, 22, 7);
		sideNorth.addBox(-3.0F, -9.0F, -3.0F, 6, 8, 1);
		
		sideSouth = new ModelRenderer(this, 0, 21);
		sideSouth.addBox(-3.0F, -9.0F, -4.0F, 6, 8, 1);
		
		sideEast = new ModelRenderer(this, 0, 7);
		sideEast.addBox(-4.0F, -9.0F, -2.0F, 1, 8, 6);
		
		sideWest = new ModelRenderer(this, 14, 14);
		sideWest.addBox(3.0F, -9.0F, -2.0F, 1, 8, 6);
		
		sideBottom = new ModelRenderer(this, 0, 0);
		sideBottom.addBox(3.0F, -1.0F, -2.0F, 6, 1, 6);
		
		handleLower = new ModelRenderer(this, 0, 4);
		handleLower.addBox(-1.0F, -3.0F, -4.0F, 2, 1, 1);
		
		handleMiddle = new ModelRenderer(this, 0, 0);
		handleMiddle.addBox(-1.0F, -6.0F, -5.0F, 2, 3, 1);
		
		handleUpper = new ModelRenderer(this, 0, 7);
		handleUpper.addBox(-1.0F, -7.0F, -4.0F, 2, 1, 1);
		
		liquid = new ModelRenderer(this);
	}
	
	public void renderAll(int level, EnumMugContent content, float scale) {
		renderBody(scale);
		renderLiquid(level, content, scale);
	}
	
	public void renderLiquid(int level, EnumMugContent content, float scale) {
		if(level>0) {
			liquid.cubeList.clear();
			ModelBox box = new ModelBox(liquid, getTexU(content), getTexV(content), -3.0F, -2.0F, -3.0F, 6, level, 6, 0.0F);
			liquid.cubeList.add(box);
			liquid.render(scale);
		}
	}
	
	private int getTexU(EnumMugContent content) {
		return 0;
	}
	
	private int getTexV(EnumMugContent content) {
		return 0;
	}
	
	public void renderBody(float scale) {
		sideNorth.render(scale);
		sideSouth.render(scale);
		sideEast.render(scale);
		sideWest.render(scale);
		sideBottom.render(scale);
	}
}
