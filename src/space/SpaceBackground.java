package space;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Final Voyage
 * 
 * Started: 9/17/2016
 * 
 * @author Tom Bialick
 */


public class SpaceBackground extends JPanel implements ActionListener {

	/**
	 * Apparently this is needed(?)
	 * TODO Figure this out
	 */
	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	private Player player;
	private Missile pMissile;
	private Missile aMissile;
	private Missile bMissile;
	private Asteroid asteroid;
	private Asteroid asteroidA;
	private Asteroid asteroidB;
	private Alien alien;
	private BadGuy badGuy;

	private int missileInitialY;
	private boolean missileExists = false;
	private boolean alienMissileExists = false;
	private boolean badGuyMissileExists = false;
	private boolean asteroidBoolean = false;
	private boolean alienBoolean = false;
	private boolean asteroidBooleanA = false;
	private boolean asteroidBooleanB = false;
	private boolean badGuyBoolean = false;
	private boolean playerAlive = false;
	private final int asteroidChance = 50;
	private int randomAsteroid;
	private int alienMissileInt;
	private int badGuyMissileInt;
	private final int alienChance = 125;
	private final int badGuyChance = 250;
	private int randomAlien;
	private int randomBadGuy;
	
	private JButton restartButton;
	private JButton startButton;

	private int score;
	private final int asteroidPts = 10;
	private final int alienPts = 50;
	private final int badGuyPts = 100;
	
	private boolean newGame;
	
	private Image playerImage = Player.getImage();
	private Image asteroidImage = Asteroid.getImage();
	private Image alienImage = Alien.getImage();
	private Image badGuyImage = BadGuy.getImage();
	private Image missileImage = Missile.getImage();
	
	private boolean keepScore;
	private ScheduledExecutorService executor;
	
	private int health;
	
	private Dreadnought dreadnought;
	private boolean dreadnoughtTime;
	private final int dreadnoughtPts = 25000;
	private Image dreadnoughtImage = Dreadnought.getImage();
	private int dreadnoughtHealth = 10;
	private boolean dreadnoughtAlive;
	private boolean madeDreadnought;
	private boolean topDreadnoughtMissileExists = false;
	private boolean botDreadnoughtMissileExists = false;
	private int dreadnoughtMissileInt;
	private Missile dBMissile;
	private Missile dTMissile;
	private boolean homDreadnoughtMissileExists;
	private Missile dHMissile;
	private boolean midDreadnoughtMissileExists;
	private Missile dMMissile;
	
	private boolean madeHive = false;
	private boolean hiveTime = false;
	private Alien hive1;
	private boolean hive1Boolean = false;
	private Alien hive2;
	private boolean hive2Boolean = false;
	private Alien hive3;
	private boolean hive3Boolean = false;
	private Alien hive4;
	private boolean hive4Boolean = false;
	private Alien hive5;
	private boolean hive5Boolean = false;
	private Alien hive6;
	private boolean hive6Boolean = false;
	private Alien hive7;
	private boolean hive7Boolean = false;
	private Alien hive8;
	private boolean hive8Boolean = false;
	private Alien hive9;
	private boolean hive9Boolean = false;
	private boolean h1MissileExists = false;
	private Missile h1Missile;
	private boolean h2MissileExists = false;
	private Missile h2Missile;
	private boolean h4MissileExists = false;
	private Missile h4Missile;
	private boolean h3MissileExists = false;
	private Missile h3Missile;
	private boolean h5MissileExists = false;
	private Missile h5Missile;
	private boolean h6MissileExists = false;
	private Missile h6Missile;
	private boolean h7MissileExists = false;
	private Missile h7Missile;
	private boolean h8MissileExists = false;
	private Missile h8Missile;
	private boolean h9MissileExists = false;
	private Missile h9Missile;
	private final int hivePts = 30000;
	
	private ScrollingBackground background;
	private Image backgroundImage = ScrollingBackground.getImage();
	private ScrollingBackground background2;
	private boolean backgroundBoolean;
	
	private Image shieldImage = Shield.getImage();
	private Shield shield1;
	private Shield shield2;
	private Shield shield3;
	private Shield shield4;
	private Shield shield5;
	
