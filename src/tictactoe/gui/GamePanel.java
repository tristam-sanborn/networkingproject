package tictactoe.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import tictactoe.Game;
import tictactoe.res.Resources;

// JPanel is helping us to render the game interface
public class GamePanel extends JPanel {

	// serialVersionUID is used to ensure that during deserialization the same class (that was used during serialize process) is loaded.
	private static final long serialVersionUID = -1463491557462486617L;

	private Game game;	// its constructor take in the Game class
	
	public GamePanel(Game game) {

		this.game = game;	// now we can pass it into GameWidow class
		addMouseListener(new Input());	// add MouseAdapter by calling addMouseListener method
	
	}
	
	@Override
	public void paint(Graphics g) {	// use paint method to render our JPanel
		
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;	// graphics2D class extends from Graphics class, cast Graphics g	
		g2D.setStroke(new BasicStroke(10));	// setStroke makes the line we draw wider than the regular line
		
		for (int x = Game.GAME_WIDTH; x <= Game.GAME_WIDTH * 2; x += Game.GAME_WIDTH) {
			g2D.drawLine(x, 0, x, Game.HEIGHT);	// use for loop draw only two lines in the window
		}
		
		for (int y = Game.GAME_HEIGHT; y <= Game.GAME_HEIGHT * 2; y += Game.GAME_HEIGHT) {
			g2D.drawLine(0, y, Game.WIDTH, y);	// use for loop draw only two lines in the window
		}
		
		for (int x = 0; x < 3; x++) {	// loop through x	
			for (int y = 0; y < 3; y++) {	// loop through y
				int field = game.getGameFields()[x][y];	
				if (field != Game.NO_WINNER) {	// we would only need to render when the game is still playing
					// if the field is 1, we need to render x; if the field is 2, we need to render y; we need to multiply the x and y encoder render the images to right position
					g2D.drawImage(Resources.letters[field - 1], x * Game.GAME_WIDTH , y * Game.GAME_HEIGHT, 
							Game.GAME_WIDTH - 8, Game.GAME_HEIGHT - 8, null);
				}
			}
		}
	}
	
	class Input extends MouseAdapter{	// extends MouseAdapter to help us handle the mouse event
		
		@Override
		public void mousePressed(MouseEvent e) {	// mousePressed method is the method call every single time when user press mouse
			if (e.getButton() == MouseEvent.BUTTON1) {	// check if the user clicks the left bottom of the mouse
				game.inputReceived(e.getX() / Game.GAME_WIDTH, e.getY()/Game.GAME_HEIGHT);	// we need to divide by the WIDTH and HEIGHT to know which field has clicked on
			
			}
		}
	}
}

