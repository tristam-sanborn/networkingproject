package game;

import game.gui.GameWindow;
import game.gui.Window;

public abstract class Game {
	
	public static final int WIDTH = 600, HEIGHT = 600;
	public static final int FIELD_WIDTH = WIDTH / 3, FIELD_HEIGHT = HEIGHT / 3;
	
	public static final int FREE = 0, PLAYER_ONE = 1, PLAYER_TWO = 2;
	private int[][] fields;
	
	private Window window;
	private GameWindow gameWindow;
	
	public Game() {
		window = new Window("TicTacToe", WIDTH, HEIGHT);
		gameWindow = new GameWindow(this);
		fields = new int [3][3];
		window.add(gameWindow);
	}
	
	public abstract void inputReceived(int x, int y);
	
	public int[][] getFields (){
		return fields;
	}
}
