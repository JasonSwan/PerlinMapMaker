package Perlin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Button {
	int xCor, yCor;
	int width, height;
	
	
	boolean motion;
	boolean pressed;
	
	Color buttonColor;
	
	public Button(int xCoordinates, int yCoordinates, int Width, int Height) {
		xCor=xCoordinates;
		yCor=yCoordinates;
		width=Width;
		height=Height;
		buttonColor = Color.GRAY;
		
		motion = false;
		
		pressed = false;
	}
	
	public void draw(Graphics2D g2d) {
		//Graphics2D g2d = (Graphics2D) g;
		if(motion) {
			g2d.setColor(buttonColor.darker());
		}
		else {
			g2d.setColor(buttonColor);
		}
		g2d.fillRect(xCor, yCor, width, height);;
		
		
	}
	
	public boolean mouseOnButton(Point coor){
		
		return ( (xCor<= coor.x) && (coor.x <= xCor+width) && (yCor <= coor.y) && (coor.y <= yCor+height) );
	}
	
	
	public Button setColor(Color color) {
		this.buttonColor=color;
		return this;
	}
	

}
