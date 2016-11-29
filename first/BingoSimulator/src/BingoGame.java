/*
 * A caller randomly calls numbers from 1 to 75 without replacement. Each player marks the corresponding number as occupied if it is present on their card. 
 * The first player to have five occupied numbers in a row horizontally, in a column vertically, or along either of the two major diagonals is the winner. 
 * The winner is required to call out the word “Bingo!”.
 * 
 */

import java.util.ArrayList;
import java.util.Random;

public class BingoGame {
	public static final int POSSIBLE_NUMBERS = 75;
	
	private String[] numberList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
									"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
									"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", 
									"46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", 
									"61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75"};
	private ArrayList<BingoCard> cards = new ArrayList<BingoCard>();
	private int drawIndex;
	private int turnsCount;
	
	public BingoGame(int numberOfCards)
	{
		drawIndex = -1;
		turnsCount = 0;
		for (int i = 0; i <= numberOfCards; i++)
		{
			BingoCard newCard = new BingoCard();
			newCard.populateCard();
			cards.add(i, newCard);
		}
	}
	
	public void randomiseDraw()
	{
		Random random = new Random();
		for (int i = 0; i < numberList.length; i++ )
		{
			int j = random.nextInt(numberList.length);
		    String temp = numberList[i];
		    numberList[i] = numberList[j];
		    numberList[j] = temp;
		}
	}
	
	public String draw()
	{
		drawIndex++;
		turnsCount++;
		String draw = "";
		if (drawIndex < POSSIBLE_NUMBERS)
		{
			draw = numberList[drawIndex];
			return draw;
		}
		else
			return null;
	}
	
	public void checkCards()
	{
		String currentDraw = draw();
		if (currentDraw != null)
		{
			for (int i = 0; i < cards.size(); i++)
			{
				BingoCard tempCard = cards.get(i);
				tempCard.checkSpace(currentDraw);
			}
		}
		else
		{
			System.out.println("End of draw");
		}
	}

	public boolean findWinner()
	{
		for (int i = 0; i < cards.size(); i++)
		{
			BingoCard tempCard = cards.get(i);
			if (tempCard.winner())
			{
				return true;
			}
		}
		return false;
	}
	
	public void playGame()
	{
		randomiseDraw();
		boolean play = true;
		while (play)
		{
			checkCards();
			if (findWinner())
			{
				play = false;
			}
		}
	}
	
	public int turnCounter()
	{
		return turnsCount;
	}
	
	public String toString()
	{
		String gameString = "";
		for (int i = 0; i < cards.size(); i++)
		{
			BingoCard tempCard = cards.get(i);
			gameString += ("" + tempCard.toString());
			gameString += "\n";
		}		
		return gameString;
	}
	
	public static void main (String[] args)
	{
		BingoGame currentGame = new BingoGame(4);
		currentGame.playGame();
	}
}
