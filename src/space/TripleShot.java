package space;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class TripleShot {

	private int x;
	private int y;
	private final int dx = -2;
	private static Image image = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/tripleShot.png"))
			.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)).getImage();
	private static Clip clip;	
	
	public TripleShot() {
		x = 1000;
		y = (int)(490* Math.random() + 10);
	}
	
	public void move() {
		x += dx;
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
	
	public static Clip getSound() {
		File soundFile = new File("./src/Sounds/Powerup6.wav");
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		return clip;
	}
}
