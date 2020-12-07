package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import game.net.Connection;
import game.packets.ClientPacket;
import game.packets.EndGamePacket;
import game.packets.PacketUpdate;

public class GameServer extends Game {
	
	private ServerSocket serverSocket ;	
	
	private Socket socket ;
	
	private Connection connection ;	
	
	public GameServer() {
		super (Game.FIRST_PLAYER) ;	// for the first player
		
		try {
			serverSocket = new ServerSocket(Game.PORT_NUMBER) ;
			socket = serverSocket.accept() ;
			connection = new Connection(this, socket) ; // initialize the connection
		
		} catch (IOException e) {
			e.printStackTrace() ;
		}
	}
	
	
	
	
	@Override
	public void inputReceived(int x,int y) {	// received from mouse
		
		if (checkTurn()) {	// checkTurn() then update
			updateField(x, y) ;
		
		}
		
	}
	
	
	
	
	@Override
	public void packetReceived(Object object) {	// receive object from the client
		
		if(object instanceof ClientPacket) {
			ClientPacket packet = (ClientPacket) object ;
			
			updateField(packet.getX(),packet.getY()) ;
		}
		
	}
	
	
	
	
	private void updateField(int x, int y) {	// switch terms by using loop
		
		if (gameFields[x][y] == Game.NO_WINNER) {
			gameFields[x][y] = currentPlayer ;
			
			if (currentPlayer == Game.FIRST_PLAYER) {
				currentPlayer = Game.SECOND_PLAYER ;
			
			} else if (currentPlayer == Game.SECOND_PLAYER) {
				currentPlayer = Game.FIRST_PLAYER ;
			}	
		
			connection.sendPacket(new PacketUpdate(gameFields, currentPlayer)) ;	// send packet with gameFields and currentPlayer
			
			gameWindow.repaint();	// after we done the PacketUpdate we need to update
			
			int winner = checkWinner() ;
			
			if (winner != Game.NO_WINNER) {
				endGame(winner) ;	// show the winner
			
			} else {
				int emptyCount = 0 ;
				
				for (int a =0; a < 3; a++) {	// go through entire board
					for (int b =0; b < 3; b++) {
						if (gameFields[a][b] == Game.NO_WINNER) {
							emptyCount++ ;
						}
					}
				}
				if(emptyCount == 9) {
					endGame(winner) ;
				}
			
			}
		}
		
	}
	
	
	
	
	private void endGame (int winner) {
		
		displayWinner(winner) ;
		connection.sendPacket(new EndGamePacket(winner)) ;
	
	}

	
	
	
	private int checkWinner() {	// check if any player wins the game
		
		for (int player = Game.FIRST_PLAYER; player <= Game.SECOND_PLAYER; player++) { // check player 1 and player 2
			for (int y = 0; y < 3; y++) {	// check each row
				int countPlayer = 0 ;	// update count
				
				for (int x =0; x<3; x++) {
					if (gameFields[x][y] == player) {	// check gameFields
						countPlayer++ ;
					}
				}
				if (countPlayer == 3) {	// check if count equals 3
					return player ;
				}
			
			}
			for (int x = 0; x < 3; x++) {	// check each column
				int countPlayer = 0 ;	// update count
				
				for (int y = 0; y < 3; y++) {
					if (gameFields[x][y] == player) {	// check gameFields
						countPlayer++ ;
					}
				}
				if (countPlayer == 3) {	// check if count equals 3
					return player ;
				} 
		
			}
			int countPlayer = 0;
			for (int coordinate = 0; coordinate < 3; coordinate++) {	// check each coordinate
				if (gameFields[coordinate][coordinate] == player) {	//check first diagonal
					countPlayer++ ;
				}
			}
			if (countPlayer == 3) {	// check if count equals 3
				return player ;
			}
		
			countPlayer = 0;
			
			for (int coordinate = 0; coordinate < 3; coordinate++) {	// check each coordinate
				if (gameFields[2 - coordinate][coordinate] == player) {	//check first diagonal
					countPlayer++ ;
				}
			}
			
			if (countPlayer == 3) {	// check if count equals 3
				return player ;
			}	
		}
		
		return Game.NO_WINNER ;	// nobody wins the game
	}
	
	
	
	
	@Override
	public void closeConnection() {	// close the connection and socket
		
		try {
			connection.close() ;
			serverSocket.close() ;
		
		} catch (IOException e) {
			e.printStackTrace() ;
		}
		
	}
}