	private Image score1 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/1.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score2 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/2.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score3 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/3.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score4 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/4.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score5 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/5.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score6 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/6.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score7 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/7.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score8 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/8.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score9 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/9.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private Image score0 = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/0.png"))
			.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)).getImage();
	private final int scoreY = 485;
	
	private Wrench wrench;
	private Image wrenchImage = Wrench.getImage();
	private boolean wrenchExists = false;
	private final int wrenchChance = 2000;
	private int randomWrench;
	
	private ImageIcon restartImage = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/restart.png"))
			.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
	private ImageIcon startImage = new ImageIcon(new ImageIcon(Player.class.getResource("/Images/start.png"))
			.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
	
	private int randomTriple;
	private int tripleChance = 5000;
	private TripleShot tripleShot;
	private Image tripleShotImage = TripleShot.getImage();
	private boolean tripleShotExists = false;
	private Missile uppMissile;
	private Missile dnpMissile;
	private boolean upMissileExists = false;
	private boolean dnMissileExists = false;
	
	private Clip pShootSound = Player.getShootSound();
	private Clip hurtSound = Player.getHurtSound();
	private Clip pExplosionSound = Player.getExplosionSound();
	private Clip aExplosionSound = Asteroid.getExplosionSound();
	private Clip alExplosionSound = Alien.getExplosionSound();
	private Clip bExplosionSound = BadGuy.getExplosionSound();
	private Clip aShootSound = Alien.getShootSound();
	private Clip bShootSound = BadGuy.getShootSound();
	private Clip wrenchSound = Wrench.getSound();
	private Clip tripleShotSound = TripleShot.getSound();
	
	
	/**
	 * Constructor
	 */
	public SpaceBackground() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);

		restartButton = new JButton(restartImage);
		restartButton.setAlignmentX(JButton.CENTER);
		restartButton.setEnabled(false);
		restartButton.setVisible(false);
		restartButton.setBackground(null);
		restartButton.setBorder(null);
		add(restartButton);
		
		startButton = new JButton(startImage);
		startButton.setAlignmentX(JButton.CENTER);
		startButton.setEnabled(true);
		startButton.setVisible(true);
		startButton.setBackground(null);
		startButton.setBorder(null);
		add(startButton);
		
		//player = new Player();
		score = 0;
		keepScore = false;
		
		newGame = true;
		
		health = 0;
		
		dreadnoughtTime = false;
		dreadnoughtAlive = false;
		
		backgroundBoolean = false;
		
		timer = new Timer(4, this);
		timer.start();
	}
                          
	public void paint(Graphics g) {
		super.paint(g);
		
		// System.out.println(player.getCount());
		Graphics2D g2d = (Graphics2D) g;
		/**
		 * Make intro menu screen
		 */
		if (newGame) {
			//TODO make this pretty
			startButton.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					player = new Player();
					playerAlive = true;
					health = 3;
					shield1 = new Shield(1);
					shield2 = new Shield(2);
					shield3 = new Shield(3);
					startButton.setEnabled(false);
					startButton.setVisible(false);
					score = 0;
					newGame = false;
					startButton.removeMouseListener(this);
					madeDreadnought = false;
					//repaint();		
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
			});
		}
		else if (playerAlive) {
			//Can assume player is not null
			startButton = null;
			
			if (!backgroundBoolean) {
				background = new ScrollingBackground();
				background2 = new ScrollingBackground(backgroundImage.getWidth(null), 0);
				backgroundBoolean = true;
			}
			
			g2d.drawImage(backgroundImage, background.getX(), background.getY(), null);
			background.move();
			g2d.drawImage(backgroundImage, background2.getX(), background2.getY(), null);
			background2.move();
			
			if (!keepScore) {
				keepScore = true;
				/**
				 * increase score by x per second
				 */
				Runnable scoreRunnable = new Runnable() {
				    public void run() {
				        score++;
				    }
				};
				executor = Executors.newScheduledThreadPool(1);
				executor.scheduleAtFixedRate(scoreRunnable, 0, 500, TimeUnit.MILLISECONDS);
			}
			
			//Draw player
			g2d.drawImage(playerImage, player.getX(), player.getY(), this);

			if (health == 0) {
				player.setTripleShot(false);
				playerAlive = false;
				if (asteroidBooleanA) {
					asteroidBooleanA = false;
					asteroidA = null;
				}
				if (asteroidBooleanB) {
					asteroidBooleanB = false;
					asteroidB = null;
				}
				if (badGuyBoolean) {
					badGuyBoolean = false;
					badGuy = null;
				}
				if (alienBoolean) {
					alienBoolean = false;
					alien = null;
				}
				if (asteroidBoolean) {
					asteroidBoolean = false;
					asteroid = null;
				}
				if (badGuyMissileExists) {
					badGuyMissileExists = false;
					bMissile = null;
				}
				if (alienMissileExists) {
					alienMissileExists = false;
					aMissile = null;
				}
				if (topDreadnoughtMissileExists) {
					topDreadnoughtMissileExists = false;
					dTMissile = null;
				}
				if (midDreadnoughtMissileExists) {
					midDreadnoughtMissileExists = false;
					dMMissile = null;
				}
				if (botDreadnoughtMissileExists) {
					botDreadnoughtMissileExists = false;
					dBMissile = null;
				}
				if (homDreadnoughtMissileExists) {
					homDreadnoughtMissileExists = false;
					dHMissile = null;
				}
				if (wrenchExists) {
					wrenchExists = false;
					wrench = null;
				}
			}
			
			/*
			 * Bosses
			 */
			//Dreadnought
			if (score >= 10000 && !madeDreadnought && score <= 10100) {
				//Set a flag to hault creation of new enemies
				dreadnoughtTime = true;
				madeDreadnought = true;
			}
			//Hive
			if (score >= 50000 && !hiveTime && score <=50100) {
				//Set a flag to hault creation of new enemies
				hiveTime = true;
			}
			
			if (player.getTripleShot() && player.getTripleCount() >= 20) {
				player.setTripleShot(false);
			}
			
			// Move Missile
			if (player.getCount() > 0 && missileExists && pMissile.getX() <= 1000) {
				g2d.drawImage(missileImage, pMissile.getX(), pMissile.getY(), this);
				pMissile.moveMissle();
			}

			// Missile off screen
			else if (missileExists && pMissile.getX() > 1000) {
				player.setCount(0);
				pMissile = null;
				missileExists = false;
			}
			
			// Move uppMissile
			if (upMissileExists && (uppMissile.getX() <= 1000 && uppMissile.getY() >= -5)) {
				g2d.drawImage(missileImage, uppMissile.getX(), uppMissile.getY(), this);
				uppMissile.moveMissle();
			}

			// uppMissile off screen
			else if (upMissileExists && (uppMissile.getX() > 1000 || uppMissile.getY() < -5)) {
				uppMissile = null;
				upMissileExists = false;
			}
			
			// Move dnpMissile
			if (dnMissileExists && (dnpMissile.getX() <= 1000 && dnpMissile.getY() >= -5)) {
				g2d.drawImage(missileImage, dnpMissile.getX(), dnpMissile.getY(), this);
				dnpMissile.moveMissle();
			}

			// dnpMissile off screen
			else if (dnMissileExists && (dnpMissile.getX() > 1000 || dnpMissile.getY() < -5)) {
				dnpMissile = null;
				dnMissileExists = false;
			}

			// Create Asteroid 1
			if (!dreadnoughtTime && !hiveTime && !asteroidBoolean) {
				randomAsteroid = (int) (asteroidChance * Math.random() + 1);
				//System.out.println("Asteroid " + randomAsteroid);
				if (randomAsteroid > 45) {
					asteroid = new Asteroid();
					asteroidBoolean = true;
				}
			}
			// Create Asteroid a
			if (!dreadnoughtTime && !hiveTime && !asteroidBooleanA && score > 25) {
				randomAsteroid = (int) (asteroidChance * Math.random() + 1);
				//System.out.println("Asteroid " + randomAsteroid);
				if (randomAsteroid > 47) {
					asteroidA = new Asteroid();
					asteroidBooleanA = true;
				}
			}	
			// Create Asteroid b
			if (!dreadnoughtTime && !hiveTime && !asteroidBooleanB && score > 75) {
				randomAsteroid = (int) (asteroidChance * Math.random() + 1);
				//System.out.println("Asteroid " + randomAsteroid);
				if (randomAsteroid > 49) {
					asteroidB = new Asteroid();
					asteroidBooleanB = true;
				}
			}	

			// Create Alien
			if (!dreadnoughtTime && !hiveTime  && !alienBoolean && score >= 200) {
				randomAlien = (int) (alienChance * Math.random() + 1);
				if (randomAlien > 99) {
					alien = new Alien();
					alienBoolean = true;
				}
			}

			// Create Bad Guy
			if (!dreadnoughtTime && !hiveTime  && !badGuyBoolean && score >= 500) {
				randomBadGuy = (int) (badGuyChance * Math.random() + 1);
				if (randomBadGuy > 249) {
					badGuy = new BadGuy(player.getY());
					badGuyBoolean = true;
				}
			}
			
			// Create Wrench
			if (!wrenchExists) {
				randomWrench = (int) (wrenchChance * Math.random() + 1);
				//System.out.println("Wrench " + randomWrench);
				if (randomWrench > 1998) {
					wrench = new Wrench();
					wrenchExists = true;
				}
			}
			// Move wrench
			if (wrenchExists) {
				wrench.move();
				g2d.drawImage(wrenchImage, wrench.getX(), wrench.getY(), this);
			}
			// Wrench off screen
			if (wrenchExists && (wrench.getX() <= -50 || wrench.getY() <= -50
					|| wrench.getX() >= 1050 || wrench.getY() >= 550)) {
				// player.setCount(0);
				wrenchExists = false;
				wrench = null;
			}
			// Create tripleshot
			if (!tripleShotExists && !player.getTripleShot()) {
				randomTriple = (int) (tripleChance * Math.random() + 1);
				if (randomTriple > 4999) {
					tripleShot = new TripleShot();
					tripleShotExists = true;
				}
			}
			// Move tripleshot
			if (tripleShotExists) {
				tripleShot.move();
				g2d.drawImage(tripleShotImage, tripleShot.getX(), tripleShot.getY(), this);
			}
			// TripleShot off screen
			if (tripleShotExists && (tripleShot.getX() <= -50 || tripleShot.getY() <= -50
					|| tripleShot.getX() >= 1050 || tripleShot.getY() >= 550)) {
				tripleShotExists = false;
				tripleShot = null;
			}
			
			// Move Asteroid 1
			if (asteroidBoolean) {
				asteroid.move();
				g2d.drawImage(asteroidImage, asteroid.getX(), asteroid.getY(), this);
			}
			// Move Asteroid a
			if (asteroidBooleanA) {
				asteroidA.move();
				g2d.drawImage(asteroidImage, asteroidA.getX(), asteroidA.getY(), this);
			}
			// Move Asteroid b
			if (asteroidBooleanB) {
				asteroidB.move();
				g2d.drawImage(asteroidImage, asteroidB.getX(), asteroidB.getY(), this);
			}

			// Asteroid off screen
			if (asteroidBoolean && asteroid.getX() <= -50 || asteroidBoolean && asteroid.getY() <= -50
					|| asteroidBoolean && asteroid.getX() >= 1050 || asteroidBoolean && asteroid.getY() >= 550) {
				// player.setCount(0);
				asteroidBoolean = false;
				asteroid = null;
			}
			
			// Asteroid a off screen
			if (asteroidBooleanA && asteroidA.getX() <= -50 || asteroidBooleanA && asteroidA.getY() <= -50
					|| asteroidBooleanA && asteroidA.getX() >= 1050 || asteroidBooleanA && asteroidA.getY() >= 550) {
				// player.setCount(0);
				asteroidBooleanA = false;
				asteroidA = null;
			}
			// Asteroid b off screen
			if (asteroidBooleanB && asteroidB.getX() <= -50 || asteroidBooleanB && asteroidB.getY() <= -50
					|| asteroidBooleanB && asteroidB.getX() >= 1050 || asteroidBooleanB && asteroidB.getY() >= 550) {
				// player.setCount(0);
				asteroidBooleanB = false;
				asteroidB = null;
			}

			// Move Alien
			if (alienBoolean) {
				alien.move();
				g2d.drawImage(alienImage, alien.getX(), alien.getY(), this);
			}
			
			// Move Bad Guy
			if (badGuyBoolean) {
				badGuy.move(player.getY());
				g2d.drawImage(badGuyImage, badGuy.getX(), badGuy.getY(), this);
			}

			// Alien off screen
			if (alienBoolean && alien.getX() <= -50 || alienBoolean && alien.getY() <= -50
					|| alienBoolean && alien.getX() >= 1050 || alienBoolean && alien.getY() >= 550) {
				// player.setCount(0);
				alienBoolean = false;
				alien = null;
			}
			
			// Bad Guy off screen
			if (badGuyBoolean && (badGuy.getX() <= -50 || badGuy.getY() <= -50
					||  badGuy.getX() >= 1050 ||  badGuy.getY() >= 550)) {
				badGuyBoolean = false;
				badGuy = null;
			}

			// Missile hit Asteroid
			if (missileExists && asteroidBoolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), asteroid.getX(), asteroid.getY())) {
				asteroidBoolean = false;
				asteroid = null;
				System.out.println("----HIT----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += asteroidPts;
				if (aExplosionSound.isRunning()) {
					aExplosionSound.stop();
				}
				aExplosionSound.start();
				aExplosionSound.setFramePosition(0);
				// possible explode sequence
			}
			// Missile hit Asteroid a
			if (missileExists && asteroidBooleanA
					&& missileHitThing(pMissile.getX(), pMissile.getY(), asteroidA.getX(), asteroidA.getY())) {
				asteroidBooleanA = false;
				asteroidA = null;
				System.out.println("----HIT----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += asteroidPts;
				if (aExplosionSound.isRunning()) {
					aExplosionSound.stop();
				}
				aExplosionSound.start();
				aExplosionSound.setFramePosition(0);
				// possible explode sequence
			}
			// Missile hit Asteroid b
			if (missileExists && asteroidBooleanB
					&& missileHitThing(pMissile.getX(), pMissile.getY(), asteroidB.getX(), asteroidB.getY())) {
				asteroidBooleanB = false;
				asteroidB = null;
				System.out.println("----HIT----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += asteroidPts;
				if (aExplosionSound.isRunning()) {
					aExplosionSound.stop();
				}
				aExplosionSound.start();
				aExplosionSound.setFramePosition(0);
				// possible explode sequence
			}

			// Missile hit Alien
			if (missileExists && alienBoolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), alien.getX(), alien.getY())) {
				alienBoolean = false;
				alien = null;
				System.out.println("----HIT----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				if (alExplosionSound.isRunning()) {
					alExplosionSound.stop();
				}
				alExplosionSound.start();
				alExplosionSound.setFramePosition(0);
				// possible explode sequence
			}
			
			// Missile hit Bad Guy
			if (missileExists && badGuyBoolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), badGuy.getX(), badGuy.getY())) {
				badGuyBoolean = false;
				badGuy = null;
				System.out.println("----HIT----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += badGuyPts;
				if (bExplosionSound.isRunning()) {
					bExplosionSound.stop();
				}
				bExplosionSound.start();
				bExplosionSound.setFramePosition(0);
				// possible explode sequence
			}
			
			// upMissile hit Asteroid
			if (upMissileExists && asteroidBoolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), asteroid.getX(), asteroid.getY())) {
				asteroidBoolean = false;
				asteroid = null;
				System.out.println("----HIT----");
				uppMissile = null;
				upMissileExists = false;
				score += asteroidPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Asteroid a
			if (upMissileExists && asteroidBooleanA
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), asteroidA.getX(), asteroidA.getY())) {
				asteroidBooleanA = false;
				asteroidA = null;
				System.out.println("----HIT----");
				uppMissile = null;
				upMissileExists = false;
				score += asteroidPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Asteroid b
			if (upMissileExists && asteroidBooleanB
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), asteroidB.getX(), asteroidB.getY())) {
				asteroidBooleanB = false;
				asteroidB = null;
				System.out.println("----HIT----");
				uppMissile = null;
				upMissileExists = false;
				score += asteroidPts;
				// Sound
				// possible explode sequence
			}

			// upMissile hit Alien
			if (upMissileExists && alienBoolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), alien.getX(), alien.getY())) {
				alienBoolean = false;
				alien = null;
				System.out.println("----HIT----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			
			// uppMissile hit Bad Guy
			if (upMissileExists && badGuyBoolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), badGuy.getX(), badGuy.getY())) {
				badGuyBoolean = false;
				badGuy = null;
				System.out.println("----HIT----");
				uppMissile = null;
				upMissileExists = false;
				score += badGuyPts;
				// Sound
				// possible explode sequence
			}
			
			// dnMissile hit Asteroid
			if (dnMissileExists && asteroidBoolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), asteroid.getX(), asteroid.getY())) {
				asteroidBoolean = false;
				asteroid = null;
				System.out.println("----HIT----");
				dnpMissile = null;
				dnMissileExists = false;
				score += asteroidPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Asteroid a
			if (dnMissileExists && asteroidBooleanA
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), asteroidA.getX(), asteroidA.getY())) {
				asteroidBooleanA = false;
				asteroidA = null;
				System.out.println("----HIT----");
				dnpMissile = null;
				dnMissileExists = false;
				score += asteroidPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Asteroid b
			if (dnMissileExists && asteroidBooleanB
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), asteroidB.getX(), asteroidB.getY())) {
				asteroidBooleanB = false;
				asteroidB = null;
				System.out.println("----HIT----");
				dnpMissile = null;
				dnMissileExists = false;
				score += asteroidPts;
				// Sound
				// possible explode sequence
			}

			// dnMissile hit Alien
			if (dnMissileExists && alienBoolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), alien.getX(), alien.getY())) {
				alienBoolean = false;
				alien = null;
				System.out.println("----HIT----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			
			// dnpMissile hit Bad Guy
			if (dnMissileExists && badGuyBoolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), badGuy.getX(), badGuy.getY())) {
				badGuyBoolean = false;
				badGuy = null;
				System.out.println("----HIT----");
				dnpMissile = null;
				dnMissileExists = false;
				score += badGuyPts;
				// Sound
				// possible explode sequence
			}

			
			// Create Alien Missile
			if (!alienMissileExists && alienBoolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					aMissile = new Missile(alien.getX() + 10, alien.getY() + 25);
					alienMissileExists = true;
					if (aShootSound.isRunning()) {
						aShootSound.stop();
					}
					aShootSound.setFramePosition(0);
					aShootSound.start();
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			
			// Create Bad Guy Missile
			if (!badGuyMissileExists && badGuyBoolean) {
				badGuyMissileInt = (int) (1000 * Math.random() + 1);
				if (badGuyMissileInt > 995) {
					bMissile = new Missile(badGuy.getX(), badGuy.getY() + 25);
					badGuyMissileExists = true;
					if (bShootSound.isRunning()) {
						bShootSound.stop();
					}
					bShootSound.setFramePosition(0);
					bShootSound.start();
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			
			// Move Alien Missile
			if (alienMissileExists && aMissile.getX() > 0) {
				g2d.drawImage(missileImage, aMissile.getX(), aMissile.getY(), this);
				aMissile.moveMissle();
			}

			// Alien Missile off screen
			else if (alienMissileExists && aMissile.getX() <= 0) {
				aMissile = null;
				alienMissileExists = false;
			}

			// Move Bad Guy Missile
			if (badGuyMissileExists && bMissile.getX() > 0) {
				g2d.drawImage(missileImage, bMissile.getX(), bMissile.getY(), this);
				bMissile.moveMissle();
			}

			// Bad Guy Missile off screen
			else if (badGuyMissileExists && bMissile.getX() <= 0) {
				bMissile = null;
				badGuyMissileExists = false;
			}
			
			
			
			/*
			 * Dreadnought
			 */
			if (dreadnoughtTime && !dreadnoughtAlive) {
				dreadnought = new Dreadnought(player.getY());
				dreadnoughtAlive = true;
			}
			// Move Dreadnought
			if (dreadnoughtAlive) {
				dreadnought.move(player.getY());
				g2d.drawImage(dreadnoughtImage, dreadnought.getX(), dreadnought.getY(), this);
			}
			// Dreadnought off screen
			if (dreadnoughtAlive && (dreadnought.getX() <= -50 || dreadnought.getY() <= -50
					||  dreadnought.getX() >= 1050 ||  dreadnought.getY() >= 550)) {
				dreadnought.setX(500);
			}
			// Create top Dreadnought Missile 
			if (!topDreadnoughtMissileExists  && dreadnoughtAlive) {
				dreadnoughtMissileInt = (int) (1000 * Math.random() + 1);
				if (dreadnoughtMissileInt > 996) {
					dTMissile = new Missile(dreadnought.getX(), dreadnought.getY() + 25);
					topDreadnoughtMissileExists = true;
				}
			}
			// Create mid Dreadnought Missile 
			if (!midDreadnoughtMissileExists  && dreadnoughtAlive) {
				dreadnoughtMissileInt = (int) (1000 * Math.random() + 1);
				if (dreadnoughtMissileInt > 998) {
					dMMissile = new Missile(dreadnought.getX(), dreadnought.getY() + 75);
					midDreadnoughtMissileExists = true;
				}
			}
			// Create bot Dreadnought Missile 
			if (!botDreadnoughtMissileExists  && dreadnoughtAlive) {
				dreadnoughtMissileInt = (int) (1000 * Math.random() + 1);
				if (dreadnoughtMissileInt > 996) {
					dBMissile = new Missile(dreadnought.getX(), dreadnought.getY() + 125);
					botDreadnoughtMissileExists = true;
				}
			}
			// Create homing Dreadnought Missile 
			if (!homDreadnoughtMissileExists  && dreadnoughtAlive) {
				dHMissile = new Missile(dreadnought.getX(), dreadnought.getY() + 75, player.getY());
				homDreadnoughtMissileExists = true;
			}
			// Move top Dreadnought Missile
			if (topDreadnoughtMissileExists && dTMissile.getX() > 0) {
				dTMissile.moveMissle();
				g2d.drawImage(missileImage, dTMissile.getX(), dTMissile.getY(), this);
			}
			// top Dreadnought Missile off screen
			else if (topDreadnoughtMissileExists && dTMissile.getX() <= 0) {
				dTMissile = null;
				topDreadnoughtMissileExists = false;
			}
			// Move mid Dreadnought Missile
			if (midDreadnoughtMissileExists && dMMissile.getX() > 0) {
				g2d.drawImage(missileImage, dMMissile.getX(), dMMissile.getY(), this);
				dMMissile.moveMissle();
			}
			// mid  Dreadnought Missile off screen
			else if (midDreadnoughtMissileExists && dMMissile.getX() <= 0) {
				dMMissile = null;
				midDreadnoughtMissileExists = false;
			}
			// Move bot Dreadnought Missile
			if (botDreadnoughtMissileExists && dBMissile.getX() > 0) {
				dBMissile.moveMissle();
				g2d.drawImage(missileImage, dBMissile.getX(), dBMissile.getY(), this);
			}
			// bot Dreadnought Missile off screen
			else if (botDreadnoughtMissileExists && dBMissile.getX() <= 0) {
				dBMissile = null;
				botDreadnoughtMissileExists = false;
			}
			// Move homing Dreadnought Missile
			if (homDreadnoughtMissileExists && dHMissile.getX() > 0) {
				dHMissile.moveHomingMissle(player.getY());
				g2d.drawImage(missileImage, dHMissile.getX(), dHMissile.getY(), this);
			}
			// homing Dreadnought Missile off screen
			else if (homDreadnoughtMissileExists && dHMissile.getX() <= 0) {
				dHMissile = null;
				homDreadnoughtMissileExists = false;
			}
			// Missile hit Dreadnought
			if (missileExists && dreadnoughtAlive
					&& missileHitDreadnought(pMissile.getX(), pMissile.getY(), dreadnought.getX(), dreadnought.getY())) {
				System.out.println("----HIT----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				dreadnoughtHealth--;
				if (dreadnoughtHealth == 0) {
					dreadnoughtAlive = false;
					dreadnought = null;
					score += dreadnoughtPts;
					dreadnoughtTime = false;
				}
				// Sound
				// possible explode sequence
			}
			// upMissile hit Dreadnought
			if (upMissileExists && dreadnoughtAlive
					&& missileHitDreadnought(uppMissile.getX(), uppMissile.getY(), dreadnought.getX(), dreadnought.getY())) {
				System.out.println("----HIT----");
				uppMissile = null;
				upMissileExists = false;
				dreadnoughtHealth--;
				if (dreadnoughtHealth == 0) {
					dreadnoughtAlive = false;
					dreadnought = null;
					score += dreadnoughtPts;
					dreadnoughtTime = false;
				}
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Dreadnought
			if (dnMissileExists && dreadnoughtAlive
					&& missileHitDreadnought(dnpMissile.getX(), dnpMissile.getY(), dreadnought.getX(), dreadnought.getY())) {
				System.out.println("----HIT----");
				dnpMissile = null;
				dnMissileExists = false;
				dreadnoughtHealth--;
				if (dreadnoughtHealth == 0) {
					dreadnoughtAlive = false;
					dreadnought = null;
					score += dreadnoughtPts;
					dreadnoughtTime = false;
				}
				// Sound
				// possible explode sequence
			}
			
			
			/*
			 * Hive 
			 */ 
			//TODO finish Hive
			if (hiveTime && !madeHive) {
				hive1 = new Alien();
				hive1Boolean = true;
				hive2 = new Alien();
				hive2Boolean = true;
				hive3 = new Alien();
				hive3Boolean = true;
				hive4 = new Alien();
				hive4Boolean = true;
				hive5 = new Alien();
				hive5Boolean = true;
				hive6 = new Alien();
				hive6Boolean = true;
				hive7 = new Alien();
				hive7Boolean = true;
				hive8 = new Alien();
				hive8Boolean = true;
				hive9 = new Alien();
				hive9Boolean = true;
				System.out.println("hives");
				madeHive = true;
			}
			// Move hive1
			if (hive1Boolean) {
				hive1.move();
				g2d.drawImage(alienImage, hive1.getX(), hive1.getY(), this);
			}
			// Hive1 off screen
			if (hive1Boolean && hive1.getY() <= -50 
					|| hive1Boolean && hive1.getX() <= -50 
					|| hive1Boolean && hive1.getY() >= 550) {
				hive1.resetX();
			}
			// Create Hive1 Missile
			if (!h1MissileExists && hive1Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h1Missile = new Missile(hive1.getX() + 10, hive1.getY() + 25);
					h1MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive1 Missile
			if (h1MissileExists && h1Missile.getX() > 0) {
				h1Missile.moveMissle();
				g2d.drawImage(missileImage, h1Missile.getX(), h1Missile.getY(), this);
			}

			// Hive1 Missile off screen
			else if (h1MissileExists && h1Missile.getX() <= 0) {
				h1Missile = null;
				h1MissileExists = false;
			}
			
			// Missile hit Hive1
			if (missileExists && hive1Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive1.getX(), hive1.getY())) {
				hive1Boolean = false;
				hive1 = null;
				System.out.println("----HIT 1----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive1
			if (upMissileExists && hive1Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive1.getX(), hive1.getY())) {
				hive1Boolean = false;
				hive1 = null;
				System.out.println("----HIT 1----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive1
			if (dnMissileExists && hive1Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive1.getX(), hive1.getY())) {
				hive1Boolean = false;
				hive1 = null;
				System.out.println("----HIT 1----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}

			// Move hive2
			if (hive2Boolean) {
				hive2.move();
				g2d.drawImage(alienImage, hive2.getX(), hive2.getY(), this);
			}
			// Hive2 off screen
			if (hive2Boolean && hive2.getY() <= -50 
					|| hive2Boolean && hive2.getX() <= -50 
					|| hive2Boolean && hive2.getY() >= 550) {
				hive2.resetX();
			}
			// Create Hive2 Missile
			if (!h2MissileExists && hive2Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h2Missile = new Missile(hive2.getX() + 10, hive2.getY() + 25);
					h2MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive2 Missile
			if (h2MissileExists && h2Missile.getX() > 0) {
				g2d.drawImage(missileImage, h2Missile.getX(), h2Missile.getY(), this);
				h2Missile.moveMissle();
			}

			// Hive2 Missile off screen
			else if (h2MissileExists && h2Missile.getX() <= 0) {
				h2Missile = null;
				h2MissileExists = false;
			}
			
			// Missile hit Hive2
			if (missileExists && hive2Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive2.getX(), hive2.getY())) {
				hive2Boolean = false;
				hive2 = null;
				System.out.println("----HIT 2----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive2
			if (upMissileExists && hive2Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive2.getX(), hive2.getY())) {
				hive2Boolean = false;
				hive2 = null;
				System.out.println("----HIT 2----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive9
			if (dnMissileExists && hive2Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive2.getX(), hive2.getY())) {
				hive2Boolean = false;
				hive2 = null;
				System.out.println("----HIT 2----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			
			// Move hive3
			if (hive3Boolean) {
				g2d.drawImage(alienImage, hive3.getX(), hive3.getY(), this);
				hive3.move();
			}
			// Hive3 off screen
			if (hive3Boolean && hive3.getY() <= -50 
					|| hive3Boolean && hive3.getX() <= -50 
					|| hive3Boolean && hive3.getY() >= 550) {
				hive3.resetX();
			}
			// Create Hive3 Missile
			if (!h3MissileExists && hive3Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h3Missile = new Missile(hive3.getX() + 10, hive3.getY() + 25);
					h3MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive3 Missile
			if (h3MissileExists && h3Missile.getX() > 0) {
				g2d.drawImage(missileImage, h3Missile.getX(), h3Missile.getY(), this);
				h3Missile.moveMissle();
			}

			// Hive3 Missile off screen
			else if (h3MissileExists && h3Missile.getX() <= 0) {
				h3Missile = null;
				h3MissileExists = false;
			}
			
			// Missile hit Hive3
			if (missileExists && hive3Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive3.getX(), hive3.getY())) {
				hive3Boolean = false;
				hive3 = null;
				System.out.println("----HIT 3----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive3
			if (upMissileExists && hive3Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive3.getX(), hive3.getY())) {
				hive3Boolean = false;
				hive3 = null;
				System.out.println("----HIT 3----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive3
			if (dnMissileExists && hive3Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive3.getX(), hive3.getY())) {
				hive3Boolean = false;
				hive3 = null;
				System.out.println("----HIT 3----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}

			// Move hive4
			if (hive4Boolean) {
				g2d.drawImage(alienImage, hive4.getX(), hive4.getY(), this);
				hive4.move();
			}
			// Hive4 off screen
			if (hive4Boolean && hive4.getY() <= -50 
					|| hive4Boolean && hive4.getX() <= -50 
					|| hive4Boolean && hive4.getY() >= 550) {
				hive4.resetX();
			}
			// Create Hive4 Missile
			if (!h4MissileExists && hive4Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h4Missile = new Missile(hive4.getX() + 10, hive4.getY() + 25);
					h4MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive4 Missile
			if (h4MissileExists && h4Missile.getX() > 0) {
				g2d.drawImage(missileImage, h4Missile.getX(), h4Missile.getY(), this);
				h4Missile.moveMissle();
			}

			// Hive4 Missile off screen
			else if (h4MissileExists && h4Missile.getX() <= 0) {
				h4Missile = null;
				h4MissileExists = false;
			}
			
			// Missile hit Hive4
			if (missileExists && hive4Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive4.getX(), hive4.getY())) {
				hive4Boolean = false;
				hive4 = null;
				System.out.println("----HIT 4----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive4
			if (upMissileExists && hive4Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive4.getX(), hive4.getY())) {
				hive4Boolean = false;
				hive4 = null;
				System.out.println("----HIT 4----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive4
			if (dnMissileExists && hive4Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive4.getX(), hive4.getY())) {
				hive4Boolean = false;
				hive4 = null;
				System.out.println("----HIT 4----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			
			// Move hive5
			if (hive5Boolean) {
				g2d.drawImage(alienImage, hive5.getX(), hive5.getY(), this);
				hive5.move();
			}
			// Hive5 off screen
			if (hive5Boolean && hive5.getY() <= -50 
					|| hive5Boolean && hive5.getX() <= -50 
					|| hive5Boolean && hive5.getY() >= 550) {
				hive5.resetX();
			}
			// Create Hive5 Missile
			if (!h5MissileExists && hive5Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h5Missile = new Missile(hive5.getX() + 10, hive5.getY() + 25);
					h5MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive5 Missile
			if (h5MissileExists && h5Missile.getX() > 0) {
				g2d.drawImage(missileImage, h5Missile.getX(), h5Missile.getY(), this);
				h5Missile.moveMissle();
			}

			// Hive5 Missile off screen
			else if (h5MissileExists && h5Missile.getX() <= 0) {
				h5Missile = null;
				h5MissileExists = false;
			}
			
			// Missile hit Hive5
			if (missileExists && hive5Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive5.getX(), hive5.getY())) {
				hive5Boolean = false;
				hive5 = null;
				System.out.println("----HIT 5----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive5
			if (upMissileExists && hive5Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive5.getX(), hive5.getY())) {
				hive5Boolean = false;
				hive5 = null;
				System.out.println("----HIT 5----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive5
			if (dnMissileExists && hive5Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive5.getX(), hive5.getY())) {
				hive5Boolean = false;
				hive5 = null;
				System.out.println("----HIT 5----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}

			// Move hive6
			if (hive6Boolean) {
				g2d.drawImage(alienImage, hive6.getX(), hive6.getY(), this);
				hive6.move();
			}
			// Hive6 off screen
			if (hive6Boolean && hive6.getY() <= -50 
					|| hive6Boolean && hive6.getX() <= -50 
					|| hive6Boolean && hive6.getY() >= 550) {
				hive6.resetX();
			}
			// Create Hive6 Missile
			if (!h6MissileExists && hive6Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h6Missile = new Missile(hive6.getX() + 10, hive6.getY() + 25);
					h6MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive6 Missile
			if (h6MissileExists && h6Missile.getX() > 0) {
				g2d.drawImage(missileImage, h6Missile.getX(), h6Missile.getY(), this);
				h6Missile.moveMissle();
			}

			// Hive6 Missile off screen
			else if (h6MissileExists && h6Missile.getX() <= 0) {
				h6Missile = null;
				h6MissileExists = false;
			}
			
			// Missile hit Hive6
			if (missileExists && hive6Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive6.getX(), hive6.getY())) {
				hive6Boolean = false;
				hive6 = null;
				System.out.println("----HIT 6----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive6
			if (upMissileExists && hive6Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive6.getX(), hive6.getY())) {
				hive6Boolean = false;
				hive6 = null;
				System.out.println("----HIT 6----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive6
			if (dnMissileExists && hive6Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive6.getX(), hive6.getY())) {
				hive6Boolean = false;
				hive6 = null;
				System.out.println("----HIT 6----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			
			// Move hive7
			if (hive7Boolean) {
				g2d.drawImage(alienImage, hive7.getX(), hive7.getY(), this);
				hive7.move();
			}
			// Hive7 off screen
			if (hive7Boolean && hive7.getY() <= -50 
					|| hive7Boolean && hive7.getX() <= -50 
					|| hive7Boolean && hive7.getY() >= 550) {
				hive7.resetX();
			}
			// Create Hive7 Missile
			if (!h7MissileExists && hive7Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h7Missile = new Missile(hive7.getX() + 10, hive7.getY() + 25);
					h7MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive7 Missile
			if (h7MissileExists && h7Missile.getX() > 0) {
				g2d.drawImage(missileImage, h7Missile.getX(), h7Missile.getY(), this);
				h7Missile.moveMissle();
			}

			// Hive7 Missile off screen
			else if (h7MissileExists && h7Missile.getX() <= 0) {
				h7Missile = null;
				h7MissileExists = false;
			}
			
			// Missile hit Hive7
			if (missileExists && hive7Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive7.getX(), hive7.getY())) {
				hive7Boolean = false;
				hive7 = null;
				System.out.println("----HIT 7----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive7
			if (upMissileExists && hive9Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive7.getX(), hive7.getY())) {
				hive7Boolean = false;
				hive7 = null;
				System.out.println("----HIT 7----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive7
			if (dnMissileExists && hive7Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive7.getX(), hive7.getY())) {
				hive7Boolean = false;
				hive7 = null;
				System.out.println("----HIT 7----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}

			// Move hive8
			if (hive8Boolean) {
				g2d.drawImage(alienImage, hive8.getX(), hive8.getY(), this);
				hive8.move();
			}
			// Hive8 off screen
			if (hive8Boolean && hive8.getY() <= -50 
					|| hive8Boolean && hive8.getX() <= -50 
					|| hive8Boolean && hive8.getY() >= 550) {
				hive8.resetX();
			}
			// Create Hive8 Missile
			if (!h8MissileExists && hive8Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h8Missile = new Missile(hive8.getX() + 10, hive8.getY() + 25);
					h8MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive8 Missile
			if (h8MissileExists && h8Missile.getX() > 0) {
				g2d.drawImage(missileImage, h8Missile.getX(), h8Missile.getY(), this);
				h8Missile.moveMissle();
			}

			// Hive8 Missile off screen
			else if (h8MissileExists && h8Missile.getX() <= 0) {
				h8Missile = null;
				h8MissileExists = false;
			}
			
			// Missile hit Hive8
			if (missileExists && hive8Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive8.getX(), hive8.getY())) {
				hive8Boolean = false;
				hive8 = null;
				System.out.println("----HIT 8----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}			
			// upMissile hit Hive8
			if (upMissileExists && hive8Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive8.getX(), hive8.getY())) {
				hive8Boolean = false;
				hive8 = null;
				System.out.println("----HIT 8----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive8
			if (dnMissileExists && hive8Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive8.getX(), hive8.getY())) {
				hive8Boolean = false;
				hive8 = null;
				System.out.println("----HIT 8----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}			
			
			// Move hive9
			if (hive9Boolean) {
				g2d.drawImage(alienImage, hive9.getX(), hive9.getY(), this);
				hive9.move();
			}
			// Hive9 off screen
			if (hive9Boolean && hive9.getY() <= -50 
					|| hive9Boolean && hive9.getX() <= -50 
					|| hive9Boolean && hive9.getY() >= 550) {
				hive9.resetX();
			}
			// Create Hive9 Missile
			if (!h9MissileExists && hive9Boolean) {
				alienMissileInt = (int) (1000 * Math.random() + 1);
				if (alienMissileInt > 995) {
					h9Missile = new Missile(hive9.getX() + 10, hive9.getY() + 25);
					h9MissileExists = true;
					//g2d.drawImage(amissileImage, aMissile.getX(), aMissile.getY(), this);
				}
			}
			// Move Hive9 Missile
			if (h9MissileExists && h9Missile.getX() > 0) {
				g2d.drawImage(missileImage, h9Missile.getX(), h9Missile.getY(), this);
				h9Missile.moveMissle();
			}

			// Hive9 Missile off screen
			else if (h9MissileExists && h9Missile.getX() <= 0) {
				h9Missile = null;
				h9MissileExists = false;
			}
			
			// Missile hit Hive9
			if (missileExists && hive9Boolean
					&& missileHitThing(pMissile.getX(), pMissile.getY(), hive9.getX(), hive9.getY())) {
				hive9Boolean = false;
				hive9 = null;
				System.out.println("----HIT 9----");
				player.setCount(0);
				pMissile = null;
				missileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// upMissile hit Hive9
			if (upMissileExists && hive9Boolean
					&& missileHitThing(uppMissile.getX(), uppMissile.getY(), hive9.getX(), hive9.getY())) {
				hive9Boolean = false;
				hive9 = null;
				System.out.println("----HIT 9----");
				uppMissile = null;
				upMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			// dnMissile hit Hive9
			if (dnMissileExists && hive9Boolean
					&& missileHitThing(dnpMissile.getX(), dnpMissile.getY(), hive9.getX(), hive9.getY())) {
				hive9Boolean = false;
				hive9 = null;
				System.out.println("----HIT 9----");
				dnpMissile = null;
				dnMissileExists = false;
				score += alienPts;
				// Sound
				// possible explode sequence
			}
			if (hiveTime && (!hive1Boolean && 
					!hive2Boolean && 
					!hive3Boolean && 
					!hive4Boolean && 
					!hive5Boolean && 
					!hive6Boolean && 
					!hive7Boolean && 
					!hive8Boolean && 
					!hive9Boolean)) {
				score += hivePts;
				hiveTime = false;				
			}


			
			
			
			/*
			 * Deaths
			 */
			//TODO finish death
			// Bad Guy Missile hit player
			if (badGuyMissileExists && missileHitPlayer(bMissile.getX(), bMissile.getY(), player.getX(), player.getY())) {
				badGuyMissileExists = false;
				bMissile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY BAD GUY MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----BAD GUY MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			
			// Alien Missile hit player
			else if (alienMissileExists && missileHitPlayer(aMissile.getX(), aMissile.getY(), player.getX(), player.getY())) {
				alienMissileExists = false;
				aMissile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY ALIEN MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----ALIEN MISSILE----");
				}
				// Sound
				// possible explode sequence
			}

			// player hit by asteroid
			else if (asteroidBoolean && thingHit(player.getX(), player.getY(), asteroid.getX(), asteroid.getY())) {
				asteroidBoolean = false;
				asteroid = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY ASTEROID CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----ASTEROID CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// player hit by asteroid a
			else if (asteroidBooleanA && thingHit(player.getX(), player.getY(), asteroidA.getX(), asteroidA.getY())) {
				asteroidBooleanA = false;
				asteroidA = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY ASTEROID CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----ASTEROID CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// player hit by asteroid b
			else if (asteroidBooleanB && thingHit(player.getX(), player.getY(), asteroidB.getX(), asteroidB.getY())) {
				asteroidBooleanB = false;
				asteroidB = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY ASTEROID CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----ASTEROID CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}

			// player hit by Bad Guy
			else if (badGuyBoolean && thingHit(player.getX(), player.getY(), badGuy.getX(), badGuy.getY())) {
				badGuyBoolean = false;
				badGuy = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY BAD GUY CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----BAD GUY CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			
			// player hit by alien
			else if (alienBoolean && thingHit(player.getX(), player.getY(), alien.getX(), alien.getY())) {
				alienBoolean = false;
				alien = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY ALIEN CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----ALIEN CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			
			// top Dreadnought Missile hit player
			else if (topDreadnoughtMissileExists && missileHitPlayer(dTMissile.getX(), dTMissile.getY(), player.getX(), player.getY())) {
				topDreadnoughtMissileExists = false;
				dTMissile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY DREADNOUGHT MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----DREADNOUGHT MISSILE----");
				}
				// Sound
				// possible explode sequence
			}

			// mid Dreadnought Missile hit player
			else if (midDreadnoughtMissileExists && missileHitPlayer(dMMissile.getX(), dMMissile.getY(), player.getX(), player.getY())) {
				midDreadnoughtMissileExists = false;
				dMMissile = null;
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY DREADNOUGHT MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----DREADNOUGHT MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			
			// bot Dreadnought Missile hit player
			else if (botDreadnoughtMissileExists && missileHitPlayer(dBMissile.getX(), dBMissile.getY(), player.getX(), player.getY())) {
				botDreadnoughtMissileExists = false;
				dBMissile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY DREADNOUGHT MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----DREADNOUGHT MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// homing Dreadnought Missile hit player
			else if (homDreadnoughtMissileExists && missileHitPlayer(dHMissile.getX(), dHMissile.getY(), player.getX(), player.getY())) {
				homDreadnoughtMissileExists = false;
				dHMissile.setX(-500);
				dHMissile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HOMING MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HOMING MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// hive1 Missile hit player
			else if (h1MissileExists && missileHitPlayer(h1Missile.getX(), h1Missile.getY(), player.getX(), player.getY())) {
				h1MissileExists = false;
				h1Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive1
			else if (hive1Boolean && thingHit(player.getX(), player.getY(), hive1.getX(), hive1.getY())) {
				hive1Boolean = false;
				hive1 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// hive2 Missile hit player
			else if (h2MissileExists && missileHitPlayer(h2Missile.getX(), h2Missile.getY(), player.getX(), player.getY())) {
				h2MissileExists = false;
				h2Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive2
			else if (hive2Boolean && thingHit(player.getX(), player.getY(), hive2.getX(), hive2.getY())) {
				hive2Boolean = false;
				hive2 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// hive3 Missile hit player
			else if (h3MissileExists && missileHitPlayer(h3Missile.getX(), h3Missile.getY(), player.getX(), player.getY())) {
				h3MissileExists = false;
				h3Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive3
			else if (hive3Boolean && thingHit(player.getX(), player.getY(), hive3.getX(), hive3.getY())) {
				hive3Boolean = false;
				hive3 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// hive4 Missile hit player
			else if (h4MissileExists && missileHitPlayer(h4Missile.getX(), h4Missile.getY(), player.getX(), player.getY())) {
				h4MissileExists = false;
				h4Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive4
			else if (hive4Boolean && thingHit(player.getX(), player.getY(), hive4.getX(), hive4.getY())) {
				hive4Boolean = false;
				hive4 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// hive5 Missile hit player
			else if (h5MissileExists && missileHitPlayer(h5Missile.getX(), h5Missile.getY(), player.getX(), player.getY())) {
				h5MissileExists = false;
				h5Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive5
			else if (hive5Boolean && thingHit(player.getX(), player.getY(), hive5.getX(), hive5.getY())) {
				hive5Boolean = false;
				hive5 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// hive6 Missile hit player
			else if (h6MissileExists && missileHitPlayer(h6Missile.getX(), h6Missile.getY(), player.getX(), player.getY())) {
				h6MissileExists = false;
				h6Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive6
			else if (hive6Boolean && thingHit(player.getX(), player.getY(), hive6.getX(), hive6.getY())) {
				hive6Boolean = false;
				hive6 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}

			// hive7 Missile hit player
			else if (h7MissileExists && missileHitPlayer(h7Missile.getX(), h7Missile.getY(), player.getX(), player.getY())) {
				h7MissileExists = false;
				h7Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive7
			else if (hive7Boolean && thingHit(player.getX(), player.getY(), hive7.getX(), hive7.getY())) {
				hive7Boolean = false;
				hive7 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// hive8 Missile hit player
			else if (h8MissileExists && missileHitPlayer(h8Missile.getX(), h8Missile.getY(), player.getX(), player.getY())) {
				h8MissileExists = false;
				h8Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive8
			else if (hive8Boolean && thingHit(player.getX(), player.getY(), hive8.getX(), hive8.getY())) {
				hive8Boolean = false;
				hive8 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			// hive9 Missile hit player
			else if (h9MissileExists && missileHitPlayer(h9Missile.getX(), h9Missile.getY(), player.getX(), player.getY())) {
				h9MissileExists = false;
				h9Missile = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE MISSILE----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE MISSILE----");
				}
				// Sound
				// possible explode sequence
			}
			// player hit by hive9
			else if (hive9Boolean && thingHit(player.getX(), player.getY(), hive9.getX(), hive9.getY())) {
				hive9Boolean = false;
				hive9 = null;
				switch (health) {
				case 1: shield1 = null;
					break;
				case 2: shield2 = null;
					break;
				case 3: shield3 = null;
					break;
				case 4: shield4 = null;
					break;
				case 5: shield5 = null;
					break;
				default: shield1 = null;
					break;	
				}
				health--;
				//playerAlive = false;
				//player = null;
				if (health <= 0) {
					System.out.println("----DEATH BY HIVE CRASH----");
				}
				else {
					hurtSound.start();
					hurtSound.setFramePosition(0);
					System.out.println("----HIVE CRASH----");
				}
				// player explode
				// sound
				// game over screen
			}
			
			
			/*
			 * Power-ups
			 */
			
			if (playerAlive && wrenchExists 
					&& thingHit(player.getX(), player.getY(), wrench.getX(), wrench.getY())) {
				wrenchExists = false;
				wrench = null;
				if (wrenchSound.isRunning()) {
					wrenchSound.stop();
				}
				wrenchSound.start();
				wrenchSound.setFramePosition(0);
				switch (health) {
					case 1: shield2 = new Shield(2);
						System.out.println("----SHIELDS REPAIRED----");
						health++;
						break;
					case 2: shield3 = new Shield(3);
						System.out.println("----SHIELDS REPAIRED----");
						health++;
						break;
					case 3: shield4 = new Shield(4);
						System.out.println("----SHIELDS OVERCHARGED ----");
						health++;
						break;
					case 4: shield5 = new Shield(5);
						System.out.println("----SHIELDS OVERCHARGED----");
						health++;
						break;
				}
				// sound
			}
			/**
			 * Triple shot
			 */
			if (playerAlive && !player.getTripleShot() && tripleShotExists
					&& thingHit(player.getX(), player.getY(), tripleShot.getX(), tripleShot.getY())) {
				player.setTripleShot(true);
				tripleShotExists = false;
				tripleShot = null;
				if (tripleShotSound.isRunning()) {
					tripleShotSound.stop();
				}
				tripleShotSound.start();
				tripleShotSound.setFramePosition(0);
			}
			

		}
		else {
			if (keepScore) {
				pExplosionSound.start();
				pExplosionSound.setFramePosition(0);
				
				keepScore = false;
				executor.shutdownNow();
				executor = null;
				player.setY(200);
				dreadnoughtTime = false;
				hiveTime = false;
				madeDreadnought = false;
				madeHive = false;
				backgroundBoolean = false;
				background = null;
				background2 = null;
				if (pMissile != null) {
					pMissile.setY(200);
					pMissile.setX(20);
					pMissile = null;
					missileExists = false;
				}
				if (uppMissile != null) {
					uppMissile.setY(200);
					uppMissile.setX(20);
					uppMissile = null;
					upMissileExists = false;
				}
				if (dnpMissile != null) {
					dnpMissile.setY(200);
					dnpMissile.setX(20);
					dnpMissile = null;
					dnMissileExists = false;
				}
				if (asteroid != null) {
					asteroidBoolean = false;
					asteroid = null;
				}
				if (asteroidA != null) {
					asteroidBooleanA = false;
					asteroidA = null;
				}
				if (asteroidB != null) {
					asteroidBooleanB = false;
					asteroidB = null;
				}
				if (alien != null) {
					alienBoolean = false;
					alien = null;
				}
				if (badGuy != null) {
					badGuyBoolean = false;
					badGuy = null;
				}
				if (dreadnought != null) {
					dreadnoughtTime = false;
					madeDreadnought = false;
					dreadnoughtAlive = false;
					dreadnought = null;
				}
				if (aMissile != null) {
					aMissile.setY(200);
					aMissile.setX(20);
					aMissile = null;
					alienMissileExists = false;
				}
				if (bMissile != null) {
					bMissile.setY(200);
					bMissile.setX(20);
					bMissile = null;
					badGuyMissileExists = false;
				}
				if (dTMissile != null) {
					dTMissile.setY(200);
					dTMissile.setX(20);
					dTMissile = null;
					topDreadnoughtMissileExists = false;
				}
				if (dBMissile != null) {
					dBMissile.setY(200);
					dBMissile.setX(20);
					dBMissile = null;
					botDreadnoughtMissileExists = false;
				}
				if (dHMissile != null) {
					dHMissile.setY(200);
					dHMissile.setX(20);
					dHMissile = null;
					homDreadnoughtMissileExists = false;
				}
				if (hive1 != null) {
					hive1.resetX();
					hive1Boolean = false;
					hive1 = null;
				}
				if (h1Missile != null) {
					h1Missile.setY(200);
					h1Missile.setX(20);
					h1Missile = null;
					h1MissileExists = false;
				}
				if (hive2 != null) {
					hive2.resetX();
					hive2Boolean = false;
					hive2 = null;
				}
				if (h2Missile != null) {
					h2Missile.setY(200);
					h2Missile.setX(20);
					h2Missile = null;
					h2MissileExists = false;
				}
				if (hive3 != null) {
					hive3.resetX();
					hive3Boolean = false;
					hive3 = null;
				}
				if (h3Missile != null) {
					h3Missile.setY(200);
					h3Missile.setX(20);
					h3Missile = null;
					h3MissileExists = false;
				}
				if (hive4 != null) {
					hive4.resetX();
					hive4Boolean = false;
					hive4 = null;
				}
				if (h4Missile != null) {
					h4Missile.setY(200);
					h4Missile.setX(20);
					h4Missile = null;
					h4MissileExists = false;
				}
				if (hive5 != null) {
					hive5Boolean = false;
					hive5 = null;
				}
				if (h5Missile != null) {
					h5Missile.setY(200);
					h5Missile.setX(20);
					h5Missile = null;
					h5MissileExists = false;
				}
				if (hive6 != null) {
					hive6.resetX();
					hive6Boolean = false;
					hive6 = null;
				}
				if (h6Missile != null) {
					h6Missile.setY(200);
					h6Missile.setX(20);
					h6Missile = null;
					h6MissileExists = false;
				}
				if (hive7 != null) {
					hive7.resetX();
					hive7Boolean = false;
					hive7 = null;
				}
				if (h7Missile != null) {
					h7Missile.setY(200);
					h7Missile.setX(20);
					h7Missile = null;
					h7MissileExists = false;
				}
				if (hive8 != null) {
					hive8.resetX();
					hive8Boolean = false;
					hive8 = null;
				}
				if (h8Missile != null) {
					h8Missile.setY(200);
					h8Missile.setX(20);
					h8Missile = null;
					h8MissileExists = false;
				}
				if (hive9 != null) {
					hive9.resetX();
					hive9Boolean = false;
					hive9 = null;
				}
				if (h9Missile != null) {
					h9Missile.setY(200);
					h9Missile.setX(20);
					h9Missile = null;
					h9MissileExists = false;
				}
				if (wrench != null) {
					wrenchExists = false;
					wrench = null;
				}
				if (tripleShot != null) {
					tripleShotExists = false;
					tripleShot = null;
				}
			}
			restartButton.setAlignmentX(JButton.CENTER);
			restartButton.setEnabled(true);
			restartButton.setVisible(true);
			restartButton.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					player = new Player();
					playerAlive = true;
					restartButton.setEnabled(false);
					restartButton.setVisible(false);
					score = 0;
					health = 3;
					shield1 = new Shield(1);
					shield2 = new Shield(2);
					shield3 = new Shield(3);
					//repaint();
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
			});
		}
		/**
		 * Make score box at lowest 100
		 */
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 450, 1000, 100);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(5, 455, 985, 90);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(10, 460, 975, 80);
		
		
		/**
		 * Score on the right
		 */
		String scoreString = Integer.toString(score);
		int index = scoreString.length() - 1;
		int spot = 950;
		while (index >= 0) {
			switch (Integer.parseInt(scoreString.substring(index, index + 1))) {
			case 1:
				g2d.drawImage(score1, spot, scoreY, null);
				break;
			case 2:
				g2d.drawImage(score2, spot, scoreY, null);
				break;
			case 3:
				g2d.drawImage(score3, spot, scoreY, null);
				break;
			case 4:
				g2d.drawImage(score4, spot, scoreY, null);
				break;
			case 5:
				g2d.drawImage(score5, spot, scoreY, null);
				break;
			case 6:
				g2d.drawImage(score6, spot, scoreY, null);
				break;
			case 7:
				g2d.drawImage(score7, spot, scoreY, null);
				break;
			case 8:
				g2d.drawImage(score8, spot, scoreY, null);
				break;
			case 9:
				g2d.drawImage(score9, spot, scoreY, null);
				break;
			default:
				g2d.drawImage(score0, spot, scoreY, null);
				break;
			}			
			spot -= 25;
			index--;
		}
		
		/**
		 * Health on left
		 */
		switch (health) {
			case 5:
				g2d.drawImage(shieldImage, shield5.getX(), shield5.getY(), null);
			case 4:
				g2d.drawImage(shieldImage, shield4.getX(), shield4.getY(), null);
			case 3:
				g2d.drawImage(shieldImage, shield3.getX(), shield3.getY(), null);
			case 2:
				g2d.drawImage(shieldImage, shield2.getX(), shield2.getY(), null);
			case 1:
				g2d.drawImage(shieldImage, shield1.getX(), shield1.getY(), null);
				break;
		}
		//TODO add a pause/menu button
		
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private boolean missileHitThing(int missileX, int missileY, int thingX, int thingY) {
		Rectangle t = new Rectangle(thingX + 40, thingY, 50, 50);
		Rectangle m = new Rectangle(missileX, missileY, 40, 20);
		return t.intersects(m);
	}
	
	private boolean missileHitDreadnought(int missileX, int missileY, int thingX, int thingY) {
		Rectangle t = new Rectangle(thingX + 40, thingY, 150, 185);
		Rectangle m = new Rectangle(missileX, missileY, 40, 20);
		return t.intersects(m);
	}

	private boolean missileHitPlayer(int missileX, int missileY, int playerX, int playerY) {
		Rectangle p = new Rectangle(playerX + 10, playerY + 12, 25, 25);
		Rectangle m = new Rectangle(missileX, missileY, 40, 20);
		return p.intersects(m);
	}
	
	private boolean thingHit(int playerX, int playerY, int thingX, int thingY) {
		Rectangle p = new Rectangle(playerX + 10, playerY + 12, 25, 25);
		Rectangle t = new Rectangle(thingX, thingY, 50, 50);
		return p.intersects(t);
	}
	/**
	 * Create new missile when you hit space bar
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (playerAlive) {
			player.move();
			missileInitialY = player.shipY + 15;
			if (!missileExists && player.getCount() == 1 && !player.getTripleShot()) {
				if (pShootSound.isRunning()) {
					pShootSound.stop();
				}
				pShootSound.start();
				pShootSound.setFramePosition(0);
				pMissile = new Missile(missileInitialY);
				// pMissile.setY(missileInitialY);
				missileExists = true;
				pMissile.moveMissle();
			}
			else if (!missileExists && player.getCount() == 1 && player.getTripleShot()) {
				pMissile = new Missile(missileInitialY);
				uppMissile = new Missile(missileInitialY, true);
				dnpMissile = new Missile(missileInitialY, false);
				missileExists = true;
				upMissileExists = true;
				dnMissileExists = true;
				pMissile.moveMissle();
				uppMissile.moveMissle();
				dnpMissile.moveMissle();
				//sound
			}
		}
		repaint();
	}
	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			if (playerAlive) {
				player.keyReleased(e);
			}
		}
		public void keyPressed(KeyEvent e) {
			if (playerAlive) {
				player.keyPressed(e);
			}
		}
	}
}
