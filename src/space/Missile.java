package space;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Missile {

	private int dx = 12;
	private int dy = 0;
	private int x = 5;
	private int y;
	private static Image image = new ImageIcon(new ImageIcon(Missile.class.getResource("/Images/missile.png"))
			.getImage().getScaledInstance(40, 20, Image.SCALE_DEFAULT)).getImage();
	private int moveCount;
	
	
	public Missile(int Y) {
		System.out.println("pmissile");
		//x = playerX;
		y = Y;
	}
	
	public Missile(int X, int Y) {				
		setX(X);
		y = Y;
		setDx((-8));
	}
	
	public Missile(int Y, boolean up) {
		System.out.println("pmissile");
		//x = playerX;
		y = Y;
		if (up) {
			dy = -2;
		}
		else {
			dy = 2;
		}
	}
	
	public Missile(int X, int Y, int playerY) {				
		setX(X);
		y = Y;
		moveCount = 0;
		setDx((-2));
		setDy(playerY);
	}
	
	public void moveMissle() {
		x += dx;
		y += dy;
	}
	
	public void moveHomingMissle(int playerY) {
		setDy(playerY);
		if (moveCount > 1) {
			x += dx;
			y += dy;
			moveCount = 0;
		}
		moveCount++;
	}
	
	public static Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}

	public void setY(int newY) {
		y = newY;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setDx(int newDx) {
		dx = newDx;
	}
	
	private void setDy(int playerY) {
		if (playerY + 15 < y) {
			dy = -1;
		} else if (playerY + 15 > y) {
			dy = 1;
		}
		else {
			dy = 0;
		}
	}
	
	public int getY() {
		return y;
	}
	
}
