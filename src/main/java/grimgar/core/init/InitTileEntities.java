package grimgar.core.init;

import grimgar.client.renderer.tileentity.TileEntityWoodenMugRenderer;
import grimgar.core.tileentity.TileEntityWoodenMug;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InitTileEntities {
	
	public static void registerTileEntities() {
		TileEntity.register("wooden_mug", TileEntityWoodenMug.class);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenMug.class, new TileEntityWoodenMugRenderer());
	}

}
