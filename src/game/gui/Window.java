package game.gui;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = -2767113216806410931L;

	public Window(String title, int width, int height) {
		super(title);
		setResizable(false);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
