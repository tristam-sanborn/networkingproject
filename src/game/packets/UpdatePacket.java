package game.packets;

import java.io.Serializable;

public class UpdatePacket implements Serializable {

	private static final long serialVersionUID = -4043686984608719773L;
	
	private int[][] fields;
	
	private int currentPlayer;

	public UpdatePacket(int[][] fields, int currentPlayer) {
		this.fields = fields;
		this.currentPlayer = currentPlayer;
	}

	public int[][] getFields() {
		return fields;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

		

}
