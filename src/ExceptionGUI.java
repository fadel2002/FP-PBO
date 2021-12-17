import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ExceptionGUI extends JFrame{

	public ExceptionGUI (Point loc){
		JFrame f = new JFrame("Exception");
		JPanel panel = new JPanel();
		
		// griy layout
		GridLayout layout = new GridLayout(2, 1);
		panel.setLayout(layout);
		
		JLabel text = new JLabel("This feature is not available now");
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
		text.setForeground(Color.black); //Label to show if the user is Active
		text.setBounds(110, 36, 100, 20);
		JPanel txt = new JPanel();
		txt.add(text);
		
		JButton button = new JButton("Close");
		JPanel key = new JPanel();
		key.add(button);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				f.setVisible(false);       	
			}
		});
		
		// add all panel
		panel.add(txt);
		panel.add(key);

		f.add(panel, BorderLayout.CENTER);
		f.setUndecorated(true);
		//set border
		f.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
		f.pack();
		f.setVisible(true);
		loc.setLocation(loc.x+20, loc.y+20);
		f.setLocation(loc);
		
	}
}
