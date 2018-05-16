package people;

public class People {
	private int health;
	private String gender;
	
	public People () {
		this.health = 100;
		this.gender = "Female";
	}
	public People (int h, String g) {
		this.health = h;
		this.gender = g;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
