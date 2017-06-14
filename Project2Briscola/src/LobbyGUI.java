
import java.awt.BorderLayout;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		private boolean[] settings=new boolean[2];
		/**
		 * Main Constructor of the class.
		 */
	    public LobbyGUI()
	    {
	        this.setSize(1000,700);
	        this.setTitle("Briscola");
	        this.setResizable(false);
	        this.getContentPane().setLayout(null);
	        settings[0]=false;
	        settings[1]=false;
	       
	        
	        //Set frame in the center of screen.
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	        
	        showGUI();
	        
	        this.setVisible(true);
	        this.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	               System.exit(0);
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
	        
	        // MiddlePanel
	        JPanel MiddlePanel = new JPanel();
	        MiddlePanel.setLayout(new GridLayout(2,1));
	        MiddlePanel.setSize(950,200);
	        MiddlePanel.setBounds(50, 100, 900, 200);
	       
	        
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
	        
	        JCheckBox changeTrumpChoice = new JCheckBox("Allow to change trump card.");
	        changeTrumpChoice.setBackground(Color.WHITE);
	        changeTrumpChoice.setSelected(false);
	        changeTrumpChoice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					settings[0]=!settings[0];
					
				}
			});
	        
	        JCheckBox changeHandChoice = new JCheckBox("Allow to change hand.");
	        changeHandChoice.setBackground(Color.WHITE);
	        changeHandChoice.setSelected(false);
	        changeHandChoice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					settings[1]=!settings[1];
				}
			});
	     
	   	        
	        //Initialization of Lower Panel Components 
	        JLabel startGameLabel = new JLabel("Start Game");
	        startGameLabel.setFont(new Font("Rockwell", Font.BOLD, 25));
	        
	        JButton startGame = new JButton();
	        //startGame.setBackground(new Color(100,100,255));
	        startGame.addActionListener(new ActionListener()
	        {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					new GameBoardGUI(settings);
					setVisible(false);
				}
	        	
	        });
	        
	        
	    
	        TopLeftPanel.add(PlayerInfoL,BorderLayout.NORTH);
	        
	        MiddlePanel.add(changeTrumpChoice);
	        MiddlePanel.add(changeHandChoice);
	       	        
	        
	        //Adding Components to Lower Panel
	        BottomLeftPanel.add(startGameLabel);
	        startPanel.add(startGame);
	        
	        
	        
	        
	        //Title Panel initialization and layout
	        JPanel titlePanel= new JPanel();
	        titlePanel.setLayout(new GridLayout(1,3,0,0));

	        //Adding to Main Panel
	        this.add(TopLeftPanel);
	        this.add(MiddlePanel);
	        this.add(BottomLeftPanel);
	        this.add(startPanel);
	        
	        
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



