package grimgar.client.handler;

import java.util.List;

import com.google.common.base.Predicate;

import grimgar.client.gui.GuiCustomDownloadTerrain;
import grimgar.client.gui.GuiCustomDownloadTerrain.EnumPathway;
import grimgar.core.handler.NetworkHandler;
import grimgar.core.util.ICustomAttackRange;
import grimgar.main.Reference;
import grimgar.network.CustomAttackRangeMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ClientEventHandler {
	
	private static int oldClientDimension;
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onTick(PlayerTickEvent event) {
		if(oldClientDimension != event.player.dimension) oldClientDimension = event.player.dimension;
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onGuiOpen(GuiOpenEvent event) {
		if(event.getGui() instanceof GuiDownloadTerrain) {
			switch(Minecraft.getMinecraft().player.dimension) {
				case Reference.DIM_ID_GRIMGAR:
					event.setGui(new GuiCustomDownloadTerrain(EnumPathway.ENTER_GRIMGAR));
					break;
				case Reference.DIM_ID_DUSK:
					event.setGui(new GuiCustomDownloadTerrain(EnumPathway.ENTER_DUSK));
					break;
				case Reference.DIM_ID_DARRENGAR:
					event.setGui(new GuiCustomDownloadTerrain(EnumPathway.ENTER_DARRENGAR));
					break;
				case Reference.DIM_ID_PARANO:
					event.setGui(new GuiCustomDownloadTerrain(EnumPathway.ENTER_PARANO));
					break;
				default:
					if(oldClientDimension == Reference.DIM_ID_GRIMGAR || oldClientDimension == Reference.DIM_ID_DUSK || oldClientDimension == Reference.DIM_ID_DARRENGAR || oldClientDimension == Reference.DIM_ID_PARANO) {
						if(!blacklistContains(Minecraft.getMinecraft().player.dimension)) {
							event.setGui(new GuiCustomDownloadTerrain(EnumPathway.DEPART));
						}
					}
					break;
			}
		}
	}
	
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
	
	//Feature - dimension blacklist for custom GUI
	//TODO - (Informal Marker) move to API package, improve list
	@Deprecated
	public static int[] blacklistedDimensions = null;
	
	@Deprecated
	public static boolean addBlacklistedDimension(int dimensionId) {
		if(blacklistedDimensions == null) {
			blacklistedDimensions = new int[] {dimensionId};
			return true;
		}
		int[] newList = new int[blacklistedDimensions.length+1];
		for(int i = 0; i<blacklistedDimensions.length; i++) {
			if(blacklistedDimensions[i]==dimensionId) return false;
			newList[i] = blacklistedDimensions[i];
		}
		newList[newList.length-1] = dimensionId;
		blacklistedDimensions = newList;
		return true;
	}
	
	@Deprecated
	public static boolean blacklistContains(int dimensionId) {
		if(blacklistedDimensions==null) return false;
		for(int i = 0; i<blacklistedDimensions.length; i++) {
			if(dimensionId==blacklistedDimensions[i]) return true;
		}
		return false;
	}

}
