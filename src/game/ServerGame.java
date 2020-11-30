package game;

public class ServerGame extends Game{
	
	@Override
	public void inputReceived(int x,int y) {
		System.out.println(x+ " "+ y);
	}
}
