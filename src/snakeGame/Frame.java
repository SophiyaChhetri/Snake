package snakeGame;

import java.awt.GridLayout;

import javax.swing.JFrame;

import snakeGame.graphics.Screen;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake");
		setResizable(false);
		init();
	}
	
	public void init() {
		setLayout(new GridLayout(1,1,0,0));
		Screen s = new Screen();
		add(s);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Frame();
	}

}
