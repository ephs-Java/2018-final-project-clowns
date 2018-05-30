package engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;


import gfx.GUI;
import level.Camera;
import level.Level;
import people.Civilian;
import people.Player;
import people.Police;

public class Engine extends Canvas implements Runnable {

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 640;
	
	public JFrame window;
	public JLabel label;
	private boolean running = false;

	public ArrayList<Civilian> civilian;
	public Police[] police;
	public ArrayList<Bullet> bullets;
	
	public Graphics g;
	public Player player;
	public Collision col;
	public InputHandler input;
	public Level level;
	public GUI gui;
	public Camera camera;
	
	public int ticks, render;
	
	public BufferStrategy bs;

	
	public Engine() {

		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		window = new JFrame();
		window.setSize(WIDTH, HEIGHT);
		window.setTitle("Bank Robbbing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		
		bullets = new ArrayList<Bullet>();
		input = new InputHandler(this);
		player = new Player(this);
		col = new Collision(this);
		camera = new Camera(this);
		
		civilian = new ArrayList<Civilian>();
		police = new Police[10];
		level = new Level(this);
		gui = new GUI(this);
		
		bs = getBufferStrategy();	
		if (bs == null) {
			createBufferStrategy(3);
			bs = getBufferStrategy();
		}
		g = bs.getDrawGraphics();
		level.render(bs);
	}

	public static void main(String[] args) {
		new Engine().start();
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start(); 
	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		
		long lastTime = System.nanoTime();
		double nsecPerTick = 1000000000 / 60D; //  ticks per second as a double

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double deltaTime = 0;
		
	
		while (running) {
			long now = System.nanoTime();
			deltaTime += (now - lastTime) / nsecPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (deltaTime >= 1) {
				ticks++;
				tick(ticks);
				deltaTime -= 1;
				shouldRender = true;
			}

			if (shouldRender) {
				frames++;
				render(ticks);
			}
			
			/*
			 * THIS CONTROLS PER SECOND RATE
			 * RESETTING FRAMES AND TICKS AFTER 
			 * 1000ms HAS PASSED FROM THE BEGINNING OF THE
			 * LOOP
			 */
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("Frames: " + frames + " Ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}
	 
	public void tick(int ticks) {
		input.checkInput();
		camera.updateAlarm();
		gui.updateTime(ticks);
		
		//UPDATES MOVEMENT ANIMATIONS FOR EACH SPRITE
		if (level.levelStage != 0 && level.levelStage != 5) {
			for (int i = 0; i < civilian.size(); i ++)
				civilian.get(i).move(ticks);
		}
		if (level.levelStage == 0) {
			for (int i = 0; i < this.police.length; i ++)
				if (police[i] != null)
					police[i].move(ticks);
		}
		if (level.levelStage != 0 && level.levelStage != 5) {
			for (int i = 2; i < police.length; i ++)
				if (police[i] != null)
					police[i].move(ticks);
		}
		player.move(ticks);
		col.checkBulletCollision();
	}
 
	public void render(int ticks) {
		g = bs.getDrawGraphics();
		level.render(bs);
		gui.render(bs, g);
		player.render(bs, g);
		
		//RENDERS EVERY CIVILIAN IN THE CIVILIAN ARRAYLIST
		if (level.levelStage != 0 && level.levelStage != 5) {
			for (int i = 0; i < civilian.size(); i ++) 
				civilian.get(i).render(bs, g);
		}
		//RENDERS EVERY POLICE INSIDE THE POLICE ARRAY FOR THEIR RESPECTIVE LEVEL
		if (this.police.length > 0) {
			if (level.levelStage == 0) {
				for (int i = 0; i < 2; i ++) {
					if (police[i] != null)
						police[i].render(bs, g);
				}
			}
			else if (level.levelStage != 0 && level.levelStage != 5) {
				for (int i = 2; i < this.police.length; i ++) {
					if (police[i] != null)
						police[i].render(bs, g);
				}
			}
		}
		//RENDERS EVERY BULLET THAT ARE CURRENT IN THE BULLETS ARRAYLIST
		if (this.bullets.size() > 0) {
			for (int i = 0; i < this.bullets.size(); i ++) {
				this.bullets.get(i).render(bs, g);
			}
		}
		gui.debugMode(bs, g);
		bs.show();
		g.dispose();
		
	}
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * TODO:
	 * 	Trigger robbing mode - DONE
	 * 	Cops can arrest player (1) - DONE
	 * 	can shoot civilians but lose money - DONE
	 * 	Implement and use the alarm system with cameras (2) - KINDA DONE
	 * 	Other sounds
	 * 	Update graphics and level designs
	 * 	Civilians run away if Player is in robbing mode - KINDA DONE
	 * 	Better graphics interface
	 * 	Police response system - KINDA DONE
	 * BUGS: 
	 * 	INVISIBLE COPS IN FIRST STAGE - NOT FIXED
	 */

}