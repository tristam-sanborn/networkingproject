package tictactoe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tictactoe.packets.CheckWinner;
import tictactoe.packets.ClientPacket;
import tictactoe.packets.Connection;
import tictactoe.packets.EndGamePacket;
import tictactoe.packets.PacketUpdate;

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
	
	

	private void updateField(int x, int y) {	// switch terms by using loop
		
		if (gameFields[x][y] == Game.NO_WINNER) {
			gameFields[x][y] = currentPlayer ;
			
			if (currentPlayer == Game.FIRST_PLAYER) {
				currentPlayer = Game.SECOND_PLAYER ;
			
			} else if (currentPlayer == Game.SECOND_PLAYER) {
				currentPlayer = Game.FIRST_PLAYER ;
			}	
		
			connection.sendPacket(new PacketUpdate(gameFields, currentPlayer)) ;	// send packet with gameFields and currentPlayer
			
			gamePanel.repaint();	// after we done the PacketUpdate we need to update
			
			int winner = checkWinner() ;
			int countField = countEmptyField();
			
			if (winner != Game.NO_WINNER || countField == 0) {
				endGame(winner) ;	// show the winner
				
			
		}
		}
		
	}
	
	
	
	
	private void endGame (int winner) {
		
		displayWinner(winner) ;
		connection.sendPacket(new EndGamePacket(winner)) ;
	
	}

	
	private int countEmptyField(){
        int fieldCount = 0;
        for(int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++) {
                if(gameFields[a][b] == Game.NO_WINNER) {
                    fieldCount++;
                }
            }
        }
        return fieldCount;
        
    }	

	
	private int checkWinner() {
        return new CheckWinner(gameFields).checking();
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
