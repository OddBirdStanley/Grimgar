package grimgar.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCustomDownloadTerrain extends GuiScreen {
	
	private EnumPathway pathway;
	
	public GuiCustomDownloadTerrain(EnumPathway pathway) {
		this.pathway = pathway;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        mc.getTextureManager().bindTexture(pathway.getBackground());
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos(0.0D, (double)height, 0.0D).tex(0.0D, (double)((float)height / 32.0F)).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos((double)width, (double)height, 0.0D).tex((double)((float)width / 32.0F), (double)((float)height / 32.0F)).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos((double)width, 0.0D, 0.0D).tex((double)((float)width / 32.0F), 0.0D).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).color(64, 64, 64, 255).endVertex();
        tessellator.draw();
        drawCenteredString(fontRenderer, I18n.format(pathway.getKey()), width / 2, height / 2 - 50, 16777215);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public EnumPathway getPathway() {
		return pathway;
	}
	
	public static enum EnumPathway	{
		
		ENTER_GRIMGAR("multiplayer.downloadingTerrain.enterGrimgar", null),
		ENTER_DUSK("multiplayer.downloadingTerrain.enterDusk", null),
		ENTER_DARRENGAR("multiplayer.downloadingTerrain.enterDarrengar", null),
		ENTER_PARANO("multiplayer.downloadingTerrain.enterParano", null),
		DEPART("multiplayer.downloadingTerrain.depart", null);
		
		private static final ResourceLocation FALLBACK_BACKGROUND = new ResourceLocation("textures/gui/options_background.png");
		
		private String key;
		private ResourceLocation background;
		
		private EnumPathway(String key, ResourceLocation background) {
			this.key = key;
			this.background = background;
		}
		
		public String getKey() {
			return key;
		}
		
		public ResourceLocation getBackground() {
			return background == null ? FALLBACK_BACKGROUND : background;
		}
		
	}

}
