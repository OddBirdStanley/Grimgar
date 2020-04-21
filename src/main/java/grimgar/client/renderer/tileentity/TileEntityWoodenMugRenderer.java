package grimgar.client.renderer.tileentity;

import grimgar.client.model.tileentity.ModelWoodenMug;
import grimgar.core.tileentity.TileEntityWoodenMug;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntityWoodenMugRenderer extends TileEntitySpecialRenderer<TileEntityWoodenMug> {
		
	public ModelWoodenMug model = new ModelWoodenMug();
	public static final ResourceLocation TEXTURE = new ResourceLocation("grimgar", "textures/entity/wooden_mug.png");
	
	public TileEntityWoodenMugRenderer() {
		
	}
	
	@Override
	public void render(TileEntityWoodenMug te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		model.renderAll(te.getLevel(), te.getContent(), 0.0625F);
	}

}
