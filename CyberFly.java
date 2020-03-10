import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;

public class CyberFly extends JPanel implements Runnable {

	private static final long serialVersionUID = 5136841004510584283L;
	private Thread thread; // The thread the fly uses for random movement.
	private int xPos; // The x - axis position of the fly.
	private int yPos; // The y - axis position of the fly.

	// default constructor 
	public CyberFly() {
		super();
		Random rand = new Random();
		int x = rand.nextInt(400) + 1;
		int y = rand.nextInt(400) + 1;
		setSize(10, 10);
		setLocation(x, y);
		setBackground(Color.black);
	}

	public void stop() {
		thread.interrupt();
		thread.stop();
	}

	//Method to make the fly move at random
	private void changeLocation() {

		Random step = new Random();

		int xStep = step.nextInt(10) + 25;
		int yStep = step.nextInt(10) + 25;

		int currentX = getFlyX();
		int currentY = getFlyY();

		Random operator = new Random();

		int xOne = operator.nextInt(2);
		int yTwo = operator.nextInt(2);

		if (xOne == 1) {
			xPos = currentX - xStep;
		}

		else {
			xPos = currentX + xStep;
		}

		if (yTwo == 1) {
			yPos = currentY - yStep;
		}

		else {

			yPos = currentY + yStep;

		}

		bounce();

		setLocation(xPos, yPos);
	}

	//Prevents the fly from exiting the screen
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


	public void pause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Thread current = Thread.currentThread();
		while (current.isAlive() == true) {
			changeLocation();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		thread = null;
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	//Used to return the information in the fly's  X position
	public int getFlyX() {
		return getX();
	}
	
	//Used to return the information in the fly's  Y position
	public int getFlyY() {
		return getY();
	}
}
