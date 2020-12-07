package game.packets;

import java.io.Serializable;

public class EndGamePacket implements Serializable{

	
	private static final long serialVersionUID = -8187076632965484159L;
	
	private int winner;
	
	public EndGamePacket(int winner) {
		this.winner = winner;
	}
	
	public int getWinner() {
		return winner;
	}
}
