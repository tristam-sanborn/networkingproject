package game.net;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import game.Game;

public class Connection implements Runnable {
	
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	private boolean running;
	
	private Game game;
	
	public Connection(Game game, Socket socket) throws IOException{
		this.game = game;
		
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());
			
		new Thread (this).start();
	}
	
	@Override
	public void run ( ) {
		running = true;
		
		while(running) {
			try {
				Object object = inputStream.readObject();
				game.packetReceived(object);
				
			}catch (EOFException | SocketException e) {				
				running = false;
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();

			}
		}
		
	}

	public void sendPacket (Object object) {
		
		try {
			outputStream.reset();
			outputStream.writeObject(object);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public void close() throws IOException {
		inputStream.close();
		outputStream.close();
	}
}
