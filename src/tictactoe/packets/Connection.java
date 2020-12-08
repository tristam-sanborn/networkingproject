package tictactoe.packets;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import tictactoe.Game;

public class Connection implements Runnable {	// writing and receiving data
	
	private ObjectOutputStream outToStream;	// OutputStream we want to send
	
	private ObjectInputStream inToStream;	// InputStream we want to send
	
	private boolean gamerunning;	
	
	private Game game;
	
	public Connection(Game game, Socket socket) throws IOException{	// handle ObjectOutputStream and ObjectInputStream
		
		this.game = game;
		
		outToStream = new ObjectOutputStream(socket.getOutputStream());
		
		inToStream = new ObjectInputStream(socket.getInputStream());
			
		new Thread (this).start();
	}
	
	@Override
	public void run ( ) {
		
		gamerunning = true;	// only running when gamerunning is true
		
		while(gamerunning) {
			
			try {
				Object object = inToStream.readObject();
				game.packetReceived(object);	// get the packet we received
				
			} catch (EOFException | SocketException e) {				
				gamerunning = false;
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();

			}
		}
		
	}

	public void sendPacket (Object target) { // send the object that we want to send
		
		try {
			outToStream.reset();	// reset the object to avoid send the same object twice
			outToStream.writeObject(target);	// pass the object that we want to write
			outToStream.flush();	// make sure nothing is lost during process	
		
		} catch (IOException e) {
			e.printStackTrace();
		
		}
		

	}
	
	public void close() throws IOException {	// close the stream when it is done
		
		inToStream.close();
		outToStream.close();
	
	}
}
