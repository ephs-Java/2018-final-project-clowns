package people;

import java.awt.image.BufferedImage;

import engine.Shoot;
import gfx.SpriteSheet;
import java.util.Random;
import level.Point;

public class People {

	private int health;
	private boolean isCaught;
	private boolean isPassive;
	public int speed;
	public boolean isFacingRight;
	public boolean isFacingForward;
	public boolean isWalking;
	public SpriteSheet spriteSheet;
	public int money;
	public Shoot shoot;
	private Random r;
	public Point playerPos;
	
	public int x, y;
	
	public People() {
		
	}

	public People(int x, int y, int health, int speed, boolean isCaught, boolean isPassive, boolean isFacingRight, String path) {
		this.x = x;
		this.y = y;
		this.playerPos = new Point(x, y);
		this.money = 0;
		this.speed = speed;
		this.health = health;
		this.isCaught = isCaught;
		this.isPassive = isPassive;
		this.isFacingRight = isFacingRight;
		this.spriteSheet = new SpriteSheet(path);
		this.r = new Random();
	}
	
	/**
	 * This method allows for the abstract use of the walking animation based off of
	 * the arrays that are sent to this method. The arrays contain the needed starting points for
	 * getSprite(int x, int y, int width, int height) of the SpriteSheet class
	 * @return
	 * 	returns a the pulled subimage of a spritesheet as a BufferedImage
	 */
	

	public BufferedImage walk(int[] faceRight, int[] faceLeft, int[] walkRight, int[] walkLeft, int ticks) {
		if (this.isWalking) {
			//FACING RIGHT SIDE
			if(this.isFacingRight) {
				if (ticks < 15 || (ticks > 30 && ticks <= 45))
					return this.spriteSheet.getSprite(walkRight[0], walkRight[1], walkRight[2], walkRight[3]);
				else//if (ticks > 15 && ticks <= 30 || ticks > 45){
					return this.spriteSheet.getSprite(faceRight[0], faceRight[1], faceRight[2], faceRight[3]);
			}
			//FACING LEFT SIDE
			else  {
				if (ticks < 15 || (ticks > 30 && ticks <= 45)) 
					return this.spriteSheet.getSprite(walkLeft[0], walkLeft[1], walkLeft[2], walkLeft[3]);
				else
					return this.spriteSheet.getSprite(faceLeft[0], faceLeft[1], faceLeft[2], faceLeft[3]);
			}
		}
		//DEFAULT IDLE STATE BASED ON IF THEY ARE FACING RIGHT OR LEFT
		else {
			if (this.isFacingRight)
				return this.spriteSheet.getSprite(faceRight[0], faceRight[1], faceRight[2], faceRight[3]);
			else 
				return this.spriteSheet.getSprite(faceLeft[0], faceLeft[1], faceLeft[2], faceLeft[3]);
		}
	}
	
	public void isNear(People other) {
		if (distance(other) < 40){
			this.isWalking = true;
			this.right();
		}
		else {
			this.isWalking = false;
		}
	}
	
	public void walkAway(People other) {
		if (distance(other) < 40){
			this.isWalking = true;
			if (this.x - other.x > 0) 
				this.x += this.speed;
			else
				this.x -= this.speed;
			
			if (this.y - other.y > 0) 
				this.y += this.speed;
			else
				this.y -= this.speed;
		}
		else {
			this.isWalking = false;
		}
		
	}
	
	public void walkTowards(People other) {
		if (distance(other) < 520){
			this.isWalking = true;
			//Random r = new Random();
			int ran = r.nextInt(this.speed + 1);
			if (this.x - other.x > 0) 
				this.x -= ran;
			else
				this.x += ran;
			
			if (this.y - other.y > 0) 
				this.y -= ran;
			else
				this.y += ran;
		}
		else {
			this.isWalking = false;
		}
		
	}
	
	public double distance(People other) {
		int x = (other.x - this.x) * (other.x - this.x);
		int y = (other.y - this.y) * (other.x - this.y);
		return Math.sqrt(x + y);
	}

	
	
	
	public void up() {
		
		this.y -= this.speed;
	}
	
	public void down() {

		this.y += this.speed;
	}
	
	public void left() {

		this.x -= this.speed;
	}
	
	public void right() {

		this.x += this.speed;
	}

	public void run(int[] faceRight, int[] faceLeft, int[] walkRight, int[] walkLeft) {

		

	}

	

	

	

}