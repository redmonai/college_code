
public class Player {
	
	public int playerID;
	public int currentX;
	public int currentY;
	public boolean trapped;
	
	public Player(int ID, int X, int Y)
	{
		playerID = ID;
		currentX = X;
		currentY = Y;
		trapped = false;
	}
}
