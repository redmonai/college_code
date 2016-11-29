/*
 * Write a TicTacToeGame data type (Java class) whose values (Java objects) represent a Tic-tac-toe game in progress. Your data
 * type must make use of the TicTacToeBoard data type and must also provide at least the following operations (Java instance 
 * methods):
 * (1) constructor operation which creates a new Tic-tac-toe game which is ready to be played by two players.
 * (2) chooseSquare operation which takes a piece type (X or O) and which gets a  Tic-tac-toe board location (row and column 
 * 		numbers) from the piece type player. The operation should return the chosen location in an array of integers of 
 * 		length 2.
 * (3) play operation which will allow two players to play a game of Tic-tac-toe.
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class TTTGame 
{
	//constants
	private static final String PLAYER_1 = "X";
	private static final String PLAYER_2 = "0";
	
	//instance variables
	private String lastPiece;
	private String currentPiece;
	private String nextPiece;
	private boolean keepPlaying;
	private TTTBoard board;
	
	//constructor
	public TTTGame()
	{
		lastPiece = PLAYER_2;
		currentPiece = PLAYER_1;
		nextPiece = PLAYER_2;
		board = new TTTBoard();
		keepPlaying = true;
	}
	
	// takes a piece type (char) and gets a tic-tac-toe board location from the player
	// returns the chosen location as an array of ints of length 2
	public int[] chooseSquare(String piece)
	{
		int[] location = new int[2];
		boolean validInput = false;
		while (!validInput)
		{
			String input = JOptionPane.showInputDialog(null, board.toString() + "\nThe current player is " + piece + ". "
											+ "\nInput the location of your next move in the form row,column.");
			Scanner inputScanner = new Scanner(input);
			inputScanner.useDelimiter(",");
			if(inputScanner.hasNextInt())
			{
				if (inputScanner.hasNextInt())
				{
					for (int i = 0; i < location.length; i++)
					{
						location[i] = inputScanner.nextInt();
					}
					validInput = true;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter two numbers separated by a comma.");
			}
			inputScanner.close();
		}
		return location;
	}
	
	// allows two players to play the game
	public void play()
	{
		while (keepPlaying)
		{
			int[] location = chooseSquare(currentPiece);
			if (board.move(currentPiece, location[0], location[1]))
			{
				if (board.winner())
				{
					JOptionPane.showMessageDialog(null, board.toString() + "\nThe winner is " + currentPiece + ". \nCongratulations!");
					keepPlaying = false;
				}
				
				else if (board.isBoardFull() && !board.winner())
				{
					JOptionPane.showMessageDialog(null, board.toString() + "\nGame over! \nThe board is full with no winner.");
					keepPlaying = false;
				}
				lastPiece = currentPiece;
				currentPiece = nextPiece;
				nextPiece = lastPiece;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid move! The square is full!");
			}
		}
	}
	
}
