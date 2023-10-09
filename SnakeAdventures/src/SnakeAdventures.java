import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.*;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JPanel;

/* CREATED BY ARNOLD
 * GAME Final Checked on 6/9/21 
 * Assign 1, Games Programming
 *
 * GAME Checked and Uploaded to Github 9/10/23
 *
 */

public class SnakeAdventures extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L; // Our unique Identifier for this class.
	//GameFrame gameframe = new GameFrame();
	static final int SCREEN_WIDTH = 600;  // GAME Window is set at 600 width
	static final int SCREEN_HEIGHT = 600; // GAME Window is set at 600 Height 
	 int UNIT_SIZE = 20; // how many units we want in the game  //25 is best
	// by changing the unit size, this will change how big we want the items in the game to be. A smaller unit size means that
	// we have more space on our screen.
	 
	 // ++++++++++++ COMPLETED ++++++++++++++++  I left the Unit Size to be 20. Any changes to this and the game will break
	
   int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // 18000 with 600 X 600
	// final int DELAY = 90; //higher the number is the slower the game is. //85 recomeneded
	int DELAY = 90;  // Normal speed that the game should be played at   6/9/21
	
	//Create two arrays
	// This is for our snake and will keep track of the postions on the the grid. 
	final int posX[] = new int[GAME_UNITS]; 
	final int posY[] = new int[GAME_UNITS];
	
	//set the body parts of the snake
	int bodyParts = 3; // we can change this value when testing to something small or large. could to set to values: 2 or 5
	// I left at 3 as this was a perfect number for when the game starts. 
	
	int applesEaten;  // Is Our Score pretty much 
	
	
	// Cordinates of X and Y for Apple
	int appleX;
	int appleY;
	//------------------------------
	//Cordinates of X and Y for evilApple
	int evilappleX;
	int evilappleY;
	//Cordinates of X and Y for evilApple2
	int evilapple2X;
	int evilapple2Y;;
	//Cordinates of X and Y for evilApple3
	int evilapple3X;
	int evilapple3Y;
	
	//Cordinates of X and Y for bonusApple
	int bonusappleX;
	int bonusappleY;
	
	int ingamemenupause = 0;
	
	int second;
	int second2;
	int minute;
	int testvalue = 0;
	int lives = 3;
	
	//----- Score System --------
	int previousscore = 0;
	int highestscrore = 0;
	int alltimebest = 0;
	int gameoverscore = 0;
	
	int restartviagameover = 0;

	
	String previousminute;
	String previoussecond;

	// Used for creating our FPS ------  6/9/21 I decide to leave this out 
	
	private static long lastFPSCheck = 0;
	private static int currentFPS = 0; 
	private static int totalframes = 0;
	//--------------------------------------------------
	
	
	char direction = 'R'; // you can have D for down, R for right, U for up. Can be changed when you start the game
			// Have set that the snake will move to the right when the game starts.
	
	boolean game_Running = false;
	
	/* Need these for when the statements occur and lives etc. It will stop the game from playing it in 
	a forever loop 
	*/
	
	boolean youronarollstatement = true;  
	boolean gameoverlessthan3 = true;
	boolean amazingstatement = true;
	boolean scorespreestatement = true;
	boolean livesout = true;
	boolean unfrigstatement = true;
	boolean gameoverstatement = true;
	
	boolean ogpostion = true;
	boolean bonusapplestatement = true;
	
	
	//------------- TIME OPTIONS --------------------
	int stopped = 0;
	
	Timer timer; 
	Timer timer2;
	Timer timer3;
	Random random;
	Random random2;
	static boolean gameOn = false;
	String Grid = "OFF"; // changed to OFF so no Grid was orginall ON
	int gameStatus = 0;
	String previoustime = "00:00";
	String gameovertime;
	
	Graphics2D mGraphics;
	
	// My enum functions with states. These worked really well in my 
	// game and helped me a lot. 
	
	public static enum STATE{
		MENU,
		GAME,
		PAUSED,
		GAMEOVER,
		RESTART,
		INGAMEMENU,
		OPTIONS,
		INGAMEOPTIONS;
	};
	
	// When game Starts the default state will be MenU
	
	public static STATE State = STATE.MENU;
	
	Runnable runnable = null;
	
	// All my Audio Imports.
	
	GameFrame.AudioClip beep; 
	GameFrame.AudioClip background;
	GameFrame.AudioClip Menugame;
	GameFrame.AudioClip appleeaten;
	GameFrame.AudioClip levelmusic;
	GameFrame.AudioClip movesound;
	GameFrame.AudioClip movement;
	GameFrame.AudioClip gamesound; 
	GameFrame.AudioClip opensound;
	GameFrame.AudioClip gameover;
	GameFrame.AudioClip youronaroll;
	GameFrame.AudioClip amazing;
	GameFrame.AudioClip scorespree;
	GameFrame.AudioClip evilapplesound;
	GameFrame.AudioClip gamepaused;
	GameFrame.AudioClip gameresumed;
	GameFrame.AudioClip unfrig; 

	GameFrame.AudioClip heartlost; 
	GameFrame.AudioClip bonusapplesound;
	GameFrame.AudioClip menuoptionscroll;
	GameFrame.AudioClip menuselect;
	GameFrame.AudioClip restartsound; 
	GameFrame.AudioClip gridsound;
	GameFrame.AudioClip menumusic;
	GameFrame.AudioClip gameovermusic;
	
