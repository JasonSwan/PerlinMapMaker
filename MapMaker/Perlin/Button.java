package Perlin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Button {
	int xCor, yCor;
	int width, height;
	
	int r = 255;
	int g = 0;
	int b = 0;
	
	
	boolean motion;
	boolean pressed;
	
	boolean disable;
	
	Color buttonColor;
	
	public Button(int xCoordinates, int yCoordinates, int Width, int Height) {
		xCor=xCoordinates;
		yCor=yCoordinates;
		width=Width;
		height=Height;
		buttonColor = Color.GRAY;
		
		
		
		//if pressed, turn true for user visual
		motion = false;
		
		//button has been clicked
		pressed = false;
		
		disable = false;
	}
	
	public void draw(Graphics2D g2d) {
		//Graphics2D g2d = (Graphics2D) g;
		if(disable) {
			if(r>g && r>b) {
				g2d.setColor(new Color(r, g +125, b+125));
			}
		}
		else {
			if(motion) {
				g2d.setColor(buttonColor.darker());
			}
			else {
				g2d.setColor(buttonColor);
			}
		}
		g2d.fillRect(xCor, yCor, width, height);;
		
		
	}
	
	public boolean mouseOnButton(Point coor){
		
		return ( (xCor<= coor.x) && (coor.x <= xCor+width) && (yCor <= coor.y) && (coor.y <= yCor+height) );
	}
	
	
	public Button setColor(int r, int g, int b) {
		this.buttonColor=new Color( r , g , b);
		return this;
	}
	

}
