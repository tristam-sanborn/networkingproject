package game;

import game.gui.GameWindow;
import game.gui.Window;

public class Game {
	
	public static final int WIDTH = 600, HEIGHT = 600;
	public static final int FIELD_WIDTH = 600/3, FIELD_HEIGHT = HEIGHT / 3;
	
	private Window window;
	private GameWindow gameWindow;
	
	public Game() {
		window = new Window("TicTacToe", WIDTH, HEIGHT);
		gameWindow = new GameWindow();
		window.add(gameWindow);
	}
}
