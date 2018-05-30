package gfx;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	BufferedImage spriteSheet;
	
	/*
	 * CONSTRUCTOR = SPREADSHEET READER
	 * TRY AND CATCH READS THE FULL SPRITESHEET IMAGE FILE
	 * AS A BUFFERED IMAGE AND A NEW SpriteSheet 
	 * OBJECT GETS DECLARED WITH THE PREVIOUSLY READ
	 * BUFFEREDIMAGE SPRITESHEET.
	 * 
	 * SpriteSheet OBJECT ALLOWS FOR THE USE OF THE
	 * getSprite(x, y, w, h) METHOD WHICH WILL RETURN A 
	 * SMALLER BUFFEREDIMAGE OF THE SPRITES INSIDE THE 
	 * FULL SPRITESHEET WITH THE GIVEN COORDINATE VALUES
	 * 
	 */
	
	public SpriteSheet(String path) {
		this.spriteSheet = null;
		try {
			this.spriteSheet = ImageIO.read(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param x
	 * 	Starting X value on the image file where the user
	 *  wants to read from.
	 * @param y
	 *  Starting Y value on the image file where the user
	 *  wants to read from.
	 * @param width
	 *  From the starting X value, how long is the single sprite image
	 *  in the spritesheet file.
	 * @param height
	 *  From the starting Y value, how down below is the single sprite image
	 *  in the spritesheet file.
	 * @return
	 *  returns the sub-sprite image in the spritesheet file as a
	 *  BufferedImage
	 */
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		BufferedImage sprite = this.spriteSheet.getSubimage(x, y, width, height);
		return sprite;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
