package game.net;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import game.Game;

public class Connection implements Runnable {
	
	private ObjectOutputStream outToStream;
	private ObjectInputStream inToStream;
	
	private boolean gamerunning;
	
	private Game game;
	
	public Connection(Game game, Socket socket) throws IOException{
		this.game = game;
		
		outToStream = new ObjectOutputStream(socket.getOutputStream());
		inToStream = new ObjectInputStream(socket.getInputStream());
			
		new Thread (this).start();
	}
	
	@Override
	public void run ( ) {
		gamerunning = true;
		
		while(gamerunning) {
			try {
				Object object = inToStream.readObject();
				game.packetReceived(object);
				
			} catch (EOFException | SocketException e) {				
				gamerunning = false;
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();

			}
		}
		
	}

	public void sendPacket (Object object) {
		
		try {
			outToStream.reset();
			outToStream.writeObject(object);
			outToStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public void close() throws IOException {
		inToStream.close();
		outToStream.close();
	}
}
