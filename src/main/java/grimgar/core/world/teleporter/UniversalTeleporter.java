package grimgar.core.world.teleporter;

import grimgar.core.util.GrimgarModException;
import grimgar.main.Reference;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

public class UniversalTeleporter implements ITeleporter{
	
	public static UniversalTeleporter teleporterGrimgar, teleporterDusk, teleporterDarrengar, teleporterParano;
	
	public static UniversalTeleporter getTeleporter(WorldServer worldServer) {
		switch (worldServer.provider.getDimension()) {
			case Reference.DIM_ID_GRIMGAR:
				if(teleporterGrimgar==null) teleporterGrimgar = new UniversalTeleporter(worldServer);
				return teleporterGrimgar;
			case Reference.DIM_ID_DUSK:
				if(teleporterDusk==null) teleporterDusk = new UniversalTeleporter(worldServer);
				return teleporterDusk;
			case Reference.DIM_ID_DARRENGAR:
				if(teleporterDarrengar==null) teleporterDarrengar = new UniversalTeleporter(worldServer);
				return teleporterDarrengar;
			case Reference.DIM_ID_PARANO:
				if(teleporterParano==null) teleporterParano = new UniversalTeleporter(worldServer);
				return teleporterParano;
			default:
				throw new GrimgarModException("Illegal world server input.");
		}
	}
	
	private WorldServer worldServer;
	private Long2ObjectMap<PortalPosition> cache = new Long2ObjectOpenHashMap<PortalPosition>(4096);
	
	public UniversalTeleporter(WorldServer worldServer) {
		this.worldServer = worldServer;
	}

	@Override
	public void placeEntity(World world, Entity entity, float yaw) {

	}
	
	private class PortalPosition extends BlockPos {
		
		private long lastUpdate;
		
		private PortalPosition(BlockPos pos, long lastUpdate) {
			super(pos.getX(), pos.getY(), pos.getZ());
			this.lastUpdate = lastUpdate;
		}
		
		private long getLastUpdate() { return lastUpdate; }
		private void setLastUpdate(long newLastUpdate) { lastUpdate = newLastUpdate; }
		
	}

}
