package game.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import game.Game;
import game.res.Resources;

public class GameWindow extends JPanel {


	private static final long serialVersionUID = -1463491557462486617L;

	private Game game;
	
	public GameWindow(Game game) {
		this.game = game;
		addMouseListener(new Input());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(10));

		for (int x = Game.GAME_WIDTH; x <= Game.GAME_WIDTH * 2; x += Game.GAME_WIDTH) {
			g2D.drawLine(x, 0, x, Game.HEIGHT);
		}
		for (int y = Game.GAME_HEIGHT; y <= Game.GAME_HEIGHT * 2; y += Game.GAME_HEIGHT) {
			g2D.drawLine(0, y, Game.WIDTH, y);
		}
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int field = game.getGameFields()[x][y];
				if (field != Game.NO_WINNER) {
					g2D.drawImage(Resources.letters[field - 1], x * Game.GAME_WIDTH , y * Game.GAME_HEIGHT, 
							Game.GAME_WIDTH - 5, Game.GAME_HEIGHT - 5, null);
				}
			}
		}
	}
	
	class Input extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			if (e.getButton() == MouseEvent.BUTTON1) {
				game.inputReceived(e.getX() / Game.GAME_WIDTH, e.getY()/Game.GAME_HEIGHT);
			}
		}
	}
}

