package game.packets;

import java.io.Serializable;

public class ClientPacket implements Serializable{

	private static final long serialVersionUID = 4322592436305797128L;
	
	private int x;
	private int y;
	
	public ClientPacket(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	
}
