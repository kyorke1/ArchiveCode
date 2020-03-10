import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class represents a frog.
 * @author Abdul Razak
 *
 */
public class Frog extends JPanel implements Runnable {

	private static final long serialVersionUID = 5136841004510584283L;	
	private Thread thread; // The thread the object is using.
	private int xPos; // The x position of the frog.
	private int yPos; // The y position of the frog.
	private Fly fly; // A reference to the fly the frog is chasing.
	private boolean hungry; // A boolean field to determine whether the frog is hungry or not.
	private String name; // The name of the frog.
	private JTextArea textArea; // A reference to the JTextArea within the Environment class.
	private JPanel displayArea; // A reference to the JPanel within the Environment class.
	private static BufferedImage image;	// BufferedImage used to hold the image file.
	private JLabel label; // A JLabel is used to allow the image to be moved randomly.

	/**
	 * The Constructor method is used to create a Frog object.
	 * 
	 * @param name - The name of the frog.
	 */
	public Frog(String name) {
		super();

		try {
			image = (ImageIO.read(new File("frog.png")));
		}

		catch (IOException e) {
		}
		Random rand = new Random();
		int x = rand.nextInt(400) + 1;
		int y = rand.nextInt(400) + 1;
		insertImage();
		setSize(32, 32);		
		setLocation(x, y);
		setName(name);
		setBackground(null);		
	}

	/**
	 * This method is used to transfer the image from a BuffreredImage into a JLabel.
	 */
	private void insertImage() {		
		label = new JLabel();		
		label.setIcon(new ImageIcon(image));
		label.setSize(32, 32);
		label.setLocation(0,0);
		add(label);
	}

	@SuppressWarnings("deprecation")
	/**
	 * This method is used to stop the current thread the Frog is using.
	 */
	public void stop() {
		thread.interrupt();
		thread.stop();
	}
	
	/**
	 * This method is used to pause the thread the Frog object is using for two seconds.
	 */
	public void pause() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to make the Frog move randomly on the screen.
	 */
	private void changeLocation() {
		
		Random step = new Random();
		
		int xStep = step.nextInt(20) + 1;
		int yStep = step.nextInt(20) + 1;
		
		int currentX = getFrogX();
		int currentY = getFrogY();		
		
		Random operator = new Random();
		
		int xOne = operator.nextInt(2);
		int yTwo = operator.nextInt(2);
		
		if(xOne == 1){
			xPos = currentX - xStep;
		}
		
		if(xOne == 0) {
			xPos = currentX + xStep;
		}
		
		if(yTwo == 1){
			yPos = currentY - yStep;
		}
		
		if(yTwo ==0){			
			yPos = currentY + yStep;
			
		}
		
		bounce();
		
		setLocation(xPos, yPos);	
	}

	/**
	 * This method is used to move the frog object back into the display area if it begins to go outside.
	 */
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

	@Override
	/**
	 * This method is used to run the thread.
	 */
	public void run() {
		Thread current = Thread.currentThread();
		while (current.isAlive() == true) {
			if(hungry == false){
				changeLocation();
			}			
			else {
				hungry();				
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		thread = null;
	}

	/**
	 * This method is used to create a new thread when the object is created.
	 */
	public void start() {
		thread = new Thread(this);
		thread.start();
	}	
	
	/**
	 * This method is used to change the location of the Frog to chase the prey.
	 */
	public void hungry() {	
		
		Random random = new Random();
		
		int number = random.nextInt(20) + 1;
		
		int number2 = random.nextInt(20) + 1;
		
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
	
	/**
	 * This method is used to draw a tongue between the Frog and the fly to replicate the 
	 * fly being eaten.
	 * 
	 * @param g - The graphics of the extended JPanel
	 */
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
			textArea.append(getName() + " " + "is not hungry!" + newline);		
			textArea.repaint();		
			displayArea.remove(fly);		
			fly = null;		
			displayArea.repaint();
		}      
	}
	
	/**
	 * This method is used to determine the distance between the Frog and the Fly objects.
	 * 
	 * @return answer - The distance between the Frog and the fly.
	 */
	private double distance() {		
		double dx = getFrogX() - getFlyX();	
		double dy = getFrogY() - getFlyY();		
		double answer = Math.sqrt(dx*dx + dy*dy);		
		return answer;		
	}
	
	/**
	 * This method is used to inform the user when the Frog is hungry within the JTextArea provided.
	 */
	public void hungryTextArea() {
		if(hungry == true){
			final String newline = "\n";					
			textArea.append(getName() + " " + "is hungry!" + newline);	
		}
	}
	
	/**
	 * This method is used to get the JPanel within the GUI that is used to display
	 * the animals.
	 */
	public void getDisplayArea(JPanel panel){
		displayArea = panel;
	}
	
	/**
	 * This method is used to return the name of frog.
	 * 
	 * @return name - The name of the Frog
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method is used to set the name of the frog.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method is used to return the x position of the fly.
	 * 
	 * @return fly.getX() - The x position of the fly
	 */
	public int getFlyX() {
		return fly.getX();
	}
	
	/**
	 * This method is used to return the y position of the fly.
	 * 
	 * @return fly.getY() - The y position of the fly
	 */
	public int getFlyY() {
		return fly.getY();
	}
	
	/**
	 * This method is used to return a boolean result which determines whether
	 * the frog is hungry or not.
	 * 
	 * @return
	 */
	public boolean getHungry() {
		return hungry;
	}
	
	/**
	 * This method is used to return the JTextArea within the GUI.
	 * 
	 * @return textArea - The JPanel used to display the animals.
	 */
	public JTextArea getTextArea() {
		return textArea;
	}
	
	/**
	 * This method is used to set the textArea field with the JTextArea within the GUI.
	 */
	public void setTextArea(JTextArea area) {
		textArea = area;
	}	
	
	/**
	 * This method is used to set the frogs prey.
	 */
	public void addFly(Fly fly){
		this.fly = fly;
	}
	
	/**
	 * This method is used to set the state of the frogs hunger.
	 */
	public void setHungry(boolean hungry) {		
		if(fly == null){			
		}		
		else {
			this.hungry = hungry;
		}
	}
	
	/**
	 * This method is used to return the x position of the frog.
	 * 
	 * @return getX() - The Frogs x position 
	 */
	public int getFrogX() {
		return getX();
	}
	
	/**
	 * This method is used to return the y position of the frog.
	 * 
	 * @return getY() - The Frogs y position 
	 */
	public int getFrogY() {
		return getY();
	}
}
