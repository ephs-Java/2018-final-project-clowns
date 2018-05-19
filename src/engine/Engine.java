package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Engine extends Canvas implements Runnable{
	
	//12*9 window aspect ratio
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	//public static final int SCALE = 3;
	
	private JFrame window;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int tickCount;
	
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
		double nsecPerTick = 1000000000/60D; //1 billion per tick per second as a double
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double deltaTime = 0;
		
		
		while(running) {
			long now = System.nanoTime();
			deltaTime += (now  - lastTime) / nsecPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while (deltaTime >= 1) {
				ticks++;
				tick();
				deltaTime-= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (shouldRender) {
				frames++;
				render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("Frames: " + frames + " Ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick() {
		tickCount++;
		for (int i = 0; i < pixels.length; i ++) {
			pixels[i] = i + tickCount;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image, 0, 0, null);
		
		g.dispose();
		bs.show();
	}
	
	
//	public static void initializeGame () {
//		/*
//		 * Creates window and creates a move object.
//		 */
//		JFrame window = new JFrame();
//		window.setSize(1440, 900);
//		window.setTitle("Bank Rob");
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Move move = new Move();
//		
//		window.add(move);
//		//window.add(b);
//		window.setVisible(true);
//		
//		
//		
//		long beginTime, timeTaken, timeLeft;
		
//		while(!gameOver) {
//			beginTime = System.nanoTime();
//			
//			timeTaken = System.nanoTime() - beginTime;
//			
//			timeLeft = (UPDATES_PER_NSEC - timeTaken) / 1000000;
//			
//			if (timeLeft > 10)
//				timeLeft = 10;
//			try {
//		         Thread.sleep(timeLeft);
//		      } catch (InterruptedException ex) { }
////			System.out.println("beginTime: " + beginTime);
////			System.out.println("timeTaken: " + timeTaken);
////			System.out.println("timeLeft: " + timeLeft);
////			System.out.println("");
//			
//		}
	//}
	
	
	public void update() {
		
		
	}
	
	
	

}
