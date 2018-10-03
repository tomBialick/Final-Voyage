package space;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ScrollingBackground {

	private static Image background = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/background.png"))
			.getImage().getScaledInstance(1000, 500, Image.SCALE_DEFAULT)).getImage();
	private int x;
	private int y;
	private int dx = 2;
	
	public ScrollingBackground() {
		x = 0;
		y = 0;	
	}
	public ScrollingBackground(int X, int Y) {
		x = X;
		y = Y;
	}
	
	public void move() {
		x -= dx ;
		if (x <= -1 * background.getWidth(null)) {
			x += (background.getWidth(null) * 2);
		}
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int X) {
		x = X;
	}
	public void setY(int Y) {
		y = Y;
	}
	
	public static Image getImage() {
		return background;
	}
}
