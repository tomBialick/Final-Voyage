package space;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Player {
	
	private int dy;
	private int x;
	private int y;
	private static Image image = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/ship.png"))
			.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)).getImage();
	public static int count;
	public int shipY;
	private boolean tripleShot;
	private int tripleCount = 0;
	private static Clip pShootClip;
	private static Clip hurtClip;
	private static Clip explosionClip;
	private static Clip warpClip;
	
	
	public Player() {
		x = 5;
		y = 200;
		count = 0;
		tripleShot = false;
	}
	
	public void move(){
		
		if (y >= 400) {
			y = 399;
		}
		else if (y <= -5) {
			y = -4;
		}
		else {
			y += dy;
		}
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean getTripleShot() {
		return tripleShot;
	}
	
	public void setTripleShot(boolean state) {
		tripleShot = state;
		if (true) {
			tripleCount = 0;
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int newCount) {
		count = newCount;
	}

	public static Image getImage() {
		return image;
	}
	
	public static Clip getJumpSound() {
		File soundFile = new File("./src/Sounds/Jump3.wav");
		try {
			warpClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			warpClip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		return warpClip;
	}
	
	public static Clip getShootSound() {
		File soundFile = new File("./src/Sounds/Laser_Shoot6.wav");
		try {
			pShootClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			pShootClip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		return pShootClip;
	}
	
	public static Clip getHurtSound() {
		File soundFile = new File("./src/Sounds/Hit_Hurt.wav");
		try {
			hurtClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			hurtClip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		return hurtClip;
	}
	
	public static Clip getExplosionSound() {
		File soundFile = new File("./src/Sounds/Explosion.wav");
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
	
	
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		
		//Quick Evade 
		if (key == KeyEvent.VK_LEFT && key != KeyEvent.VK_RIGHT
				&& key != KeyEvent.VK_DOWN && key != KeyEvent.VK_UP) {
			Clip j = getJumpSound();
			j.start();
			y = (int)(500 * Math.random() + 5);
			j.setFramePosition(0);
		}
		/*
		// Right
		if (key == KeyEvent.VK_RIGHT && key != KeyEvent.VK_LEFT
				&& key != KeyEvent.VK_DOWN && key != KeyEvent.VK_UP) {
			
			dx = 1;
		}
		*/
		//Up
		if (key == KeyEvent.VK_UP && key != KeyEvent.VK_DOWN) {
			
			dy = -3;
		}
			
		//Down
		if (key == KeyEvent.VK_DOWN && key != KeyEvent.VK_UP) {
			
			dy = 3;
		}
		
		if (key == KeyEvent.VK_SPACE) {
			shipY = getY();							//PlayerMissle pMissle = new PlayerMissle();
													//pMissle.setY(getY());
			count++;
			if (tripleShot) {
				tripleCount++;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			
		}

		if (key == KeyEvent.VK_RIGHT) {
			
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	/**
	 * Only used when player is dead to prevent "zombie missiles"
	 * @param Y
	 */
	public void setY(int Y) {
		y = Y;
	}

	public int getTripleCount() {
		return tripleCount ;
	}
			
		
		

}
