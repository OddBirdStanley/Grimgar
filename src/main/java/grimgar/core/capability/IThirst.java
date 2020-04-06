package grimgar.core.capability;

public interface IThirst {
	
	public float getThirst();
	public float setThirst(float thirst);
	public float addThirst(float thirst);
	default public float getMaxThirst() { return 20.0F; };

}
