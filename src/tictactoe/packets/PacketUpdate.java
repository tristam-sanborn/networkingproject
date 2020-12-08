package tictactoe.packets;

import java.io.Serializable;

public class PacketUpdate implements Serializable {	// Serializable allow us to serialize the class
	
	//SerialVersionUID is used to ensure that during deserialization the same class (that was used during serialize process) is loaded.
	private static final long serialVersionUID = -4043686984608719773L;
	
	private int[][] gameFields;	// update gameFields
	
	private int currentPlayer;	// send who is playing now

	public PacketUpdate(int[][] gameFields, int currentPlayer) {
		this.gameFields = gameFields;
		this.currentPlayer = currentPlayer;
	}

	public int[][] getGameFields() {	// we need a getter be able to get this variable from outside the class
		return gameFields;
	}

	public int getCurrentPlayer() {	// we need a getter be able to get this variable from outside the class
		return currentPlayer;
	}

		

}
