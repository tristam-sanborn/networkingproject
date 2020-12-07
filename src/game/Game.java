package game;

import javax.swing.JOptionPane;

import game.gui.GameWindow;
import game.gui.Window;

public abstract class Game {
	
	public static final int PORT_NUMBER = 1234;
	
	public static final int WIDTH = 900, HEIGHT = 900;
	public static final int GAME_WIDTH = WIDTH / 3, GAME_HEIGHT = HEIGHT / 3;
	
	public static final int NO_WINNER = 0, PLAYER_ONE = 1, PLAYER_TWO = 2;
	protected int[][] gameFields;
	
	private Window window;
	protected GameWindow gameWindow;
	
	protected int currentPlayer;
	
	protected int gamePlayer;
	
	public Game(int gamePlayer) {
		this.gamePlayer = gamePlayer;
		window = new Window(this, "Tic Tac Toe", WIDTH, HEIGHT);
		gameWindow = new GameWindow(this);
		gameFields = new int [3][3];
		window.add(gameWindow);
		window.setVisible(true);
		currentPlayer = Game.PLAYER_ONE;
	}
	
	protected void showWinner (int winner) {
		if (winner == Game.PLAYER_ONE) {
			JOptionPane.showMessageDialog(null, "The player " + winner + " has won the game! ");
			
		} else if (winner == Game.PLAYER_TWO) {
			JOptionPane.showMessageDialog(null, "The player " + winner + " has won the game! ");
		
		} else {
			JOptionPane.showMessageDialog(null, "No winner!");
		}
	}
	
	protected boolean checkTurn() {
		if (gamePlayer == currentPlayer) {
			return true;
		} else {
			return false;
		}
	}
	
	public abstract void inputReceived(int x, int y);
	
	public abstract void packetReceived (Object object);
	
	public abstract void closeConnection ();
	
	public int[][] getGameFields (){
		return gameFields;
	}
}
