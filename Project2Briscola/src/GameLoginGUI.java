

	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Contains the graphical of interface of the login screen.
 * @author Crow-kun
 *
 */
	public class GameLoginGUI extends JFrame
	{
		private JTextField usernameTF;
		private JTextField passwordTF;
		private String username;
		private String password;
		private static final int PORT= 404;
		private static final String HOST= "Crow-kun";
		private Socket SOCKET;
		private LobbyGUI lobby;
		static boolean accessgame=true;
		
		/**
		 * Main constructor, Initializes the main frame.
		 */
	    public GameLoginGUI()
	    {
	        this.setSize(800,600);
	        this.setTitle("Welcome to Briscola!");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setResizable(false);
	        
	        //Set frame in the center of screen.
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	        
	        showGUI();
  
	        this.setVisible(true);
	    }

	  /**
	   * Contains the components of the frame.
	   */
		private void showGUI() 
	    {
			
	        //Frame layout and background
	       
	        try {
				BufferedImage bImage= ImageIO.read(new File("briscolabackground.jpg"));
				this.setContentPane(new backgroundImage(bImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Image not found");
				e.printStackTrace();
			}
	        
	        //Login Panel initialization and layout
	        JPanel loginPanel= new JPanel();
	        loginPanel.setLayout(new BorderLayout());
	        loginPanel.setSize(400, 200);
	        JPanel centerPanel= new JPanel();
	        centerPanel.setLayout(new GridLayout(2,2,20,20));
	        centerPanel.setBackground(Color.WHITE);
	        
	        //Button Panel initialization and layout
	        JPanel buttonPanel= new JPanel();
	        buttonPanel.setLayout(new FlowLayout());
	        buttonPanel.setBackground(Color.WHITE);
	        
	        //Title Panel initialization and layout
	        JPanel titlePanel= new JPanel();
	        titlePanel.setLayout(new GridLayout(1,3,0,0));
	        
	        //Login Panel components
	        JLabel usernameLabel = new JLabel("Username: ");
	        usernameTF= new JTextField();
	        usernameTF.setPreferredSize(new Dimension(200,35));;
	        JLabel passwordLabel= new JLabel("Password: ");
	        passwordTF= new JTextField();
	        JButton loginButton= new JButton("Login");
	        loginButton.setBackground(new Color(100,100,255));
	        loginButton.setForeground(Color.BLACK);
	        
	        centerPanel.add(usernameLabel);
	        centerPanel.add(usernameTF);
	        centerPanel.add(passwordLabel);
	        centerPanel.add(passwordTF);
	        loginPanel.add(centerPanel, BorderLayout.CENTER);
	        loginPanel.add(loginButton, BorderLayout.SOUTH);
	        
	        loginPanel.setBounds(200, 150, 400, 300);
	        
	        //Button Panel components
	        JButton button1= new JButton("Forgot Password");
	        JButton button2= new JButton("Create Account");
	        JButton button3= new JButton("Play as Guest");
	        JButton button4= new JButton("Options");
	        JButton button5= new JButton("About");
	        
	        buttonPanel.add(button1);
	        buttonPanel.add(button2);
	        buttonPanel.add(button3);
	        buttonPanel.add(button4);
	        buttonPanel.add(button5);
	        
	        buttonPanel.setBounds(0,537,800,35);
	        
	        //Title Panel components
	        JButton title= new JButton("Game Login Screen");
	        title.setBackground(Color.BLACK);
	        title.setForeground(Color.WHITE);
	        title.setEnabled(false);
	        titlePanel.add(title);
	        
	        titlePanel.setBounds(200,115,400,35);
	        
	        
	        //MainPanel components
	        this.add(titlePanel);
	        this.add(loginPanel);
	        this.add(buttonPanel);
	        
	        //Action Listeners
	        loginButton.addActionListener(new ActionListener()
	        {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(!usernameTF.getText().equals("") && !passwordTF.getText().equals(""))
					{
						username= usernameTF.getText();
						
						password= passwordTF.getText();
						setVisible(false);
						lobby= new LobbyGUI();
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(!accessgame)
						{
							JOptionPane.showMessageDialog(null, "Incorrect username or password.");
							try 
							{
								lobby.setVisible(false);
								setVisible(true);
								SOCKET.close();
								accessgame=true;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								System.out.println("Couldn't close the socket...");
							}
						}
					}
				}
	        	
	        });
	        
	        button2.addActionListener(new ActionListener()
	        {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(!usernameTF.getText().equals("") && !passwordTF.getText().equals(""))
					{	
						username= "@NewAccount@"+usernameTF.getText();
						password= passwordTF.getText();
						setVisible(false);
						new LobbyGUI();
					}
				}
	        
	        });
	        
	    }
		
		/**
		 * Attempts a connection to the server.
		 */
		
		
		/**
		 * Contains the background of the frame.
		 * @author Crow-kun
		 *
		 */
		public class backgroundImage extends JComponent
		{
			private Image i;
			
			public backgroundImage(Image image)
			{
				i= image;
			}
			
			@Override
			public void paintComponent(Graphics g)
			{
				g.drawImage(i, 0, 0, null);
			}
		}
		
		
	    
	    /**
	     * Main
	     * @param args
	     */
	    public static void main(String[] args)
	    {
	        new GameLoginGUI();
	    }
	}

