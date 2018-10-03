package space;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class BadGuy {
	
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int dySpeed;
	private static Image image = new ImageIcon(new ImageIcon(BadGuy.class.getResource("/Images/badGuy.png"))
			.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)).getImage();
	private static Clip explosionClip;
	private static Clip bShootClip;

	public BadGuy(int playerY) {
		x = 1000;
		y = (int) (500 * Math.random() + 1);
		dx = (int) (-5 * (Math.random() - 2));
		while (dx >= 0 || dx == -2) {
			dx = (int) (-5 * Math.random() - 2);
		}
		dySpeed = 1;
		setDY(playerY);

	}

	private void setDY(int playerY) {
		if (playerY < y) {
			dy = -1 * dySpeed;
		} else if (playerY > y) {
			dy = dySpeed;
		}
	}

	public void move(int playerY) {
		x += dx;
		y += dy;
		setDY(playerY);
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
	
	public static Clip getExplosionSound() {
		File soundFile = new File("./src/Sounds/Explosion4.wav");
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
		File soundFile = new File("./src/Sounds/Laser_Shoot22.wav");
		try {
			bShootClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			bShootClip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		return bShootClip;
	}

}
