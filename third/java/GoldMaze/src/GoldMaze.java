/* Players hunt for gold in a 2D matrix array. Each cell can hold either a displacement
 * value, a trap, or the gold. A player is given a start cell pos and teleport through 
 * the maze based on displacement values. Players move until one finds gold or they are
 * all trapped.
 *
 * Sample input
 * 5 4 					//the size of the matrix
 * D 2 0 D 0 2 T D 3 0 	//the cell arrangement 
 * T D 6 0 D 0 3 D 2 0 
 * D 0 -1 D 1 0 G D 0 -1 
 * D 0 3 D 0 2 D 0 2 D 0 3 
 * D -1 0 T D 0 -3 T 
 * 4 						//the number of players
 * 1 4 2 					//player id and start pos
 * 2 2 0 
 * 3 0 3 
 * 4 2 3
 * 
 * output is the winning player's id; in this example, 4
*/


import java.util.*;

public class GoldMaze {
	
	static Cell[][] maze;
	static ArrayList<Player> players;
	static int R, C, noPlayers;
	
	static void read() throws Exception
	{
		Scanner inputScan = new Scanner(System.in);
		R = inputScan.nextInt();
		C = inputScan.nextInt();
		maze = new Cell[R][C];						//create the maze
		inputScan.nextLine();
		for (int i = 0; i < R; i++)
		{
			String temp = inputScan.nextLine();
			int j = 0;
			while (j < C)
			{
				for (int x = 0; x < temp.length(); x = x + 2)
				{
					char ch = temp.charAt(x);			
					switch(ch) {
						case 'D':												//cell has displacement
						{
							int X = 0;
							int Y = 0;
							ch = temp.charAt(x+=2);
							if (ch != '-')
							{
								X = Character.getNumericValue(temp.charAt(x));
							}
							else
							{
								X = 0 - Character.getNumericValue(temp.charAt(x+=1));
							}
							ch = temp.charAt(x+=2);
							if (ch != '-')
							{
								Y = Character.getNumericValue(temp.charAt(x));
							}
							else
							{
								Y = 0 - Character.getNumericValue(temp.charAt(x+=1));
							}
							maze[i][j] = new Cell(true, false, false, X, Y);
							j++;
							break;
						}
						case 'T':												//cell has a trap
						{
							maze[i][j] = new Cell(false, true, false, 0, 0);
							j++;
							break;
						}
						case 'G':												//cell has gold
						{
							maze[i][j] = new Cell(false, false, true, 0 ,0);
							j++;
							break;
						}
						default:
						{
							maze[i][j] = new Cell(false, false, false, 0, 0);
							j++;
							break;
						}
					}
				}
			}
		}
		
		noPlayers = inputScan.nextInt();
		players = new ArrayList<Player>();
		for (int i = 0; i < noPlayers; i++)
		{
			int ID = inputScan.nextInt();
			int x = inputScan.nextInt();
			int y = inputScan.nextInt();
			Player temp = new Player(ID, x, y);
			players.add(temp);
		}
		inputScan.close();	
	}
	
	public static void main(String[] args) throws Exception 
	{		
		boolean gold = false;
		read();
		while (!gold)
		{
			gold = move();
		}
		int winnerID = findWinner();
		System.out.println(winnerID);
	}
	
	//moves all players
	public static boolean move()
	{
		for (int i = 0; i < players.size(); i++)
		{
			if (!players.get(i).trapped)
			{
				Player temp = players.get(i);
				int dispX = maze[temp.currentX][temp.currentY].dispX;
				int dispY = maze[temp.currentX][temp.currentY].dispY;
				int newX = temp.currentX + dispX;
				int newY = temp.currentY + dispY;
				while (newX >= R)					
				{
					newX = newX - R;
				}
				while (newX < 0)
				{
					newX = R + newX;
				}
				while (newY >= C)					
				{
					newY = newY - C;
				}
				while (newY < 0)
				{
					newY = C + newY;
				}
				players.get(i).currentX = newX;
				players.get(i).currentY = newY;
				temp = players.get(i);
				checkTrapped(temp, i);
				if (checkGold(temp))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkGold(Player player)
	{
		if (maze[player.currentX][player.currentY].gold)
		{
			return true;
		}
		return false;
	}
	
	public static void checkTrapped(Player player, int index)
	{
		if (maze[player.currentX][player.currentY].trap)
		{
			players.get(index).trapped = true;
		}
	}

	public static int findWinner()
	{
		int winnerID = 0;
		for (int i = 0; i < players.size(); i++)
		{
			Player temp = players.get(i);
			if (maze[temp.currentX][temp.currentY].gold)
				winnerID = temp.playerID;
		}
		return winnerID;
	}
}
