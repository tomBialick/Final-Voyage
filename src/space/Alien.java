package space;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;


public class Alien {

	private int dx;
	private int dy;
	private int x;
	private int y;
	private static Image image = new ImageIcon(new ImageIcon(Alien.class.getResource("/Images/alienShip.png"))
			.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)).getImage();
	public static int alienCount;
	private static Clip explosionClip;
	private static Clip aShootClip;
	private int moveCount;
	
	public Alien() {
		moveCount = 0;
		x = 1000;
		y = (int)(500* Math.random() + 1);
		dx = (int)(-4 * (Math.random() - 1));
		while (dx >= 0 || dx == -2) {
			dx = (int)(-4 * Math.random() - 1);
		}
		dy = (int)((-2 * Math.random() + 1) * (5* Math.random() + 1));
		alienCount = 0;	
	}

	public void move(){
		x += dx;
		y += dy;
		moveCount++;
		changeDY();
	}
	
	private void changeDY() {
		if (moveCount >= 25) {
			dy = (int)((-2 * Math.random() + 1) * (2* Math.random() + 1));
			moveCount = 0;
		}
		//alienCount = 0;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setCount(int newCount) {
		alienCount = newCount;
	}

	public static Image getImage() {
		return image;
	}

	public int getCount() {
		return alienCount;
	}

	public void resetX() {
		x = 1000;
		y = (int)(500* Math.random() + 1);
		
	}
	
	public static Clip getExplosionSound() {
		File soundFile = new File("./src/Sounds/Explosion3.wav");
		try {
			explosionClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			explosionClip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		return explosionClip;
	}
	
	public static Clip getShootSound() {
		File soundFile = new File("./src/Sounds/Laser_Shoot21.wav");
		try {
			aShootClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			aShootClip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		return aShootClip;
	}
}
