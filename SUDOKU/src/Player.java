
public class Player {
	private String name;
	private int sec;
	private int min;
	
	public Player (String name, int min, int sec) {
		setName(name);
		setMin(min);
		setSec(sec);
	}
	
	public String getName() {
		return name;
	}
	
	public int getSec() {
		return sec;
	}
	
	public int getMin() {
		return min;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setMin(int min) {
		this.min=min;
	}
	
	public void setSec(int sec) {
		this.sec=sec;
	}
	
	public String toString() {
		return name + "  " + sec;
	}
}
