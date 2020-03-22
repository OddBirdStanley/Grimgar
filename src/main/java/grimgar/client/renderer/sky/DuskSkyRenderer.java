package grimgar.client.renderer.sky;

import java.nio.ByteBuffer;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;

public class DuskSkyRenderer extends IRenderHandler {
	
	private int glSkyList = -1;
    private int glSkyList2 = -1;
	private OpenVertexBuffer skyVBO;
    private OpenVertexBuffer sky2VBO;
    private boolean vboEnabled;
    private VertexFormat vertexBufferFormat;
    
    private Random rand = new Random(2604440L);
	
	public DuskSkyRenderer() {
		vboEnabled = OpenGlHelper.useVbo();
		vertexBufferFormat = new VertexFormat();
		vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
	}
	
	private BufferBuilder randColor(BufferBuilder bb) {
		return bb.color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255);
	}

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		generateSky();
		generateSky2();
		renderSky(partialTicks, world, mc);
	}
	
	private void generateSky() {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        if (skyVBO != null)
        {
            skyVBO.deleteGlBuffers();
        }

        if (glSkyList >= 0)
        {
            GLAllocation.deleteDisplayLists(glSkyList);
            glSkyList = -1;
        }

        if (vboEnabled)
        {
            skyVBO = new OpenVertexBuffer(vertexBufferFormat);
            renderSky(bufferbuilder, 16.0F, false);
            bufferbuilder.finishDrawing();
            bufferbuilder.reset();
            skyVBO.bufferData(bufferbuilder.getByteBuffer());
        }
        else
        {
            glSkyList = GLAllocation.generateDisplayLists(1);
            GlStateManager.glNewList(glSkyList, 4864);
            renderSky(bufferbuilder, 16.0F, false);
            tessellator.draw();
            GlStateManager.glEndList();
        }
	}
	
	private void generateSky2() {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        if (sky2VBO != null)
        {
            sky2VBO.deleteGlBuffers();
        }

        if (glSkyList2 >= 0)
        {
            GLAllocation.deleteDisplayLists(glSkyList2);
            glSkyList2 = -1;
        }

        if (vboEnabled)
        {
            sky2VBO = new OpenVertexBuffer(vertexBufferFormat);
            renderSky(bufferbuilder, -16.0F, true);
            bufferbuilder.finishDrawing();
            bufferbuilder.reset();
            sky2VBO.bufferData(bufferbuilder.getByteBuffer());
        }
        else
        {
            glSkyList2 = GLAllocation.generateDisplayLists(1);
            GlStateManager.glNewList(glSkyList2, 4864);
            renderSky(bufferbuilder, -16.0F, true);
            tessellator.draw();
            GlStateManager.glEndList();
        }
	}
	
	private void renderSky(BufferBuilder bufferBuilder, float posY, boolean reverseX) {
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);

        for (int k = -384; k <= 384; k += 64)
        {
            for (int l = -384; l <= 384; l += 64)
            {
                float f = (float)k;
                float f1 = (float)(k + 64);

                if (reverseX)
                {
                    f1 = (float)k;
                    f = (float)(k + 64);
                }
                
                bufferBuilder.pos((double)f, (double)posY, (double)l).endVertex();
                bufferBuilder.pos((double)f1, (double)posY, (double)l).endVertex();
                bufferBuilder.pos((double)f1, (double)posY, (double)(l + 64)).endVertex();
                bufferBuilder.pos((double)f, (double)posY, (double)(l + 64)).endVertex();
            }
        }
	}

	private void renderSky(float partialTicks, WorldClient world, Minecraft mc) {
		
		TextureManager renderEngine = mc.getTextureManager();
		
		GlStateManager.disableTexture2D();
        Vec3d vec3d = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
        float f = (float)vec3d.x;
        float f1 = (float)vec3d.y;
        float f2 = (float)vec3d.z;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.depthMask(false);
        GlStateManager.enableFog();
        GlStateManager.color(f, f1, f2);

        if (vboEnabled)
        {
            skyVBO.bindBuffer();
            GlStateManager.glEnableClientState(32884);
            GlStateManager.glVertexPointer(3, 5126, 12, 0);
            for(int i = 0; i<skyVBO.count; i++) {
            	GlStateManager.color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            	GlStateManager.glDrawArrays(7, i, i+1);
            }
            skyVBO.unbindBuffer();
            GlStateManager.glDisableClientState(32884);
        }
        else
        {
            GlStateManager.callList(glSkyList);
        }

        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();
        float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);

        if (afloat != null)
        {
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            float f6 = afloat[0];
            float f7 = afloat[1];
            float f8 = afloat[2];
            
            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(0.0D, 100.0D, 0.0D).color(f6, f7, f8, afloat[3]).endVertex();
            int l1 = 16;

            for (int j2 = 0; j2 <= 16; ++j2)
            {
                float f21 = (float)j2 * ((float)Math.PI * 2F) / 16.0F;
                float f12 = MathHelper.sin(f21);
                float f13 = MathHelper.cos(f21);
                bufferbuilder.pos((double)(f12 * 120.0F), (double)(f13 * 120.0F), (double)(-f13 * 40.0F * afloat[3])).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.shadeModel(7424);
        }
        
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        float f16 = 1.0F - world.getRainStrength(partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, f16);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        float f15 = world.getStarBrightness(partialTicks) * f16;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableFog();
        GlStateManager.popMatrix();
        GlStateManager.disableTexture2D();
        
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        double d3 = mc.player.getPositionEyes(partialTicks).y - world.getHorizon();
        if (d3 < 0.0D)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);

            if (vboEnabled)
            {
                sky2VBO.bindBuffer();
                GlStateManager.glEnableClientState(32884);
                GlStateManager.glVertexPointer(3, 5126, 12, 0);
                sky2VBO.drawArrays(7);
                sky2VBO.unbindBuffer();
                GlStateManager.glDisableClientState(32884);
            }
            else
            {
                GlStateManager.callList(glSkyList2);
            }

            GlStateManager.popMatrix();
            double f19 = -((double)(d3 + world.getHorizon()));
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
           
            bufferbuilder.pos(-1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            
            tessellator.draw();
        }

        if (world.provider.isSkyColored())
        {
            GlStateManager.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
        }
        else
        {
            GlStateManager.color(f, f1, f2);
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -((float)(d3 - 16.0D)), 0.0F);
        GlStateManager.callList(glSkyList2);
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
	}
	
	public static class OpenVertexBuffer {
		public int glBufferId;
		public final VertexFormat vertexFormat;
		public int count;

	    public OpenVertexBuffer(VertexFormat vertexFormat)
	    {
	        this.vertexFormat = vertexFormat;
	        glBufferId = OpenGlHelper.glGenBuffers();
	    }

	    public void bindBuffer()
	    {
	        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, glBufferId);
	    }

	    public void bufferData(ByteBuffer data)
	    {
	        bindBuffer();
	        OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, data, 35044);
	        unbindBuffer();
	        this.count = data.limit() / this.vertexFormat.getSize();
	    }

	    public void drawArrays(int mode)
	    {
	        GlStateManager.glDrawArrays(mode, 0, this.count);
	    }

	    public void unbindBuffer()
	    {
	        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
	    }

	    public void deleteGlBuffers()
	    {
	        if (this.glBufferId >= 0)
	        {
	            OpenGlHelper.glDeleteBuffers(glBufferId);
	            glBufferId = -1;
	        }
	    }
	}
	
}
