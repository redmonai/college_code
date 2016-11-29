/*
 * US bingo cards contain 25 squares arranged  in five vertical columns and five horizontal rows. Each space in the grid contains a number. A 
 * typical Bingo game utilises the numbers 1 through 75. The five columns of the card are labeled ‘B’, ‘I’, ‘N’, ‘G’, and ‘O’ from left to right. 
 * The centre space is usually marked ‘Free’, and is considered  automatically filled. The range of the unique random numbers which appear on the 
 * card is restricted by column, with the ‘B’ column only containing numbers between 1 and 15 inclusive, the ‘I’ column containing only 16 through 
 * 30, ‘N’ containing 31 through 45, ‘G’ containing 46 through 60, and ‘O’ containing 61 through 75. A 46 through 60, and ‘O’ containing 61 through 
 * 75 [see Figure 1 below].
 *   B	 I	 N	 G	 O
 *   5	25  33	47	75
 *   13	21  37	53	67
 *   14	28	F   48	61
 *   7	17	44	58	69
 *   8	24	32	55	65
 * Figure 1: A typical US bingo card.
 */

import java.util.Random;

public class BingoCard 
{
	//constants
	public static final int ROWS = 5;
	public static final int COLUMNS = 5;
	public static final int MIDDLE_SPACE = 2;
	
	//instance variables
	private String[][] bingoCard = new String[ROWS][COLUMNS];
	private String[] firstCol = {" 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10", "11", "12", "13", "14", "15"};
	private String[] secondCol = {"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
	private String[] thirdCol = {"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45"};
	private String[] fourthCol = {"46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"};
	private String[] fifthCol = {"61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75"};

	//constructor
	public BingoCard()
	{
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLUMNS; col++)
			{
				bingoCard[row][col] = "  ";
			}
		}
	}
	
	//instance methods
	public void populateCard()
	{
		String[] one = randomiseSpaces(firstCol);
		String[] two = randomiseSpaces(secondCol);
		String[] three = randomiseSpaces(thirdCol);
		String[] four = randomiseSpaces(fourthCol);
		String[] five = randomiseSpaces(fifthCol);
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLUMNS; col++)
			{
				if (col == 0)
					bingoCard[row][col] = one[row];
				else if (col == 1)
					bingoCard[row][col] = two[row];
				else if (col == 2)
					bingoCard[row][col] = three[row];
				else if (col == 3)
					bingoCard[row][col] = four[row];
				else if (col == 4)
					bingoCard[row][col] = five[row];
			}	
		}
		bingoCard[MIDDLE_SPACE][MIDDLE_SPACE] = " F";
	}
	
	public String[] randomiseSpaces(String[] values)
	{
		Random random = new Random();
		for (int i = 0; i < values.length; i++ )
		{
			int j = random.nextInt(values.length);
		    String temp = values[i];
		    values[i] = values[j];
		    values[j] = temp;
		}
		return values;
	}
	
	public boolean checkSpace(String drawn)
	{
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLUMNS; col++)
			{
				String currentSquare = bingoCard[row][col];
				if (currentSquare.equals(drawn))
				{
					bingoCard[row][col] = " X";
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean winner()
	{
		//vertical winner
		for (int row = 0; row < ROWS; row++)
		{
			int col = 0;
			if ((bingoCard[row][col].equals(bingoCard[row][col+1])) && (bingoCard[row][col].equals(bingoCard[row][col+2])) 
									&& (bingoCard[row][col].equals(bingoCard[row][col+3])) && (bingoCard[row][col].equals(bingoCard[row][col+4])))
				return true;	
		}
		
		//horizontal winner
		for (int col = 0; col < COLUMNS; col++)
		{
			int row = 0;
			if ((bingoCard[row][col].equals(bingoCard[row+1][col])) && (bingoCard[row][col].equals(bingoCard[row+2][col])) 
									&& (bingoCard[row][col].equals(bingoCard[row+3][col])) && (bingoCard[row][col].equals(bingoCard[row+4][col])))
				return true;
		}
		
		//vertical on middle row
		if ((bingoCard[0][MIDDLE_SPACE].equals(bingoCard[1][MIDDLE_SPACE])) && (bingoCard[0][MIDDLE_SPACE].equals(bingoCard[3][MIDDLE_SPACE])) 
									&& (bingoCard[0][MIDDLE_SPACE].equals(bingoCard[4][MIDDLE_SPACE])))
			return true;
		//horizontal on middle row
		else if ((bingoCard[MIDDLE_SPACE][0].equals(bingoCard[MIDDLE_SPACE][1])) && (bingoCard[MIDDLE_SPACE][0].equals(bingoCard[MIDDLE_SPACE][3])) 
									&& (bingoCard[MIDDLE_SPACE][0].equals(bingoCard[MIDDLE_SPACE][4])))
			return true;
		//diagonal winners
		//bottom left to top right
		else if ((bingoCard[4][0].equals(bingoCard[3][1])) && (bingoCard[4][0].equals(bingoCard[1][3])) && (bingoCard[4][0].equals(bingoCard[0][4])))
			return true;
		//top left to bottom right
		else if ((bingoCard[0][0].equals(bingoCard[1][1])) && (bingoCard[0][0].equals(bingoCard[3][3])) && (bingoCard[0][0].equals(bingoCard[4][4])))
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		String bingoString = new String("  B   I   N   G   O \n");
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLUMNS; col++)
			{
				if (col == 4)
					bingoString += "[" + bingoCard[row][col] + "]\n";
				else
					bingoString += "[" + bingoCard[row][col] + "]";
			}
		}
		return bingoString;
	}
	
	public static void main(String[] args)
	{
		BingoCard currentCard = new BingoCard();
		currentCard.populateCard();
		System.out.println(currentCard.toString());
		System.out.println(currentCard.checkSpace("13"));
		System.out.println(currentCard.toString());
	}

}
