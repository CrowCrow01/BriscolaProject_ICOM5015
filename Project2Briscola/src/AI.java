import java.util.ArrayList;
 
public class AI {
 
    private ArrayList<String> hand;
    private String tablecard="None";
    private String trumpcard;
    
    public AI(ArrayList<String> hand, String tablecard, String trumpcard)
    {
        this.hand = hand;
        this.tablecard = tablecard;
        this.trumpcard = trumpcard;
    }
    
    public String playcard()
    {
        if(tablecard.equals("None"))
        {
            String chosenCard = hand.get(0);
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
            String chosenCard = hand.get(0);
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
    
    private Object comparegreater(String string, String string2) {
        String winnerCard = string;
        if(getValue(string2) > getValue(string))
        {
            winnerCard = string2;
        }
        
        return winnerCard;
    }
 
    private Object compareless(String string, String string2) {
        String winnerCard = string;
        if(getValue(string2) < getValue(string))
        {
            winnerCard = string2;
        }
        
        return winnerCard;
    }
 
    private int getValue(String string) {
        String suit = string.substring(0,string.length()-1);
        int A=0,B=0,C=0,D=0,club=1,sword=1,coin=1,cup=1;
        if(trumpcard.equals("club")) club = 10;
        if(trumpcard.equals("sword")) sword = 10;
        if(trumpcard.equals("coin")) coin = 10;
        if(trumpcard.equals("cup")) cup = 10;
        if(tablecard.substring(0, tablecard.length()-1).equals("club")) club = 100;
        if(tablecard.substring(0, tablecard.length()-1).equals("sword")) sword = 100;
        if(tablecard.substring(0, tablecard.length()-1).equals("coin")) coin = 100;
        if(tablecard.substring(0, tablecard.length()-1).equals("cup")) cup = 100;
        if(suit.equals("club"))A=1;
        if(suit.equals("sword"))B=1;
        if(suit.equals("coin"))C=1;
        if(suit.equals("cup"))D=1;
        
        int number = Integer.parseInt(string.substring(string.length()-1));
        if(number==1) number = 14;
        if(number==3) number = 13;
        number= number*(A*club+B*sword+C*coin+D*cup);
        return number;
    }    
 
}
 
 
