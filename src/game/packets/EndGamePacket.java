package game.packets;

import java.io.Serializable;

public class EndGamePacket implements Serializable{	// Serializable allow us to serialize the class

	//SerialVersionUID is used to ensure that during deserialization the same class (that was used during serialize process) is loaded.
	private static final long serialVersionUID = -8187076632965484159L;
	
	private int gameWinner;
	
	public EndGamePacket(int gameWinner) {	// we need a getter be able to get this variable from outside the class
		this.gameWinner = gameWinner;
	}
	
	public int getWinner() {	// we need a getter be able to get this variable from outside the class
		return gameWinner;
	}
}
