
public class Card
{
	private int cardNumber;
	private String cardSuit;
	
	public Card()
	{
		cardNumber=0;
		cardSuit= "";
	}
	
	public Card(int number, String suit)
	{
		cardNumber= number;
		cardSuit= new String(suit);
				
	}
	
	public int getCardNumber()
	{
		return cardNumber;
	}
	
	public String getCardSuit()
	{
		return cardSuit;
	}
	
	public boolean isGreaterThan(Card c)
	{
		int cardValue1, cardValue2;
		if(this.cardNumber== 1)
		{
			cardValue1= 14;
		}
		else if(this.cardNumber== 3)
		{
			cardValue1= 13;
		}
		else
		{
			cardValue1= this.cardNumber;
		}
		
		if(c.cardNumber==1)
		{
			cardValue2= 14;
		}
		else if(c.cardNumber== 3)
		{
			cardValue2= 13;
		}
		else
		{
			cardValue2= c.cardNumber;
		}
		
		if(cardValue1 > cardValue2)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean isLesserThan(Card c)
	{
		if(this.isGreaterThan(c)==true)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	public boolean isEqualto(Card c)
	{
		if(this.cardNumber== c.cardNumber)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
