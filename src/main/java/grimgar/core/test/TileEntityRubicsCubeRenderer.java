package grimgar.core.test;

import grimgar.core.test.TileEntityRubicsCube.EnumRubicsCubeColor;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class TileEntityRubicsCubeRenderer extends TileEntitySpecialRenderer<TileEntityRubicsCube> {
		
	public TileEntityRubicsCubeRenderer() {
		
	}
	
	@Override
	public void render(TileEntityRubicsCube te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		EnumRubicsCubeColor[] list = te.getColors();
		
		GlStateManager.disableCull();
		GlStateManager.disableAlpha();
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		
		double s = 0.125D;
		x += 0.25D;
		z += 0.25D;
		y += 0.7501D;
		
		GlStateManager.pushMatrix();
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		rectangle(bufferBuilder, s, 1, x, y, z, list[0].getRed(), list[0].getGreen(), list[0].getBlue(), 255);
		x += 0.5D;
		rectangle(bufferBuilder, s, 1, x, y, z, list[1].getRed(), list[1].getGreen(), list[1].getBlue(), 255);
		z += 0.5D;
		rectangle(bufferBuilder, s, 1, x, y, z, list[2].getRed(), list[2].getGreen(), list[2].getBlue(), 255);
		x -= 0.5D;
		rectangle(bufferBuilder, s, 1, x, y, z, list[3].getRed(), list[3].getGreen(), list[3].getBlue(), 255);
		z -= 0.5D;
		tessellator.draw();
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		GlStateManager.translate(0.0D, -0.7502D, 0.0D);
		rectangle(bufferBuilder, s, 1, x, y, z, list[4].getRed(), list[4].getGreen(), list[4].getBlue(), 255);
		x += 0.5D;
		rectangle(bufferBuilder, s, 1, x, y, z, list[5].getRed(), list[5].getGreen(), list[5].getBlue(), 255);
		z += 0.5D;
		rectangle(bufferBuilder, s, 1, x, y, z, list[6].getRed(), list[6].getGreen(), list[6].getBlue(), 255);
		x -= 0.5D;
		rectangle(bufferBuilder, s, 1, x, y, z, list[7].getRed(), list[7].getGreen(), list[7].getBlue(), 255);
		z -= 0.5D;
		tessellator.draw();
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		GlStateManager.translate(0.0D, -s - 0.5D, -s - 0.0001D);
		rectangle(bufferBuilder, s, 2, x, y, z, list[4].getRed(), list[4].getGreen(), list[4].getBlue(), 255);
		x += 0.5D;
		rectangle(bufferBuilder, s, 2, x, y, z, list[5].getRed(), list[5].getGreen(), list[5].getBlue(), 255);
		y += 0.5D;
		rectangle(bufferBuilder, s, 2, x, y, z, list[1].getRed(), list[1].getGreen(), list[1].getBlue(), 255);
		x -= 0.5D;
		rectangle(bufferBuilder, s, 2, x, y, z, list[0].getRed(), list[0].getGreen(), list[0].getBlue(), 255);
		y -= 0.5D;
		tessellator.draw();
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		GlStateManager.translate(0.0D, -s - 0.5D, -s + 0.7501D);
		rectangle(bufferBuilder, s, 2, x, y, z, list[7].getRed(), list[7].getGreen(), list[7].getBlue(), 255);
		x += 0.5D;
		rectangle(bufferBuilder, s, 2, x, y, z, list[6].getRed(), list[6].getGreen(), list[6].getBlue(), 255);
		y += 0.5D;
		rectangle(bufferBuilder, s, 2, x, y, z, list[2].getRed(), list[2].getGreen(), list[2].getBlue(), 255);
		x -= 0.5D;
		rectangle(bufferBuilder, s, 2, x, y, z, list[3].getRed(), list[3].getGreen(), list[3].getBlue(), 255);
		y -= 0.5D;
		tessellator.draw();
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		GlStateManager.translate(s + 0.5001D, -s - 0.5D, 0.0D);
		rectangle(bufferBuilder, s, 0, x, y, z, list[5].getRed(), list[5].getGreen(), list[5].getBlue(), 255);
		y += 0.5D;
		rectangle(bufferBuilder, s, 0, x, y, z, list[1].getRed(), list[1].getGreen(), list[1].getBlue(), 255);
		z += 0.5D;
		rectangle(bufferBuilder, s, 0, x, y, z, list[2].getRed(), list[2].getGreen(), list[2].getBlue(), 255);
		y -= 0.5D;
		rectangle(bufferBuilder, s, 0, x, y, z, list[6].getRed(), list[6].getGreen(), list[6].getBlue(), 255);
		z -= 0.5D;
		tessellator.draw();
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		GlStateManager.translate(s - 0.2501D, -s - 0.5D, 0.0D);
		rectangle(bufferBuilder, s, 0, x, y, z, list[4].getRed(), list[4].getGreen(), list[4].getBlue(), 255);
		y += 0.5D;
		rectangle(bufferBuilder, s, 0, x, y, z, list[0].getRed(), list[0].getGreen(), list[0].getBlue(), 255);
		z += 0.5D;
		rectangle(bufferBuilder, s, 0, x, y, z, list[3].getRed(), list[3].getGreen(), list[3].getBlue(), 255);
		y -= 0.5D;
		rectangle(bufferBuilder, s, 0, x, y, z, list[7].getRed(), list[7].getGreen(), list[7].getBlue(), 255);
		z -= 0.5D;
		tessellator.draw();
		GlStateManager.popMatrix();
		
		GlStateManager.enableCull();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();
		
	}
	
	private void rectangle(BufferBuilder bufferBuilder, double halfSideLength, int axisAligned,
			double x, double y, double z,
			int r, int g, int b, int a) {
		if(axisAligned == 0) {
			bufferBuilder.pos(x, y + halfSideLength, z + halfSideLength).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x, y + halfSideLength, z - halfSideLength).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x, y - halfSideLength, z - halfSideLength).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x, y - halfSideLength, z + halfSideLength).color(r, g, b, a).endVertex();
		}else if(axisAligned == 1) {
			bufferBuilder.pos(x + halfSideLength, y, z + halfSideLength).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x - halfSideLength, y, z + halfSideLength).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x - halfSideLength, y, z - halfSideLength).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x + halfSideLength, y, z - halfSideLength).color(r, g, b, a).endVertex();
		}else if(axisAligned == 2) {
			bufferBuilder.pos(x + halfSideLength, y + halfSideLength, z).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x - halfSideLength, y + halfSideLength, z).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x - halfSideLength, y - halfSideLength, z).color(r, g, b, a).endVertex();
			bufferBuilder.pos(x + halfSideLength, y - halfSideLength, z).color(r, g, b, a).endVertex();
		}
	}

}
