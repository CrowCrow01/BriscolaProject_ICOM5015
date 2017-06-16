import java.util.ArrayList;
 
public class AI {
 
    private ArrayList<Card> hand;
    private Card tablecard=new Card(0,"None");
    private String trumpcard;
    
    public AI(ArrayList<Card> hand, Card tablecard, String trumpcard)
    {
    	ArrayList<Card> temphand = new ArrayList<Card>();
    	for(int i=0;i<hand.size();i++) temphand.add(hand.get(i));
        this.hand = temphand;
        this.tablecard = tablecard;
        this.trumpcard = trumpcard;
    }
    
    public Card playcard()
    {
        if(tablecard == null)
        {
            Card chosenCard = hand.get(0);
            try
            {    if(!(this.compareless(chosenCard,hand.get(1)).equals(chosenCard)))
                {
                    chosenCard = hand.get(1);
                }
                if(!(this.compareless(chosenCard,hand.get(2)).equals(chosenCard)))
                {
                    chosenCard = hand.get(2);
                }
            }
            catch(Exception E){};
            
            return chosenCard;
        }
        else
        {
        	Card chosenCard = hand.get(0);
            try
            {
                if(!(this.comparegreater(chosenCard,hand.get(1)).equals(chosenCard)))
                {
                    chosenCard = hand.get(1);
                }
                if(!(this.comparegreater(chosenCard,hand.get(2)).equals(chosenCard)))
                {
                    chosenCard = hand.get(2);
                }
                
            }
            catch(Exception E){};
            return chosenCard;
        }        
    }
    
    private Object comparegreater(Card card, Card card2) {
        Card winnerCard = card;
        if(getValue(card2) > getValue(card))
        {
            winnerCard = card2;
        }
        
        return winnerCard;
    }
 
    private Object compareless(Card card, Card card2) {
        Card winnerCard = card;
        if(getValue(card2) < getValue(card))
        {
            winnerCard = card2;
        }
        
        return winnerCard;
    }
 
    private int getValue(Card card) {
        String suit = card.getCardSuit();
        int winsTurn = 1;
        int A=0,B=0,C=0,D=0,club=1,sword=1,coin=1,cup=1;
        if(trumpcard.equals("club")) club = 10;
        if(trumpcard.equals("sword")) sword = 10;
        if(trumpcard.equals("coin")) coin = 10;
        if(trumpcard.equals("cup")) cup = 10;
        if(tablecard != null)
        {
        	if(tablecard.getCardSuit().equals("club")) club = 100;
        	if(tablecard.getCardSuit().equals("sword")) sword = 100;
        	if(tablecard.getCardSuit().equals("coin")) coin = 100;
        	if(tablecard.getCardSuit().equals("cup")) cup = 100;
        }
        if(suit.equals("club"))A=1;
        if(suit.equals("sword"))B=1;
        if(suit.equals("coin"))C=1;
        if(suit.equals("cup"))D=1;
        
        int number = card.getCardNumber();
        if(number==1) number = 14;
        if(number==3) number = 13;
        if(tablecard != null && !cardWinner(tablecard,card).equals(card)) winsTurn = -1;
        number= winsTurn*(number*(A*club+B*sword+C*coin+D*cup));
       // System.out.println("Winner is: "+Integer.toString(number));
        return number;
    }
    
    public Card cardWinner(Card c1, Card c2)
	{
		if(c1.getCardSuit().equals(trumpcard) && !(c2.getCardSuit().equals(trumpcard)))
		{
			return c1;
		}
		else if(c2.getCardSuit().equals(trumpcard) && !(c1.getCardSuit().equals(trumpcard)))
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
 
}
 
 
