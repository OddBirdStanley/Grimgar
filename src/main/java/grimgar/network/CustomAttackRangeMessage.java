package grimgar.network;

import java.util.List;

import com.google.common.base.Predicate;

import grimgar.core.util.ICustomAttackRange;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CustomAttackRangeMessage implements IMessage{
	
	private int entityId;
	
	public CustomAttackRangeMessage() {
		
	}
	
	public CustomAttackRangeMessage(int entityId) {
		this.entityId = entityId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityId);
	}
	
	public static class CustomAttackRangeMessageHandler implements IMessageHandler<CustomAttackRangeMessage, IMessage>{
		
		public CustomAttackRangeMessageHandler(){
			
		}

		@Override
		public IMessage onMessage(CustomAttackRangeMessage message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			World world = player.getServerWorld();
			int entityId = message.entityId;
			Entity entity = world.getEntityByID(entityId);
			player.getServerWorld().addScheduledTask(()->{
				if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ICustomAttackRange) {
					ICustomAttackRange icar = (ICustomAttackRange)player.getHeldItemMainhand().getItem();
					Vec3d eyeVec = player.getPositionVector().add(new Vec3d(0.0D, player.getEyeHeight(), 0.0D));
					Vec3d lookVec = player.getLookVec();
					Vec3d reachVec = lookVec.scale(icar.getRange());
					Vec3d endVec = eyeVec.add(reachVec);
					AxisAlignedBB playerSurrounding = player.getEntityBoundingBox().grow(icar.getRange());
					List<Entity> list = player.world.getEntitiesInAABBexcluding(player, playerSurrounding, new Predicate<Entity>() {
						@Override
						public boolean apply(Entity input) {
							return input != null && input.canBeCollidedWith() && input instanceof EntityLivingBase;
						}
					});
					Integer minId = null;
					double minDist = Double.MAX_VALUE;
					Vec3d minVec = null;
					for(Entity each : list) {
						RayTraceResult rtr = each.getEntityBoundingBox().calculateIntercept(eyeVec, endVec);
						if(rtr != null) {
							if(rtr.hitVec.distanceTo(eyeVec)<minDist) {
								minId = each.getEntityId();
								minDist = rtr.hitVec.distanceTo(eyeVec);
								minVec = rtr.hitVec;
							}
						}
					}
					if(minId != null && minVec != null && minId==entityId) {
						player.attackTargetEntityWithCurrentItem(entity);
					}
				}
			});
			return null;
		}
		
	}

}
