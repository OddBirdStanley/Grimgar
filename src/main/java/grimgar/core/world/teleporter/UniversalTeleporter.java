package grimgar.core.world.teleporter;

import java.util.Random;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

public class UniversalTeleporter implements ITeleporter{
	
	private WorldServer worldServer;
	private Random rand;
	private Long2ObjectMap<PortalPosition> cache = new Long2ObjectOpenHashMap<PortalPosition>(4096);
	
	public UniversalTeleporter(WorldServer worldServer) {
		this.worldServer = worldServer;
		rand = new Random(worldServer.getSeed());
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
