package window;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Shape extends JComponent {
	private Rectangle rect;
	private int x;
	private int y;
	private int height;
	private int width;
	public Shape(int x, int y, int h, int w) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		rect = new Rectangle(100, 100, 50, 50);
		g2.fill(rect);
	}
	public int getHeight() {
		return this.height;
	}
	public int getWidth() {
		return this.width;
	}
}
