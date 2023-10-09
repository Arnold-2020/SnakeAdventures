import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class MainMenu   {

	//													x   y     w    h
	public static Rectangle playButton = new Rectangle(150, 150, 320, 50);
	public static Rectangle helpButton = new Rectangle(150, 250, 320, 50);
	public static Rectangle exitButton = new Rectangle(150, 350, 320, 50);
	
	public static Rectangle MainSquare = new Rectangle(20, 100, 560, 400);
	
	
	public static void render(Graphics g, int menuoption) {
		Graphics2D g2d = (Graphics2D) g;
	//	System.out.println("MainMenu called in class");
		//	System.out.println(menuoption);

		if (menuoption == 0) {
		//Font fnt0 = new Font("arial", Font.BOLD, 50);
		//g.setFont(fnt0);
		//g.setColor(Color.black);
	//	g.drawString("Snake Game", 150 , 100 );
			g2d.setColor(Color.white);
			g2d.draw(MainSquare);
			
			
		Font fnt1 = new Font("arial", Font.BOLD, 30); 
		g.setFont(fnt1);
		//g.drawString("Play", playButton.x + 19, playButton.y + 30);
		g2d.setColor(Color.gray);
		g2d.fill(playButton);
		
		g2d.setColor(Color.blue);
		g.drawString("Play", playButton.x + 140, playButton.y + 35);
		g2d.draw(playButton);
		
		
		
		g2d.setColor(Color.gray);
		g2d.fill(helpButton);
		g.setColor(Color.black);
		g.drawString("Help", helpButton.x + 140, playButton.y + 135);
		g2d.draw(helpButton);
	
		
		
		g2d.setColor(Color.gray);
		g2d.fill(exitButton);
		g2d.setColor(Color.black);
		g.drawString("Exit", exitButton.x + 140, playButton.y + 235);
		g2d.draw(exitButton);
		
		//SnakeAdventures.mainmenuoption = 1;
		}
		
		if (menuoption == 1) {
			
			g2d.setColor(Color.white);
			g2d.draw(MainSquare);
			
			
		Font fnt1 = new Font("arial", Font.BOLD, 30); 
		g.setFont(fnt1);
		//g.drawString("Play", playButton.x + 19, playButton.y + 30);
		g2d.setColor(Color.gray);
		g2d.fill(playButton);
		
		g2d.setColor(Color.black);
		g.drawString("Play", playButton.x + 140, playButton.y + 35);
		g2d.draw(playButton);
		
		
		
		g2d.setColor(Color.gray);
		g2d.fill(helpButton);
		g.setColor(Color.blue);
		g.drawString("Help", helpButton.x + 140, playButton.y + 135);
		g2d.draw(helpButton);
	
		
		
		g2d.setColor(Color.gray);
		g2d.fill(exitButton);
		g2d.setColor(Color.black);
		g.drawString("Exit", exitButton.x + 140, playButton.y + 235);
		g2d.draw(exitButton);
		}
		
		if (menuoption == 2) {
			g2d.setColor(Color.white);
			g2d.draw(MainSquare);
			
			
		Font fnt1 = new Font("arial", Font.BOLD, 30); 
		g.setFont(fnt1);
		//g.drawString("Play", playButton.x + 19, playButton.y + 30);
		g2d.setColor(Color.gray);
		g2d.fill(playButton);
		
		g2d.setColor(Color.black);
		g.drawString("Play", playButton.x + 140, playButton.y + 35);
		g2d.draw(playButton);
		
		 
		
		g2d.setColor(Color.gray);
		g2d.fill(helpButton);
		g.setColor(Color.black);
		g.drawString("Help", helpButton.x + 140, playButton.y + 135);
		g2d.draw(helpButton);
	
		
		
		g2d.setColor(Color.gray);
		g2d.fill(exitButton);
		g2d.setColor(Color.blue);
		g.drawString("Exit", exitButton.x + 140, playButton.y + 235);
		g2d.draw(exitButton);
			
			
			
			
			
		}
		
		
		
		
		
		
	}
}