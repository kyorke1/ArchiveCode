/**
 * 
 * @author KyleYorke
 *
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;



public class CyberFrog extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread; // The thread the object is using.
	private int xPos; // The x position of the frog.
	private int yPos; // The y position of the frog.
	private CyberFly fly; // A reference to the fly the frog is chasing.
	private boolean hungry; // A boolean field to determine whether the frog is hungry or not.
	private String name; // The name of the frog.
	private JTextArea textArea; // A reference to the JTextArea within the Environment class.
	private JPanel displayArea;// A reference to the JPanel within the Environment class. 
	private static BufferedImage image;	// BufferedImage used to hold the image file.
	private JLabel label; // A JLabel is used to allow the image to be moved randomly.
	
	public CyberFrog(String name){
		super();
		
		try {
			image = (ImageIO.read(new File("frog.png")));
		}
		catch (IOException e){	
		}
		
		Random rand = new Random();
		int x = rand.nextInt(400) + 1;
		int y = rand.nextInt(400) + 1;
		insertImage();
		setSize(32,32);
		setLocation(x,y);
		setName(name);
		setBackground(null);
	}
	
	// Method for creating the frog image
	public void insertImage() {
		label = new JLabel();
		label.setIcon(new ImageIcon(image));
		label.setSize(32, 37);
		label.setLocation(0,0);
		add(label);
	}
	
	// Method that makes the frog move randomly
	private void changeLocation() {

		Random step = new Random();

		int xStep = step.nextInt(10) + 5;
		int yStep = step.nextInt(10) + 5;

		int currentX = getFrogX();
		int currentY = getFrogY();

		Random operator = new Random();

		int xOne = operator.nextInt(2);
		int yTwo = operator.nextInt(2);

		if (xOne == 0) {
			xPos = currentX - xStep;
		}

		if (xOne == 1){
			xPos = currentX + xStep;
		}

		if (yTwo == 0) {
			yPos = currentY - yStep;
		}

		if (yTwo == 1){

			yPos = currentY + yStep;

		}

		bounce();

		setLocation(xPos, yPos);
	}

	@Override
	public void run() {
		Thread current = Thread.currentThread();
		while (current.isAlive() == true) {
			if(hungry == false){
				changeLocation();
			}			
			else {
				Hungry();				
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		thread = null;
	}

	// Method that makes the frog chase the fly only after the hungry button has been pressed by the user
	public void Hungry() {	
		
		Random random = new Random();
		
		int number = random.nextInt(10) + 25;
		
		int number2 = random.nextInt(10) + 25;
		
		int flyX = getFlyX();
		int flyY = getFlyY();
		
		if(xPos > flyX){
			xPos = xPos - number;			
		}
		
		if(xPos < flyX) {
			xPos = xPos + number;
		}
		
		if(yPos > flyY){
			yPos = yPos - number2;
		}
		
		if(yPos < flyY){
			yPos = yPos + number2;
		}
		
		bounce();
		
		drawTongue(displayArea.getGraphics());
		
		setLocation(xPos, yPos);
	}

	// When the frog gets within 60 pixels a red line is drawn and then text is displayed
	public void drawTongue(Graphics g) {		
		
		int flyX = getFlyX();
		int flyY = getFlyY();
		
		int frogX = getFrogX();
		int frogY = getFrogY();	
		
		distance();
			
		if(distance() <= 60){		
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.red);               
			g2d.drawLine(flyX, flyY , frogX + 16, frogY + 16);		
			fly.stop();       
			pause();		
			hungry = false;				
			final String newline = "\n";		
			textArea.append(getName() + " " + "is no longer hungry!" + newline);		
			textArea.repaint();		
			displayArea.remove(fly);		
			fly = null;		
			displayArea.repaint();
		}      
	}

	public void pause() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Math to make the frog chase down the fly
	private double distance() {		
		double dx = getFrogX() - getFlyX();	
		double dy = getFrogY() - getFlyY();		
		double answer = Math.sqrt(dx*dx + dy*dy);		
		return answer;
	}

	public int getFrogY() {
		return getY();
	}

	public int getFrogX() {
		return getX();
	}

	// Method used to stop the frog from exiting the screen
	private void bounce() {

		if (xPos < 20) {
			xPos = xPos + 50;
		}

		else if (xPos > 480) {
			xPos = xPos - 50;
		}

		else if (yPos < 20) {
			yPos = yPos + 50;
		}

		else if (yPos > 480) {
			yPos = yPos - 50;
		}			
	}

	public int getFlyY() {
		return fly.getY();
	}

	public int getFlyX() {
		return fly.getX();
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		thread.interrupt();
		thread.stop();
		
	}
	public void setHungry(boolean hungry) {	
		if (fly == null){
			
		}
		else{
			this.hungry = hungry;
		}
	}	
	public void hungryText() {
		if (hungry == true){
			textArea.append(getName() + " is now hungry" + "\n");
		}
	}
	
	public void setTextArea(JTextArea TA1){
		textArea = TA1;
	}


	public boolean getHungry() {
		return hungry;

	}
	
	public void addFly(CyberFly fly){
		this.fly = fly;
	}

	public void getPanel1(JPanel panel1) {
		displayArea = panel1;
		
	}

	public void setPanel3(JPanel panel3) {
		// TODO Auto-generated method stub
		
	}
	


}