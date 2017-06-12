import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Contains graphical interface of the game room, where cards are displayed.
 * @author Crow-kun
 *
 */
public class GameBoardGUI extends JFrame implements ActionListener
{
	//Globals
	static JButton southPlayer= new JButton("Player");
	static JButton northPlayer= new JButton("AI Agent");
	static JButton startGame= new JButton("Start Game!");
	static JButton changeTrumpCard;
	static JButton changeHand;
	static JButton nextTurn = new JButton("Next Turn");
	static JButton cleanTableButton= new JButton("Clean");
	static JButton showResultButton= new JButton("showResult");
	static JLabel playerScore= new JLabel("Player: 0");
	static JLabel opponentScore= new JLabel("AI: 0");
	static ArrayList<JButton> visualCards= new ArrayList<JButton>();
	static boolean enableCards= false, enableTrumpChange= true, enableChangeHand=true;
	static String displaycard;
	static int resultOptions=0;
	
	private AnimationPanel animationPanel;
	private AnimationPanel3 animationPanel3;
	private boolean[] settings;
	private Timer tm= new Timer(10,this),ta = new Timer(3000,null);
	private int xPos= 364, yPos=340, yPos3=112, playerAnimation;
	private boolean flag= true, flag3= true, resultflag=true;
	private int swapCardPos=2;
	private String turnresult;
	protected Game game;
	
