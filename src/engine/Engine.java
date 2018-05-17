package engine;

import java.awt.Component;

import javax.swing.JFrame;


import window.Move;

public class Engine {
	//CHECK
	/*
	 * SOURCE: https://www.ntu.edu.sg/home/ehchua/programming/java/J8d_Game_Framework.html
	 */
	//static final int UPDATES_PER_SEC = 4; //updates per second
	//static final int UPDATES_PER_NSEC = (int) (1000000000L / UPDATES_PER_SEC); //updates per nanosecond
	
	static boolean gameOver = false;
	
	public static void main(String[] args) {
		initializeGame();

	}
	
	public static void initializeGame () {
		/*
		 * Creates window and creates a move object.
		 */
		JFrame window = new JFrame();
		window.setSize(1440, 900);
		window.setTitle("Bank Rob");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Move move = new Move();
		
		window.add(move);
		//window.add(b);
		window.setVisible(true);
		
		
		
		long beginTime, timeTaken, timeLeft;
		
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
	}
	
	public void run() {
		
	}
	
	public void update() {
		
		
	}
	
	
	

}
