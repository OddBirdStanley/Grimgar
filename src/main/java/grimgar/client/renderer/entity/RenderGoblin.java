package grimgar.client.renderer.entity;

import grimgar.client.model.entity.ModelGoblin;
import grimgar.core.entity.EntityGoblin;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGoblin extends RenderLiving<EntityGoblin>{

	public RenderGoblin(RenderManager renderManager) {
		super(renderManager, new ModelGoblin(), 0.78125F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGoblin entity) {
		return new ResourceLocation("grimgar", "textures/entity/goblin.png");
	}

}
