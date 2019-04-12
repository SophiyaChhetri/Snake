package snakeGame.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import entities.Apple;
import entities.bodyPart;

public class Screen extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	private bodyPart b;
	private ArrayList<bodyPart> snake;
	
	private int xCor = 10, yCor = 10; //head of the snake
	private int size = 5; //snake size
	
	private boolean right = true, left = false, up = false, down = false;
	private int ticks = 0;
	
	private Key key;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	public Screen() {
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		snake = new ArrayList<bodyPart>();
		apples = new ArrayList<Apple>();
		r= new Random();
		start();
	}
	
	public void tick() {
		if(snake.size() == 0) {
			b = new bodyPart(xCor, yCor,10);
			snake.add(b);
		}
		
		if(apples.size() == 0) {
			int xCor = r.nextInt(79);
			int yCor = r.nextInt(79);
			apple = new Apple(xCor,yCor,10);
			apples.add(apple);
		}
		
		for(int i = 0; i < snake.size(); i++) {
			if(xCor == snake.get(i).getxCor() && yCor == snake.get(i).getyCor()) {
				if(i != snake.size()) {
					stop();
				}
			}
		}
		
		ticks++;
		
		if(ticks > 25000) {
			if(right) xCor++;
			if(left) xCor--;
			if(up) yCor--;
			if(down) yCor++;
			
			ticks = 0;
			b = new bodyPart(xCor, yCor,10);
			snake.add(b);
			
			if(snake.size()>size) {
				snake.remove(0);
			}
		}
	}
	public void paint(Graphics g) {
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		//vertical lines in grid
		for(int i = 0; i < WIDTH/10; i++) {
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		
		//horizontal lines in grid
		for(int i = 0; i < HEIGHT/10; i++) {
			g.drawLine(0, i*10, WIDTH, i*10);
		}
		
		for(int i = 0; i < snake.size() - 1;i++) {
			snake.get(i).draw(g);
		}
		
		for(int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}
	
	public void start() {
		running = true;
		thread = new Thread(this,"Game loop");
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(running) {
			tick();
			repaint();
		}
		
	}
	
	private class Key implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_RIGHT && !left) {
				up = false;
				down = false;
				right = true;				
			}
			
			if(key == KeyEvent.VK_LEFT && !right) {
				up = false;
				down = false;
				left = true;				
			}
			if(key == KeyEvent.VK_UP && !down) {
				left = false;
				right = false;
				up = true;				
			}
			if(key == KeyEvent.VK_DOWN && !up) {
				left = false;
				right = false;
				down = true;				
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
