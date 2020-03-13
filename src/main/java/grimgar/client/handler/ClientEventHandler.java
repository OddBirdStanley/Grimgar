package grimgar.client.handler;

import java.util.List;

import com.google.common.base.Predicate;

import grimgar.core.handler.NetworkHandler;
import grimgar.core.util.ICustomAttackRange;
import grimgar.network.CustomAttackRangeMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ClientEventHandler {
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onClickEvent(MouseEvent event) {
		if(event.getButton()==0 && event.isButtonstate()) {
			EntityPlayerSP player = Minecraft.getMinecraft().player;
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
				if(minId != null && minVec != null) {
					NetworkHandler.INSTANCE.sendToServer(new CustomAttackRangeMessage(minId));
				}
			}
		}
	}

}
