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

	public DisplayPictureGUI (ImageIcon imgServer, Point loc, Color c){
		JFrame f = new JFrame("Image");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(c);

		Image img = new ImageIcon(this.getClass().getResource("/3.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		
		ImageIcon img1 = new ImageIcon(img);
		JLabel label = new JLabel(img1); //Image of the go back arrow
		label.setBounds(5, 17, 30, 30);
		panel.add(label);
		
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				f.setVisible(false);       	
			}
		});
		
		JLabel pic = new JLabel(imgServer);
		panel.add(pic);
		f.add(panel);
		f.setUndecorated(true);
		f.pack();
		f.setVisible(true);
		loc.setLocation(loc.x + 7, loc.y);
		f.setLocation(loc);
	}
}
