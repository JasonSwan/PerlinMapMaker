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
	Button seedButton;
	InputContainer seedContainer;
	
	
	//LOWEST AND HIGHEST VALUES OF EACH COLOR RANGE
	double deepWaterLow; double deepWaterHigh;
	
	double lightWaterLow; double lightWaterHigh;
	
	double sandLow; double sandHigh;
	
	double greenLow; double greenHigh;
	
	double mountLow; double mountHigh;
	
	double peakLow; double peakHigh; 
	
	
	//RANGE FOR EACH COLOR
	//+-2 IS THE CAP IN MOST EVERY CASE
	
	double deepWaterBottom=-2; double deepWaterTop = 0.05;
	
	double lightWaterBottom = 0.05; double lightWaterTop = 0.10;
	
	double sandBottom = 0.10; double sandTop = 0.11;
	
	double greenBottom = 0.11; double greenTop = 0.50;
	
	double mountBottom = 0.50; double mountTop = 0.65;
	
	double peakBottom = 0.65; double peakTop=2; 
	
	
	
	
	int ampl;
	double freq = 5;
	
	int currentSeed = 100;

	//List<Double> points1 = new ArrayList<Double>();
	//List<Double> points2 = new ArrayList<Double>();
	//List<Double> points3 = new ArrayList<Double>();
	
	List<Double> points2D = new ArrayList<Double>();
	List<Double> points2D2 = new ArrayList<Double>();
	List<Double> points2D3 = new ArrayList<Double>();
	List<Double> points2D4 = new ArrayList<Double>();
	List<Double> points2D5 = new ArrayList<Double>();
	List<Double> points2D6 = new ArrayList<Double>();
	List<Double> points2D7 = new ArrayList<Double>();
	List<Double> points2D8 = new ArrayList<Double>();
	List<Double> points2D9 = new ArrayList<Double>();
	List<Double> points2D10 = new ArrayList<Double>();
	
	
	BufferedImage bf;
	
	boolean gettingPerlinValues;
	boolean mapDrawn;
	boolean startDrawing;
	
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
		exitButton = new Button(screenWidth-40, 0, 40, 20).setColor(255,0,0);
		//button that randomizes the seed and then loads the new map
		seedButton = new Button(screenWidth-(screenWidth/8)-20, 100, 40, 20).setColor(255,0,0);
		//inputContainer that allows user to input new seed and then loads the new map
		seedContainer = new InputContainer(screenWidth-(screenWidth/8)-145, 100,120,20);
		
		gettingPerlinValues = true;
		mapDrawn = false;
		startDrawing=false;
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//try to get everything to draw to buffered image
		//Graphics2D g2d = (Graphics2D) bf.createGraphics();
		//uncomment for fix
		Graphics2D g2d = (Graphics2D) g;
		
		
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, width, height);
		
		//APPLY BUTTON AND VISUAL CURRENT SEED
		seedButton.draw(g2d);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Current Seed:" + String.valueOf(currentSeed), seedButton.xCor+seedButton.width+5, Math.round(seedButton.yCor+(seedButton.height-5)));
		
		//SEED CONTAINER
		seedContainer.draw(g2d);
		
		
		//EXIT BUTTON AND DECORATIVE 'X'
		exitButton.draw(g2d);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(width-25, 4, width-15, 14);
		g2d.drawLine(width-25, 14, width-15, 4);
		
		
		g2d.setColor(Color.BLACK);
		g2d.drawLine(width*3/4, 0, width*3/4, height);
		//Color myWhite = new Color(255, 255, 255);
		
		g2d.drawImage(bf, 0, 0, width*3/4, height, this);
		
		if(startDrawing) {
			if(!mapDrawn) {
	
				int i=0;
				for(int y = 0; y<height; y++) {
					for(int x = 0; x<width*3/4; x++) {
						double sum =points2D.get(i)+points2D2.get(i)+points2D3.get(i)+points2D4.get(i)
									+points2D5.get(i)+points2D6.get(i)+points2D7.get(i)+points2D8.get(i)
									+points2D9.get(i)+points2D10.get(i);
						
						//WATER COLOR DECIDER
						if(deepWaterBottom <=sum && sum <deepWaterTop) {
							
							double difference = deepWaterHigh-deepWaterLow;
							double diffToLow = deepWaterHigh-sum;
							int deduct =  (int) Math.round(diffToLow/difference*200);
							if(deduct>120) { bf.setRGB(x, y, (new Color(0,50-((int)(deduct/4)),180-( (int) (0.45*(deduct)))).hashCode())); i++; continue; }
							if(deduct>65) { bf.setRGB(x, y, (new Color(0,50-((int)(deduct/4)),180-( (int) (0.46*(deduct)))).hashCode())); i++; continue; }
							if(deduct>45) { bf.setRGB(x, y, (new Color(0,50-((int)(deduct/4)),180-( (int) (0.47*(deduct)))).hashCode())); i++; continue; }
							if(deduct>35) { bf.setRGB(x, y, (new Color(0,50-((int)(deduct/4)),180-( (int) (0.48*(deduct)))).hashCode())); i++; continue; }
							if(deduct>30) { bf.setRGB(x, y, (new Color(0,50-((int)(deduct/4)),180-( (int) (0.50*(deduct)))).hashCode())); i++; continue; }
							if(deduct>25) { bf.setRGB(x, y, (new Color(0,60-((int)(deduct/4)),180-( (int) (0.40*(deduct)))).hashCode())); i++; continue; }
							if(deduct>20) { bf.setRGB(x, y, (new Color(0,70-((int)(deduct/4)),180-( (int) (0.30*(deduct)))).hashCode())); i++; continue; }
							if(deduct>15) { bf.setRGB(x, y, (new Color(0,80-((int)(deduct/4)),180-( (int) (0.20*(deduct)))).hashCode())); i++; continue; }
							if(deduct>10) { bf.setRGB(x, y, (new Color(0,90-((int)(deduct/4)),180-( (int) (0.10*(deduct)))).hashCode())); i++; continue; }
							else { bf.setRGB(x, y, (new Color(0,100-((int)(deduct/4)),180-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
						}
						else if(lightWaterBottom<=sum && sum<lightWaterTop) {
							double difference = lightWaterHigh-lightWaterLow;
							double diffToLow = lightWaterHigh-sum;
							int deduct =  (int) Math.round(diffToLow/difference*200);
							if(deduct>150) { bf.setRGB(x, y, (new Color(0,112-((int)(0.05*(deduct))),185-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							if(deduct>110) { bf.setRGB(x, y, (new Color(0,115-((int)(0.05*(deduct))),185-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							if(deduct>100) { bf.setRGB(x, y, (new Color(0,130-((int)(0.05*(deduct))),190-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							if(deduct>85) { bf.setRGB(x, y, (new Color(0,145-((int)(0.05*(deduct))),195-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							if(deduct>70) { bf.setRGB(x, y, (new Color(0,160-((int)(0.05*(deduct))),200-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							if(deduct>55) { bf.setRGB(x, y, (new Color(0,185-((int)(0.05*(deduct))),205-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							if(deduct>40) { bf.setRGB(x, y, (new Color(0,200-((int)(0.05*(deduct))),210-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							if(deduct>25) { bf.setRGB(x, y, (new Color(0,215-((int)(0.05*(deduct))),215-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
							else { bf.setRGB(x, y, (new Color(0,230-((int)(0.05*(deduct))),230-( (int) (0.05*(deduct)))).hashCode())); i++; continue; }
						}
						else if(sandBottom<=sum && sum<sandTop) {
							double difference = sandHigh-sandLow;
							double diffToLow = sandHigh-sum;
							int deduct =  (int) Math.round(diffToLow/difference*200);
							if(deduct>160) { bf.setRGB(x, y, (new Color(200+((int) (0.25*deduct)),175+((int) (0.25*deduct)),90+((int) (0.25*deduct)) ).hashCode())); i++; continue; }
							if(deduct>120) { bf.setRGB(x, y, (new Color(200+((int) (0.23*deduct)),175+((int) (0.23*deduct)),90+((int) (0.23*deduct)) ).hashCode())); i++; continue; }
							if(deduct>80) { bf.setRGB(x, y, (new Color(200+((int) (0.20*deduct)),175+((int) (0.20*deduct)),90+((int) (0.20*deduct)) ).hashCode())); i++; continue; }
							if(deduct>40) { bf.setRGB(x, y, (new Color(200+((int) (0.17*deduct)),175+((int) (0.17*deduct)),90+((int) (0.17*deduct)) ).hashCode())); i++; continue; }
							else { bf.setRGB(x, y, (new Color(200+((int) (0.14*deduct)),175+((int) (0.14*deduct)),90+((int) (0.14*deduct)) ).hashCode())); i++; continue; }
						}
						//GREEN COLOR DECIDER
						else if(greenBottom<=sum && sum<greenTop) {
							double difference = greenHigh-greenLow;
							double diffToLow = greenHigh-sum;
							int deduct =  (int) Math.round(diffToLow/difference*100);
							
							bf.setRGB(x, y, (new Color(0,100+deduct,0).hashCode()));
							i++;
							continue;
						}
						else if(mountBottom<=sum && sum<mountTop) {
							bf.setRGB(x, y, Color.DARK_GRAY.getRGB());
							i++;
							continue;
						}
						else if (peakBottom<=sum && sum<peakTop){
							bf.setRGB(x, y, Color.WHITE.getRGB());
							i++;
						}
						
						
						
						/*
						//BLACK AND WHITE VARIENT
						double difference = peakHigh-deepWaterLow;
						double diffToLow = peakHigh-sum;
						int deduct =  (int) Math.round(diffToLow/difference*255);
						//System.out.println(difference);
						//System.out.println(diffToLow);
						//System.out.println(deduct);
						bf.setRGB(x, y, (new Color(255-(deduct),255-(deduct),255-(deduct)).hashCode()) );
						i++;
						*/
						
						
					}
				}
				mapDrawn=true;
				
				//allow users to click button again
				seedButton.disable=false;
				seedContainer.disable=false;
			}
		}
		
		
		
		repaint();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(gettingPerlinValues) {
			//currentSeed = (int) Math.round(Math.random()*1000000);
			Noise.seedInput(currentSeed);
			
			//2D TEST
			//minus values should be 0.50 but are currently 0.14 for testing
			for(double y =0; y<height; y+=1.0) {
				for(double x=0; x<width*3/4; x++) {
					//System.out.println(Noise.noise(freq*x/(width*3/4)-0.5, freq*4*y/(height)-0.5));
					//Noise.seedInput(1000);
					points2D.add(Noise.noise(freq*x/(width*3/4)-0.14, freq*y/(height)-0.14));
					points2D2.add((1.0/2.0)*Noise.noise(freq*2*x/(width*3/4)-0.14, freq*2*y/(height)-0.14));
					points2D3.add((1.0/4.0)*Noise.noise(freq*4*x/(width*3/4)-0.14, freq*4*y/(height)-0.14));
					points2D4.add((1.0/8.0)*Noise.noise(freq*8*x/(width*3/4)-0.14, freq*8*y/(height)-0.14));
					points2D5.add((1.0/16.0)*Noise.noise(freq*16*x/(width*3/4)-0.14, freq*16*y/(height)-0.14));
					points2D6.add((1.0/32.0)*Noise.noise(freq*32*x/(width*3/4)-0.14, freq*32*y/(height)-0.14));
					points2D7.add((1.0/64.0)*Noise.noise(freq*64*x/(width*3/4)-0.14, freq*64*y/(height)-0.14));
					points2D8.add((1.0/128.0)*Noise.noise(freq*128*x/(width*3/4)-0.14, freq*128*y/(height)-0.14));
					points2D9.add((1.0/256.0)*Noise.noise(freq*256*x/(width*3/4)-0.14, freq*256*y/(height)-0.14));
					points2D10.add((1.0/256.0)*Noise.noise(freq*512*x/(width*3/4)-0.14, freq*512*y/(height)-0.14));
					
				}
			}
			
			
			//GET LOW AND HIGH VALUES OF EACH RANGE
			double highest=0;
			for(int i = 0; i< points2D.size(); i++) {
				//System.out.println(points2D.get(i));
				
				double sum = points2D.get(i)+points2D2.get(i)+points2D3.get(i)+points2D4.get(i)
							+points2D5.get(i)+points2D6.get(i)+points2D7.get(i)+points2D8.get(i)
							+points2D9.get(i)+points2D10.get(i);
				if(deepWaterBottom <=sum && sum <deepWaterTop) {
					if(deepWaterLow==0 && deepWaterHigh ==0) {deepWaterLow=sum;deepWaterHigh=sum;}
					else {if(sum<deepWaterLow) {deepWaterLow=sum;} if(sum>deepWaterHigh) {deepWaterHigh=sum;}}
					continue;
				}
				else if(lightWaterBottom<=sum && sum<lightWaterTop) {
					if(lightWaterLow==0 && lightWaterHigh ==0) {lightWaterLow=sum;lightWaterHigh=sum;}
					else {if(sum<lightWaterLow) {lightWaterLow=sum;} if(sum>lightWaterHigh) {lightWaterHigh=sum;}}
					continue;
				}
				else if(sandBottom<=sum && sum<sandTop) {
					if(sandLow==0 && sandHigh ==0) {sandLow=sum;sandHigh=sum;}
					else { if(sum<sandLow) {sandLow=sum;} if(sum>sandHigh) {sandHigh=sum;}}
					continue;
				}
				else if(greenBottom<=sum && sum<greenTop) {
					if(greenLow==0 && greenHigh ==0) { greenLow=sum; greenHigh=sum;}
					else {if(sum<greenLow) {greenLow=sum;} if(sum>greenHigh) {greenHigh=sum;}}
					continue;	
				}
				else if(mountBottom<=sum && sum<mountTop) {
					if(mountLow==0 && mountHigh ==0) {mountLow=sum; mountHigh=sum;}
					else { if(sum<mountLow) {mountLow=sum;} if(sum>mountHigh) {mountHigh=sum;}}
					continue;
				}
				else if (peakBottom<=sum && sum<peakTop){
					if(peakLow==0 && peakHigh ==0) { peakLow=sum; peakHigh=sum;}
					else { if(sum<peakLow) {peakLow=sum;} if(sum>peakHigh) {peakHigh=sum;}}
					continue;	
				}
				
				
				//if(sum>highest) {
				//	highest = points2D.get(i)+points2D2.get(i)+points2D3.get(i)+points2D4.get(i)+points2D5.get(i);
				//}
			}
			/*
			System.out.print(deepWaterLow); System.out.print(" , "); System.out.println(deepWaterHigh);
			System.out.print(lightWaterLow); System.out.print(" , "); System.out.println(lightWaterHigh);
			System.out.print(sandLow); System.out.print(" , "); System.out.println(sandHigh);
			System.out.print(greenLow); System.out.print(" , "); System.out.println(greenHigh);
			System.out.print(mountLow); System.out.print(" , "); System.out.println(mountHigh);
			System.out.print(peakLow); System.out.print(" , "); System.out.println(peakHigh);
			//System.out.println(highest);
			 */
			
			
			gettingPerlinValues=false;
			mapDrawn=false;
			startDrawing=true;
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
		if(seedContainer.pressed && seedContainer.currentString.length()<9) {
				if(Character.isDigit(e.getKeyChar())) {
					seedContainer.currentString=seedContainer.currentString+=e.getKeyChar();
				}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(seedContainer.pressed) {
			if(seedContainer.currentString.length()>0 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				seedContainer.currentString = seedContainer.currentString.substring(0, seedContainer.currentString.length()-1);
			}
			else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(!seedContainer.disable) {
					currentSeed = Integer.valueOf(seedContainer.currentString);
					gettingPerlinValues=true;
					points2D.clear();
					points2D2.clear();
					points2D3.clear();
					points2D4.clear();
					points2D5.clear();
					points2D6.clear();
					points2D7.clear();
					points2D8.clear();
					points2D9.clear();
					points2D10.clear();
					seedButton.disable=true;
					seedContainer.disable=true;
				}
			}
			//USELESS FOR SEED GENERATION BUT KEEP FOR FUTURE BUTTONS
			/*
			else if (seedContainer.currentString.length()<9 && e.getKeyCode() == KeyEvent.VK_PERIOD && !seedContainer.currentString.contains(".")) {
				seedContainer.currentString=seedContainer.currentString+=e.getKeyChar();
			}
			*/
		}
		
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
		
		if(seedContainer.mouseOnButton(e.getPoint())) {
			seedContainer.pressed = true;
		}
		else {
			seedContainer.pressed=false;
		}
		
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(exitButton.mouseOnButton(e.getPoint())) {
			exitButton.motion=true;
			exitButton.pressed=true;
		}
		if(!seedButton.disable) {
			if(seedButton.mouseOnButton(e.getPoint())) {
				seedButton.motion=true;
				seedButton.pressed=true;
			}
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
		
		if(!seedButton.disable) {

			if(seedButton.mouseOnButton(e.getPoint())) {
				if(seedButton.pressed) {
					currentSeed = (int) Math.round(Math.random()*1000000);
					seedButton.motion=false;
					seedButton.pressed=false;
					gettingPerlinValues=true;
					points2D.clear();
					points2D2.clear();
					points2D3.clear();
					points2D4.clear();
					points2D5.clear();
					points2D6.clear();
					points2D7.clear();
					points2D8.clear();
					points2D9.clear();
					points2D10.clear();
					seedButton.disable=true;
				}
			}
			else {
				seedButton.motion=false;
				seedButton.pressed=false;
			}
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
		
		if(seedButton.mouseOnButton(e.getPoint())) {
			if(seedButton.pressed) {
				seedButton.motion=true;
			}
		}
		else {
			seedButton.motion=false;
		}
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	

	
}
