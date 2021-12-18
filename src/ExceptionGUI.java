import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ExceptionGUI extends JFrame{
	/**
	 * ExceptionGUI class for warning of feature that has not available
	 */
	public ExceptionGUI (Point loc){
		// new frame 
		JFrame f = new JFrame("Exception");
		JPanel panel = new JPanel();
		
		// griy layout
		GridLayout layout = new GridLayout(2, 1);
		panel.setLayout(layout);
		
		// display for the warning box
		JLabel text = new JLabel("This feature is not available now");
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
		text.setForeground(Color.black);
		text.setBounds(110, 36, 100, 20);
		JPanel txt = new JPanel();
		txt.add(text);
		
		// button to close the warning box
		JButton button = new JButton("Close");
		JPanel key = new JPanel();
		key.add(button);
		
		// action when the button is clicked
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				f.setVisible(false);       	
			}
		});
		
		// add all panel
		panel.add(txt);
		panel.add(key);
		
		// centering the component
		f.add(panel, BorderLayout.CENTER);
		f.setUndecorated(true);
		
		//set border
		f.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
		f.pack();
		f.setVisible(true);
		
		// set pop up location of the warning box
		loc.setLocation(loc.x+20, loc.y+20);
		f.setLocation(loc);
		
	}
}
