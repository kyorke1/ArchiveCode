import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
/**
 * This class demonstrates how to load an Image from an external file.
 * Class name LoadImageDemo
 * Abdul Razak
 * Images could be of any type (.bmp, .jpg, .gif …etc.)
 */
public class LoadImageDemo extends Component {
           
    BufferedImage img;
 
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
 
    public LoadImageDemo() {
       try {
           img = ImageIO.read(new File("frog.png"));
       } catch (IOException e) {
       }
 
    }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
 
    public static void main(String[] args) {
 
        JFrame f = new JFrame("Load Image Demo");
             
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
 
        f.add(new LoadImageDemo());
        f.setSize(500,500);
        f.setVisible(true);
    }
}
