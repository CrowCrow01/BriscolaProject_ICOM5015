import java.util.ArrayList;


public class AITester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//***********Firts Part***********************
		//***********AI vs AI*************************
		
		System.out.println("Testing: Part I");
		ArrayList<Card> playerhand1= new ArrayList<Card>();
		ArrayList<Card> playerhand2= new ArrayList<Card>();
		String trumpcard = "coin";
		Card tablecard = null;
		
		playerhand1.add(new Card(12,"club"));
		playerhand1.add(new Card(11,"coin"));
		playerhand1.add(new Card(12,"sword"));
		

		playerhand2.add(new Card(10,"sword"));
		playerhand2.add(new Card(9,"sword"));
		playerhand2.add(new Card(8,"club"));
		
		//Initializing AI1
		AI player1 = new AI(playerhand1,tablecard,trumpcard);
		//AI1 plays a card. Card is on table now.
		tablecard = player1.playcard();
		
		System.out.println("Player 1 played: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber()+"\n");
		//Initializing AI2
		AI player2 = new AI(playerhand2,tablecard,trumpcard);
		//AI2 plays its card.
		Card play2 = player2.playcard();
		
		System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber());
		
		
		//**************Second Part*********************************************
		//**************Test cases**********************************************
		
		
		System.out.println("\n\n");
		System.out.println("Testing: Part II");
		trumpcard="coin";
		
		//***Test 1: Trump card on table. User has no trump cards to beat it
				System.out.println("Test 1: Trump card on table. User has no trump cards to beat it");
				
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"sword"));
				playerhand2.add(new Card(9,"sword"));
				playerhand2.add(new Card(8,"club"));
				
				tablecard=new Card(7,"coin");
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
		
		
		//***Test 2: Trump card in table. User can beat it.
				System.out.println("Trump card in table. User can beat it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"club"));
				playerhand2.add(new Card(9,"cup"));
				playerhand2.add(new Card(8,"coin"));
				
				tablecard=new Card(7,"coin");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
		
		
		//***Test 3: Trump card in table. All 3 cards can beat it.
				System.out.println("Trump card in table. All 3 cards can beat it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"coin"));
				playerhand2.add(new Card(9,"coin"));
				playerhand2.add(new Card(8,"coin"));
				
				tablecard=new Card(7,"coin");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
		
		
		//***Test 4: Trump card in table. User has trump but can't beat it. All are trump
				System.out.println("Test 4: Trump card in table. User has trump but can't beat it. All are trump");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"coin"));
				playerhand2.add(new Card(9,"coin"));
				playerhand2.add(new Card(8,"coin"));
				
				tablecard=new Card(11,"coin");
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
		
		//***Test 5: Trump card in table. User has trump but can't beat it
				System.out.println("Test 5: Trump card in table. User has trump but can't beat it");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"coin"));
				
				playerhand2.add(new Card(9,"cup"));
				playerhand2.add(new Card(8,"club"));
				
				tablecard=new Card(11,"coin");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
		
		//***Test 6: Card on table is not trump, but value is 1. User can obtain it with trump.
				System.out.println("Test 6: Card on table is not trump, but value is 1. User can obtain it with trump.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"coin"));
				playerhand2.add(new Card(9,"cup"));
				playerhand2.add(new Card(8,"club"));
				
				tablecard=new Card(1,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 7: Card on table is not trump, but value is 1. User cannot obtain it.
				System.out.println("Test 7: Card on table is not trump, but value is 1. User cannot obtain it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(2,"sword"));
				playerhand2.add(new Card(9,"cup"));
				playerhand2.add(new Card(8,"club"));
				
				tablecard=new Card(1,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 8: Card on table is not trump, but value is 3. User can obtain it with trump.
				System.out.println("Test 8: Card on table is not trump, but value is 3. User can obtain it with trump.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"coin"));
				playerhand2.add(new Card(9,"cup"));
				playerhand2.add(new Card(8,"club"));
				
				tablecard=new Card(3,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 9: Card on table is not trump, but value is 3. User cannot obtain it with trump, but has the 1 of that suit.
				
				System.out.println("Test 9: Card on table is not trump, but value is 3. User cannot obtain it with trump, but has the 1 of that suit.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"club"));
				playerhand2.add(new Card(9,"cup"));
				playerhand2.add(new Card(1,"club"));
				
				tablecard=new Card(3,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 10: Card on table is not trump, but value is 3. User cannot obtain it.
				System.out.println("Test 10: Card on table is not trump, but value is 3. User cannot obtain it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"cup"));
				playerhand2.add(new Card(9,"cup"));
				playerhand2.add(new Card(1,"sword"));
				
				tablecard=new Card(3,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 11: Card on table is not trump, but it has value. User can obtain it with trump.
				
				System.out.println("Test 11: Card on table is not trump, but it has value. User can obtain it with trump.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(9,"club"));
				playerhand2.add(new Card(9,"coin"));
				playerhand2.add(new Card(7,"sword"));
				
				tablecard=new Card(10,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 12: Card on table is not trump, but it has value. User cannot obtain it.
				System.out.println("Test 12: Card on table is not trump, but it has value. User cannot obtain it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"cup"));
				playerhand2.add(new Card(2,"club"));
				playerhand2.add(new Card(4,"sword"));
				
				tablecard=new Card(10,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 13: Card on table is not trump, but it has value. User can obtain it.
				System.out.println("Test 13: Card on table is not trump, but it has value. User can obtain it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"cup"));
				playerhand2.add(new Card(11,"club"));
				playerhand2.add(new Card(4,"sword"));
				
				tablecard=new Card(10,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 14: Card on table is not trump and has no actual value. User can obtain it.
				System.out.println("Test 14: Card on table is not trump and has no actual value. User can obtain it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"cup"));
				playerhand2.add(new Card(9,"club"));
				playerhand2.add(new Card(4,"sword"));
				
				tablecard=new Card(5,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");
				
		//***Test 15: Card on table is not trump and has no actual value. User cannot obtain it.
				System.out.println("Test 15: Card on table is not trump and has no actual value. User cannot obtain it.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"cup"));
				playerhand2.add(new Card(2,"club"));
				playerhand2.add(new Card(4,"sword"));
				
				tablecard=new Card(5,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
				
		//***Test 16: Card on table is not trump and has no actual value. User has a trump card.
				System.out.println("Test 16: Card on table is not trump and has no actual value. User has a trump card.");
				playerhand2.removeAll(playerhand2);
				playerhand2.add(new Card(10,"coin"));
				playerhand2.add(new Card(2,"club"));
				playerhand2.add(new Card(4,"sword"));
				
				tablecard=new Card(5,"club");
				
				System.out.println("Card on table: "+tablecard.getCardSuit()+" "+tablecard.getCardNumber());
				System.out.println("Players hand: "+playerhand2.get(0).getCardSuit()+" "+playerhand2.get(0).getCardNumber()+
						", "+playerhand2.get(1).getCardSuit()+" "+playerhand2.get(1).getCardNumber()+
						", "+playerhand2.get(2).getCardSuit()+" "+playerhand2.get(2).getCardNumber());
				
				
				player2 = new AI(playerhand2,tablecard,trumpcard);
				
				play2 = player2.playcard();
				
				System.out.println("Player 2 played: "+play2.getCardSuit()+" "+play2.getCardNumber()+"\n\n");	
		
		//********End of Test Cases**************
				
	}

}
