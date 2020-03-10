/**
 * 
 * @author KyleYorke
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class UseCyberFrog extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 JButton makePet, Hungry, Reset;
	 JPanel panel1, panel2, panel3;
	 JTextField textField;
	 JTextArea textArea;
	
	 CyberFrog cyberfrog;
	
	public static void main(String[]args){
		UseCyberFrog demo = new UseCyberFrog();
		demo.setSize(850, 850);
		demo.createGUI();
		demo.show();
	}
	
	// Starts to create the GUI and sets the layout
	private void createGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container window = getContentPane();
		window.setLayout(new FlowLayout());
	// set the panel that is the background	
		panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(500,500));
		panel1.setBackground(Color.lightGray);
		window.add(panel1);
	// set the panel that the pets are diplayed		
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(500,50));
		panel2.setBackground(Color.white);
		window.add(panel2);
	// Sets the area where you enter a name	
		textField = new JTextField ("Please enter a name");
		window.add(textField);
		textField.setPreferredSize(new Dimension(150, 20));
		textField.addActionListener(this);
		panel2.add(textField);
	//Creates the button that creates the pet	
		makePet = new JButton("Make a new Frog");
		window.add(makePet);
		makePet.addActionListener(this);
		panel2.add(makePet);
	//Creates the button that makes said pet hungry 	
		Hungry = new JButton("Hungry");
		window.add(Hungry);
		Hungry.addActionListener(this);
		panel2.add(Hungry);
	//Creates the button that clears the display area screen	
		Reset = new JButton("Reset");
		window.add(Reset);
		Reset.addActionListener(this);
		panel2.add(Reset);
	//Creates the area that displays where all the text is	
		textArea = new JTextArea("");
		textArea.setLocation(0, 0);
		textArea.setSize(500, 100);
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);	
		
		panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(500,100));
		panel3.setBackground(Color.white);
		window.add(panel3);
		panel3.add(textArea);
		
		
		cyberfrog = new CyberFrog("Kyle");
	}

		
		
	//Method so that when then makePet button is pressed it runs a boolean to check if a valid name has been entered before a frog is displayed
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == makePet) {
			
			if (textField.getText().isEmpty()==true || textField.getText().equals("Please enter a name")){
				JOptionPane.showMessageDialog(null, "Please will you give your pet frog a name");
            }
			else {
				String name = textField.getText();
				CyberFrog cyberfrog = new CyberFrog(name);
				cyberfrog.start();
				cyberfrog.getPanel1(panel1);
				textArea.append("Your CyberFrog's name is: " + name + "\n");
				CyberFly cyberfly = new CyberFly();
				cyberfly.start();
				cyberfrog.addFly(cyberfly);
				panel1.add(cyberfrog);
				panel1.add(cyberfly);
				panel1.repaint();
				
			}
		}
		// Method that clearrs the screen then displays the appropriate text 
		else if (event.getSource() == Reset){
			while (panel1.getComponentCount() > 0){
				for (int i = 0; i < panel1.getComponentCount(); i++){
					if (panel1.getComponent(i).getName() != null){
						CyberFrog cyberfrog = (CyberFrog) panel1.getComponent(i);
						cyberfrog.stop();
					}
					else {
						CyberFly cyberfly = (CyberFly) panel1.getComponent(i);
						cyberfly.stop();
					}
					panel1.remove(i);
					panel1.repaint();

				}
			}
			textArea.setText("The screen has been reset!" + "\n");
		}
		//Method that makes the frog chase the fly once the button is pressed
		if (event.getSource()==Hungry){
			for (int i = 0; i < panel1.getComponentCount(); i++){
			if(panel1.getComponent(i).getName() != null){
				CyberFrog frog = (CyberFrog) panel1.getComponent(i);
				frog.setHungry(true);
				frog.setTextArea(textArea);
				frog.hungryText();
				
				}
			}
		}
	}
}
