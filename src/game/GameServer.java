package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import game.net.Connection;
import game.packets.ClientPacket;
import game.packets.EndGamePacket;
import game.packets.UpdatePacket;

public class GameServer extends Game {
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	private Connection connection;
	
	public GameServer() {
		super (Game.PLAYER_ONE);
		try {
			serverSocket = new ServerSocket(Game.PORT_NUMBER);
			socket = serverSocket.accept();
			connection = new Connection(this, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void inputReceived(int x,int y) {
		if (checkTurn()) {
			updateField(x, y);
		}
		
	}
	
	@Override
	public void packetReceived(Object object) {
		
		if(object instanceof ClientPacket) {
			ClientPacket packet = (ClientPacket) object;
			
			updateField(packet.getX(),packet.getY());
		}
		
	}
	
	private void updateField(int x, int y) {
		
		if (gameFields[x][y] == Game.NO_WINNER) {
			gameFields[x][y] = currentPlayer;
			
			if (currentPlayer == Game.PLAYER_ONE) {
				currentPlayer = Game.PLAYER_TWO ;
			
			} else if (currentPlayer == Game.PLAYER_TWO) {
				currentPlayer = Game.PLAYER_ONE ;
			}	
		
			connection.sendPacket(new UpdatePacket(gameFields, currentPlayer));
			gameWindow.repaint();
			
			int winner = checkWinner();
			
			if (winner != Game.NO_WINNER) {
				endGame(winner);
			} else {
				int emptyCount = 0;
				
				for (int a =0; a < 3; a++) {
					for (int b =0; b < 3; b++) {
						if (gameFields[a][b] == Game.NO_WINNER) {
							emptyCount++;
						}
					}
				}
				if(emptyCount == 9) {
					endGame(winner);
				}
			
			}
		}
		
	}
	
	private void endGame (int winner) {
		showWinner(winner);
		connection.sendPacket(new EndGamePacket(winner));
	}

	private int checkWinner() {
		
		for (int player = Game.PLAYER_ONE; player <= Game.PLAYER_TWO; player++) {
			for (int y = 0; y < 3; y++) {
				int countPlayer = 0;
				
				for (int x =0; x<3; x++) {
					if (gameFields[x][y] == player) {
						countPlayer++;
					}
				}
				if (countPlayer == 3) {
					return player;
				}
			
			}
			for (int x = 0; x < 3; x++) {
				int countPlayer = 0;
				
				for (int y = 0; y < 3; y++) {
					if (gameFields[x][y] == player) {
						countPlayer++;
					}
				}
				if (countPlayer == 3) {
					return player;
				}
		
			}
			int countPlayer = 0;
			for (int coordinate = 0; coordinate < 3; coordinate++) {
				if (gameFields[coordinate][coordinate] == player) {
					countPlayer++;
				}
			}
			if (countPlayer == 3) {
				return player;
			}
		
			countPlayer = 0;
			
			for (int coordinate = 0; coordinate < 3; coordinate++) {
				if (gameFields[2 - coordinate][coordinate] == player) {
					countPlayer++;
				}
			}
			
			if (countPlayer == 3) {
				return player;
			}	
		}
		
		return Game.NO_WINNER;
	}
	
	@Override
	public void closeConnection() {
		try {
			connection.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
