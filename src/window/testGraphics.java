package window;
import javax.swing.JFrame;


public class testGraphics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setSize(1440, 900);
		window.setTitle("Bank Rob");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Move move = new Move();
		window.add(move);
		window.setVisible(true);
	}
}
