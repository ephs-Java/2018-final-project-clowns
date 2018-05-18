package window;
import javax.swing.*;
import guns.Bullet;
import people.People;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import engine.Point;

//checking updates
public class Move extends JPanel implements ActionListener, KeyListener, MouseListener {
	Timer t = new Timer(100, this);
	int x, y, x2, y2 = 0;
	int bullet_x, bullet_y; 
	
	boolean canShoot, shot = false;
	Point currentPos;
	Rectangle bullet;
	Point mouse;
	Point riseOverRun;
	
	
	
	
	ArrayList<Integer> keys = new ArrayList<Integer>();
	public Move() {
		t.start();
		shoot();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseListener(this);
		
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		//Graphics2D g2 = (Graphics2D) g;
		//g2.fill(new Ellipse2D.Double(x, y, 40, 40));
		
		g.fillRect(x, y, 100, 100);
		if (shot) { 
			g.setColor(Color.BLACK);
			g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
		}
	}
	
	public void shoot() {

		if (shot) {
//			int length = (int) Math.sqrt(Math.pow(mouse.getX() - x, 2) + Math.pow(mouse.getY() - x, 2));
//			int xrate = (mouse.getX() - x) / length;
//			int yrate = (mouse.getY() - y) / length;
////			bullet.x += (riseOverRun.getX() / 10) + 1;
////		bullet.y += (riseOverRun.getY() / 10) + 1;
//			bullet.x += xrate;
//			bullet.y += yrate;
//			System.out.println("Bullet Pos: " + bullet.x + "," + bullet.y);
//			System.out.println("riseOverRun:" + riseOverRun);
			System.out.println("Slope: " + currentPos.getSlope(mouse));
			if (bullet.x < mouse.getX()) 
				bullet.x += currentPos.getSlope(mouse);
			if (bullet.y < mouse.getY()) 
				bullet.y += currentPos.getSlope(mouse);
		}
	}
	
	//bob.paintCompnent();
	
	
	
	public void actionPerformed(ActionEvent e) {
		repaint();
		shoot();
		x += x2;
		y += y2;
		x2 = 0;
		y2 = 0;
		//System.out.println("(" + x + ", " + y + ")");
	}
	public void up() {
		y2 = -5;
		x2 = 0;
	}
	public void down() {
		y2 = 5;
		x2 = 0;
	}
	public void left() {
		y2 = 0;
		x2 = -5;
	}
	public void right() {
		y2 = 0;
		x2 = 5;
	}
	
	public void diagUpRight() {
		x2 = 5;
		y2 = 5;
	}

	

	public void keyPressed(KeyEvent e) {
	
		
		int code = e.getKeyCode();
		//System.out.println(code);
		//keys.add(code);
		if(code == KeyEvent.VK_UP) {
			up();
		}
		if(code == KeyEvent.VK_DOWN) {
			down();
		}
		if(code == KeyEvent.VK_LEFT) {
			left();
		}
		if(code == KeyEvent.VK_RIGHT) {
			right();
		}
		if(code == KeyEvent.VK_RIGHT && code == KeyEvent.VK_UP ) {
			diagUpRight();
		}
		
		if(code == KeyEvent.VK_SPACE) {
			if (bullet == null)
				canShoot = true;
			if (canShoot == true) {
				bullet_x = x;
				bullet_y = y;
				bullet = new Rectangle(bullet_x, bullet_y, 5, 5);
				shot = true;
				shoot();
			}
				
			
		}
		
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	 @Override
	public void mouseClicked(MouseEvent e) {
	    int mouse_x=e.getX();
	    int mouse_y=e.getY();
	    mouse = new Point(mouse_x, mouse_y);
	    currentPos = new Point(x, y);
	    riseOverRun = mouse.getRiseOverRunSet(currentPos);
	    //System.out.println(x+","+y);//these co-ords are relative to the component
	    System.out.println("Mouse Clicked at: " + mouse_x + ", " + mouse_y);
	    if (bullet == null)
			canShoot = true;
		if (canShoot == true) {
			bullet_x = x;
			bullet_y = y;
			bullet = new Rectangle(bullet_x, bullet_y, 5, 5);
			shot = true;
			shoot();
		}
	}

		
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
}
