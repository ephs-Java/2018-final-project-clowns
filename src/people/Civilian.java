package people;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import engine.Engine;

public class Civilian extends People {
	
	private Engine game;
	public Graphics g1;
	public BufferedImage civilianSprite;
	
	public Civilian(Engine g, int x, int y) {
		super(x, y, 100, 4, false, true, true, "Civilian.png");
		game = g;
		this.civilianSprite = this.spriteSheet.getSprite( 0, 114, 12, 57);
	}
	
	public void move(int ticks) {	
		int[] faceRight = {0, 114, 12, 57};
		int[] walkRight = {27, 114, 18, 57};
		
		int[] faceLeft = {0, 56, 16, 57};
		int[] walkLeft = {36, 56, 16, 57};
		this.playerPos.x = this.x;
		this.playerPos.y = this.y;
		this.walkAway(game.player);
		civilianSprite = this.walk(faceRight, faceLeft, walkRight, walkLeft, ticks);	
	}

	public void render(BufferStrategy bs, Graphics g) {
		g.drawImage(civilianSprite, this.x, this.y, null);	
	}	
}
