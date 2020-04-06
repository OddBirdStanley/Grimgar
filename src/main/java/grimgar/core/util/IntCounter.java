package grimgar.core.util;

public class IntCounter {
	
	private int start;
	private int value;
	
	public static final IntCounter INSTANCE = new IntCounter();
	
	public IntCounter() {
		this(-1);
	}
	
	public IntCounter(int start) {
		this.start = start;
		value = start;
	}
	
	public synchronized int getNext() {
		return value++;
	}
	
	public synchronized IntCounter reset() {
		value = start;
		return this;
	}
	
	public synchronized int set(int value) {
		this.value = value;
		return value;
	}

}
