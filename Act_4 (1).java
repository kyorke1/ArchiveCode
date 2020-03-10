package javaSwing_activity_4;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Act_4 implements  ActionListener{
	  
	JFrame convFrame;
	  JPanel convPanel;
	  JTextField tempCelsius;
	  JLabel celsiusLabel, fahrenheitLabel;
	  JButton convTemp;
	  
	  public void CelsiusConverter() {
		    // Create the frame and container.
		    convFrame = new JFrame("Convert Celsius to Fahrenheit");
		    convPanel = new JPanel();
		    convPanel.setLayout(new GridLayout(2, 2));
		    
		    // Add the widgets.
		    addWidgets();

		    // Add the panel to the frame.
		    convFrame.getContentPane()
		        .add(convPanel, BorderLayout.CENTER);

		    // Exit when the window is closed.
		    convFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    // Show the converter.
		    convFrame.pack();
		    convFrame.setVisible(true);
	  }

	private void addWidgets() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
