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

		for (int x = Game.FIELD_WIDTH; x <= Game.FIELD_WIDTH * 2; x += Game.FIELD_WIDTH) {
			g2D.drawLine(x, 0, x, Game.HEIGHT);
		}
		for (int y = Game.FIELD_HEIGHT; y <= Game.FIELD_HEIGHT * 2; y += Game.FIELD_HEIGHT) {
			g2D.drawLine(0, y, Game.WIDTH, y);
		}
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int field = game.getFields()[x][y];
				if (field != Game.NOBODY) {
					g2D.drawImage(Resources.letters[field - 1], x * Game.FIELD_WIDTH , y * Game.FIELD_HEIGHT, 
							Game.FIELD_WIDTH - 5, Game.FIELD_HEIGHT - 5, null);
				}
			}
		}
	}
	
	class Input extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			if (e.getButton() == MouseEvent.BUTTON1) {
				game.inputReceived(e.getX() / Game.FIELD_WIDTH, e.getY()/Game.FIELD_HEIGHT);
			}
		}
	}
}

