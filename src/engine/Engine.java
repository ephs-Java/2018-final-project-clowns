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
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Player player;
	
	public ArrayList<Civilian> civilian;
	public Police[] police;
	public ArrayList<Bullet> bullets;
	
	public Graphics g;
	
	public Collision col;
	public InputHandler input;
	public Level level;
	public GUI gui;
	public Camera camera;
	
	public int vTicks;
	
	public int x, y = 0;
	public int ticks, render;
	
	public BufferStrategy bs;
	public BufferStrategy bs_two;
	
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

//			try {
//				Thread.sleep(2);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

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
		if (camera.getcTicks() < 1000) {
			camera.isAlarm();
		} else {
			gui.alarmMessage = "ALARM! ALARM!";
			player.isRobbing = true;
		}
		//System.out.print(camera.isAlarm());
	   // System.out.print(camera.getcTicks());
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
		
		
		/*
		 * UPDATES EVERY BULLET'S POSITION THAT ARE IN THE ARRAYLIST
		 * AND ALSO CHECKS FOR IF THE BULLET HAS HIT A POLICE IN 
		 * THE POLICE ARRAY
		 * IF SO, THE POLICE AND THE BULLET GETS REMOVE FROM THEIR
		 * ARRAYLIST
		 */
		if (this.bullets.size() > 0) {
			for (int i = 0; i < this.bullets.size(); i ++) {
				boolean flag = false;
				this.bullets.get(i).updatePos();
				if (police.length > 0 && bullets.size() > 0) {
					for (int j = 0; j < police.length; j ++) {
						if (police[j] != null && bullets.get(i).bulletPos.isNear(police[j].playerPos, 20)) {
							bullets.remove(i);
							police[j] = null;
							//System.out.println("died");
							player.isRobbing = true;
							i--;
							flag = true;
							break;
						}
					}
					if (!flag && (level.levelStage != 0 && level.levelStage != 5)) {
						for (int k = 0; k < civilian.size(); k ++) {
							if (bullets.get(i).bulletPos.isNear(civilian.get(k).playerPos, 20)) {
								bullets.remove(i);
								civilian.remove(k);
								player.money -= 5000;
								player.isRobbing = true;
								k--;
								i--;
								flag = true;
								break;
							}
						}
					}
				}
				if (!flag) {
					if (this.bullets.get(i).isBulletFinished()) {
						this.bullets.remove(i);
						i--;
					}
				}			
			}
		}	
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
		//SHOWS CURRENT PLAYER COORDINATES ON SCREEN
		gui.debugMode(bs, g);
		bs.show();
		g.dispose();
		
	}

	/**
	 * TODO:
	 * 	Trigger robbing mode
	 * 	Cops can arrest player (1)
	 * 	can shoot civilians but lose money
	 * 	Implement and use the alarm system with cameras (2)
	 * 	Other sounds
	 * 	Update graphics and level designs
	 * 	Civilians run away if Player is in robbing mode
	 * 	Better graphics interface
	 * 	Police response system
	 * BUGS: 
	 * 	INVISIBLE COPS IN FIRST STAGE
	 */

}