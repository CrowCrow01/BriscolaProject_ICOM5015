
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Graphical Interface of the Lobby of the game. 
 * @author Crow-kun
 *
 */
	public class LobbyGUI extends JFrame
	{
		public static JButton Create;
		public static JFrame CreateFrame; 
		public static Player P1;
		
		/**
		 * Main Constructor of the class.
		 */
	    public LobbyGUI()
	    {
	        this.setSize(1000,700);
	        this.setTitle("Briscola");
	        this.setResizable(false);
	        this.getContentPane().setLayout(null);
	       
	        
	        //Set frame in the center of screen.
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	        
	        showGUI();
	        
	        this.setVisible(true);
	        this.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                int result= JOptionPane.showConfirmDialog(null, "You sure you want to quit the game?", "Exit Briscola", JOptionPane.OK_CANCEL_OPTION);
	                if(result==JOptionPane.OK_CANCEL_OPTION)
	                {
	                	System.exit(0);
	                	/*
	                	try {
							GameLoginGUI.client.socket.close();
							System.exit(0);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} */
	                }
	            }
	        });
	        
	        
	    }
	    
	    /**
	     * Contains all graphicals contents of the GUI
	     */
		private void showGUI() 
	    {
			
	        //Frame layout and background
	       
	        try {
				BufferedImage bImage= ImageIO.read(new File("Green-Vector-Wallpaper-Fullscreen.jpg"));
				this.setContentPane(new backgroundImage(bImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Image not found");
				e.printStackTrace();
			}
	        
	     
	        
	        //main Panel initialization and layout
	       
	        
	        // Top left Panel 
	        JPanel TopLeftPanel= new JPanel();
	        TopLeftPanel.setLayout(new FlowLayout());
	        TopLeftPanel.setBackground(Color.WHITE);
	        TopLeftPanel.setSize(950,350);
	        TopLeftPanel.setBounds(50,50,900,50);
	       
	        
	        //Bottom Left Panel
	        JPanel BottomLeftPanel = new JPanel();
	        BottomLeftPanel.setLayout(new FlowLayout());
	        BottomLeftPanel.setBackground(Color.WHITE);
	        BottomLeftPanel.setSize(900,50);
	        BottomLeftPanel.setBounds(50,400,900,50);
	        
	        
	        
	        //Initialization of Upper Panel Components 
	        JLabel PlayerInfoL = new JLabel("Options:");
	        PlayerInfoL.setFont(new Font("Rockwell", Font.BOLD, 25));
	        
	        JPanel settingsPanel = new JPanel();
	        settingsPanel.setBackground(Color.WHITE);
	        settingsPanel.setLayout(new GridLayout(5,1));
	        
	        JPanel startPanel = new JPanel();
	        startPanel.setBackground(Color.WHITE);
	        startPanel.setLayout(new GridLayout(1,3));
	        startPanel.setBounds(50,450,900,150);
	     
	   	        
	        //Initialization of Lower Panel Components 
	        JLabel startGameLabel = new JLabel("Start Game");
	        startGameLabel.setFont(new Font("Rockwell", Font.BOLD, 25));
	        
	        JButton startGame = new JButton();
	        //startGame.setBackground(new Color(100,100,255));
	        startGame.addActionListener(new ActionListener()
	        {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					boolean[] settings = new boolean[2];
					settings[0] = false;
					settings[1] = false;
					new GameBoardGUI(settings);
					
				}
	        	
	        });
	        
	        //chatboxLB.setBounds(50,450,450,215);
	        
	        //playerlist= new PlayerList(400,215);
	        //playerlist.setBounds(500,450,450,215);
	        //String[] test= {"Player1", "Player2", "Player3", "Player4"};
	        //playerlist.addData(test);
	        
	        //gamelist= new GameList(400,300);
	        //gamelist.setBounds(500,100,450,300);
	        //String[] test2= {"Game1", "Game2", "Game3", "Game4"};
	        //gamelist.addData(test2);
	        
	    
	        TopLeftPanel.add(PlayerInfoL);
	        
	        
	        
	        
	        //Adding Components to Lower Panel
	        BottomLeftPanel.add(startGameLabel);
	        startPanel.add(startGame);
	        
	        
	        
	        
	        //Title Panel initialization and layout
	        JPanel titlePanel= new JPanel();
	        titlePanel.setLayout(new GridLayout(1,3,0,0));

	        //Adding to Main Panel
	        this.add(TopLeftPanel);
	      
	        this.add(BottomLeftPanel);
	        
	        this.add(startPanel);
	      
	        /*this.add(chatboxLB);
	        this.add(playerlist);
	        this.add(gamelist); */
	        

	        
	        //Lobby Panel components
	        JButton title= new JButton("Main Menu");
	        title.setBackground(Color.BLACK);
	        title.setForeground(Color.WHITE);
	        title.setEnabled(false);
	        titlePanel.add(title);
	        
	        titlePanel.setBounds(50,0,900,50);
	        
	        title.setFont(new Font("Rockwell", Font.BOLD, 20));
	        
	        //MainPanel components
	        this.add(titlePanel);
	       
	        
	    }
		/**
		 * Provides background for the lobby.
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
	    
	}



