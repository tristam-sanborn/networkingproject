package game;

import javax.swing.JOptionPane;

import game.gui.GameWindow;
import game.gui.Window;

public abstract class Game {	// abstract class to handle class server and class client
	
	public static final int PORT_NUMBER = 1234 ;
	
	public static final int WIDTH = 900, HEIGHT = 900 ;	// the width and height of the window
	
	public static final int GAME_WIDTH = WIDTH / 3, GAME_HEIGHT = HEIGHT / 3 ;	// the position of the line we draw in the window
	
	public static final int NO_WINNER = 0, FIRST_PLAYER = 1, SECOND_PLAYER = 2 ;	// 0 indicate nobody wins the game, 1 indicate player 1 win the game, and 2 indicate player 2 win the game
	
	protected int[][] gameFields ;	// store player press the fields in the game window
	
	private Window window ;
	
	protected GameWindow gameWindow ;
	
	protected int currentPlayer ;
	
	protected int gamePlayer ;
	
	
	
	
	
	public Game(int gamePlayer) {
		
		this.gamePlayer = gamePlayer;
		
		window = new Window(this, "Tic Tac Toe", WIDTH, HEIGHT);	// set up game name
		
		gameWindow = new GameWindow(this) ;	// initialize the GameWindow
		
		gameFields = new int [3][3] ;	// initialize the fields by 3 * 3
		
		window.add(gameWindow) ;	// add gameWindow class
		
		window.setVisible(true) ;	// make our window visible
		
		currentPlayer = Game.FIRST_PLAYER ;
	}
	
	
	
	
	
	
	protected void displayWinner (int winner) {
		
		if (winner == Game.FIRST_PLAYER) {
			
			JOptionPane.showMessageDialog(null, "Player " + winner + " has won the game! ");
			
		} else if (winner == Game.SECOND_PLAYER) {
			
			JOptionPane.showMessageDialog(null, "Player " + winner + " has won the game! ");
		
		} else {
			
			JOptionPane.showMessageDialog(null, "DRAW!") ;
		}
	}
	
	
	
	
	protected boolean checkTurn() {
		
		return (gamePlayer == currentPlayer) ;	// check and return a boolean 
	
	}
	
	public abstract void inputReceived(int x, int y) ;	// inputReceived is going to take in x and y on where our mouse press
	
	public abstract void packetReceived (Object object) ;	// taking the object we received
	
	public abstract void closeConnection () ;	// close the socket
	
	public int[][] getGameFields () {	// we need a getter be able to get this variable from outside the class
		
		return gameFields ;
	
	}
}
