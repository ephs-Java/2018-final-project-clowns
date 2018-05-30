package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import level.Point;

public class InputHandler implements KeyListener, MouseListener {

	public boolean facingRight;
	public boolean isWalking;
	public Point mouseClickPos;
    public Point playerPos;
    public Point slopePoint;
    public Point cMoneyLoc;
    public Point vaultLoc;
    public int index;
	
	

	private Engine game;
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key space = new Key();
	public Key shift = new Key();

	public InputHandler(Engine e) {
		game = e;
		this.facingRight = true;
		this.isWalking = false;
		game.addKeyListener(this);
		game.addMouseListener(this);

	}

	public void keyPressed(KeyEvent e) {

		toggleKey(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		game.player.isWalking = false;
		toggleKey(e.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void checkInput() {
		if (this.up.isPressed()) {
			// System.out.println("UP");
			if (game.level.levelStage == 0) {
				game.col.checkColUp_0();
			} else if (game.level.levelStage == 2) {
				game.col.checkColUp_2();
			} else if (game.level.levelStage == 3) {
				game.col.checkColUp_3();
			} else if (game.level.levelStage == 4) {
				game.col.checkColUp_4();
			} else if (game.level.levelStage == 5) {
				game.col.checkColUp_5();
			}
		}
		if (this.down.isPressed()) {
			if (game.level.levelStage == 0) {
				game.col.checkColDown_0();
			} else if (game.level.levelStage == 2) {
				game.col.checkColDown_2();
			} else if (game.level.levelStage == 3) {
				game.col.checkColDown_3();
			} else if (game.level.levelStage == 4) {
				game.col.checkColDown_4();
			} else if (game.level.levelStage == 5) {
				game.col.checkColDown_5();
			}
		}
		if (this.left.isPressed()) {
			if (game.level.levelStage == 0) {
				game.col.checkColLeft_0();
			} else if (game.level.levelStage == 2) {
				game.col.checkColLeft_2();
			} else if (game.level.levelStage == 3) {
				game.col.checkColLeft_3();
			} else if (game.level.levelStage == 4) {
				game.col.checkColLeft_4();
			} else if (game.level.levelStage == 5) {
				game.col.checkColLeft_5();
			}
		}
		if (this.right.isPressed()) {
			if (game.level.levelStage == 0) {
				game.col.checkColRight_0();
			} else if (game.level.levelStage == 2) {
				game.col.checkColRight_2();
			} else if (game.level.levelStage == 3) {
				game.col.checkColRight_3();
			} else if (game.level.levelStage == 4) {
				game.col.checkColRight_4();
			} else if (game.level.levelStage == 5) {
				game.col.checkColRight_5();
			}
		}
		
		if (this.shift.isPressed()) {
			
			game.player.canShoot = false;
			isMoney(getMouseClickPos().getX(), getMouseClickPos().getY());
		} else {
			game.player.canShoot = true;
		}
		
	}

	public void toggleKey(int keyCode, boolean state) {

		if (keyCode == KeyEvent.VK_W) {
			changeWalk(state);
			this.up.toggle(state);
		}
		if (keyCode == KeyEvent.VK_S) {
			changeWalk(state);
			this.down.toggle(state);
		}
		if (keyCode == KeyEvent.VK_A) {
			changeWalk(state);
			this.left.toggle(state);
			game.player.isFacingRight = false;
		}
		if (keyCode == KeyEvent.VK_D) {
			changeWalk(state);
			this.right.toggle(state);
			game.player.isFacingRight = true;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			this.space.toggle(state);
		}
		if (keyCode == KeyEvent.VK_SHIFT) {
			this.shift.toggle(state);
		}
	}

	public void changeWalk(boolean state) {
		if (state)
			game.player.isWalking = true;
	}

	public void mouseClicked(MouseEvent e) {
		mouseClickPos = new Point(e.getX(), e.getY());
		playerPos = new Point(game.player.x, game.player.y);
		isVault(e.getX(), e.getY());
		isMoney(getMouseClickPos().getX(), getMouseClickPos().getY());
		System.out.println("Mouse Clicked at: " + e.getX() + ", " + e.getY());
		
		if (game.level.levelStage == 3) {
			if (game.level.keyCardPos.isNear(mouseClickPos, 30) && game.level.keyCardPos.isNear(playerPos, 30) ) {
				game.player.hasKeyCard = true;
			}
		}
		
		/*
		 * PLAYER ONLY ALLOWED TO SHOOT ONCE PER SECOND
		 */
		if (!this.shift.isPressed()) {
			if (Math.abs(game.gui.seconds - game.gui.prev_sec) >= 1) {
				game.bullets.add(new Bullet(playerPos, mouseClickPos));
				game.gui.prev_sec = game.gui.seconds;
			}
		}
		game.level.randomPoliceSpawn();
	}

	public boolean isVault(int x, int y) {
		if (game.level.levelStage == 4) {
			vaultLoc = new Point(992, 157);
			if ((vaultLoc.getX() - x) < 100 && (vaultLoc.getX() - x) > -100 && (vaultLoc.getY() - y) < 100
					&& (vaultLoc.getY() - y) > -100 && (vaultLoc.getX() - game.player.x) < 150
					&& (vaultLoc.getX() - game.player.x) > -150 && (vaultLoc.getY() - game.player.y) < 150
					&& (vaultLoc.getY() - game.player.y) > -150) {
				return true;
			}
		}
		return false;
	}

	public boolean isMoney(int x, int y) {
		if (game.level.levelStage == 5) {
			if (game.level.moneyLocationList.size() == 0) {
				return false;
			}
			for (int i = 0; i < game.level.moneyLocationList.size(); i++) {
				index = i;
				cMoneyLoc = game.level.moneyLocationList.get(i);
				if ((cMoneyLoc.getX() - x) < 50 && (cMoneyLoc.getX() - x) > -50 && (cMoneyLoc.getY() - y) < 50
					&& (cMoneyLoc.getY() - y) > -50 && (cMoneyLoc.getX() - game.player.x) < 100
					&& (cMoneyLoc.getX() - game.player.x) > -100 && (cMoneyLoc.getY() - game.player.y) < 100
					&& (cMoneyLoc.getY() - game.player.y) > -100) {

					game.level.moneyLocationList.remove(index);
					game.player.money += 3250;
					i--;
					return true;
				}
			}
		}
		return false;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public Point getMouseClickPos() {
        return mouseClickPos;
    }
 
    public void setMouseClickPos(Point mouseClickPos) {
        this.mouseClickPos = mouseClickPos;
    }
 
    public Engine getGame() {
        return game;
    }
 
    public void setGame(Engine game) {
        this.game = game;
    }

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}
