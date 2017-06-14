import java.util.ArrayList;

/**
 * Contains rules and game mechanics.
 * @author Crow-kun
 *
 */
public class Game 
{
	private Card TrumpCard;
	CardDeck deck;
	private int actualTurn=0;
	private int changeTrumpIndex;
	
	private ArrayList<Card> PlayersHand;
	private ArrayList<Card> AIsHand;
	private ArrayList<Card> Table;
	private ArrayList<Card> Bench1;
	private ArrayList<Card> Bench2;
	private int roundcounter=0;
	
	/**
	 * Main constructor. initializes players and hands.
	 * @param p1 The player to be host.
	 * @param name The name of the host.
	 * @param set Game settings.
	 */
	public Game()
	{
		
		
		//Initializes Player's Hand
		PlayersHand= new ArrayList<Card>();
		AIsHand = new ArrayList<Card>();
		
		//Table initialization
		Table= new ArrayList<Card>();
				
		//PlayersHand' bench initialization
		Bench1= new ArrayList<Card>();
		Bench2= new ArrayList<Card>();
		
	}

	
	/**
	 * Determines what will happen on a specific turn, or if the table needs to be inspected, or if the game has ended.
	 * @return Information about the game's status.
	 */
	public String getTurn()
	{
		//System.out.println(getBench1Points()+getBench2Points());
		if(getBench1Points()+getBench2Points()==120)
		{
			return getWinners(); 
		}
		else if(Table.size()==2)
		{
			System.out.println("dealing cards...");
			inspectTable();
			dealCards();
			try
			{
				GameBoardGUI.setCardIcon(2, PlayersHand.get(2).getCardSuit()+Integer.toString(PlayersHand.get(2).getCardNumber()));
			}catch(Exception e){};	
			if(roundcounter<=16)
			{
				
				if(actualTurn==0) 
				{
					GameBoardGUI.gamelog.append("Player won round "+ Integer.toString(roundcounter) + "\n");
					GameBoardGUI.gamelog.append("----------"+"Round "+Integer.toString(roundcounter+1)+"----------\n");
					return "Player won round "+ Integer.toString(roundcounter);
				}
				else 
				{
					GameBoardGUI.gamelog.append("AI won round "+ Integer.toString(roundcounter) + "\n");
					GameBoardGUI.gamelog.append("----------"+"Round "+Integer.toString(roundcounter+1)+"----------\n");
					return "AI won round " + Integer.toString(roundcounter);
				}
			}
			else
			{
				if(actualTurn==0) 
				{
					GameBoardGUI.gamelog.append("Player won round "+ Integer.toString(roundcounter) + "\n");
					GameBoardGUI.gamelog.append("----------"+"Round "+Integer.toString(roundcounter+1)+"----------\n");
					return "Player won round "+ Integer.toString(roundcounter) + " (Final Rounds)";
				}
				else 
				{
					GameBoardGUI.gamelog.append("AI won round "+ Integer.toString(roundcounter) + "\n");
					GameBoardGUI.gamelog.append("----------"+"Round "+Integer.toString(roundcounter+1)+"----------\n");
					return "AI won round " + Integer.toString(roundcounter) + " (Final Rounds)";
				}
			}
		}
		else
		{
			if (roundcounter == 17) GameBoardGUI.visualCards.get(6).setIcon(GameBoardGUI.getCardIcon("nocard0"));
			return "Turn"+Integer.toString(actualTurn);
		}
	}
	
	/**
	 * Plays a card which the player has selected.
	 * @param player Player to play a card.
	 * @param cardindex index of the card to be played in the arraylist (from 0 to 2).
	 * @return
	 */
	public String playCard(String player, int cardindex)
	{
		if(player.equals("Player"))
		{
			String tempCard= PlayersHand.get(cardindex).getCardSuit()+Integer.toString(PlayersHand.get(cardindex).getCardNumber());
			Table.add(PlayersHand.get(cardindex));
			PlayersHand.remove(cardindex);
			actualTurn = (actualTurn + 1) % 2;
			GameBoardGUI.gamelog.append("You play "+tempCard + "\n");
			return tempCard;
		}
		else
		{
			String tempCard= AIsHand.get(cardindex).getCardSuit()+Integer.toString(AIsHand.get(cardindex).getCardNumber());
			Table.add(AIsHand.get(cardindex));
			AIsHand.remove(cardindex);
			actualTurn = (actualTurn + 1) % 2;
			GameBoardGUI.gamelog.append("AI plays "+tempCard + "\n");
			return tempCard;
		}
		
	}
	
	/**
	 * Deal cards to players.
	 */
	private void dealCards() 
	{
		if(roundcounter<=17)
		{
			if(actualTurn==0)
			{
				PlayersHand.add(deck.deal());
				AIsHand.add(deck.deal());
			}
			else
			{
				AIsHand.add(deck.deal());
				PlayersHand.add(deck.deal());
			}
		}
	}
	
