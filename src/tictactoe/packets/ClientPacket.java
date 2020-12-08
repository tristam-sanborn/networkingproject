package tictactoe.packets;

import java.io.Serializable;

public class ClientPacket implements Serializable{	 // Serializable allow us to serialize the class

	//SerialVersionUID is used to ensure that during deserialization the same class (that was used during serialize process) is loaded.
	private static final long serialVersionUID = 4322592436305797128L;
	
	private int x;
	private int y;
	
	public ClientPacket(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {	// we need a getter be able to get this variable from outside the class
		return x;
	}
	
	public int getY() {	// we need a getter be able to get this variable from outside the class
		return y;
	}

	
}
