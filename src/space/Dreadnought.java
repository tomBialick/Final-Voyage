package space;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Dreadnought {
	
	private int dx;
	private int dy;
	private int x;
	private int y;
	private static Image image = new ImageIcon(new ImageIcon(BadGuy.class.getResource("/Images/dreadnought.png"))
			.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)).getImage();
	private int moveCount;

	public Dreadnought(int playerY) {
		x = 1000;
		y = 250;	//(int) (500 * Math.random() + 1);
		dx = -1;
		setDY(playerY);
		moveCount = 0;
	}

	private void setDY(int playerY) {
		if (playerY - 65 < y) {
			dy = -1;
		} else if (playerY - 65 > y) {
			dy = 1;
		}
		else {
			dy = 0;
		}
	}

	public void move(int playerY) {
		setDY(playerY);
		if (moveCount > 5) {
			x += dx;
			y += dy;
			moveCount = 0;
		}
		moveCount++;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static Image getImage() {
		return image;
	}

	public void setX(int X) {
		x = X;
	}

}
