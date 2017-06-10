import java.util.ArrayList;
import java.util.Collections;

/**
 * Contains the deck of the game, and actions to be performed to it.
 * @author Crow-kun
 *
 */
public class CardDeck 
{
	private ArrayList<Card> Deck;
	
	/**
	 * Main constructor, initializes deck.
	 */
	public CardDeck()
	{
		Deck= new ArrayList<Card>();
		for(int i=0; i<40; i++)
		{
			if(i<10)
			{
				if(i>=7)
				{
					Deck.add(new Card(i+3, "sword"));
				}
				else
				{
					Deck.add(new Card(i+1, "sword"));
				}
			}
			else if(i>=10 && i<20)
			{
				if(i>=17)
				{
					Deck.add(new Card(i+3-10, "cup"));
				}
				else
				{
					Deck.add(new Card(i+1-10, "cup"));
				}
			}
			else if(i>=20 && i<30)
			{
				if(i>=27)
				{
					Deck.add(new Card(i+3-20, "coin"));
				}
				else
				{
					Deck.add(new Card(i+1-20, "coin"));
				}
			}
			else
			{
				if(i>=37)
				{
					Deck.add(new Card(i+3-30, "club"));
				}
				else
				{
					Deck.add(new Card(i+1-30, "club"));
				}
			}
		}
	}
	
	/**
	 * Shuffles the current deck.
	 */
	public void shuffleDeck()
	{
		Collections.shuffle(Deck);
	}
	
	/**
	 * Removes the top card of the deck and gives it to the caller.
	 * @return The top card of the deck.
	 */
	public Card deal()
	{
		Card tempCard= Deck.get(0);
		Deck.remove(0);
		return tempCard;
	}
	
	/**
	 * Changes the trump card to a new one.
	 * @param c New trump card.
	 */
	public void changeTrumpCard(Card c)
	{
		Deck.remove(Deck.size()-1);
		Deck.add(c);
	}
	
	/**
	 * Takes a hand and replaces for a new one.
	 * @param cards Cards to be replaced.
	 * @return New cards going to the caller.
	 */
	public ArrayList<Card> changeHand(ArrayList<Card> cards)
	{
		Card tempCard= Deck.get(Deck.size()-1);
		Deck.remove(Deck.size()-1);
		ArrayList<Card> newCards= new ArrayList<Card>();
		for(int i=0;i<3;i++)
		{
			newCards.add(this.deal());
		}
		for(int i=0;i<3;i++)
		{
			Deck.add(cards.get(i));
		}
		this.shuffleDeck();
		Deck.add(tempCard);
		
		return newCards;
	}
	
	/**
	 * Returns the actual trump card(Last card of the deck).
	 * @return Trump Card.
	 */
	public Card getTrumpCard()
	{
		return Deck.get(Deck.size()-1);
	}
	
	
}
