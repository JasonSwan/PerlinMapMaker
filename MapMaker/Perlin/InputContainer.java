package Perlin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class InputContainer {
	int xCor, yCor;
	int width, height;
	
	//Visble '|' if pressed
	boolean pressed;
	
	//Visble '|' timer
	int indicator;
	
	String currentString = "";
	
	boolean disable = false;
	
	public InputContainer(int xCoordinates, int yCoordinates, int Width, int Height) {
		xCor=xCoordinates;
		yCor=yCoordinates;
		width=Width;
		height=Height;
		
		
		pressed = false;
		
		indicator= 1500;
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(xCor, yCor, width, height);
		
		g2d.setColor(Color.BLACK);
		g2d.drawRect(xCor, yCor, width, height);
		
		
		
		if(pressed) {
			if(indicator>750) {
				g2d.drawString(currentString + "|", xCor+2, yCor+14);
			}
			else {
				g2d.drawString(currentString, xCor+2, yCor+14);
			}
		}
		else {
			g2d.drawString(currentString, xCor+2, yCor+14);
		}
		
		if(indicator>0) {
			indicator--;
		}
		else {
			indicator=1500;
		}
		
		
	}
	
	
	public boolean mouseOnButton(Point coor){
		
		return ( (xCor<= coor.x) && (coor.x <= xCor+width) && (yCor <= coor.y) && (coor.y <= yCor+height) );
	}
	

}
