package game.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import game.Game;

public class GameWindow extends JPanel {


	private static final long serialVersionUID = -1463491557462486617L;

	public GameWindow() {
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(10));

		for (int x = Game.FIELD_WIDTH; x <= Game.FIELD_WIDTH * 2; x += Game.FIELD_WIDTH) {
			g2D.drawLine(x, 0, x, Game.HEIGHT);
		}
		for (int y = Game.FIELD_WIDTH; y <= Game.FIELD_WIDTH * 2; y += Game.FIELD_WIDTH) {
			g2D.drawLine(0, y, Game.WIDTH, y);
		}
	}
}

