package Perlin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;



public class Content extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener{
	private Timer timer;
	private int delay = 8;
	
	int width;
	int height;
	
	Button exitButton;
	
	double frequency;
	int amplitude;

	//List<Double> points1 = new ArrayList<Double>();
	//List<Double> points2 = new ArrayList<Double>();
	//List<Double> points3 = new ArrayList<Double>();
	
	List<Double> points2D = new ArrayList<Double>();
	List<Double> points2D2 = new ArrayList<Double>();
	List<Double> points2D3 = new ArrayList<Double>();
	List<Double> points2D4 = new ArrayList<Double>();
	List<Double> points2D5 = new ArrayList<Double>();
	
	BufferedImage bf;
	
	boolean mapDrawn;
	
	public Content(int screenWidth, int screenHeight){
		
		super.setDoubleBuffered(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		bf = new BufferedImage(screenWidth*3/4, screenHeight, BufferedImage.TYPE_INT_RGB);
		
		timer = new Timer(delay,this);
		timer.start();
		
		width = screenWidth;
		height = screenHeight;
		
		//button in top right of screen that will always exit the application
		exitButton = new Button(screenWidth-40, 0, 40, 20).setColor(Color.RED);
		
		mapDrawn = false;
		
		
		//amplitude = 7;
		
		double freq = 10;
		
		//1D LINE TEST
		/*
		for(double i =0; i<=screenWidth*3/4; i+=1.0) {
			//System.out.println(Noise.noise(*i));
			points1.add(Noise.noise(freq*i/(screenWidth*3/4)-0.5));
		}
		for(double i =0; i<=screenWidth*3/4; i+=1.0) {
			//System.out.println(Noise.noise(*i));
			points2.add((1.0/2.0)*Noise.noise(freq*2*i/(screenWidth*3/4)-0.5));
		}

		for(double i =0; i<=screenWidth*3/4; i+=1.0) {
			//System.out.println(Noise.noise(*i));
			points3.add((1.0/4.0)*Noise.noise(freq*4*i/(screenWidth*3/4)-0.5));
		}
		*/
		
		System.out.println(width*3/4);
		System.out.println(height);
		//2D TEST
		
		for(double y =0; y<height; y+=1.0) {
			for(double x=0; x<width*3/4; x++) {
				//System.out.println(Noise.noise(freq*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
				points2D.add(Noise.noise(freq*x/(width*3/4)-0.5, freq*y/(height)-0.5));
			}
		}
		

		for(double y =0; y<height; y+=1.0) {
			for(double x=0; x<width*3/4; x++) {
				//System.out.println(Noise.noise(freq*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
				points2D2.add((1.0/2.0)*Noise.noise(freq*2*x/(width*3/4)-0.5, freq*2*y/(height)-0.5));
			}
		}
		

		for(double y =0; y<height; y+=1.0) {
			for(double x=0; x<width*3/4; x++) {
				//System.out.println(Noise.noise(freq*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
				points2D3.add((1.0/4.0)*Noise.noise(freq*4*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
			}
		}

		for(double y =0; y<height; y+=1.0) {
			for(double x=0; x<width*3/4; x++) {
				//System.out.println(Noise.noise(freq*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
				points2D4.add((1.0/8.0)*Noise.noise(freq*8*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
			}
		}

		for(double y =0; y<height; y+=1.0) {
			for(double x=0; x<width*3/4; x++) {
				//System.out.println(Noise.noise(freq*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
				points2D5.add((1.0/16.0)*Noise.noise(freq*16*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
			}
		}
		
		
		/*
		double highest = 0;
		double highest1 = 0;
		double highest2 = 0;
		for(int i = 0; i< points2D.size(); i++) {
			//System.out.println(points2D.get(i));
			if(Math.abs(points2D.get(i))>highest) {
				highest = points2D.get(i);
			}
		}
		
		for(int i = 0; i< points2D2.size(); i++) {
			//System.out.println(points2D.get(i));
			if(Math.abs(points2D.get(i))>highest) {
				highest1 = points2D.get(i);
			}
		}
		
		for(int i = 0; i< points2D3.size(); i++) {
			//System.out.println(points2D.get(i));
			if(Math.abs(points2D.get(i))>highest) {
				highest2 = points2D.get(i);
			}
		}
		
		System.out.println(Math.abs(highest)+Math.abs(highest1)+Math.abs(highest2));
		
		System.out.println(points2D.size());
		*/
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//try to get everything to draw to buffered image
		//Graphics2D g2d = (Graphics2D) bf.createGraphics();
		//uncomment for fix
		Graphics2D g2d = (Graphics2D) g;
		
		
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, width, height);
		
		//draws exitbutton and adds decorative 'X' to to it
		exitButton.draw(g2d);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(width-25, 5, width-15, 15);
		g2d.drawLine(width-25, 15, width-15, 5);
		
		g2d.setColor(Color.BLACK);
		g2d.drawLine(width*3/4, 0, width*3/4, height);
		//Color myWhite = new Color(255, 255, 255);
		
		g2d.drawImage(bf, 0, 0, width*3/4, height, this);
		
		
		
		
		
		/*
		//THIS IS FOR PIXEL DRAWING
		for(int i =0; i<20; i++) {
			bf.setRGB(i, 50, Color.RED.getRGB());
		}
		bf.setRGB(10, 10, Color.RED.getRGB());
		g2d.drawImage(bf, 0, 0, width*3/4, height, this);
		*/
		
		/*
		g2d.setColor(Color.YELLOW);
		for(int i = 0; i<points.size();i++) {
			g2d.drawLine(i, (500+(int) Math.round(points.get(i)*(Math.pow(2, amplitude)))), i, (500+(int) Math.round(points.get(i)*(Math.pow(2, amplitude)))) );
		}
		
		g2d.setColor(Color.BLUE);
		for(int i = 0; i<points2.size();i++) {
			g2d.drawLine(i, (500+(int) Math.round(points2.get(i)*(Math.pow(2, amplitude)))), i, (500+(int) Math.round(points2.get(i)*(Math.pow(2, amplitude)))) );
		}
		
		g2d.setColor(Color.GREEN);
		for(int i = 0; i<points3.size();i++) {
			g2d.drawLine(i, (500+(int) Math.round(points3.get(i)*(Math.pow(2, amplitude)))), i, (500+(int) Math.round(points3.get(i)*(Math.pow(2, amplitude)))) );
		}
		*/
		/*
		g2d.setColor(Color.YELLOW);
		for(int i = 0; i<points3.size();i++) {
			g2d.drawLine(i, (500+(int) Math.round((points1.get(i)+points2.get(i)+points3.get(i))*(Math.pow(2, amplitude)))), i, (500+(int) Math.round((points1.get(i)+points2.get(i)+points3.get(i))*(Math.pow(2, amplitude)))) );
		}
		*/
		
		if(!mapDrawn) {

			/*
			int y = 0;
			int x = 0;
			for(int i = 0; i<points2D.size();i++) {
				x=i;
				while(x>1439) {
					x=x-1439;
				}
				//x = i-(width*3/4*(y));
				if(points2D.get(i)<0) {
					bf.setRGB(x, y, Color.BLUE.getRGB());
				}
				else {
					bf.setRGB(x, y, Color.GREEN.getRGB());
				}
				
				

				//System.out.println(i);
				//System.out.print(x); System.out.print(", "); System.out.println(y);
				
				if(i%(width*3/4+1)==0 && i!=0) {
					y+=1;
				}
			}
			*/
			
			int i=0;
			for(int y = 0; y<height; y++) {
				for(int x = 0; x<width*3/4; x++) {
					double sum = points2D.get(i)+points2D2.get(i)+points2D3.get(i)+points2D4.get(i)+points2D5.get(i);
					if(sum <-0.05) {
						bf.setRGB(x, y, Color.BLUE.getRGB());
						i++;
						continue;
					}
					else if(-0.05<=sum && sum<0.00) {
						bf.setRGB(x, y, Color.CYAN.getRGB());
						i++;
						continue;
					}
					else if(0.00<=sum && sum<0.05) {
						bf.setRGB(x, y, Color.YELLOW.getRGB());
						i++;
						continue;
					}
					else if(0.05<=sum && sum<0.50) {
						bf.setRGB(x, y, Color.GREEN.getRGB());
						i++;
						continue;
					}
					else if(0.50<=sum && sum<0.75) {
						bf.setRGB(x, y, Color.DARK_GRAY.getRGB());
						i++;
						continue;
					}
					else {
						bf.setRGB(x, y, Color.WHITE.getRGB());
						i++;
					}
				}
			}
			
			
			
			mapDrawn=true;
		}
		
		
		
		repaint();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//if(exitButton.mouseOnButton(e.getPoint())) {
		//	System.exit(0);
		//}
		
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(exitButton.mouseOnButton(e.getPoint())) {
			exitButton.motion=true;
			exitButton.pressed=true;
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(exitButton.mouseOnButton(e.getPoint())) {
			if(exitButton.pressed) {
				System.exit(0);
			}
		}
		else {
			exitButton.motion=false;
			exitButton.pressed=false;
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(exitButton.mouseOnButton(e.getPoint())) {
			if(exitButton.pressed) {
				exitButton.motion=true;
			}
		}
		else {
			exitButton.motion=false;
		}
		
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	

	
}
