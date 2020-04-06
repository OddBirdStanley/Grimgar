package grimgar.core.spell;

import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import grimgar.core.init.InitItems;
import grimgar.core.item.ItemPriestStaff;
import grimgar.core.util.IChatSpell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellHeal implements IChatSpell{
	
	public SpellHeal() {
	}
	
	@Override
	public boolean performAction(World world, EntityPlayer player) {
		Vec3d eyeVec = player.getPositionVector().add(new Vec3d(0.0D, player.getEyeHeight(), 0.0D));
		Vec3d lookVec = player.getLookVec();
		Vec3d reachVec = lookVec.scale(ItemPriestStaff.DISTANCE);
		Vec3d endVec = eyeVec.add(reachVec);
		AxisAlignedBB playerSurrounding = player.getEntityBoundingBox().grow(ItemPriestStaff.DISTANCE);
		List<Entity> list = world.getEntitiesInAABBexcluding(player, playerSurrounding, new Predicate<Entity>() {
			@Override
			public boolean apply(Entity input) {
				return input != null && input.canBeCollidedWith() && (input instanceof EntityLivingBase) && !(input instanceof EntityArmorStand);
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
		ItemStack stack = player.getHeldItemMainhand();
		Random rng = world.rand;
		if(minId != null && minVec != null) {
			EntityLivingBase targetEntity = ((EntityLivingBase)world.getEntityByID(minId));
			if(targetEntity.getHealth()<targetEntity.getMaxHealth() && !(targetEntity instanceof EntityMob)) {
				targetEntity.heal(ItemPriestStaff.HEAL);
				if(rng.nextInt(50)>0) {
					if(stack.getItemDamage()<stack.getMaxDamage()-ItemPriestStaff.COST) {
						stack.setItemDamage(stack.getItemDamage()+ItemPriestStaff.COST);
					}else {
						if(rng.nextInt(25)>0) {
							stack.setItemDamage(stack.getMaxDamage());
						}else {
							stack.setItemDamage(stack.getMaxDamage()+1);
						}
					}
				}
				if(world.isRemote) {
					performClientAction(world, player, rng, new Boolean(true), targetEntity, EnumParticleTypes.REDSTONE, minVec, eyeVec);
				}
				return true;
			}
		}
		if(rng.nextInt(50)>0) {
			if(stack.getItemDamage()<stack.getMaxDamage()-ItemPriestStaff.COST) {
				stack.setItemDamage(stack.getItemDamage()+ItemPriestStaff.COST);
			}else {
				if(rng.nextInt(25)>0) {
					stack.setItemDamage(stack.getMaxDamage());
				}else {
					stack.setItemDamage(stack.getMaxDamage()+1);
				}
			}
		}
		if(world.isRemote) {
			performClientAction(world, player, rng, new Boolean(false), null, EnumParticleTypes.FALLING_DUST, minVec, endVec);
		}
		return false;
	}
	
	@Override
	public boolean isValid(World world, EntityPlayer player) {
		return player.getHeldItemMainhand().getItem() != null && player.getHeldItemMainhand().getItemDamage()<=player.getHeldItemMainhand().getMaxDamage()-ItemPriestStaff.COST && player.getHeldItemMainhand().getItem()==heldItem();
	}
	
	@Override
	public String getSpell() {
		return "HEAL";
	}
	
	@Override
	public String getName() {
		return "heal";
	}

	@Override
	public Item heldItem() {
		return InitItems.PRIEST_STAFF;
	}

	@Override
	public boolean accessibleInCreative() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean performClientAction(World world, EntityPlayer player, Object... objects) {
		Random rng = (Random) objects[0];
	    boolean isSuccess = ((Boolean) objects[1]).booleanValue();
	    EntityLivingBase targetEntity;
	    if(objects[2]==null) {
	    	targetEntity = null;
	    }else {
	    	targetEntity = (EntityLivingBase) objects[2];
	    }
	    EnumParticleTypes particleType = (EnumParticleTypes) objects[3];
		Vec3d minVec = (Vec3d) objects[4];
		Vec3d endVec = (Vec3d) objects[5];
		if(isSuccess && targetEntity != null) {
			world.spawnParticle(EnumParticleTypes.HEART, targetEntity.posX+rng.nextDouble()/2-0.5, targetEntity.posY+rng.nextDouble()/10, targetEntity.posZ+rng.nextDouble()/2-0.5, rng.nextDouble()/100, rng.nextDouble()/50, rng.nextDouble()/100);
		}
		int particles = MathHelper.floor(minVec.distanceTo(endVec))*3;
		for(int i = 1; i<=particles; i++) {
			double difX = (minVec.x-endVec.x)/particles;
			double difY = (minVec.y-endVec.y)/particles;
			double difZ = (minVec.z-endVec.z)/particles;
			world.spawnParticle(particleType, minVec.x-difX*i, minVec.y-difY*i, minVec.z-difZ*i, 0.0D, 0.0D, 0.0D);
		}
		return false;
	}
	
}
