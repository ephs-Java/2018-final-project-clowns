package people;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import engine.Engine;
import engine.Shoot;
import gfx.SpriteSheet;
import level.Point;

public class Player extends People{
	public int width;
	public int height;
	public boolean isRobbing;
	public boolean hasKeyCard;
	public boolean canShoot;
	public BufferedImage playerSprite;
	public Point currentPos;
	
	//private Engine game;
	public Player(Engine e) {
		super(483, 556, 200, 2, false, false, true, "Player.png" );
		//super(100, 100, 200, 2, false, false, true, "raptor_32x32.png" );
		//game = e;
		currentPos = new Point(this.x, this.y);
		this.canShoot = false;
		this.isRobbing = false;
		this.hasKeyCard = false;
		//this.playerSprite = this.spriteSheet.getSprite( 0, 114, 12, 57);
	}
	
	public void move(int ticks) {
		/*
		 * THESE ARE THE ARRAYS THAT CONTAIN THE 
		 * X Y POSITIONS OF THE SUBIMAGE OF THE SPRITESHEET
		 */
//RAPTOR
//		int[] faceRight = {0, 0, 26, 54};
//		int[] walkRight = {26, 0, 26, 54};
//		
//		int[] faceLeft = {0, 64, 26, 54};
//		int[] walkLeft = {26, 64, 26, 54};
//PLAYER
		
		int[] faceRight = {0, 112, 15, 56};
		int[] walkRight = {37, 112, 21, 56};
		
		int[] faceLeft = {0, 56, 20, 56};
		int[] walkLeft = {37, 56, 20, 56};
		//playerSprite = this.walk(faceRight, faceLeft, walkRight, walkLeft, ticks);
		
		currentPos.x = this.x;
		currentPos.y = this.y;
		playerSprite = this.walk(faceRight, faceLeft, walkRight, walkLeft, ticks);

	}
	
	public void render(BufferStrategy bs, Graphics g) {
		g.drawImage(playerSprite, this.x, this.y, null);
		
	}

	
	public void renderBullet(BufferStrategy bs, Graphics g) {
		g.fillRect(height, height, width, height);
	}

	
}
