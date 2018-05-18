package engine;

public class Point {
	
	private int x;
	private int y;
	private double slope;
	
	public Point() {
		this.x = 0;
		this.y = 0;
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getSlope(Point other) {
		int x = other.x - this.x;
		int y = other.y - this.y;
		return y / x;
	}
	
	public Point getRiseOverRunSet(Point other) {
		int x = other.x - this.x;
		int y = other.y - this.y;
		Point temp = new Point(x, y); 
		return temp;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}
	
	
}
