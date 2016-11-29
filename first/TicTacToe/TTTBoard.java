/*
 * Write a TicTacToeBoard data type (Java class) whose values (Java objects) represent Tic-tac-toe game boards in any state. 
 * Your data type must provide at least the following operations (Java instance methods):
 * (1) constructor operation which creates a blank Tic-tac-toe game board.
 * (2) isSquareFull operation which takes a location (row and column numbers) and  determines whether the location on the 
 * 		board is occupied.
 * (3) move operation which takes a piece type (X or O) and a board location (row and  column numbers) and which updates the 
 * 		Tic-tac-toe board with the move. The operation should return true or false depending on whether it was able to apply  
 * 		the move or not. The operation should have no effect on the Tic-tac-toe board if there is a piece already in the 
 * 		specified location or if that player, indicated by the piece type, made the previous move.
 * (4) isBoardFull operation which determines whether the Tic-tac-toe board is full.
 * (5) winner operation which inspects the Tic-tac-toe board and returns which player won or indicates that no one has won so 
 * 		far. A player has won if they have three pieces in a straight line (vertically, horizontally or diagonally).
 * (6)  toString operation which returns the current state of the Tic-tac-toe board represented as a String.
 */

public class TTTBoard 
{
	//instance variables
	private final int ROWS = 3;
	private final int COLUMNS = 3;
	private String[][] board = new String[ROWS][COLUMNS];
	
	//constructor which creates a blank tic-tac-toe game board
	//2d array of pieces (char) and board layout
	public TTTBoard()
	{
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLUMNS; j++)
			{
				board[i][j] = "";
			}
		}
	}
	
	// takes a board location (row and column numbers) and determines if that location is currently occupied
	public boolean isSquareFull(int xpos, int ypos)
	{
		if (board[xpos][ypos] == "")
		{
			return false;
		}
		else
			return true;
	}
	
	// takes a piece type (char) and a board location and places the piece in that location, updating the board
	// returns true if this was successful
	// does nothing if there is already a piece there or if that player made the last move
	public boolean move(String piece, int xpos, int ypos)
	{
		int tempX = xpos - 1;
		int tempY = ypos - 1;
		// check if that player made the last move too
		if (!isSquareFull(tempX, tempY))
		{
			board[tempX][tempY] = piece;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// determines if the board is full
	public boolean isBoardFull()
	{
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLUMNS; j++)
			{
				if (!isSquareFull(i, j))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	// determines if a player has won
	// a player wins if they have a straight line vertically, horizontally, or diagonally
	public boolean winner()
	{
		// vertical winner
		for (int i = 0; i < ROWS; i++)
		{
			int j = 0;
			if ((board[i][j].equals(board[i][j+1])) && (board[i][j].equals(board[i][j+2])) && (!board[i][j].equals("")))
				return true;	
		}
		
		// horizontal winner
		for (int j = 0; j < COLUMNS; j++)
		{
			int i = 0;
			if ((board[i][j].equals(board[i+1][j])) && (board[i][j].equals(board[i+2][j])) && (!board[i][j].equals("")))
				return true;
		}

		// diagonal winner
		if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && (!board[0][0].equals("")))
			return true;
		else if (board[2][0].equals(board[1][1]) && board[2][0].equals(board[0][2]) && (!board[2][0].equals("")))
			return true;
		else
			return false;		
	}
	
	// returns the board as a string
	public String toString()
	{
		String boardString = new String("       1        2        3 ");
		for (int i = 0; i < this.board.length; i++)
		{
			boardString += "\n" + (i+1) + " ";
			for (int j = 0; j < this.board[0].length; j++)
			{
				if (this.board[i][j] != "")
				{
					boardString += "[_" + board[i][j] + "_]";
				}
				else
				{
					boardString += "[___]";
				}
			}
		}
		return boardString;
	}
	
	// main method for testing data type
	public static void main(String[] args)
	{
		TTTBoard currentBoard = new TTTBoard();
		System.out.println(currentBoard.toString() + "\n");
		String playerChar = "X";
		String computerChar = "O";
		currentBoard.move(playerChar, 1, 1);
		System.out.println(currentBoard.toString());
		System.out.println(currentBoard.winner());
		currentBoard.move(computerChar, 3, 3);
		System.out.println(currentBoard.toString());
		System.out.println(currentBoard.winner());
		currentBoard.move(playerChar, 1, 2);		
		System.out.println(currentBoard.toString());
		System.out.println(currentBoard.winner());
		currentBoard.move(computerChar, 2, 3);
		System.out.println(currentBoard.toString());
		System.out.println(currentBoard.winner());
		currentBoard.move(playerChar, 1, 3);		
		System.out.println(currentBoard.toString());
		System.out.println(currentBoard.winner());
		currentBoard.move(computerChar, 2, 2);
		System.out.println(currentBoard.toString());
		System.out.println(currentBoard.winner());	
	}
}
