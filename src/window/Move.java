package window;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
//checking updates
public class Move extends JPanel implements ActionListener, KeyListener {
	Timer t = new Timer(5, this);
	double x = 0, y = 0, x2 = 0, y2 = 0;
	ArrayList<Integer> keys = new ArrayList<Integer>();
	public Move() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(new Ellipse2D.Double(x, y, 40, 40));
	}
	public void actionPerformed(ActionEvent e) {
		repaint();
		x += x2;
		y += y2;
		x2 = 0;
		y2 = 0;
	}
	public void up() {
		y2 = -10;
		x2 = 0;
	}
	public void down() {
		y2 = 10;
		x2 = 0;
	}
	public void left() {
		y2 = 0;
		x2 = -10;
	}
	public void right() {
		y2 = 0;
		x2 = 10;
	}
	
	public void diagUpRight() {
		x2 = 10;
		y2 = 10;
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
		
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		
	}
}
