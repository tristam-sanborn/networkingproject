package game.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import game.Game;

public class Window extends JFrame { //JFrame class create a window for us
	
	//SerialVersionUID is used to ensure that during deserialization the same class (that was used during serialize process) is loaded.
	private static final long serialVersionUID = -2767113216806410931L;
	
	private Game game;

	public Window(Game game, String title, int width, int height) {
		
		super(title);	// super constructor invoke the JFrame class
		
		this.game = game;
		
		setResizable(false);	// we need to make sure our window is not resizable
		
		getContentPane().setPreferredSize(new Dimension(width, height));
		
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Close the window with program
		
		setLocationRelativeTo(null);	// put the window in the middle of our screen
		
		addWindowListener(new Listener());	// add WindowListener to the class
	}
	
	class Listener extends WindowAdapter{	// we need to use a listener to close the connection
		
		@Override
		public void windowClosing(WindowEvent e) {
			
			game.closeConnection();	// close the socket
		
		}
	}
}
