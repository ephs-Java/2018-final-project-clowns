package people;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import engine.Bullet;
import engine.Engine;
import level.Point;

public class Police extends People {
	
	private Engine game;
	public Graphics g1;
	public BufferedImage policeSprite;
	public boolean onPatrole;
	private int patroleType;
	private int patroleStart;
	private int patroleEnd;
	private boolean switchPatrole;
	//public Point playerPos;
	private static int count;
	public int dx, dy;
	
	public Police(Engine g, int x, int y) {
		super(x, y, 100, 2, false, false, true, "Police.png");
		game = g;
		this.playerPos = new Point(x, y);
		this.policeSprite = this.spriteSheet.getSprite( 0, 114, 12, 57);
		this.onPatrole = true;
		this.switchPatrole = false;
		
		this.count = 0;
	}
	
	public void move(int ticks) {
		
		
		
		int[] faceRight = {0, 116, 12, 58};
		int[] walkRight = {12, 116, 17, 58};
		
		int[] faceLeft = {0, 58, 16, 58};
		int[] walkLeft = {16, 58, 17, 58}; 
		
		
		//this.isNear(game.player);
		//this.walkAway(game.player);
		this.playerPos.x = this.x;
		this.playerPos.y = this.y;
		this.patrole(ticks);
		policeSprite = this.walk(faceRight, faceLeft, walkRight, walkLeft, ticks);
		
	}

	public void render(BufferStrategy bs, Graphics g) {
		g.drawImage(policeSprite, this.x, this.y, null);
		
	}
	
	public void patrole(int ticks) {
		if (this.onPatrole && !game.player.isRobbing) {
			this.speed = 1;
			if (this.patroleType == 0) {
				if (this.x > 69 && !this.switchPatrole) 
					patroleMove();
				else 
					this.switchPatrole = true;
				if (this.x < 420 && this.switchPatrole) 
					patroleMove();
				else 
					this.switchPatrole = false;	
			}
			else if (this.patroleType == 1) {
				if (this.x < 730 && this.switchPatrole) 
					patroleMove();
				else 
					this.switchPatrole = false;
				if (this.x > 520 && !this.switchPatrole) 
					patroleMove();
				else 
					this.switchPatrole = true;	
			} 
			else if (this.patroleType == 2) {
				if (this.x < 720 && this.switchPatrole) 
					patroleMove();
				else 
					this.switchPatrole = false;
				if (this.x > 100 && !this.switchPatrole) 
					patroleMove();
				else 
					this.switchPatrole = true;	
			}
			
		} else {
			fight(ticks);
		}
	}
	
	public void patroleMove() {
		if (!this.switchPatrole) {
			this.isWalking = true;
			this.isFacingRight = false;
			this.x -= this.speed;
		}
		else {
			this.isWalking = true;
			this.isFacingRight = true;
			this.x += this.speed;
		}
	}
	
	public void fight(int ticks) {
		this.walkTowards(game.player);
		if (this.playerPos.isNear(game.player.currentPos, 15)) {
			System.out.println("ARRESTED");
			game.gui.gameOverMessage = "YOU LOST";
			//game.setRunning(false);
			
		}
	}

	public int getPatroleType() {
		return patroleType;
	}

	public void setPatroleType(int patroleType) {
		this.patroleType = patroleType;
	}
	
	
	
}
