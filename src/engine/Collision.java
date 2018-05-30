package engine;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import level.Point;
import gfx.SpriteSheet;
 
public class Collision { // 64 31
    private Engine game;
    public Point adjustedHitbox;
    
    public Collision(Engine e) {
        game = e;
    }
    
    /*
     * COLLISION CHECK FOR STAGE 0
     */
    public void checkColUp_0() {
        int upy = 4 * 64 + 32;
        int upx2 = 11 * 64;
        int enter1 = 7 * 64 + 32;
        int enter2 = 8 * 64 + 32;
        if (game.player.y == 0 || ((game.player.y == upy && game.player.x >= 0 && game.player.x < enter1)
                || (game.player.y == upy && game.player.x + 17 > enter2 && game.player.x < upx2))) {
            game.player.x += 0;
            game.player.y += 0;
        } else if (game.player.y == upy && game.player.x > enter1 && game.player.x + 17 < enter2) {
            game.level.levelStage = 2;
            game.player.x = 733;
            game.player.y = 623;
        } else {
            game.player.x += 0;
            game.player.y -= game.player.speed;
        }
    }
 
    public void checkColLeft_0() {
        int leftx = 11 * 64;
        int lefty2 = 4 * 64 + 32;
        if (game.player.x == 0 || (game.player.x == leftx && game.player.y >= 0 && game.player.y < lefty2)) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
            game.player.x -= game.player.speed;
            game.player.y += 0;
        }
    }
 
    public void checkColRight_0() {
        if (game.player.x == 1000) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
            game.player.x += game.player.speed;
            game.player.y += 0;
        }
    }
 
    public void checkColDown_0() {
        if (game.player.y + 56 == 640) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
            game.player.x += 0;
            game.player.y += game.player.speed;
        }
    }
    /*
     * END OF COLLISION CHECK FOR STAGE 0
     */
    
    
    /*
     * COLLISION CHECK FOR STAGE 2
     */
    public void checkColUp_2() {
        if(((game.player.x < 579 && game.player.y == 289) || (game.player.x > 623 && game.player.x < 703 && game.player.y == 289)) || (game.player.y == 449 && game.player.x > 117 && game.player.x < 703)) {
            game.player.x += 0;
            game.player.y += 0;
        }  else if(game.player.y == 289 && game.player.x > 579 && game.player.x < 623) {
        	//INTO THE OFFICE ROOM
            game.level.levelStage = 3;
            game.player.isRobbing = true;
        }  else if(game.player.y == 257 && game.player.x >= 703 && game.player.x <= 769) {
        	//HALLWAY BETWEEN BREAK ROOM AND VAULT
            game.level.levelStage = 4;
            game.player.isRobbing = true;
        } else {
            game.player.x += 0;
            game.player.y -= game.player.speed;
        }
    }
 
    public void checkColLeft_2() {
        if(game.player.x == 31 || (game.player.x == 703 && game.player.y > 422-64 && game.player.y < 446)) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
            game.player.x -= game.player.speed;
            game.player.y += 0;
        }
    }
 
    public void checkColRight_2() {
        if(game.player.x + 21 == 772 || (game.player.x == 113 && game.player.y > 424-64 && game.player.y < 444)) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
            game.player.x += game.player.speed;
            game.player.y += 0;
        }
    }
 
    public void checkColDown_2() {
        if((game.player.y + 64 == 619 && game.player.x < 704) || (game.player.y + 64 == 425 && game.player.x > 117 && game.player.x < 703)) {
            game.player.x += 0;
            game.player.y += 0;
        } else if(game.player.y == 611 && game.player.x >= 705 && game.player.x <= 769) {
            game.level.levelStage = 0;
            game.player.x = 490;
            game.player.y = 300;
        }else {
            game.player.x += 0;
            game.player.y += game.player.speed;
        }
    }
    /*
     * END OF COLLISION CHECK FOR STAGE 2
     */
    
    /*
     * COLLISION CHECK FOR STAGE 3
     */
    public void checkColUp_3() {
        if(game.player.y == 31 || (game.player.y == 191 && game.player.x > 113 && game.player.x < 257)
                || (game.player.y == 191 && game.player.x > 320 && game.player.x < 369) || (game.player.y == 191 && game.player.x > 431 && game.player.x < 481)) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x += 0;
        game.player.y -= game.player.speed;
        }
    }
    public void checkColLeft_3() {
        if(game.player.x == 31 || (game.player.x == 575 && game.player.y  > 105 && game.player.y < 285) || (game.player.x == 163 && game.player.y >= 29 && game.player.y <= 61)
                || (game.player.x == 163 && game.player.y + 64 > 131 && game.player.y + 64 <= 169) || (game.player.x == 243 && game.player.y > 189) || (game.player.x == 349 && game.player.y > 189) ||
                (game.player.x == 463 && game.player.y > 189)) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x -= game.player.speed;
        game.player.y += 0;
        }
    }
    public void checkColRight_3() {
        if(game.player.x == 657 || (game.player.x == 117 && game.player.y >= 29 && game.player.y <= 61) || (game.player.x == 117 && game.player.y + 64 > 131 && game.player.y <= 187)
                || (game.player.x == 197 && game.player.y > 189 && game.player.y < 257) || (game.player.x == 307 && game.player.y > 189 && game.player.y < 257)
                ||(game.player.x == 415 && game.player.y > 189 && game.player.y < 257) || (game.player.x == 527 && game.player.y > 189 && game.player.y < 257)) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x += game.player.speed;
        game.player.y += 0;
        }
    }
    public void checkColDown_3() {
        if(game.player.y + 64 == 263 && game.player.x < 573 || (game.player.x > 623 && game.player.x < 703 && game.player.y + 64 == 263) ||
                (game.player.y + 64 == 167 && game.player.x > 133 && game.player.x < 251) || (game.player.x > 527 && game.player.x < 573 && game.player.y + 64 == 167) || 
                (game.player.y + 64 == 167 && game.player.x > 305 && game.player.x < 365) || (game.player.y + 64== 167 && game.player.x > 417 && game.player.x < 475)) {
            game.player.x += 0;
            game.player.y += 0;
        } else if(game.player.y == 259 && game.player.x > 579 && game.player.x < 639) {
            game.level.levelStage = 2;
        } else {
        game.player.x += 0;
        game.player.y += game.player.speed;
        }
    }
    /*
     * END OF COLLISION CHECK FOR STAGE 3
     */
    
    /**
     * COLLISION CHECK FOR STAGE 4
     */    
    public void checkColUp_4() {
    	
        if(game.player.y == 1 && game.player.x > 705) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x += 0;
        game.player.y -= game.player.speed;
        }
    }
    public void checkColLeft_4() {
    	game.gui.message2 = "";
        if(game.player.x == 703 && game.player.y >=1) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x -= game.player.speed;
        game.player.y += 0;
        }
    }
    public void checkColRight_4() {
    	
    	//CHECKING THE MOST RIGHT WALL 
        if(game.player.x == 755 && game.player.y >= 1 && game.player.y < 61 || (game.player.x == 755 && game.player.y + 64 > 135)) {
            game.player.x += 0;
            game.player.y += 0;
        } else if(game.player.x  > 978 && game.player.y + 64 < 135) {
        	//CHANGES THE STAGE TO STAGE 5: THE VAULT
        	if (game.player.hasKeyCard) {
        		 game.level.levelStage = 5;
                 game.player.x = 30;
                 game.player.y = 104;
        	} else {
        		game.gui.message2 = "No keycard, cannot go";
        	}
           
        }
//        } else if (game.player.x == 55 && game.player.hasKeyCard){
//        	game.player.x += 2;
//        	game.player.y += 0;
//        }
        else {
        	game.player.x += game.player.speed;
        	game.player.y += 0;
        }
        
//        if (game.player.x == 755 && (game.player.y > 65 || game.player.y < 130)) {
//        	System.out.println("need keycarc");
//        }
    }
    public void checkColDown_4() {
    	
        if(game.player.y == 257 && game.player.x >= 703 && game.player.x <= 769) {
            game.level.levelStage = 2;
        } else {
        game.player.x += 0;
        game.player.y += game.player.speed;
        }
    }
    /**
     * END OF COLLISION CHECK FOR STAGE 4
     */
    
    /*
     * COLLISION CHECK FOR STAGE 5
     */
    public void checkColUp_5() {
        if(game.player.y == 32 || (game.player.y == 352 && game.player.x > 494) || (game.player.y == 194 && game.player.x > 512 && game.player.x < 624)
                || (game.player.y == 192 && game.player.x >690 && game.player.x < 848) || (game.player.y == 476 && game.player.x < 92) || (game.player.y == 476 && game.player.x > 146 && game.player.x < 284) 
                || (game.player.y == 476 && game.player.x > 340 && game.player.x < 474) || (game.player.y == 476 && game.player.x > 532 && game.player.x < 670)
                || (game.player.y == 476 && game.player.x > 724 && game.player.x < 862) || game.player.y == 476 && game.player.x > 916) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x += 0;
        game.player.y -= game.player.speed;
        }
    }
    public void checkColLeft_5() {
        if((game.player.x == 32 && game.player.y < 96) || (game.player.x == 32 && game.player.y > 164) || (game.player.x == 562 && game.player.y > 176 && game.player.y + 64 < 336) || (game.player.x == 48 && game.player.y > 470)
                || (game.player.x == 224 && game.player.y > 470) || (game.player.x == 418 && game.player.y > 470) || (game.player.x == 608 && game.player.y > 470) ||
                (game.player.x == 800 && game.player.y > 470) || (game.player.x == 784 && game.player.y > 178 &&game.player.y < 320)) {
            game.player.x += 0;
            game.player.y += 0;
        } else if(game.player.x == 32 && game.player.y > 96 && game.player.y < 164) { 
        	game.player.x = 913;
            game.player.y = 93;
            game.level.levelStage = 4;
            
        } else {
        game.player.x -= game.player.speed;
        game.player.y += 0;
        }
    }
    public void checkColRight_5() {
        if(game.player.x == 976 || (game.player.x == 494 && game.player.y + 64 > 180 && game.player.y < 350) || (game.player.x == 194 && game.player.y > 470) 
                || (game.player.x == 388 && game.player.y > 470) || (game.player.x == 578 && game.player.y > 470) ||
                (game.player.x == 770 && game.player.y > 470) || (game.player.x == 490 && game.player.y > 470) || (game.player.x == 754 && game.player.y > 178 &&game.player.y < 320)) {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x += game.player.speed;
        game.player.y += 0;
        }
    }
    public void checkColDown_5() {
        if(game.player.y + 64 == 610 ||(game.player.y + 64 == 328 && game.player.x > 522) || (game.player.y + 64 == 182 && game.player.x > 512 && game.player.x < 624)
                || (game.player.y + 64 == 182 && game.player.x > 914) || (game.player.y == 402 && game.player.x < 92) || (game.player.y == 402 && game.player.x > 146 && game.player.x < 284) 
                || (game.player.y == 402 && game.player.x > 340 && game.player.x < 474) || (game.player.y == 402 && game.player.x > 532 && game.player.x < 670)
                || (game.player.y == 402 && game.player.x > 724 && game.player.x < 862) || (game.player.y == 402 && game.player.x > 916) || (game.player.y + 64 == 182 && game.player.x > 692 && game.player.x < 848))  {
            game.player.x += 0;
            game.player.y += 0;
        } else {
        game.player.x += 0;
        game.player.y += game.player.speed;
        }
    }
    
    /*
	 * UPDATES EVERY BULLET'S POSITION THAT ARE IN THE ARRAYLIST
	 * AND ALSO CHECKS FOR IF THE BULLET HAS HIT A POLICE IN 
	 * THE POLICE ARRAY
	 * IF SO, THE POLICE AND THE BULLET GETS REMOVE FROM THEIR
	 * ARRAYLIST
	 */  
    public void checkBulletCollision() {
		if (game.bullets.size() > 0) {
			for (int i = 0; i < game.bullets.size(); i ++) {
				boolean flag = false;
				game.bullets.get(i).updatePos();
				if (game.police.length > 0 && game.bullets.size() > 0) {
					for (int j = 0; j < game.police.length; j ++) {
						if (game.police[j] != null) 
							adjustedHitbox = new Point(game.police[j].playerPos.x + 6, game.police[j].playerPos.y + 23 );
						if (game.police[j] != null && game.bullets.get(i).bulletPos.isNear(adjustedHitbox, 20)) {
							game.bullets.remove(i);
							game.police[j] = null;
							game.player.isRobbing = true;
							i--;
							flag = true;
							break;
						}
					}
					if (!flag && (game.level.levelStage != 0 && game.level.levelStage != 5)) {
						for (int k = 0; k < game.civilian.size(); k ++) {
							if (game.bullets.get(i).bulletPos.isNear(game.civilian.get(k).playerPos, 20)) {
								game.bullets.remove(i);
								game.civilian.remove(k);
								game.player.money -= 5000;
								game.player.isRobbing = true;
								k--;
								i--;
								flag = true;
								break;
							}
						}
					}
				}
				if (!flag) {
					if (game.bullets.get(i).isBulletFinished()) {
						game.bullets.remove(i);
						i--;
					}
				}			
			}
		}
    }
    
    
    
    
    /*
     * END OF COLLISION CHECK FOR STAGE 5
     */
}
 
    /*
     * All if statements so it can handle
     * diagonal movement.
     */
    