	/**
	 * Main Constructor of the class, initializes the class frame.
	 * @param set
	 */
 	public GameBoardGUI(boolean[] set)
	{
		this.setSize(1105, 600);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int result= JOptionPane.showConfirmDialog(null, "You sure you want to quit this match?", "Exit Match", JOptionPane.OK_CANCEL_OPTION);
                if(result==JOptionPane.OK_CANCEL_OPTION)
                {
                	GameBoardGUI.this.dispose();
                }
            }});
            
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        //Set frame in the center of screen.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        settings= set;
        showGUI();
       	
	}
	/**
	 * Contains all components of the GameBoard
	 */
	private void showGUI()
	{
		//Gameboard Panel --------------------------------------
		JPanel GameboardPanel = new JPanel();
		GameboardPanel.setLayout(new GridLayout(1,2,0,0));
		//Gameboard Panel Background
	       
        try {
			BufferedImage bImage= ImageIO.read(new File("green_background_by_carbomcoco-d56kdpp.jpg"));
			this.setContentPane(new backgroundImage(bImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Image not found");
			e.printStackTrace();
		}
		
        this.add(GameboardPanel);
		
        //Right Organization Panel
        JPanel RightPanel = new JPanel();
        RightPanel.setBackground(Color.WHITE);
        RightPanel.setSize(new Dimension(225, 600));
        RightPanel.setBackground(Color.BLACK);
        
        //Chat Panel---------------------------------------
        JPanel ChatPanel = new JPanel();
       
        
        //Special Rules Button
        changeTrumpCard= new JButton("Change Trump Card");
        changeHand= new JButton("Change Hand");
        
        
        //Adding default nocrd image to buttons and setting their bounds

        for(int i=0;i<6;i++)
        {
        	visualCards.add(new JButton(this.getCardIcon("nocard0"))); 
        }
        
        visualCards.add(new JButton(this.getCardIcon("nocard1")));
        
        
        visualCards.get(0).setBounds(275,450,72,108);
        this.add(visualCards.get(0));        
        
        visualCards.get(1).setBounds(364,450,72,108);
        this.add(visualCards.get(1));
        
        visualCards.get(2).setBounds(453,450,72,108);
        this.add(visualCards.get(2));
        
        //Other cards
       
        visualCards.get(3).setBounds(275,2,72,108);
        this.add(visualCards.get(3));
        visualCards.get(4).setBounds(364,2,72,108);
        this.add(visualCards.get(4));
        visualCards.get(5).setBounds(453,2,72,108);
        this.add(visualCards.get(5));
        
        visualCards.get(6).setBounds(2,2,72,108);
        this.add(visualCards.get(6));
        
        nextTurn.setBounds(600,400,100,20);
        nextTurn.setSize(100,20);
        this.add(nextTurn);
        
        
        //More buttons...
        startGame.setBounds(400,200,100,20);
        startGame.setSize(100,20);
      
        
        changeTrumpCard.setBounds(400,220,100,20);
        changeTrumpCard.setSize(100,20);
        
        
        changeTrumpCard.setEnabled(settings[0]);
        
        
        changeHand.setBounds(400,240,100,20);
        changeHand.setSize(100,20);
        
        
        changeHand.setEnabled(settings[1]);
        
        
        //Labels
       
        playerScore.setBounds(680, 2, 100, 30);
        playerScore.setSize(100,30);
        playerScore.setFont(new Font("Rockwell", Font.BOLD, 16));
        this.add(playerScore);
            
        opponentScore.setBounds(680, 35, 120, 30);
        opponentScore.setSize(120,30);
        opponentScore.setFont(new Font("Rockwell", Font.BOLD, 16));
        this.add(opponentScore);
        
        
        //Adding all the Components 
        RightPanel.add(ChatPanel);
        RightPanel.add(startGame);
        RightPanel.add(changeTrumpCard);
        RightPanel.add(changeHand);
        RightPanel.setBounds(800,0,300,600);
        
        this.add(RightPanel);
        
        this.setVisible(true);
        
        //Listeners
        visualCards.get(0).addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(enableCards) 
				{
					swapCardPos=0;
					game.playCard("Player", 0);
					moveSouthCard();
					enableCards = false;
					enableChangeHand=false;
					runGame();
				}				
			}
    		
    	});
        
        visualCards.get(1).addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(enableCards) 
				{
					swapCardPos=1;
					game.playCard("Player",1);
					moveSouthCard();
					enableCards=false;
					enableChangeHand = false;
					runGame();
				}			
			}
    		
    	});
        
        visualCards.get(2).addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(enableCards) 
				{
					swapCardPos=2;
					game.playCard("Player", 2);
					moveSouthCard();
					enableCards=false;
					enableChangeHand = false;
					runGame();
				}			
			}
    		
    	});
        
        
        startGame.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				game = new Game(LobbyGUI.P1);
				game.startGame("Player");
				String[] newcards = game.getCardNames("Player");
				for(int i=0;i<3;i++)
				{
					visualCards.get(i).setIcon(getCardIcon(newcards[i]));
				}
				visualCards.get(6).setIcon(getCardIcon(game.getTrumpCard()));
				runGame();
			}        	
        });
        
        nextTurn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runGame();
			}});
        
    
        cleanTableButton.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
	        		cleanTable();
	       
			}
        	
        });
        
        showResultButton.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resultflag)
				{
					resultflag=false;
					if(resultOptions==0)
					{
						JOptionPane.showMessageDialog(null, "You win the match! :D");
						dispose();
						
					}
					else if(resultOptions==1)
					{
						JOptionPane.showMessageDialog(null, "You lose the match! :(");
						dispose();
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "It's a tie!!");
						dispose();
					}
				}
				
			}
        	
        });
       
        changeTrumpCard.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(enableTrumpChange)
				{
					System.out.println(game.changeTrumpCard("Player"));
					String[] newcards = game.getCardNames("Player");
					for(int i=0;i<3;i++)
					{
						visualCards.get(i).setIcon(getCardIcon(newcards[i]));
					}
					visualCards.get(6).setIcon(getCardIcon(game.getTrumpCard()));
				}
			}
        	
        });
        
        changeHand.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(enableChangeHand)
				{
					System.out.println(game.changeHand("Player"));
					String[] newcards = game.getCardNames("Player");
					for(int i=0;i<3;i++)
					{
						visualCards.get(i).setIcon(getCardIcon(newcards[i]));
					}
					
				}
			}
        	
        });
        
 
        
        
        //Players' usernames bounds
        southPlayer.setBackground(Color.BLACK);
        southPlayer.setBounds(530,546,108,12);
        
      
        northPlayer.setBackground(Color.BLACK);
        northPlayer.setBounds(167, 2, 108,12);
        
        
        
        this.add(southPlayer);
       
        this.add(northPlayer);
        
        
        
	}
	
	protected void runGame() {
		turnresult = game.getTurn();
		while(!(turnresult.substring(0,3).equals("Win")))
		{
			if(turnresult.equals("Turn0"))
			{
				enableCards=true;
				break;
			}
			else if(turnresult.equals("Turn1"))
			{
				int cardindex = game.AIplay();
				displaycard= game.playCard("AI",cardindex);
				moveNorthCard();
				ta.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						runGame();
						ta.stop();
					}});
				ta.start();
				break;
				
			}
			else
			{
				cleanTableButton.doClick();
				playerScore.setText("Player: "+Integer.toString(game.getBench1Points()));
				opponentScore.setText("AI: "+Integer.toString(game.getBench2Points()));
			}
			String[] newcards = game.getCardNames("Player");
			for(int i=0;i<3;i++)
			{
				visualCards.get(i).setIcon(getCardIcon(newcards[i]));
			}
			newcards = game.getCardNames("AI");
			for(int i=3;i<6;i++)
			{
				visualCards.get(i).setIcon(getCardIcon(newcards[i-3]));
			}
			turnresult = game.getTurn();
		}
		System.out.println(turnresult);
		
	}
	
	public void moveSouthCard()
	{			
		yPos=300;
		playerAnimation=1;
		if(flag==true) flag=false;
		else remove(animationPanel);
		animationPanel= new AnimationPanel(visualCards.get(swapCardPos).getIcon().toString());
		animationPanel.setBounds(0,0,800,600);
		add(animationPanel);
		animateCardS(visualCards.get(swapCardPos).getIcon().toString());
		if(swapCardPos==0)
		{
			visualCards.get(0).setIcon(visualCards.get(1).getIcon());
			visualCards.get(1).setIcon(visualCards.get(2).getIcon());
		}
		else if(swapCardPos==1)
		{
			visualCards.get(1).setIcon(visualCards.get(2).getIcon());
		}
		visualCards.get(2).setIcon(getCardIcon("nocard0"));
		tm.start();
	        
	}
	
	public void moveNorthCard() {
		// TODO Auto-generated method stub
		yPos3= 112;
		playerAnimation=3;
		if(flag3==true) flag3=false;
		else remove(animationPanel3);
		animationPanel3= new AnimationPanel3(displaycard+".jpg");
		animationPanel3.setBounds(0,0,800,600);
		add(animationPanel3);
		animateCardN(displaycard+".jpg");
		tm.start();
	}
	
	/**
	 * Contains parameters from the card played by the player from the south part of the screen.
	 * @param cardname The name of the card.
	 */
	public void animateCardS(String cardname)
	{
		yPos=300;
		playerAnimation=1;
		if(flag==true) flag=false;
		else remove(animationPanel);
		animationPanel= new AnimationPanel(cardname);
		animationPanel.setBounds(0,0,800,600);
		add(animationPanel);
		tm.start();
	}
	
	/**
	 * Contains parameters from the card played by the player from the north part of the screen.
	 * @param cardname The name of the card.
	 */
	public void animateCardN(String cardname)
	{
		yPos3= 112;
		playerAnimation=3;
		if(flag3==true) flag3=false;
		else remove(animationPanel3);
		animationPanel3= new AnimationPanel3(cardname);
		animationPanel3.setBounds(0,0,800,600);
		add(animationPanel3);
		//visualCards.get(3).setIcon(getCardIcon("nocard0"));
		tm.start();
	}
	
	/**
	 * Contains Background of the GameBoard
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
	 * Contains animations from the player of the south part of the screen.
	 * @author Crow-kun
	 *
	 */
	public class AnimationPanel extends JPanel
	{
		private ImageObserver observer;
		private String cardIcon;
		private BufferedImage cardImage= null;
		
		public AnimationPanel(String cardiconref)
		{
			cardIcon=cardiconref;
		}
		
 		public void paintComponent(Graphics g)
		{
			try {
				cardImage= ImageIO.read(new File(cardIcon));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(cardImage, xPos, yPos, observer);
			
		}
		
		
	}
	
	/**
	 * Contains animations from the player of the north part of the screen.
	 * @author Crow-kun
	 *
	 */
	public class AnimationPanel3 extends JPanel
	{
		private ImageObserver observer;
		private String cardIcon;
		private BufferedImage cardImage= null;
		
		public AnimationPanel3(String cardiconref)
		{
			cardIcon=cardiconref;
		}
		
 		public void paintComponent(Graphics g)
		{
			try {
				cardImage= ImageIO.read(new File(cardIcon));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(cardImage, xPos, yPos3, observer);
			
		}
		
	}
	
	
	
	/**
	 * Sets the image of a specific card in the player's hand.
	 * @param index The index of the card to be played(from 0 to 2).
	 * @param cardname The name of the card(contains suit and number together).
	 */
	public static void setCardIcon(int index, String cardname)
	{
		visualCards.get(index).setIcon(getCardIcon(cardname));
	}
	
	/**
	 * Gets the image of a specific card in the player's hand.
	 * @param cardname The index of the desired card.
	 * @return The image of the desired card.
	 */
	public static Icon getCardIcon(String cardname)
	{
		Icon cardImage= new ImageIcon(cardname+ ".jpg");
		return cardImage;
	}
	/**
	 * Erases the four cards that have been played by players.
	 */
	public void cleanTable()
	{
		remove(animationPanel);
		remove(animationPanel3);
		flag=true;
		flag3=true;
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(playerAnimation==1)
		{
			if(yPos!=288)
			{
				yPos--;
			}
			else 
			{
				tm.stop();
			}
			
		}

		else if(playerAnimation==3)
		{
			if(yPos3!=164)
			{
				yPos3++;
			}
			else 
			{

				tm.stop();
			}
		}
		
		this.repaint();
	}
 }
