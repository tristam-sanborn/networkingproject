package game;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		int choice = Integer.parseInt(JOptionPane.showInputDialog("Type 1 for server | Type 2 for client"));
		
		if (choice == 1) {
			new GameServer() ;
		} else if (choice == 2) {
			new GameClient() ;
		} else {
			JOptionPane.showMessageDialog(null, "You entered the wrong number, please try again!") ;
		}
		
	}
	
}
