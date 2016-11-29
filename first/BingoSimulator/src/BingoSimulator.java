/*
 * Write a Java program which compute the average number of calls it takes for one bingo card in play to achieve "Bingo!", that is, if there is only one bingo 
 * card in play compute the average number of calls required for the single bingo card achieve "Bingo!". Your program must also compute the average number 
 * of calls it takes for one of 500 bingo cards in play to achieve "Bingo!". Your solution must make use of a BingoCard data type 
 * (and perhaps a BingoGame data type) which you will have to design and implement. 
 */

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class BingoSimulator {

	public static void main(String[] args) 
	{
		boolean run = true;
		while(run)
		{
			try
			{
				int numberOfCards = 0;
				int numberOfGames = 0;
				int gameCounter = 0;
				
				JTextField noOfCards = new JTextField(10);
				JTextField noOfGames = new JTextField(10);
				JPanel inputPanel = new JPanel(new GridLayout(0,1));
				inputPanel.add(new JLabel("Enter the number of bingo cards to test: "));
				inputPanel.add(noOfCards);
				inputPanel.add(Box.createVerticalStrut(15));
				inputPanel.add(new JLabel("Enter the number of games to perform: "));
				inputPanel.add(noOfGames);
				inputPanel.add(Box.createVerticalStrut(15));
				boolean invalidInput = true;
				while (invalidInput)
				{
					int input = JOptionPane.showOptionDialog(null, inputPanel, "Bingo Simulator", JOptionPane.OK_CANCEL_OPTION, 
															JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (input == JOptionPane.CLOSED_OPTION || input == JOptionPane.CANCEL_OPTION)
					{
						System.out.println("pls work");
						run = false;
					}
					String cards = noOfCards.getText();
					String games = noOfGames.getText();
					Scanner cardsScanner = new Scanner(cards);
					Scanner gamesScanner = new Scanner(games);
					if (cardsScanner.hasNextInt() && gamesScanner.hasNextInt())
					{
						numberOfCards = cardsScanner.nextInt();
						numberOfGames = gamesScanner.nextInt();
						if (numberOfCards > 0 && numberOfGames > 0)
							invalidInput = false;
						else
							JOptionPane.showMessageDialog(null, "Invalid input. Please enter two integer values greater than zero.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid input. Please enter two integer values greater than zero.");
					}
					cardsScanner.close();
					gamesScanner.close();
				}
				
				int[] turns = new int[numberOfGames];
				while (gameCounter != numberOfGames)
				{
					BingoGame testGame = new BingoGame(numberOfCards);
					testGame.playGame();
					turns[gameCounter] = testGame.turnCounter();
					gameCounter++;			
				}
				double average = averageTurns(turns);
				JOptionPane.showMessageDialog(null, "In " + numberOfGames + " games with " + numberOfCards + " BINGO card" + (numberOfCards!=1?"s":"") + ", "
											+ "it took " + average + " turns on average to reach a winner.");
				run = false;
			}
			catch(NullPointerException exception)
			{
				JOptionPane.showMessageDialog(null, "You have exited the program.");
				run = false;
			}
		}
	}
	
	public static double averageTurns (int[] turns)
	{
		double average = 0.0;
		int turnsTotal = 0;
		if (turns != null)
		{
			for (int i = 0; i < turns.length; i++)
			{
				turnsTotal += turns[i];
			}
		}
		average = turnsTotal/turns.length;
		return average;
	}
	
	

}
