package grimgar.client.renderer.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraftforge.client.IRenderHandler;

public abstract class ModifiedSkyRenderer extends IRenderHandler{
	
	protected int glSkyList = -1;
	protected int glSkyList2 = -1;
	protected int starGLCallList = -1;
	protected VertexBuffer skyVBO;
	protected VertexBuffer sky2VBO;
	protected VertexBuffer starVBO;
	protected boolean vboEnabled;
	protected VertexFormat vertexBufferFormat;
	
	public ModifiedSkyRenderer() {
		this.vboEnabled = OpenGlHelper.useVbo();
		this.vertexBufferFormat = new VertexFormat();
		this.vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
	}

	@Override
	public abstract void render(float partialTicks, WorldClient world, Minecraft mc);
	
	public abstract void generateSky();
	
	public abstract void generateSky2();
	
	public abstract void generateStars();
	
	public abstract void renderStars(BufferBuilder bufferBuilder);
	
	public abstract void renderSky(float partialTicks, int pass, WorldClient world, Minecraft mc);
	
	public abstract void renderSky(BufferBuilder bufferBuilder, float posY, boolean reverseX);
	
	protected TextureManager renderEngine(Minecraft mc) {
		return mc.getTextureManager();
	}

}