	/**
	 * Inspects the table and the determines who has won that round.
	 */
	private void inspectTable() 
	{
		Card tempWinner= new Card();
		tempWinner= Table.get(0);
		if(!(cardWinner(tempWinner, Table.get(1)).equals(tempWinner)))
		{
			tempWinner= Table.get(1);
		}
		if(tempWinner.equals(Table.get(0)))
		{
			for(int i=0; i<2;i++)
			{
				if(actualTurn == 0) Bench1.add(Table.get(0));
				else Bench2.add(Table.get(0));
				Table.remove(0);
				
			}
		}
		else if(tempWinner.equals(Table.get(1)))
		{
			actualTurn = (actualTurn + 1) % 2;
			for(int i=0; i<2;i++)
			{
				if(actualTurn == 0) Bench1.add(Table.get(0));
				else Bench2.add(Table.get(0));
				Table.remove(0);
			}
		}
		roundcounter++;
	}
	
	/**
	 * Compares two card and determines which one is more valuable
	 * @param c1 First card to be compared.
	 * @param c2 Second card to be compared.
	 * @return The winner card.
	 */
	public Card cardWinner(Card c1, Card c2)
	{
		if(c1.getCardSuit().equals(TrumpCard.getCardSuit()) && !(c2.getCardSuit().equals(TrumpCard.getCardSuit())))
		{
			return c1;
		}
		else if(c2.getCardSuit().equals(TrumpCard.getCardSuit()) && !(c1.getCardSuit().equals(TrumpCard.getCardSuit())))
		{
			return c2;
		}
		else if(!(c1.getCardSuit().equals(c2.getCardSuit())))
		{
			return c1;
		}
		else
		{
			if(c1.isGreaterThan(c2))
			{
				return c1;
			}
			else
			{
				return c2;
			}
			
		}
		
	}

	/**
	 * Determines if the game is ready to start, and starts the game.
	 * @param player The player to be checked if he's the host.
	 * @return If the game has started or not.
	 */
	public String startGame(String player)
	{
		deck= new CardDeck();
		deck.shuffleDeck();
		for(int i=0;i<3;i++)
		{
			PlayersHand.add(deck.deal());
			AIsHand.add(deck.deal());
		}
			
		TrumpCard= deck.getTrumpCard();
		GameBoardGUI.gamelog.setText("Trump Suit: "+TrumpCard.getCardSuit()+"\n");
		GameBoardGUI.gamelog.append("----------Round 1----------\n");
		return "@GameStart@";	
	}
	
	/**
	 * Returns the points of the cards contained in Bench1
	 * @return The points of the bench.
	 */
	public int getBench1Points()
	{
		int points=0;
		for(int i=0; i< Bench1.size(); i++)
		{
			if(Bench1.get(i).getCardNumber()== 1)
			{
				points+=11;
			}
			else if(Bench1.get(i).getCardNumber()== 3)
			{
				points+=10;
			}
			else if(Bench1.get(i).getCardNumber()== 12)
			{
				points+=4;
			}
			else if(Bench1.get(i).getCardNumber()== 11)
			{
				points+=3;
			}
			else if(Bench1.get(i).getCardNumber()== 10)
			{
				points+=2;
			}
		}
		return points;
	}
	
	/**
	 * Returns the points of the cards contained in Bench2
	 * @return The points of the bench.
	 */
	public int getBench2Points()
	{
		int points=0;
		for(int i=0; i< Bench2.size(); i++)
		{
			if(Bench2.get(i).getCardNumber()== 1)
			{
				points+=11;
			}
			else if(Bench2.get(i).getCardNumber()== 3)
			{
				points+=10;
			}
			else if(Bench2.get(i).getCardNumber()== 12)
			{
				points+=4;
			}
			else if(Bench2.get(i).getCardNumber()== 11)
			{
				points+=3;
			}
			else if(Bench2.get(i).getCardNumber()== 10)
			{
				points+=2;
			}
		}
		return points;
	}
	
	/**
	 * Return who are the winners of the match.
	 * @return Winner team of the match.
	 */
	private String getWinners() 
	{
		if(this.getBench1Points()>this.getBench2Points())
		{
			
			return "WinPlayer";
		}
		else if(this.getBench1Points() == this.getBench2Points())
		{
			
			return "WinNone";
		}
		else
		{
			return "WinAI";
		}
	}

	/**
	 * Determines if the hand of a player can be changed.
	 * @param player
	 * @return If the hand has been changed or not.
	 */
	public String changeHand(String player)
	{
		if(ischangeHand(PlayersHand) && (player.equals("Player")))
		{
			ArrayList<Card> tempCards= deck.changeHand(PlayersHand);
			for(int i=0;i<3;i++)
			{
				PlayersHand.remove(0);
				PlayersHand.add(tempCards.get(i));
			}
			GameBoardGUI.gamelog.append("You change hand.\n");
			return "@ChangeHand0@";
		}
		else if(ischangeHand(AIsHand) && (player.equals("AI")))
		{
			ArrayList<Card> tempCards= deck.changeHand(AIsHand);
			for(int i=0;i<3;i++)
			{
				AIsHand.remove(0);
				AIsHand.add(tempCards.get(i));
			}
			GameBoardGUI.gamelog.append("AI changes hand.\n");
			return "@ChangeHand1@";
		}
		else
		{
			return "@NoAction@";
		}
	}
	
