package space;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Shield {
	
	private int x;
	private int y;
	private static Image shield = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/shield.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	
	
	public Shield(int num) {
		x = 25 + ((num - 1) * 30);
		y = 485;
	}
	
	public static Image getImage() {
		return shield;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	

}
