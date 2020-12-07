package game;

import java.io.IOException;
import java.net.Socket;

import game.net.Connection;
import game.packets.ClientPacket;
import game.packets.EndGamePacket;
import game.packets.PacketUpdate;

public class GameClient extends Game{
	
	private Socket clientSocket;
	
	private Connection clientConnection;
	
	public GameClient() {
		super (Game.SECOND_PLAYER);	// for the second player
		
		try {
			clientSocket = new Socket("localhost", Game.PORT_NUMBER);
			clientConnection = new Connection(this, clientSocket);	// initialize the connection
		
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
	}
	
	@Override
	public void inputReceived(int x, int y) {	// // inputReceived is going to take in x and y on where our mouse press
		if (checkTurn()) {
			clientConnection.sendPacket(new ClientPacket(x,y)); 
		}
	}
	
	@Override
	public void packetReceived(Object object) {	
		
		if (object instanceof PacketUpdate) {
			PacketUpdate packet = (PacketUpdate) object;	// update the gameFields and currentPlayer situation
			
			gameFields = packet.getGameFields();
			currentPlayer = packet.getCurrentPlayer();
		
		} else if (object instanceof EndGamePacket) {
			EndGamePacket packet = (EndGamePacket) object;	// show packet
			displayWinner(packet.getWinner());	// show winner
		
		}
		
		gameWindow.repaint();	// after we done the PacketUpdate or EndGamePacket we need to update
	}
	
	@Override
	public void closeConnection() {	// close the connection and socket
		try {
			clientConnection.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
