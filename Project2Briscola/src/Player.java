/**
 * Contains the information about a player in the game or server.
 * @author Crow-kun
 *
 */
public class Player 
{
	private String username, password;
	private int totalScore=0, timesWon=0, timesLost=0, timesDraw=0;
	//private final String server= "   "; implement server assigned
	
	/**
	 * Main constructor.
	 * @param name The username of the new player.
	 * @param pass The password of the new player.
	 */
	public Player(String name, String pass)
	{
		username=name;
		password= pass;
	}
	
	public void username(String player1) 
	{
		username = player1;
	}
	public void password (String passwordP1)
	{
		password = passwordP1;
	}
	public void scores(int points)
	{
		totalScore+= points;
	}
	public void wins ()
	{
		timesWon++;
	}
	public void loses ()
	{
		timesLost++;
	}
	public void draw ()
	{
		timesDraw++;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public int getScore()
	{
		return totalScore;
	}
	
	public int getWins()
	{
		return timesWon;
	}
	
	public int getLoses()
	{
		return timesLost;
	}
	
	public int getDraw()
	{
		return timesDraw;
	}
}
