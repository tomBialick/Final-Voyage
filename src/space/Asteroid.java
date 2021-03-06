package space;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Asteroid {

	private int dx;
	private int dy;
	private int x;
	private int y;
	private static Image image = new ImageIcon(new ImageIcon(Asteroid.class.getResource("/Images/asteroid.png"))
			.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)).getImage();;
	public static int asteroidCount;
	private static Clip explosionClip;
	
	
	public Asteroid() {
		x = 1000;
		y = (int)(500* Math.random() + 1);
		dx = (int)(-7 * (Math.random() - 1));
		while (dx >= 0) {
			dx = (int)(-7 * Math.random() - 1);
		}
		dy = (int)((-2 * Math.random() + 1) * (5* Math.random() + 1));
		asteroidCount = 0;
	}
	
	public void move(){
		x += dx;
		y += dy;
		//System.out.println(dx + " " + dy);
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getAsteroidCount() {
		return asteroidCount;
	}
	
	public void setCount(int newCount) {
		asteroidCount = newCount;
	}

	public static Image getImage() {
		return image;
	}
	
	public static Clip getExplosionSound() {
		File soundFile = new File("./src/Sounds/Explosion2.wav");
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
}
