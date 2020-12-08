package tictactoe;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		
		// show the interface in order to let the player choose
		int choice = Integer.parseInt(JOptionPane.showInputDialog("Type 1 for server | Type 2 for client"));
		
		if (choice == 1) {	// assign to server
			new GameServer() ;
		} else if (choice == 2) {	// assign to client
			new GameClient() ;
		} else {
			// show the message when user enter wrong number
			JOptionPane.showMessageDialog(null, "You entered the wrong number, please try again!") ;
		}
		
	}
	
}
