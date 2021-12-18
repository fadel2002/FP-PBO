import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame implements ActionListener{
	/**
	 * Client class for server interlocutor 
	 */
	
	// initialize variables and objects
	static JPanel p1;
	JTextField text;
	JButton button;
	static JPanel area;
    static JFrame f1 = new JFrame();
    static Box vertical = Box.createVerticalBox();
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	Boolean typing;
	static ImageIcon clientIcon;
	static ImageIcon clientDP;
	
	// get client image icon
	public ImageIcon getClientIcon () {
		return clientIcon;
	}
	
	// get client for client display picture
	public ImageIcon getClientDP () {
		return clientDP;
	}
	
	// contructor for client image
	public Client(String str) {
		Image img = new ImageIcon(this.getClass().getResource("/male-modified.png")).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		clientDP = new ImageIcon(img);
		img = new ImageIcon(this.getClass().getResource("/male-modified.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		clientIcon = new ImageIcon(img);
	}
	
	public Client(){
		
		f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.blue);
		p1.setBounds(0, 0, 450, 70);
		f1.add(p1);
		
		Image img = new ImageIcon(this.getClass().getResource("/3.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		
		ImageIcon img1 = new ImageIcon(img);
		JLabel label = new JLabel(img1);
		label.setBounds(5, 17, 30, 30);
		p1.add(label);

		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
		
		Image img2 = new ImageIcon(this.getClass().getResource("/male-modified.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		
		ImageIcon img3 = new ImageIcon(img2);
		JLabel label2 = new JLabel(img3);
		label2.setBounds(40, 5, 60, 60);
		p1.add(label2);
		
		// client display picture
		Image cDP = new ImageIcon(this.getClass().getResource("/male-modified.png")).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		label2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				new DisplayPictureGUI(new ImageIcon(cDP), f1.getLocation(), p1.getBackground());
			}
		});
		
		Image img4 = new ImageIcon(this.getClass().getResource("/video.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		
		ImageIcon img5 = new ImageIcon(img4);
		JLabel label5 = new JLabel(img5);
		label5.setBounds(290, 23,30, 30);
		p1.add(label5);
		
		Image img6 = new ImageIcon(this.getClass().getResource("/phone.png")).getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);

		ImageIcon img7 = new ImageIcon(img6);
		JLabel label6 = new JLabel(img7);
		label6.setBounds(350, 23, 35, 30);
		p1.add(label6);
		
		Image img8 = new ImageIcon(this.getClass().getResource("/3icon.png")).getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);

		ImageIcon img9 = new ImageIcon(img8);
		JLabel label7 = new JLabel(img9);
		label7.setBounds(410, 23, 13, 25);
		p1.add(label7);
		
		
		// action when video icon is clicked
		label5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				new ExceptionGUI(f1.getLocation());
			}
		});
		// action when phone icon is clicked
		label6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				new ExceptionGUI(f1.getLocation());
			}
		});
		// action when 3 dots icon is clicked
		label7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				new ExceptionGUI(f1.getLocation());
			}
		});
		
		JLabel label3 = new JLabel("Name 2");
		label3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
		label3.setForeground(Color.white);
		label3.setBounds(110, 17, 100, 20);
		p1.add(label3);
		
		JLabel label4 = new JLabel("Active Now");
		label4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
		label4.setForeground(Color.white);
		label4.setBounds(110, 36, 100, 20);
		p1.add(label4);
		
		Timer t = new Timer(1, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(!typing){
					label4.setText("Active Now");
				}
			}
	    });
	       
	    t.setInitialDelay(2000);
		
		area = new JPanel();
		area.setBounds(5, 75, 440, 570);
		area.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		
		JScrollPane pane = new JScrollPane(area);
		pane.setBounds(5, 75, 440, 570);
		pane.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		pane.getVerticalScrollBar().setUnitIncrement(14);
		f1.add(pane);
		
		text = new JTextField();
		text.setBounds(5, 655, 320, 40);
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		f1.add(text);
		
		text.addKeyListener(new KeyAdapter(){
	        public void keyPressed(KeyEvent ke){
	        	label4.setText("typing...");
		               
		        t.stop();
		        typing = true;
	        }
		           
	        public void keyReleased(KeyEvent ke){
	        	typing = false;
		               
	        	if(!t.isRunning()){
	        		t.start();
	        	}
	        }
		});
		
		button = new JButton("Send");
		button.setBounds(335, 655, 110, 40);
		button.setBackground(Color.green.darker());
		button.setForeground(Color.white);
		button.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		button.addActionListener(this);
		f1.add(button);
		
		f1.getContentPane().setBackground(Color.black);
		f1.setLayout(null);
		f1.setSize(465, 750);
		f1.setLocation(1000, 50);
		f1.setVisible(true);
	}
	
	// function to perform mouse on click
	@Override
	public void actionPerformed(ActionEvent ae){
		// TODO Auto-generated method stub
		try {
			String out = text.getText();
			// if the string on text label is not empty
			if(!out.isEmpty()) {
				
				// GUI for client chat box
				JPanel p2 = formatLabel(out);
				text.setText("");	
				area.setLayout(new BorderLayout());
				JPanel right = new JPanel(new BorderLayout());
				
				//So the messages appear at the right side of the screen
	            right.add(p2, BorderLayout.LINE_END);
	            vertical.add(right);
	            //Add a small space between the messages
	            vertical.add(Box.createVerticalStrut(15));
	            
	            area.add(vertical, BorderLayout.PAGE_START);
	            
	            // refresh the GUI
	            f1.revalidate();
	            
	            //Write the message at the dataOutputStream
				dout.writeUTF(out); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// function to generate client format text
	public static JPanel formatLabel(String out){
		// new panel for chat box
		JPanel p3 = new JPanel();
		
		// panel attribute displayed vertically
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
		        
		// make the label in which the messages will appear
		JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		l1.setBackground(new Color(37, 211, 102));
		l1.setOpaque(true);
		l1.setBorder(new EmptyBorder(15,15,15,50));
		        
		// time when message send 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		JLabel l2 = new JLabel();
		l2.setText(sdf.format(cal.getTime()));
		        
		p3.add(l1);
		p3.add(l2);
		return p3;
	}
	
	// function to generate server format text
	public static JPanel serverFormatLabel(String out, ImageIcon icon, ImageIcon dp){
		// make a new panel for the message
		JPanel p5 = new JPanel();
		JPanel p4 = formatLabel(out); 
		
		// panel attribute displayed vertically 
        p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
        
        // add image above the chat box 
		JLabel labelServerImg = new JLabel(icon); 
		
		// action when the image clicked
		labelServerImg.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				new DisplayPictureGUI(dp, f1.getLocation(), p1.getBackground());
			}
		});
		
		// add all panel to chat box panel
        p5.add(labelServerImg);
        p5.add(p4);
        return p5;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
		// object for client image
		Server sImg = new Server("");
		
		try {
			s = new Socket("127.0.0.1", 1000); // make a new connection with the server
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			String msgin = "";
			
			while(true){
				area.setLayout(new BorderLayout());
				
				msgin = din.readUTF();
				
				// GUI for the server chat box 
				JPanel p2 = serverFormatLabel(msgin, sImg.getServerIcon(), sImg.getServerDP());
                JPanel left = new JPanel(new BorderLayout());
                left.add(p2, BorderLayout.LINE_START);

                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                area.add(vertical, BorderLayout.PAGE_START);
                
	            try {
					Thread.sleep(1000);// sleep for 1000 milliseconds => 1 second
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // fter a second passes, then show the message to the Server
                f1.validate();    
			}
		}catch(Exception e) {
			System.out.println("There is no Server to connect to");
			System.out.println("Please run the Server class");
			e.printStackTrace();
		}
	}
}