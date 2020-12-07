package game;

import java.io.IOException;
import java.net.Socket;

import game.net.Connection;
import game.packets.ClientPacket;
import game.packets.EndGamePacket;
import game.packets.UpdatePacket;

public class GameClient extends Game{
	
	private Socket clientSocket;
	
	private Connection clientConnection;
	
	public GameClient() {
		super (Game.PLAYER_TWO);	
		try {
			clientSocket = new Socket("localhost", Game.PORT_NUMBER);
			clientConnection = new Connection(this, clientSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void inputReceived(int x, int y) {
		if (checkTurn()) {
			clientConnection.sendPacket(new ClientPacket(x,y)); 
		}
	}
	
	@Override
	public void packetReceived(Object object) {
		
		if (object instanceof UpdatePacket) {
			UpdatePacket packet = (UpdatePacket) object;
			
			gameFields = packet.getFields();
			currentPlayer = packet.getCurrentPlayer();
		} else if (object instanceof EndGamePacket) {
			EndGamePacket packet = (EndGamePacket) object;
			showWinner(packet.getWinner());
		}
		
		gameWindow.repaint();
	}
	
	@Override
	public void closeConnection() {
		try {
			clientConnection.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
