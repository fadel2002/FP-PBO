import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayPictureGUI extends JFrame{
	/**
	 * DisplayPictureGUI class for the display picture
	 */
	public DisplayPictureGUI (ImageIcon imgDP, Point loc, Color c){
		// new frame
		JFrame f = new JFrame("Image");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// set collor same as the GUI's background 
		panel.setBackground(c);
		
		// go back arrow icon
		Image img = new ImageIcon(this.getClass().getResource("/3.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon img1 = new ImageIcon(img);
		JLabel label = new JLabel(img1); //Image of the go back arrow
		label.setBounds(5, 17, 30, 30);
		panel.add(label);
		
		// action when the go back icon is clicked
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				f.setVisible(false);       	
			}
		});
		
		// add display picture
		JLabel pic = new JLabel(imgDP);
		panel.add(pic);
		f.add(panel);
		f.setUndecorated(true);
		f.pack();
		f.setVisible(true);
		
		// set pop up location of the display picture
		loc.setLocation(loc.x + 7, loc.y);
		f.setLocation(loc);
	}
}
