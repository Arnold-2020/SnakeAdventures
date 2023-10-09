import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

public class HelpsviaMenu   {

	//												x   y     w    h
	public static Rectangle playButton = new Rectangle(150, 150, 320, 50);
	public static Rectangle helpButton = new Rectangle(150, 250, 320, 50);
	public static Rectangle exitButton = new Rectangle(150, 350, 320, 50);
	
	public static Rectangle MainSquare = new Rectangle(20, 100, 560, 400);
	
	static Image apple;
	static Image evilapple;
	static Image heart; 
	static Image bonusapple;
	
	public static void render(Graphics g, int menuoption) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.yellow);
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("About Game", 160, 160); // this will be placed in the center of the scren width
		
		g.setFont(new Font("SansSerif", Font.BOLD, 20));
		g.setColor(Color.green);
		g.drawString("Movement", 20, 180);
		g.drawString("Menu Controls", 400, 180);
		g.drawString("Menu in Game", 400, 320);
		g.setColor(Color.yellow);
		g.setFont(new Font("SansSerif", ~Font.BOLD, 20));
		g.drawString(" ￪ - Move Section up", 380, 200);
		g.drawString(" ￬ - Move Section down", 380, 220);
		g.drawString(" Enter - To Select", 380, 240);
		g.drawString(" P - To Play", 380, 260);
		g.drawString(" H - For Help", 380, 280);
		g.drawString(" E - To Exit", 380, 300);
		
		g.drawString(" ESC - In Game Menu", 380, 340);
		g.drawString(" M - In Game Menu", 380, 360);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("SansSerif", ~Font.BOLD,  20));
		g.drawString("W and ￪ - Move Upwards", 20, 200);
		g.drawString("D and ￫ - Move Right", 20, 220);
		g.drawString("S and ￬ - Move Down", 20, 240);
		g.drawString("A and ￩ - Move Left ", 20, 260);
		
		
		g.setFont(new Font("SansSerif", Font.BOLD, 20));
		g.setColor(Color.green);
		g.drawString("Features", 20, 300);
		
		g.setFont(new Font("SansSerif", ~Font.BOLD, 20));
		g.setColor(Color.yellow);
		g.drawString("Space Bar - To Pause/Resume Game", 20, 320);
		g.drawString("R - To Restart Game", 20, 340);
		g.drawString("E - To Exit Game", 20, 360);
		g.drawString("G - Turn Grid ON/OFF",20, 380);
		
		//g.setFont(new Font("SansSerif", Font.BOLD, 20));
		//g.setColor(Color.green);
		//g.drawString("Features", 20, 300);
		apple = GameFrame.loadImage("Images/newapple.png");
		 g.drawImage(apple, 20, 400, 20, 20, null);
		 g.setFont(new Font("SansSerif", ~Font.BOLD, 20));
		 g.setColor(Color.green);
		 g.drawString(" - Gives you 1 point and adds Snake Length by 1", 40, 415);
		evilapple = GameFrame.loadImage("Images/newevilapple.png");
		 g.drawImage(evilapple, 20, 440, 20, 20, null);
		 g.setColor(Color.red);
		 g.drawString(" - Lose 1 point and subtracts Snake Length by 1", 40, 455);
		 heart = GameFrame.loadImage("Images/heart.png");
		 g.drawImage(heart, 20, 480, 20, 20, null);
		 g.setColor(Color.yellow);
		 g.drawString(" - Game has a total of 3 Lives", 40, 495);
		 g.drawString("   when live is lost, background will change", 40, 515);
		 bonusapple = GameFrame.loadImage("Images/bonusapple.png");
		 g.drawImage(bonusapple, 20, 530, 20, 20, null);
		 g.drawString(" - Gives you + 5 points, will spawn every 15 seconds", 40, 545);
		 g.drawString("    and 5 seconds to find it", 40, 570);		 
		 
		 
		
		g.setColor(Color.green);
		g.drawString("Press ESC to go back ", 200, 590);
			
		//In the game over method, I want the user to see their final score
		g.setColor(Color.yellow);
		g.setFont(new Font("arial", Font.BOLD, 50));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		//g.drawString("Score: "
			
	}


	private static FontMetrics getFontMetrics(Font font) {
		// TODO Auto-generated method stub
		return null;
	}
}