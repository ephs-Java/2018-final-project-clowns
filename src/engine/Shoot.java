package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import level.Point;
import people.People;

public class Shoot {

	private Engine game;
	
	public double bullet_x, bullet_y;
	public double dx;
	public double dy;
	public Rectangle bullet;

	public boolean canShoot;
	public boolean shot;
	public People person;
	
	public Point mouseClickPos;
	
	public Shoot(Engine g) {
		this.game = g;
		this.dx = 0;
		this.dy = 0;
	}
	
	public void paintComponent(Graphics g) {
		 //Graphics g = new Graphics();
			g.fillRect(person.x, person.y, 100, 100);
			if (shot) {
				g.setColor(Color.BLACK);
				g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
			}
	    }
	
//	public void render(People person) {
//	//Graphics g = new Graphics();
//	g.fillRect(person.x, person.y, 100, 100);
//	if (shot) {
//		g.setColor(Color.BLACK);
//		g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
//	}
//}

}
