package entities;

import java.awt.Color;
import java.awt.Graphics;

public class bodyPart {

	private int xCor, yCor, width,height;
	
	public bodyPart(int xCor, int yCor, int tileSize) {
		this.xCor= xCor;
		this.yCor = yCor;
		width = tileSize;
		height = tileSize;
	}
	
	public int getxCor() {
		return xCor;
	}

	public void setxCor(int xCor) {
		this.xCor = xCor;
	}

	public int getyCor() {
		return yCor;
	}

	public void setyCor(int yCor) {
		this.yCor = yCor;
	}

	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xCor * width, yCor * height, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xCor * width+2, yCor * height+2, width -3, height-3);
	}
}
