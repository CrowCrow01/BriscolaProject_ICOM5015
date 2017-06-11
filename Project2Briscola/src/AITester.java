import java.util.ArrayList;


public class AITester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Card> playerhand1= new ArrayList<Card>();
		ArrayList<Card> playerhand2= new ArrayList<Card>();
		String trumpcard = "coin";
		Card tablecard = new Card(0,"None");
		
		playerhand1.add(new Card(5,"cup"));
		playerhand1.add(new Card(10,"sword"));
		playerhand1.add(new Card(3,"coin"));
		

		playerhand2.add(new Card(12,"cup"));
		playerhand2.add(new Card(6,"coin"));
		playerhand2.add(new Card(9,"sword"));
		
		AI player1 = new AI(playerhand1,tablecard,trumpcard);
		
		tablecard = player1.playcard();
		
		System.out.println("Player 1 played: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
		
		AI player2 = new AI(playerhand2,tablecard,trumpcard);
		
		Card play2 = player2.playcard();
		
		System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber());
		

	}

}
