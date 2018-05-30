package level;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import engine.Engine;
import gfx.SpriteSheet;
import people.Civilian;
import people.Police;

public class Level {
	
	private Engine game;
	//public TileGrid tileGrid;
	
	public int levelStage;
	
	private SpriteSheet lvl1s0;
	private BufferedImage lvl1s0_image;
	
	private SpriteSheet lvl1s1;
	public BufferedImage lvl1s1_image;
	
	private SpriteSheet lvl1s2;
	public BufferedImage lvl1s2_image;
	
	private SpriteSheet lvl1s25;
	private BufferedImage lvl1s25_image;
	
	private SpriteSheet lvl1s3;
	public BufferedImage lvl1s3_image;
	
	private SpriteSheet lvl1s4;
	public BufferedImage lvl1s4_image;
	
	private SpriteSheet lvl1s5;
	public BufferedImage lvl1s5_image;
	
	public SpriteSheet moneyBag_sprite;
	public BufferedImage moneyBag_image;
	
	public Point keyCardPos;
	public SpriteSheet keyCard_sprite;
	public BufferedImage keyCard_image;
	
	public SpriteSheet keyCardBig_sprite;
	public BufferedImage keyCardBig_image;
	
	public ArrayList<Point> moneyLocationList;
	public ArrayList<Point> civilianLocationList;
	public ArrayList<Point> policeLocationList;
	
	private Random r;
	
	public Level(Engine e) {
		game = e;
		
		levelStage = 0;
		lvl1s0 = new SpriteSheet("level_1.png");
		lvl1s0_image = lvl1s0.getSprite(0, 0, 1024, 640);
		
		lvl1s1 = new SpriteSheet("lvl1s1.png");
		lvl1s1_image = lvl1s1.getSprite(0, 0, 1024, 640);
		
		lvl1s2 = new SpriteSheet("lvl1s2.png");
		lvl1s2_image = lvl1s2.getSprite(0, 0, 1024, 640);
		
		lvl1s25 = new SpriteSheet("lvl1s25.png");
		lvl1s25_image = lvl1s25.getSprite(0, 0, 1024, 640);
		
		lvl1s3 = new SpriteSheet("lvl1s3.png");
		lvl1s3_image = lvl1s3.getSprite(0, 0, 1024, 640);
		
		lvl1s4 = new SpriteSheet("lvl1s4.png");
		lvl1s4_image = lvl1s4.getSprite(0, 0, 1024, 640);
		
		lvl1s5 = new SpriteSheet("lvl1s5.png");
		lvl1s5_image = lvl1s5.getSprite(0, 0, 1024, 640);
		
		keyCardPos = new Point(257, 200);
		keyCard_sprite = new SpriteSheet("keyCard.png");
		keyCard_image = keyCard_sprite.getSprite(0, 0, 24, 16);
		
		keyCardBig_sprite = new SpriteSheet("keyCard_big.png");
		keyCardBig_image = keyCardBig_sprite.getSprite(0, 0, 48, 32);
		
		moneyBag_sprite = new SpriteSheet("money.png"); 
		moneyBag_image = moneyBag_sprite.getSprite(0, 0, 64, 64);
		moneyLocationList = new ArrayList<Point>();
		
		
		civilianLocationList = new ArrayList<Point>();
		policeLocationList = new ArrayList<Point>();
		fillLists();
		
		for (int i = 0; i < 4; i ++) 
			game.civilian.add(new Civilian(game, civilianLocationList.get(i).x, civilianLocationList.get(i).y));
		
		for (int i = 0; i < 3; i ++) 
			game.police[i] = new Police(game, policeLocationList.get(i).x, policeLocationList.get(i).y);
		
		game.police[0].setPatroleType(0);
		game.police[1].setPatroleType(1);
		game.police[2].setPatroleType(2);
		
		r = new Random();
		
	}

	public void render(BufferStrategy bs) {
		
		Graphics tileGridBackground = bs.getDrawGraphics();
		Graphics money = bs.getDrawGraphics();
		if (this.levelStage == 0)
			tileGridBackground.drawImage(lvl1s0_image, 0, 0, null);
		else if (this.levelStage == 1)
			tileGridBackground.drawImage(lvl1s1_image, 0, 0, null);
		else if (this.levelStage == 2)
			tileGridBackground.drawImage(lvl1s2_image, 0, 0, null);
		else if (this.levelStage == 25)
			tileGridBackground.drawImage(lvl1s25_image, 0, 0, null);
		else if (this.levelStage == 3)
			tileGridBackground.drawImage(lvl1s3_image, 0, 0, null);
		else if (this.levelStage == 4)
			tileGridBackground.drawImage(lvl1s4_image, 0, 0, null);
		else 
			tileGridBackground.drawImage(lvl1s5_image, 0, 0, null);
		
		/*
		 * Renders the keycard in the correct position.
		 * Once the player aquires the keycard, the position 
		 * is changed to show the player has posession of the card.
		 */
		if (!game.player.hasKeyCard && (this.levelStage == 3 || this.levelStage == 4))
			money.drawImage(keyCard_image, 257, 200, null);
		 else if (game.player.hasKeyCard)
			money.drawImage(keyCardBig_image, 943, 585, null);
		
		
		
		
		/*
		 * Loads all the bags of money that still exist inside the moneyLocationList
		 * only if the current level stage is 5
		 */
		if (this.levelStage == 5) {
			for (int i = 0; i < moneyLocationList.size(); i ++) 
				money.drawImage(moneyBag_image, moneyLocationList.get(i).x, moneyLocationList.get(i).y, null);
		}
		
		tileGridBackground.dispose();
	}
	
	public void fillLists() {
		
		moneyLocationList.add(new Point(570, 260));
		moneyLocationList.add(new Point(691, 260));
		moneyLocationList.add(new Point(800, 260));
		moneyLocationList.add(new Point(815, 220));
		moneyLocationList.add(new Point(917, 248));
		moneyLocationList.add(new Point(642, 227));
		moneyLocationList.add(new Point(248, 552));
		moneyLocationList.add(new Point(322, 513));
		moneyLocationList.add(new Point(467, 495));
		moneyLocationList.add(new Point(622, 538));
		moneyLocationList.add(new Point(463, 558));
		moneyLocationList.add(new Point(120, 540));
		moneyLocationList.add(new Point(55, 510));
		
		civilianLocationList.add(new Point(209, 450));
		civilianLocationList.add(new Point(332, 450));
		civilianLocationList.add(new Point(464, 450));
		civilianLocationList.add(new Point(592, 450));
		
		policeLocationList.add(new Point(420, 350));
		policeLocationList.add(new Point(520, 350));
		policeLocationList.add(new Point(100, 520));
			
	}
	
	public void randomPoliceSpawn() {
		int w = r.nextInt(game.WIDTH - 100);
		int h = r.nextInt(game.HEIGHT - 150);
		int index = r.nextInt(game.police.length - 3) + 3;
		
		if (game.police[index] == null) {
			game.police[index] = new Police(game, w, h);
		}
	}
	
	
	
	
}
