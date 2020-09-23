package Perlin;

//import java.applet.Applet;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class MapMaker extends JFrame {
	
	JFrame frame;
	BufferedImage bf;
	
	Dimension screenSize;
	static int screenWidth;
	int screenHeight;
	
	public MapMaker() {

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		
		bf = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		super.setTitle("MapMaker");
		super.setSize(screenWidth, screenHeight);
		super.setLocation(0, 0);
		super.setResizable(false);
		super.setAlwaysOnTop(false);
		super.add(new Content(screenWidth,screenHeight) );
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		super.setUndecorated(true);
		super.setVisible(true);
	}

	public static void main(String[] args) {
		new MapMaker();
		
		/*
		Random r = new Random();
		r.setSeed(0);
		for(int i = 0; i<5; i++) {
			System.out.println(r.nextInt(2));
		}
		System.out.println("_______________");
		r.setSeed(5);
		for(int i = 0; i<5; i++) {
			System.out.println(r.nextInt(2));
		}
		System.out.println("_______________");
		r.setSeed(0);
		for(int i = 0; i<5; i++) {
			System.out.println(r.nextInt(2));
		}
		*/
		
		for(double i=0.25; i<200; i++) {
			//System.out.println(Noise.noise(i,i));
		}
		//System.out.println(screenWidth);
		//System.out.println(screenWidth*3/4);
		//System.out.println((double)1/100);
		//System.out.println(1/(screenWidth*3/4));
	}
	
}