package engine;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import level.Point;

public class Bullet {
		
	public Point bulletPos;
	public Point targetPos;
	
	public int velX, velY;

	public Bullet(Point playerPos, Point mouseClickPos) {
		this.bulletPos = playerPos;
		this.targetPos = mouseClickPos;

		this.velX = (mouseClickPos.x - playerPos.x) / 10;
		this.velY = (mouseClickPos.y - playerPos.y) / 10;

	}
	
	public void updatePos() {
		this.bulletPos.x += velX;
		this.bulletPos.y += velY;
	}
	
	public void render(BufferStrategy bs, Graphics g) {
		g.fillRect(this.bulletPos.x, this.bulletPos.y, 5, 5);
	}
	
	public boolean isBulletFinished() {
		if (this.bulletPos.x > 1024
			|| this.bulletPos.x < 0
			|| this.bulletPos.y < 0
			|| this.bulletPos.y > 640) {
			return true;
		} else {
			return false;
		}
	} 
}
