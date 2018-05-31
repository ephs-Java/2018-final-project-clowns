package gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import engine.Engine;


public class GUI {

	private Engine game;
	public JLabel debugLabel_1;
	private SpriteSheet gui_sheet;
	public BufferedImage gui_image;
	public String timerState;
	public String message1;
	public String message2;
	public String message3;
	public String message4;
	public String message5;
	public String message6;
	public String alarmMessage;
	public String gameOverMessage;
	
	private int minutes;
	public int prev_sec;
	public int seconds;
	
	public GUI (Engine g) {
		game = g;
		this.minutes = 0;
		this.seconds = 0;
		this.prev_sec = 0;
		this.timerState = "Start";
		this.message1 = "";
		this.message2 = "";
		this.message6 = "BUG: invisible cops on first level.";
		//this.message3 = "";
		this.message3 = "You are a robber about to rob a bank";
        this.message4 = "You have a gun and watch out for the cops";
        this.message5 = "You have to find the keycard, get the money, and get out";
		this.alarmMessage = "ALARM:";
		this.gameOverMessage = "";
	}
	
	public void debugMode(BufferStrategy bs, Graphics g) {
		g.drawString("Pos: (" + game.player.x + ", " + game.player.y + ")" , 300, 24);
		//g.drawString("Pos: (" + game.police[0].playerPos.x + ", " + game.police[0].playerPos.y + ")" , 300, 24);
	}
	
	public void render(BufferStrategy bs, Graphics g) {
		Graphics topGui = bs.getDrawGraphics();
		
		topGui.drawImage(gui_image, 0, 0, null);
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		if (game.player.money < 0) 
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.drawString("$" + game.player.money, 25, 24);
		
		
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		g.setColor(Color.WHITE);
		g.drawString(minutes + "m : " + seconds + "s", 175 , 24);
		topGui.setFont(new Font("Arial", Font.BOLD, 24));
		topGui.setColor(Color.WHITE);
		topGui.drawString(message1, 18 , 625);
		topGui.setFont(new Font("Arial", Font.BOLD, 16));
		topGui.drawString(message2, 813 , 330);
		topGui.drawString(message3, 300, 360);
	    topGui.drawString(message4, 300, 380);
	    topGui.drawString(message5, 300, 400);
	    topGui.drawString(message6, 300, 420);
		topGui.setFont(new Font("Arial", Font.BOLD, 80));
		topGui.setColor(Color.black);
		topGui.drawString(gameOverMessage, 250 , 300);
		
		showAlarm(topGui);
		
		
//		time.dispose();
//		topGui.dispose();
		
	}
	
	public void showAlarm(Graphics topGui) {
		topGui.setFont(new Font("Arial", Font.BOLD, 16));
		topGui.setColor(Color.RED);
		double percent = game.camera.getcTicks();
		topGui.drawString(alarmMessage + " " + percent, 800, 24);
	}
	
	public void updateTime(int ticks) {
		/*
		 * TIME SHOULD START WHEN YOU ENTER THE BANK
		 * STOP WHEN YOU EXIT
		 */
		if (ticks == 58 && gameOverMessage.equals("")) {
			seconds++;
		}
		if (seconds == 60) {
			minutes ++;
			seconds = 0;
		}
		if(seconds == 10) {
	        message3 = "";
	        message4 = "";
	        message5 = "";
	        message6 = "";
	    }
	}
	
	
}
