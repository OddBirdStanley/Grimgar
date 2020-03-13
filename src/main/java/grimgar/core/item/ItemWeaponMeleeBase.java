package grimgar.core.item;

import com.google.common.collect.Multimap;

import grimgar.core.util.ICustomAttackRange;
import grimgar.main.Reference;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;

public class ItemWeaponMeleeBase extends ItemSword implements ICustomAttackRange{
	
	private float attackDamage,attackSpeed;
	private double attackRange;
	private String registryName, unlocalizedName;

	public ItemWeaponMeleeBase(String registryName, String unlocalizedName, ToolMaterial material, double attackRange, float attackDamage, float attackSpeed) {
		super(material);
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		setTranslationKey(unlocalizedName);
		setRegistryName(Reference.MOD_ID,registryName);
		setCreativeTab(Reference.GRIMGAR_EQUIPMENTS);
	}

	@Override
	public double getRange() {
		return attackRange;
	}

	@Override
	public float getDamage() {
		return attackDamage;
	}
	
	@Override
	public float getSpeed() {
		return attackSpeed;
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)attackSpeed-4, 0));
        }
		return multimap;
	}

}
