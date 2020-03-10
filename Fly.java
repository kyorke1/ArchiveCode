import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;

/**
 * This is the fly class which is represented by a graphic component of a square dot.
 * @author Abdul Razak
 * 
 *The class inherits from JPanel and implements the runnable interface which allows multithreading.
 */

public class Fly extends JPanel implements Runnable {

	private static final long serialVersionUID = 5136841004510584283L;
	private Thread thread; // The thread the fly uses for random movement.
	private int xPos; // The x - axis position of the fly.
	private int yPos; // The y - axis position of the fly.

	/**
	 * Default Constructor
	 */
	public Fly() {
		super();
		Random rand = new Random();
		int x = rand.nextInt(400) + 1;
		int y = rand.nextInt(400) + 1;
		setSize(10, 10);
		setLocation(x, y);
		setBackground(Color.black);
	}

	@SuppressWarnings("deprecation")
	/**
	 * This method is used to stop the thread.
	 */
	public void stop() {
		thread.interrupt();
		thread.stop();
	}

	/**
	 * This method is used to move the fly randomly.
	 */
	private void changeLocation() {

		Random step = new Random();

		int xStep = step.nextInt(10) + 1;
		int yStep = step.nextInt(10) + 1;

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

	/**
	 * This method is used to prevent the fly from moving outside the display.
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

	/**
	 * This method is used to pause the fly's movement for two seconds.
	 */
	public void pause() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * This method is used to run the thread whilst it is still alive.
	 */
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

	/**
	 * This method is used to create and start a new thread for the fly object.
	 */
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * This method is used to return the x position of the fly.
	 * 
	 * @return getX() - The Fly's x position.
	 */
	public int getFlyX() {
		return getX();
	}
	
	/**
	 * This method is used to return the y position of the fly.
	 * 
	 * @return getY() - The Fly's y position.
	 */
	public int getFlyY() {
		return getY();
	}
}

