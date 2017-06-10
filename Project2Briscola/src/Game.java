import java.util.ArrayList;

/**
 * Contains rules and game mechanics.
 * @author Crow-kun
 *
 */
public class Game 
{
	Player[] players= new Player[2];
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
	public Game(Player p1)
	{
		players[0]= p1;
		players[1]= p1; //AI goes here
		
		
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
		
		if(getBench1Points()+getBench2Points()==120)
		{
			return getWinners(); 
		}
		else if(Table.size()==2)
		{
			System.out.println("dealing cards...");
			inspectTable();
			dealCards();
			GameBoardGUI.setCardIcon(2, PlayersHand.get(2).getCardSuit()+Integer.toString(PlayersHand.get(2).getCardNumber()));
			GameBoardGUI.setCardIcon(5, AIsHand.get(2).getCardSuit()+Integer.toString(AIsHand.get(5).getCardNumber()));
			if(roundcounter<=14)
			{
				return Integer.toString(roundcounter);
			}
			else
			{
				return "Final Turns" + Integer.toString(roundcounter);
			}
		}
		else
		{
			return "@RequestTurn@";
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
		if(player == "Player")
		{
			String tempCard= PlayersHand.get(cardindex).getCardSuit()+Integer.toString(PlayersHand.get(cardindex).getCardNumber());
			Table.add(PlayersHand.get(cardindex));
			PlayersHand.remove(cardindex);
			actualTurn = (actualTurn + 1) % 2;
			return tempCard;
		}
		else
		{
			String tempCard= AIsHand.get(cardindex).getCardSuit()+Integer.toString(AIsHand.get(cardindex).getCardNumber());
			Table.add(AIsHand.get(cardindex));
			AIsHand.remove(cardindex);
			actualTurn = (actualTurn + 1) % 2;
			return tempCard;
		}
		
	}
	
	/**
	 * Deal cards to players.
	 */
	private void dealCards() 
	{
		if(roundcounter<=14)
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
				Bench1.add(Table.get(0));
				Table.remove(0);
				
			}

			System.out.println("RoundWinner is "+ players[actualTurn].getUsername());
		}
		else if(tempWinner.equals(Table.get(1)))
		{
			actualTurn = (actualTurn + 1) % 2;
			for(int i=0; i<2;i++)
			{
				Bench1.add(Table.get(0));
				Table.remove(0);
			}
			System.out.println("RoundWinner is "+ players[actualTurn].getUsername());
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
			GameBoardGUI.setCardIcon(i, PlayersHand.get(i).getCardSuit()+Integer.toString(PlayersHand.get(i).getCardNumber()));
			GameBoardGUI.setCardIcon(i+3, AIsHand.get(i).getCardSuit()+Integer.toString(AIsHand.get(i).getCardNumber()));
		}
			
		TrumpCard= deck.getTrumpCard();
		GameBoardGUI.setCardIcon(6, TrumpCard.getCardSuit()+Integer.toString(TrumpCard.getCardNumber()));
			
		return "@GameStart@";	
	}
	
	/**
	 * Returns the points of the cards contained in Bench1
	 * @return The points of the bench.
	 */
	private int getBench1Points()
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
	private int getBench2Points()
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
			
			return "You win!";
		}
		else if(this.getBench1Points() == this.getBench2Points())
		{
			
			return "It's a tie!";
		}
		else
		{
			return "You lose!";
		}
	}

	/**
	 * Determines if the hand of a player can be changed.
	 * @param player
	 * @return If the hand has been changed or not.
	 */
	public String changeHand(String player)
	{
		if(ischangeHand(PlayersHand) && (player == "Player"))
		{
			ArrayList<Card> tempCards= deck.changeHand(PlayersHand);
			for(int i=0;i<3;i++)
			{
				PlayersHand.remove(0);
				PlayersHand.add(tempCards.get(i));
			}
			return "@ChangeHand0@";
		}
		else if(ischangeHand(AIsHand) && (player == "AI"))
		{
			ArrayList<Card> tempCards= deck.changeHand(AIsHand);
			for(int i=0;i<3;i++)
			{
				AIsHand.remove(0);
				AIsHand.add(tempCards.get(i));
			}
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
		if(ischangeTrumpCard(PlayersHand) && (player == "Player"))
		{
			Card tempCard= PlayersHand.get(changeTrumpIndex);
			PlayersHand.remove(changeTrumpIndex);
			PlayersHand.add(TrumpCard);
			TrumpCard= tempCard;
			deck.changeTrumpCard(tempCard);
			
			return "@TrumpCard0@";
		}
		else if(ischangeTrumpCard(AIsHand) && (player == "AI"))
		{
			Card tempCard= AIsHand.get(changeTrumpIndex);
			AIsHand.remove(changeTrumpIndex);
			AIsHand.add(TrumpCard);
			TrumpCard= tempCard;
			deck.changeTrumpCard(tempCard);
			
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
		for(int i=0; i<3; i++)
		{
			if(playerhand.get(i).getCardSuit().equals(TrumpCard) && playerhand.get(i).getCardNumber()== 7)
			{
				changeTrumpIndex=i;
				return true;
			}
			
			if(playerhand.get(i).getCardSuit().equals(TrumpCard) && playerhand.get(i).getCardNumber()== 2 && roundcounter== 0)
			{
				changeTrumpIndex=i;
				return true;
			}
		}
		
		return false;
	}

	
}