// All Image Imports
	Image apple;
	Image snakebody;
	Image snakehead;
	Image menubackground;
	Image heart;
	Image heart2; 
	Image heart3;
	Image evilapple;
	Image bonusapple;
	
	Image title;
	Image titlecreated; 
	
	Image space;
	Image backgroundchange;
	Image finalbackgroundchange;
	
	
	Image pause;
	Image powerup;
	int menuoption = 0;
	
	int mainmenuoption = 0;
	
String ddSecond = "00", ddMinute = "00";	
DecimalFormat dFormat = new DecimalFormat("00");		
		
	
public void simpleTimer() {
	timer2 = new Timer(1000, new ActionListener() { // 1000 for 1 second

	@Override
	public void actionPerformed(ActionEvent e) {
		 second++;
		 second2++;
		 ddSecond = dFormat.format(second);
		 ddMinute = dFormat.format(minute);
		 
		 if(second2 == 15) {
			// System.out.println("3 Seconds has passed");
			 second2 = 0;
			 newbonusApple();
		 }
		 
		 if (second2 == 5) {
			 bonusappleX = -20;
				bonusappleY = 360;
		 }
		/*  I will try this again in the future when making more implemenations.
		 		totalframes++;	
			if (System.nanoTime() > lastFPSCheck + 1000000000) {
				lastFPSCheck = System.nanoTime();
				currentFPS = totalframes;
				totalframes = 0;
				System.out.println("FPS: " + currentFPS);
			} */
	
		 if(second == 60) {
			 second = 0; 
			 minute++;
			 ddSecond = dFormat.format(second);
			 ddMinute = dFormat.format(minute);
		 }	
	}	
	});
	}
	





	SnakeAdventures() {
		///System.out.println("Snake Adventures is called");
		
		// ----------------- SOUNDS and Music ----------------------------
		appleeaten= GameFrame.loadAudio("Sounds/beepapple.wav");
		bonusapplesound = GameFrame.loadAudio("Sounds/powerup.wav");
		levelmusic = GameFrame.loadAudio("Sounds/levelmusic.wav");
		movesound = GameFrame.loadAudio("Sounds/movesound.wav");
		movement = GameFrame.loadAudio("Sounds/sfx_movement_footsteps5.wav");
		opensound = GameFrame.loadAudio("Sounds/SnakeMusic.wav");
		evilapplesound = GameFrame.loadAudio("Sounds/pls.wav");
		gamepaused = GameFrame.loadAudio("Sounds/gamepaused.wav");
		//---------------- Voice Sound Effects -------------------------		
		gameover = GameFrame.loadAudio("Sounds/gameover.wav");
		gameresumed = GameFrame.loadAudio("Sounds/gameresumed.wav");
		gameovermusic = GameFrame.loadAudio("Sounds/death.wav");
		heartlost = GameFrame.loadAudio("Sounds/pls.wav");
		amazing = GameFrame.loadAudio("Sounds/amazing.wav");
		youronaroll = GameFrame.loadAudio("Sounds/youronaroll.wav");
		scorespree = GameFrame.loadAudio("Sounds/scorespree.wav");
		unfrig = GameFrame.loadAudio("Sounds/unfrig.wav");
		
		//-----------------------Images ------------------------------------------------------------
		space = GameFrame.loadImage("Images/space.png");
		apple = GameFrame.loadImage("Images/newapple.png");
		evilapple = GameFrame.loadImage("Images/newevilapple.png");
		bonusapple = GameFrame.loadImage("Images/bonusapple.png");
		title = GameFrame.loadImage("Images/cooltext.png");
		titlecreated = GameFrame.loadImage("Images/createdby.png"); 
		backgroundchange = GameFrame.loadImage("Images/newbackground.png");
		finalbackgroundchange = GameFrame.loadImage("Images/finalbackgroundchange.png");
		
		
		menuoptionscroll = GameFrame.loadAudio("Sounds/sfx_menu_move2.wav");
		menuselect = GameFrame.loadAudio("Sounds/sfx_menu_select1.wav");
		restartsound = GameFrame.loadAudio("Sounds/sfx_sounds_interaction1.wav"); 
		gridsound = GameFrame.loadAudio("Sounds/sfx_sounds_interaction17.wav");
		menumusic = GameFrame.loadAudio("Sounds/awesomeness.wav");
		gameovermusic = GameFrame.loadAudio("Sounds/Game Over.wav");
		
		
		random = new Random();
		random2 = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT)); // Setting the game to the resoultion we have made static
		
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(new MouseInput());
		timer = new Timer(DELAY,this);
		simpleTimer();
		menuStart();
	}
	
	public void menuStart() {
		GameFrame.startAudioLoop(menumusic, -20);
		timer.start();	
	}
	
	public void restartwithTimer() {
		//previousscore = gameoverscore;
		previousscore = applesEaten;
		
		// Used for keeping track of all the scores after the game has restarted or it went to gameover then restarted.
		if (gameoverscore > previousscore) {
			previousscore = gameoverscore;
		}
		
		
		if (previousscore > alltimebest) {
			alltimebest = previousscore;
		}
		
		if (gameoverscore > alltimebest) {
			alltimebest = gameoverscore;
		}
		//------------------------
		GameFrame.stopAudioLoop(gameovermusic);
		game_Running = false;
		timer.restart();
		timer2.restart();
		bodyParts = 3;
		livesout = true;
		lives = 3;
		applesEaten = 0; // which is score.
		ogpostion = true;
		direction = 'R';
		 youronarollstatement = true;
		amazingstatement = true;
		scorespreestatement = true;
		unfrigstatement = true;
		
		 bonusapplestatement = true;

		 // Makes snake postion allways spawn in the middle 
		 
		    posX[0] = 300;
			posY[0] = 320;
			
			posX[1] = 280;
			posY[1] = 320;
			
			posX[2] = 260;
			posY[2] = 320;
		 
		//running = false;
		//State = STATE.GAME;
		
		
		startGame();
		State = STATE.GAME;
	}
	
	public void startGame() {
		System.out.println("Start Game is Called");
		GameFrame.stopAudioLoop(menumusic);
		//gameframe.test();
		DELAY = 90;
		newApple();
		newevilApple();
		game_Running = true; //needs to begin with true.
		//timer = new Timer(DELAY,this); //uses our set time method that we have declared at the top of the page
		//this.setBackground(Color.yellow);
		second = 0;
		minute = 0;
		
		// To make sure that when the game starts that the collisons are correct
		
		// Make bonus apple get drawn off screen when game launches
		bonusappleX = -20;
		bonusappleY = 360;
		// Same applies for evil apple 2 and 3
		evilapple2X = -40;
		evilapple2Y = 360;
		
		evilapple3X = -60;
		evilapple3Y = 360;
		// ------------------
		
		// draws in the postion of the snake for the center of the board.
		posX[0] = 300;
		posY[0] = 320;
		posX[1] = 280;
		posY[1] = 320;
		posX[2] = 260;
		posY[2] = 320;
		
		timer2.start();
		timer.start();
		
		GameFrame.startAudioLoop(opensound, -30);
			
	}
	
	public void changeintime() {	
	/* We want the delay to be in for stages
		normal delay is 90 
		
		Higher number mean game is slower, 
		lower number the game is faster.
		
		User can change speeds with plus and minus key and also use the numpad ones too. 
		30 - fastest
		60 - fast
		90  - normal
		120  - slow 
		150 - slowest
		 */
		if (DELAY > 150 ) {
			DELAY = 150;
		
			timer.setDelay(DELAY);
			
			System.out.println("Time DELAY is:" + DELAY);
		}
		
		if (DELAY < 30) {
			DELAY = 30;
			timer.setDelay(DELAY);
			System.out.println("Time DELAY is:" + DELAY);
		}
		
		else {
			timer.setDelay(DELAY);
			System.out.println("Time DELAY is:" + DELAY);
		}	
	}
	
	public void paused() {
		SnakeAdventures.gameOn = true;
		stopped = 1;
		System.out.println();
		System.out.println("Game is paused via pause function");
	
	}
	
	public void resume() {
		SnakeAdventures.gameOn = false;
		timer.start();
		timer2.start();
		System.out.println();
		System.out.println("Game Resumed");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g); // makes a call to a draw component and will draw in our matrix	
	}

	public void draw(Graphics mGraphics)  {
		if (State == STATE.OPTIONS) {
			mGraphics.drawImage(space, 0, 0, 600, 600, this);
			title = GameFrame.loadImage("Images/cooltext.png");
			mGraphics.drawImage(title, 50, 0, 500, 100, this);
			HelpsviaMenu.render(mGraphics, mainmenuoption);
		}
		
	if (State == STATE.INGAMEOPTIONS) {
			mGraphics.drawImage(space, 0, 0, 600, 600, this);
			title = GameFrame.loadImage("Images/cooltext.png");
			mGraphics.drawImage(title, 50, 0, 500, 100, this);
			HelpsviaMenu.render(mGraphics, mainmenuoption);
		}
		
		
		if (State == STATE.PAUSED) {
			System.out.println("Drawing Pause Menu");
			gamePaused(mGraphics);
			
		}
		
		if(State == STATE.MENU) {
			mGraphics.drawImage(space, 0, 0, 600, 600, this);
			mGraphics.drawImage(title, 50, 0, 500, 100, this);
			mGraphics.drawImage(titlecreated, 50, 500, 500, 100, this);
			MainMenu.render(mGraphics, mainmenuoption);
		}
		
		if(State == STATE.INGAMEMENU) {
			mGraphics.drawImage(space, 0, 0, 600, 600, this);
			
			//title = GameFrame.loadImage("cooltext.png");
			mGraphics.drawImage(title, 50, 0, 500, 100, this);
			
			mGraphics.drawImage(titlecreated, 50, 500, 500, 100, this);
			//System.out.println(mainmenuoption);
			InGameMenu.render(mGraphics, mainmenuoption);
			ingamemenupause = 1;
			
			GameFrame.stopAudioLoop(opensound);
		}
		
		if(State == STATE.GAME) {
			
			//System.out.println("Game is playing");
			if (stopped == 1) {
				this.setBackground(Color.black);
			//mGraphics.clearRect(appleX, DELAY, SCREEN_WIDTH, SCREEN_HEIGHT);
			}
			
			this.setBackground(Color.black);
		
		 if (game_Running || State == STATE.RESTART ) {
	
			 if (lives == 2) {
				 mGraphics.drawImage(backgroundchange, 0, 0, 600, 600, this);
			 }
			 else 
				 
			 if (lives == 1) {
				 mGraphics.drawImage(finalbackgroundchange, 0, 0, 600, 600, this);
			 }
			 
			 else {
			 
			 mGraphics.drawImage(space, 0, 0, 600, 600, this);
			 }
			 
			
			 
			 if (stopped == 1) {  // this is the correct one and will work for our pause method
					System.out.println("Drawing Pause Menu after runing if");
					
					gamePaused(mGraphics);
					stopped = 2;
					timer.stop();
					timer2.stop();
				}
			 
			 // Used for creating our top box oon the screen which will draw this in and fill it.
			 
			 Rectangle topBox = new Rectangle(0, 0, 600, 60);
				mGraphics.setColor(Color.white);
				((Graphics2D) mGraphics).draw(topBox);
				mGraphics.setColor(Color.gray);
				((Graphics2D) mGraphics).fill(topBox); 
				
				// -------------------------  End of drawing box ------------------------------
		
			
			if (Grid == "ON") {

			for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {  // will go by height divided by unit size
				mGraphics.setColor(Color.gray);
				
				mGraphics.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT); //Create our vertical lines of matrix
				mGraphics.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE); //Creates our Horizontal lines of a matrix	
			}
			
			mGraphics.setColor(Color.white);
			mGraphics.setFont(new Font("arial", Font.BOLD, 20));
		mGraphics.drawString("Grid:", 440, 44);
		mGraphics.setColor(Color.blue);
		mGraphics.drawString(" ON ", 490, 44);

			} 
			if (Grid == "OFF") {
				mGraphics.setColor(Color.white);
				mGraphics.setFont(new Font("arial", Font.BOLD, 20));
				mGraphics.drawString("Grid:", 440, 44);
				mGraphics.setColor(Color.red);
				mGraphics.drawString(" OFF ", 490, 44);
			}
			
			//--- end ------
			// Grid section ends here.
			
		
		// ------------------	OBJECT DRAWING -------------------------------------
			
	
			mGraphics.drawImage(apple, appleX, appleY, UNIT_SIZE, UNIT_SIZE, this);

			mGraphics.drawImage(evilapple, evilappleX, evilappleY, UNIT_SIZE, UNIT_SIZE, this);
	
			mGraphics.drawImage(bonusapple, bonusappleX, bonusappleY, UNIT_SIZE, UNIT_SIZE, this);
			
			mGraphics.drawImage(evilapple, evilapple2X, evilapple2Y, UNIT_SIZE, UNIT_SIZE, this);
			mGraphics.drawImage(evilapple, evilapple3X, evilapple3Y, UNIT_SIZE, UNIT_SIZE, this);
		
			if (applesEaten == 0 && ogpostion == true) {
				ogpostion = false;
				
				// This cordinates are for if the window is at 600 x 600 
				posX[0] = 300;
				posY[0] = 320;
				
				posX[1] = 280;
				posY[1] = 320;
				
				posX[2] = 260;
				posY[2] = 320;
				
			for(int i = 0; i < bodyParts; i++) {
				if (i == 0) { // when I is 0 its spawns the first postions of the snake to be the head
					snakehead = GameFrame.loadImage("Images/head.png");
					mGraphics.drawImage(snakehead, 300, 320, UNIT_SIZE, UNIT_SIZE, this); // head
				}
			else {
			
				// Once its got the head, the remaining snake parts are part of the body. 
				snakebody = GameFrame.loadImage("Images/dot.png");
				mGraphics.drawImage(snakebody, 280, 320, UNIT_SIZE, UNIT_SIZE, this); // body part
			}
		}
			}
		
			else {
			
				for(int i = 0; i < bodyParts; i++) {
					if (i == 0) { // when I is 0 its spawns the first postions of the snake to be the head
						snakehead = GameFrame.loadImage("Images/head.png");
						mGraphics.drawImage(snakehead, posX[i], posY[i], UNIT_SIZE, UNIT_SIZE, this);
					}
						
				else {
	
					// Once its got the head, the remaining snake parts are part of the body. 
					snakebody = GameFrame.loadImage("Images/dot.png");
					mGraphics.drawImage(snakebody, posX[i], posY[i], UNIT_SIZE, UNIT_SIZE, this);
					//mGraphics.setColor(new Color(0, 0, 255)); //rgb colour
					
				}
			}
		
			} 
			
				if(lives == 3) {
				heart = GameFrame.loadImage("Images/heart.png");
				heart2 = GameFrame.loadImage("Images/heart.png");
				heart3 = GameFrame.loadImage("Images/heart.png"); 
				 mGraphics.drawImage(heart, 120, 5, UNIT_SIZE, UNIT_SIZE, this);
				 mGraphics.drawImage(heart2, 140, 5, UNIT_SIZE, UNIT_SIZE, this);
				 mGraphics.drawImage(heart3, 160, 5, UNIT_SIZE, UNIT_SIZE, this);
				}
				
				if(lives == 2) {		
					heart = GameFrame.loadImage("Images/heart.png");
					heart2 = GameFrame.loadImage("Images/heart.png");
					 mGraphics.drawImage(heart, 120, 5, UNIT_SIZE, UNIT_SIZE, this);
					 mGraphics.drawImage(heart2, 140, 5, UNIT_SIZE, UNIT_SIZE, this);	
				}
				
				if (lives == 1) {
					heart = GameFrame.loadImage("Images/heart.png");			
					 mGraphics.drawImage(heart, 120, 5, UNIT_SIZE, UNIT_SIZE, this);
			
				}
			
				
				// this sequence is working but again the same issue occurs where the gameOver method appears then disapears.
				if (lives == 0 && livesout == true) {
					System.out.println("Game over all hearts gone");
					//bodyParts = 3;
					gameOver(mGraphics);
					livesout = false;
					State = STATE.GAMEOVER;
				}
				

				mGraphics.setColor(Color.white);
				mGraphics.setFont(new Font("arial", Font.BOLD, 20));
			mGraphics.drawString("Score: " + applesEaten, 10, 24);
			mGraphics.drawString("Previous:" + previousscore, 10, 44);
			
			
			//------------------------------- Timer testing-----------------------------

			mGraphics.drawString("Time: " + ddMinute + ":" + ddSecond, 250, 24);
			mGraphics.drawString("Best Time: " + previoustime, 220, 44);
			
			
			
			if (applesEaten == 5 && youronarollstatement == true) {
				GameFrame.playAudio(youronaroll);
				youronarollstatement = false;
				//break;
			}
			
			if (applesEaten == 10 && amazingstatement == true) {
				GameFrame.playAudio(amazing);
				amazingstatement = false;
			}
			
			if(applesEaten > 8 && scorespreestatement == true) {
				GameFrame.playAudio(scorespree);
				scorespreestatement = false;			
			}
			
			
			if (applesEaten == 12 && unfrigstatement == true ) {
				GameFrame.playAudio(unfrig);
				unfrigstatement = false;	
			}
			
			
			
			
			mGraphics.setColor(Color.white);
			mGraphics.setFont(new Font("arial", Font.BOLD, 20));	
			mGraphics.drawString("Snake Length: " + bodyParts, 430, 24);		
	}
		
		else {  // we will create our Game Over Method here
			gameOver(mGraphics);
			
		}

		}	// end of state loop 	
		
		else if (State == STATE.MENU) { // will go in here for what our menu is going to look like.	
		}
		
		if (State == STATE.PAUSED) {	
			//paused.render(mGraphics);
		}
		
		
		if (State == STATE.GAMEOVER) {
			System.out.println("Gameover via State statement in draw");
			gameOver(mGraphics);
			//gameoverstatement = false;
			//State = STATE.GAME;
		}			
}
	
	
	public void Movement() {
		if(ingamemenupause != 1) { // When in gamemenu is pressed, the snake will stop moving.
		
		
		for(int i = bodyParts; i > 0; i--) {
			posX[i] = posX[i-1]; // will shift our parts across on the x-axis
			posY[i] = posY[i-1]; // will shift our parts across on the y-axis
		}
			
		switch(direction) {
		case 'U': 
			posY[0] = posY[0] - UNIT_SIZE;  // Will change our direction of the head when going up
			break;
		case 'D': 
			posY[0] = posY[0] + UNIT_SIZE;  // Will change our direction of the head when going down;
			break;
		case 'L': 
			posX[0] = posX[0] - UNIT_SIZE;  // Will change our direction of the head when going left
			break;
		case 'R': 
			posX[0] = posX[0] + UNIT_SIZE;  // Will change our direction of the head when going to the right
			break;
		}	
		}
	}
	
	public void newbonusApple() {
		bonusappleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //makes sure that we place and apple randomly on x cordinates
		bonusappleY = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		
		if(bonusappleY < 60 ) {
			newbonusApple();
		}
		
		if((posX[0] == bonusappleX) && (posY[0] == bonusappleY)) {
			newbonusApple();
			//newevilApple();
		}
		
		if ((appleX == bonusappleX) && (appleY == bonusappleY)) {
			newbonusApple();
		}
	
	}
	
	public void newApple() { //In this function we want an apple to appear when we eat the apple or the game ends etc..
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //makes sure that we place and apple randomly on x cordinates
		appleY = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; // For the Y coordinates to be placed randomly
		
		System.out.println("New Apple at" + " " + appleX + " " + appleY);
		
		newevilApple();
		
		if(appleY < 60 ) {
			newApple();
		}		
		// unit size is 20 and width is 600 
		
		if((posX[0] == appleX) && (posY[0] == appleY)) {
			newApple();
			//newevilApple();
		}
		
		if ((evilappleX == appleX) && (evilappleY == appleY)) {
			newApple();
		}
		
		if ((evilapple2X == appleX) && (evilapple2Y == appleY)) {
			newApple();
		}
		
		if ((evilapple3X == appleX) && (evilapple3Y == appleY)) {
			newApple();
		}
	}
	
	public void newevilApple() {
		
		if (applesEaten > 10) {
			
			evilapple2X = random2.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //makes sure that we place and apple randomly on x cordinates
			evilapple2Y = random2.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
			
			evilapple3X = random2.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //makes sure that we place and apple randomly on x cordinates
			evilapple3Y = random2.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
			if(evilapple2Y < 60 ) {
				newevilApple();
			}
			if(evilapple3Y < 60 ) {
				newevilApple();
			}
		}
		
		evilappleX = random2.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //makes sure that we place and apple randomly on x cordinates
		evilappleY = random2.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		System.out.println("New EvilApple at" + " " + evilappleX + " " + appleY);
		
		if((posX[0] == evilappleX) && (posY[0] == evilappleY)) {
			newevilApple();
		}
		
		if(evilappleY < 60 ) {
			newevilApple();
		}

		if ((evilappleY == appleY) && (evilappleX == appleX)) {
			newevilApple();
		}
		
		if ((evilapple2Y == appleY) && (evilapple2X == appleX)) {
			newevilApple();
		}
		if ((evilapple3Y == appleY) && (evilapple3X == appleX)) {
			newevilApple();
		}
		// When snake dies it will spawn in the middle and go to the right
		// I don't want the player to die straight away due to an evil apple spawing in so close.
		if(evilappleY == 320 && evilappleX == 320) {
			newevilApple();
		}
		
		if(evilappleY == 340 && evilappleX == 320) {
			newevilApple();
		}	
	}
	
	public void checkevilApple() {
		
		if((posX[0] == evilapple2X) && (posY[0] == evilapple2Y)) {
			System.out.println("Snake hit EvilApple");
			GameFrame.playAudio(heartlost, -20);
			newevilApple();
			bodyParts--;
			applesEaten--;
			lives--;
		}
		
		if((posX[0] == evilapple3X) && (posY[0] == evilapple3Y)) {
			System.out.println("Snake hit EvilApple");
			GameFrame.playAudio(heartlost, -20);
			newevilApple();
			bodyParts--;
			applesEaten--;
			lives--;
		}
		
		if((posX[0] == evilappleX) && (posY[0] == evilappleY)) {
			if(bodyParts == 3) {
				
				posX[0] = 300;
				posY[0] = 320;		
					GameFrame.playAudio(heartlost, -20);
				newevilApple();
				lives--;
				direction = 'R';
			}
			else {
			
			System.out.println("Snake hit EvilApple");
			GameFrame.playAudio(heartlost, -20);
			newevilApple();
			bodyParts--;
			applesEaten--;
			lives--;
		}
		}
	} 
	
	public void checkbonusApple() {
		if((posX[0] == bonusappleX) && (posY[0] == bonusappleY)) {
			
			if (bodyParts == 20) {
		
			applesEaten = applesEaten + 5; // will basically be our score
			newbonusApple();
			GameFrame.playAudio(bonusapplesound, -20);
			}
			
			else {
				bodyParts++;
				applesEaten = applesEaten + 5; // will basically be our score
				newbonusApple();
				GameFrame.playAudio(bonusapplesound, -20);
				}
		} 
	}
	
	public void checkApple() {
		// going to check for the cordinates of the snake and the apple
		if((posX[0] == appleX) && (posY[0] == appleY)) {
		
			if (bodyParts == 20) {
				applesEaten++; // will basically be our score
				GameFrame.playAudio(appleeaten, -20);
				newApple();	
			}	
			else {				
		//	System.out.println("Snake length: " + applesEaten);	
			bodyParts++;
			applesEaten++; // will basically be our score
			GameFrame.playAudio(appleeaten, -20);
			newApple(); // will call our method to generate a new apple for us
			}
		}
	}
	
	public void checkCollisions() { // checking if the snake collides with its body parts.
		
		
		//checks to see if head collides with body
		for(int i = bodyParts; i> 0; i--) {
		if((posX[0] == posX[i]) && (posY[0] == posY[i])) {
			if (lives == 0) {
				game_Running = false; // this will help trigger us a GameOver method.
			State = STATE.GAMEOVER;
			System.out.println("Snake Collided with body");
			}
			else {
				posX[0] = 300;
				posY[0] = 320;
				lives--;
				GameFrame.playAudio(heartlost, -20);
				direction = 'R';
			}
	
		}
		}
		// checks if the head will touch the left border
		if(posX[0] < 0) {
			if (lives == 0) {
				game_Running = false;
			State = STATE.GAMEOVER;
			System.out.println("Snake Collided left border");
			}
			 else {
				
				 posX[0] = 300;
				 posY[0] = 320;
				lives--;
				GameFrame.playAudio(heartlost, -20);
				direction = 'R';
			}	
		}
		// checks if the head will touch the right border
				if(posX[0] > SCREEN_WIDTH-UNIT_SIZE) {
					if(lives == 0) {
					
						game_Running = false;
					State = STATE.GAMEOVER;
					System.out.println("Snake Collided right border");
					}
					else {
						
						posX[0] = 300;
						posY[0] = 320;			
						lives--;
						GameFrame.playAudio(heartlost, -20);
						direction = 'R';
					}
					
				}
			
				if (posY[0] < 45) { // used 45 when testing with 20 unit size 
					if (lives == 0) {
					
						game_Running = false;
					State = STATE.GAMEOVER;
					System.out.println("Snake Collided top border");
					}
						else {
						
							posX[0] = 300;
							posY[0] = 320;			
						lives--;
						GameFrame.playAudio(heartlost, -20);
						direction = 'R';
					}
				}
				
		// checks to see if the head touches the bottom border section.
				if (posY[0] > SCREEN_HEIGHT-UNIT_SIZE) {
					if (lives == 0) {
						game_Running = false;
					State = STATE.GAMEOVER;
					System.out.println("Snake Collided bottom border");
					}
					else {
						posX[0] = 300;
						posY[0] = 320;			
						lives--;
						GameFrame.playAudio(heartlost, -20);
						direction = 'R';
						
					}
				}
				
				// we should stop the timer if the game is not running
				if (!game_Running) {  // if the timer is not running the we must stop the timer.
					timer.stop(); 
					//timer.restart();
				}
		
	}
	
	public void gamePaused(Graphics g) {
		GameFrame.playAudio(gamepaused);
		g.setColor(Color.green);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.setColor(Color.green);
		g.drawString("Game Paused", (SCREEN_WIDTH - metrics.stringWidth("Game Paused"))/2, SCREEN_HEIGHT/2);
		g.setFont(new Font("Ink Free", ~Font.BOLD, 45));
		g.drawString("(Press Space Bar to Resume)", 20, 380);
		
	}
	
	public void gameOver(Graphics g) {
		// Will will have here our game over text.
				g.drawImage(space, 0, 0, 600, 600, this);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("SansSerif", Font.BOLD, 75));
		g.drawString("Game Over GG!", 20, 400 ); // this will be placed in the center of the scren width
		
		//In the game over method, I want the user to see their final score
		g.setColor(Color.yellow);
		g.setFont(new Font("arial", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
		
		if (applesEaten > alltimebest) {
			alltimebest = applesEaten;
			previoustime = ddMinute+ ":" + ddSecond;
		}
		
		if (alltimebest == 0) {
			previoustime = ddMinute+ ":" + ddSecond;
		}
		
		g.drawString("Best Score: " + alltimebest, 60, 200);
		
		g.drawString("Time: " + ddMinute + ":" + ddSecond, 80, 300);
		
		g.setColor(Color.green);
		g.setFont(new Font("arial", Font.BOLD, 55));
		//FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Press 'R' to Restart", 50, 500);
		g.drawString("or 'E' to Quit", 140, 580);
		
		gameoverscore = applesEaten;
		
		GameFrame.stopAudioLoop(opensound);
		System.out.println("Game over has occured");
	
		GameFrame.playAudio(gameover);
		GameFrame.startAudioLoop(gameovermusic, -25);
		
		previousscore = applesEaten;
		timer.stop();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(game_Running) { // checks to see if game is running 
			Movement(); // For movement 
			checkApple(); // checks for apple
			checkevilApple(); // checks for evilapple
			checkbonusApple(); // checks for bonusapple
			checkCollisions(); // Checks to see if any collisions occur while in game
		}
		repaint(); //calls paint to draw the object 
	}

	public class MyKeyAdapter extends KeyAdapter {	
		// ----------------------- TO DEAL WITH MOVEMENT SOUNDS ----------------------------------------
		public void keyReleased(KeyEvent e) {
			if(State == STATE.GAME) {
				
				switch(e.getKeyCode()) {
		
				case KeyEvent.VK_LEFT: // left
					GameFrame.playAudio(movement, -20);	
					break;
					
			case KeyEvent.VK_RIGHT: // right
				GameFrame.playAudio(movement, -20);
				break;
				
			case KeyEvent.VK_UP: // up
				GameFrame.playAudio(movement, -20);
				break;
				
			case KeyEvent.VK_DOWN: // down
				GameFrame.playAudio(movement, -20);
				break;

			case KeyEvent.VK_A: // left
					GameFrame.playAudio(movement, -20);		
				break;
				
			case KeyEvent.VK_D: // right
				GameFrame.playAudio(movement, -20);
				break;
				
			case KeyEvent.VK_W: // up
				GameFrame.playAudio(movement, -20);
				break;
				
			case KeyEvent.VK_S: // down
				GameFrame.playAudio(movement, -20);
				break;
				}
			}
	}
		
		@Override 
		public void keyPressed(KeyEvent e) {
			
			if(State == STATE.GAMEOVER) {
				
				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_R:
					System.out.println("R key is pressed While in Gameover");
					State = STATE.GAME;
					restartwithTimer(); 				
					break;
					
				case KeyEvent.VK_E:
					System.exit(64);
					break;
				}				
			}

				if(State == STATE.OPTIONS) {
				
				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_ESCAPE: 
					GameFrame.playAudio(menuselect, -20);
					State = STATE.MENU;
				
							}
				}
				
				if(State == STATE.INGAMEOPTIONS) {
					
					switch(e.getKeyCode()) {
					
					case KeyEvent.VK_ESCAPE: 
						GameFrame.playAudio(menuselect, -20);
						State = STATE.INGAMEMENU;
								}
					}

			if(State == STATE.MENU) {
				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_P:
					GameFrame.playAudio(menuselect, -20);
					System.out.println("P key is pressed");
					State = STATE.GAME; 
					startGame();
					break;
					
			case KeyEvent.VK_E:
				GameFrame.playAudio(menuselect, -20);
				System.exit(32);
				break;
				
			case KeyEvent.VK_H:
				GameFrame.playAudio(menuselect, -20);
				System.out.println("Options key is pressed ");
				State = STATE.OPTIONS;
				break; 
				
			case KeyEvent.VK_UP:
				GameFrame.playAudio(menuoptionscroll, -20);
				System.out.println("UP key is pressed");
				
				if (mainmenuoption == 0) {
					mainmenuoption = 2;
				}
				else {
				mainmenuoption--;
				if (mainmenuoption < 0) {
					mainmenuoption = 0;
					}
				}
				break; 
				
			case KeyEvent.VK_DOWN:
				GameFrame.playAudio(menuoptionscroll, -20);
				if (mainmenuoption == 2) {
					mainmenuoption = 0;
				}	
				else {
				mainmenuoption++;
				}
			
				System.out.println("Down key is pressed");
				break;
				
			case KeyEvent.VK_ENTER: 
				GameFrame.playAudio(menuselect, -20);
				if (mainmenuoption == 0) {
					State = STATE.GAME; 			
					startGame();
				}
				
				if (mainmenuoption == 1) {
					System.out.println("Options key is pressed ");
					State = STATE.OPTIONS;
				}
				
				if (mainmenuoption == 2) {
					System.exit(12);
				}
				break;
				}
			}
			
			if(State == STATE.INGAMEMENU) {
				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_UP:
					GameFrame.playAudio(menuoptionscroll, -20);
					System.out.println("UP key is pressed");
					
					if (mainmenuoption == 0) {
						mainmenuoption = 2;
					}
					else {
					
					mainmenuoption--;
					if (mainmenuoption < 0) {
						mainmenuoption = 0;
						}
					}
					break;
				
				case KeyEvent.VK_DOWN:
					GameFrame.playAudio(menuoptionscroll, -20);
					if (mainmenuoption == 2) {
						mainmenuoption = 0;
					}
					else {
					mainmenuoption++;
					}
	
					break;
				
				case KeyEvent.VK_ENTER: 
					GameFrame.playAudio(menuselect, -20);
					if (mainmenuoption == 0) {
						State = STATE.GAME; 
						ingamemenupause = 0;
						GameFrame.stopAudioLoop(menumusic);
						GameFrame.startAudioLoop(opensound, -25);
					}
					
					if (mainmenuoption == 1) {
						System.out.println("Options key is pressed ");
						State = STATE.INGAMEOPTIONS;
						
					}
					
					if (mainmenuoption == 2) {
						System.exit(12);
					}
					break;
			
					}
				}
				
			if(State == STATE.GAME) {
			
			switch(e.getKeyCode()) {
			
			// change all keys to wasd
			case KeyEvent.VK_LEFT: // left
				if (direction != 'R') { // if the direction does not equal to right then then the direction is left
					direction = 'L';
				}
				break;
				
		case KeyEvent.VK_RIGHT: // right
			if (direction != 'L') { // if the direction does not equal to left then then the direction is right 
				direction = 'R';
			}
			break;
			
		case KeyEvent.VK_UP: // up
			if (direction != 'D') { 
				direction = 'U';
			}
			break;
			
		case KeyEvent.VK_DOWN: // down
			if (direction != 'U') { 
				direction = 'D';
			}
			break;
	//--------------------------- WASD KEY LAYOUT ------------------------------		
		case KeyEvent.VK_A: // left
			if (direction != 'R') { // if the direction does not equal to right then then the direction is left
				direction = 'L';	
			}
			break;
			
		case KeyEvent.VK_D: // right
			if (direction != 'L') { // if the direction does not equal to left then then the direction is right 
				direction = 'R';	
			}
			break;
			
		case KeyEvent.VK_W: // up
			if (direction != 'D') { // 
				direction = 'U';
			}
			break;
			
		case KeyEvent.VK_S: // down
			if (direction != 'U') { // 
				direction = 'D';
			}
			break;
	// -------------------------- END OF WASD KEYS ---------------------------------------------			
		case KeyEvent.VK_E:
			System.exit(32);
			break;
	
		case KeyEvent.VK_G:   // used for grid. Simply press g to turn grid on or off.
			GameFrame.playAudio(gridsound, -20);
			System.out.println("G key is pressed");
			if (Grid == "ON") {
			Grid = "OFF";
			}
			else {
				Grid = "ON";
			}
			break;
			
		case KeyEvent.VK_ADD:
			System.out.println("ADD key is pressed");
			DELAY = DELAY - 30;
			changeintime();
			break;
			
		case KeyEvent.VK_EQUALS:
			System.out.println("Equals key is pressed");
			DELAY = DELAY - 30;
			changeintime();
			break;
	
		case KeyEvent.VK_SUBTRACT:
			System.out.println("Subtract key is pressed");
			DELAY = DELAY + 30;
			changeintime();
			break;
			
		case KeyEvent.VK_MINUS:
			System.out.println("Minus key is pressed");
			DELAY = DELAY + 30;
			changeintime();
			break;

		case KeyEvent.VK_M: 
			System.out.println("M key is pressed");
			//State = STATE.MENU;
			State = STATE.INGAMEMENU;
			GameFrame.startAudioLoop(menumusic, -20);
			break;
			
		case KeyEvent.VK_ESCAPE:
			System.out.println("M key is pressed");
			//State = STATE.MENU;
			State = STATE.INGAMEMENU;
			GameFrame.startAudioLoop(menumusic, -20);
			break;
			
			
		case KeyEvent.VK_R:
			GameFrame.playAudio(restartsound, -20);
			System.out.println("R key is pressed while in game");
			restartwithTimer(); 		
			break;
	
		case KeyEvent.VK_SPACE:
			if(SnakeAdventures.gameOn) {
				GameFrame.playAudio(gameresumed);
				resume();  // if paused is not pressed then game will carry on
		} else {
				paused();
		}
			break;
			}
		}	
	}
  } 
}