	/**
	 * Evaluates a player's hand and tells if it's changeable
	 * @param playerhand the hand of the player to be evaluated.
	 * @return The result of the evaluation.
	 */
	public boolean ischangeHand(ArrayList<Card> playerhand)
	{
		boolean sameSuit= true, noPoints= true;
		String suit= new String(playerhand.get(0).getCardSuit());
		for(int i=1; i<3;i++)
		{
			if(!(playerhand.get(i).getCardSuit().equals(suit)))
			{
				sameSuit= false;
				break;
			}
		}
		
		for(int i=0; i<3;i++)
		{
			if(playerhand.get(i).getCardNumber()== 1 || playerhand.get(i).getCardNumber()== 3 || 
					playerhand.get(i).getCardNumber()== 12 || playerhand.get(i).getCardNumber()== 11 || 
					playerhand.get(i).getCardNumber()== 10)
				{
					noPoints= false;
					break;
				}
		}
		
		if(sameSuit== true || noPoints== true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	/**
	 * Determines if a player can change the trump card.
	 * @param player The player requesting to change trump card.
	 * @return If the trump card has been changed or not.
	 */
	public String changeTrumpCard(String player)
	{
		if(ischangeTrumpCard(PlayersHand) && (player.equals("Player")))
		{
			Card tempCard= PlayersHand.get(changeTrumpIndex);
			PlayersHand.remove(changeTrumpIndex);
			PlayersHand.add(TrumpCard);
			TrumpCard= tempCard;
			deck.changeTrumpCard(tempCard);
			GameBoardGUI.gamelog.append("You change trump card.\n New Trump card is: "+TrumpCard.getCardSuit()+Integer.toString(TrumpCard.getCardNumber())+"\n");
			return "@TrumpCard0@";
		}
		else if(ischangeTrumpCard(AIsHand) && (player.equals("AI")))
		{
			Card tempCard= AIsHand.get(changeTrumpIndex);
			AIsHand.remove(changeTrumpIndex);
			AIsHand.add(TrumpCard);
			TrumpCard= tempCard;
			deck.changeTrumpCard(tempCard);
			GameBoardGUI.gamelog.append("AI changes trump card.\n New Trump card is: "+TrumpCard.getCardSuit()+Integer.toString(TrumpCard.getCardNumber())+"\n");
			return "@TrumpCard1@";
		}
		else
		{
			return "@NoAction@";
		}
	}
	
	/**
	 * Checks if the hand of a player contains a card that can change the trump card.
	 * @param playerhand the hand of the player to be evaluated.
	 * @return The result of the evaluation.
	 */
	public boolean ischangeTrumpCard(ArrayList<Card> playerhand)
	{
		if(playerhand.size()<3) return false;
		for(int i=0; i<3; i++)
		{
			if(playerhand.get(i).getCardSuit().equals(TrumpCard.getCardSuit()) && playerhand.get(i).getCardNumber()== 7)
			{
				changeTrumpIndex=i;
				return true;
			}
			
			if(playerhand.get(i).getCardSuit().equals(TrumpCard.getCardSuit()) && playerhand.get(i).getCardNumber()== 2 && roundcounter== 0)
			{
				changeTrumpIndex=i;
				return true;
			}
		}
		
		return false;
	}
	
	public String[] getCardNames(String player)
	{
		int counter = 0;
		String[] result = new String[3];
		if(player.equals("Player")) {
		try
		{
			for(;counter<3;counter++)
			{
				result[counter] = PlayersHand.get(counter).getCardSuit() + Integer.toString(PlayersHand.get(counter).getCardNumber());
			}
		}catch(Exception E){};
		}
		else {
		try
		{
			for(;counter<3;counter++)
			{
				result[counter] = AIsHand.get(counter).getCardSuit() + Integer.toString(AIsHand.get(counter).getCardNumber());
			}
		}catch(Exception E){};
		}
		for(;counter<3;counter++)
		{
			result[counter] = "nocard0";
		}
		
		return result;	
	}
	
	public String getTrumpCard()
	{
		
		return TrumpCard.getCardSuit()+Integer.toString(TrumpCard.getCardNumber());
	}
	
	public int AIplay()
	{
		AI ai;
		try
		{
			ai = new AI(AIsHand,Table.get(0),TrumpCard.getCardSuit());
		}catch(Exception e){
			ai = new AI(AIsHand,null,TrumpCard.getCardSuit());
		};
		Card winnerCard = ai.playcard();
		int result=0;
		for(int i=0;i<3;i++)
		{
			if(AIsHand.get(i).getCardSuit().equals(winnerCard.getCardSuit()) && (AIsHand.get(i).getCardNumber() == winnerCard.getCardNumber()))
			{
				result = i;
				break;
			}
		}
		return result;
	}

	
}

