package grimgar.core.capability;

public class Thirst implements IThirst{
	
	private float thirst;
	
	public Thirst() {
		thirst = 0.0F;
	}
	
	public Thirst(float thirst) {
		this.thirst = thirst;
	}
	
	@Override
	public float getThirst() {
		return thirst;
	}
	
	@Override
	public float setThirst(float thirst) {
		this.thirst = thirst>getMaxThirst() ? getMaxThirst() : thirst;
		return getThirst();
	}

	@Override
	public float addThirst(float thirst) {
		this.thirst += thirst;
		if(this.thirst<0.0F) this.thirst = 0.0F;
		else if(this.thirst>getMaxThirst()) this.thirst = getMaxThirst();
		return getThirst();
	}

}
