package grimgar.core.init;

import grimgar.client.renderer.entity.RenderGoblin;
import grimgar.core.entity.EntityGoblin;
import grimgar.main.Grimgar;
import grimgar.main.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InitEntities {
	
	public static void registerEntities() {
		EntityRegistry.registerModEntity(new ResourceLocation("grimgar","goblin"), EntityGoblin.class, "goblin", Reference.ENTITY_ID_GOBLIN, Grimgar.instance, 80, 1, false, 0xA19465, 0xCDF465);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, manager -> new RenderGoblin(manager));
	}

}
