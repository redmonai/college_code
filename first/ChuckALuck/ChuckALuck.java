import java.awt.Font;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ChuckALuck {

	public static final int DICE_HW = 40;
	public static final int CIRCLE_RADIUS = 5;
	public static final int CIRCLE_SPACING = 20;
	public static final int SCREEN_HEIGHT = 400;
	public static final int SCREEN_WIDTH = 400;
	public static final int MARGIN = 0;
	public static final int SPACING = 150;

	public static void main(String[] args) {
		boolean play = false;
		try
		{
			Wallet playerWallet = new Wallet();
			Dice diceOne = new Dice();
			Dice diceTwo = new Dice();
			Dice diceThree = new Dice();
			
			int startPlay = JOptionPane.showOptionDialog(null, "Would you like to play the Chuck-A-Luck game?",
										"Chuck-A-Luck", JOptionPane.YES_NO_OPTION, 
										JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (startPlay == 0)
			{
				play = true;
				fillWallet(playerWallet);
				playGame(play, playerWallet, diceOne, diceTwo, diceThree);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You have quit the game.");
			}
		}
		catch(NullPointerException exception)
		{
			JOptionPane.showMessageDialog(null, "Thank you for playing. You have quit the game.");
			play = false;
		}
	}

	public static void fillWallet(Wallet playerWallet)
	{
		boolean validInput = false;
		while (!validInput)
		{
			String input = JOptionPane.showInputDialog(null, "How much money will you put in your wallet?");
			Scanner inputScanner = new Scanner(input);
			if (inputScanner.hasNextDouble())
			{
				double moneyInput = inputScanner.nextDouble();
				if (moneyInput == 0.0)
				{
					JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number value greater than zero.");
				}
				else
				{
					playerWallet.put(moneyInput);
					validInput = true;
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number value.");
			}
			inputScanner.close();
		}
	}
	
	public static void playGame(boolean play, Wallet playerWallet, Dice diceOne, Dice diceTwo, Dice diceThree)
	{
		while (play)
		{
			int numberOfTurns = 0;
			int keepPlaying = 0;
			if (keepPlaying == 0)
			{
				numberOfTurns++;
				double betAmount = betWallet(playerWallet);
				int bet = chooseBet();
				int rollOne = diceOne.roll();
				int rollTwo = diceTwo.roll();
				int rollThree = diceThree.roll();
				double result = calculateResult(rollOne, rollTwo, rollThree, bet, betAmount);
				drawDice(playerWallet, rollOne, rollTwo, rollThree, bet, betAmount, result);
				playerWallet.put(result);
				keepPlaying = 1;
				if ((numberOfTurns >= 1) && (playerWallet.check() != 0))
				{
					keepPlaying = JOptionPane.showOptionDialog(null, "Would you like to keep playing?" +
										"\nYour wallet currently holds €" + playerWallet.check() +"0.", 
										"Chuck-A-Luck", JOptionPane.YES_NO_CANCEL_OPTION, 
										JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (keepPlaying == 1 || keepPlaying == 2 || keepPlaying == JOptionPane.CLOSED_OPTION)
					{
						JOptionPane.showMessageDialog(null, "Thanks for playing. You have quit the game.");
						play = false;
					}
				}
				if (playerWallet.check() == 0)
				{
					JOptionPane.showMessageDialog(null, "Your wallet is empty. You have lost the game!", 
										"Chuck-A-Luck", JOptionPane.PLAIN_MESSAGE);
					play=false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You have quit the game.", "Chuck-A-Luck", 
										JOptionPane.PLAIN_MESSAGE);
				play = false;
			}
		}
	}

	public static double betWallet (Wallet playerWallet)
	{
		double betAmount = 0.0;
		boolean invalidInput = true;
		while (invalidInput)
		{
			String moneyBet = JOptionPane.showInputDialog(null, "How much would you like to bet? \nYour current "
														+ "wallet balance is €" + playerWallet.check() + "0.");
			Scanner inputScanner = new Scanner(moneyBet);
			if (inputScanner.hasNextDouble())
			{
				betAmount = inputScanner.nextDouble();
				if (betAmount == 0.0)
				{
					JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number value greater than zero."
														+ "and less than the wallet balance.");
				}
				else
				{
					playerWallet.get(betAmount);
					invalidInput = false;
				}	
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number value.");
			}
			inputScanner.close();
		}
		return betAmount;
	}
	
	public static int chooseBet()
	{
		int bet = 0;
		boolean validBet = false;
		while (!validBet)
		{
			String betInput = JOptionPane.showInputDialog(null, "What result will you bet? Type a number value:" +
								"\n (1) A Triple Roll - all three dice have the same result" +
								"\n (2) A Big Result - the sum of the result is greater than 11" +
								"\n (3) A Field Result - the sum of the result is less than 8 "
								+ "or greater than 11" +
								"\n (4) A Small Result - the sum of the result is less than 8");
			Scanner betScanner = new Scanner (betInput);
			if (betScanner.hasNextInt())
			{
				bet = betScanner.nextInt();
				if (bet >= 1 && bet <= 4)
				{
					validBet = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number value between 1 and 4.");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number value between 1 and 4.");
			}
			betScanner.close();
		}
		return bet;
	}
	
	public static double calculateResult(int rollOne, int rollTwo, int rollThree, int bet, double betAmount)
	{
		double result = 0;
		//triple
		if ((rollOne == rollTwo) && (rollTwo == rollThree) && (bet == 1))
		{
			result = betAmount * 31;
			JOptionPane.showMessageDialog(null, "Congratulations, you were right! \nThe number rolled was " + rollOne + 
											". \nYou get €" + result + "0 added to your wallet.",
											"Chuck-A-Luck", JOptionPane.PLAIN_MESSAGE);
		}
		//big result
		else if (((rollOne + rollTwo + rollThree) > 11) && (bet == 2))
		{
			result = betAmount*2;
			JOptionPane.showMessageDialog(null, "Congratulations, you were right! \nThe numbers rolled were " + rollOne + 
											", " + rollTwo + " and " + rollThree + ". \nYou get €" + result + 
											"0 added to your wallet.", "Chuck-A-Luck", JOptionPane.PLAIN_MESSAGE);
		}
		//field result
		else if ((((rollOne + rollTwo + rollThree) < 8) && (bet == 3)) || (((rollOne + rollTwo + rollThree) > 12) && (bet == 3)))
		{
			result = betAmount*2;
			JOptionPane.showMessageDialog(null, "Congratulations, you were right! \nThe numbers rolled were " + rollOne + 
											", " + rollTwo + " and " + rollThree + ". \nYou get €" + result + 
											"0 added to your wallet.", "Chuck-A-Luck", JOptionPane.PLAIN_MESSAGE);
		}
		//small result
		else if (((rollOne + rollTwo + rollThree) < 8) && (bet == 4))
		{
			result = betAmount*2;
			JOptionPane.showMessageDialog(null, "Congratulations, you were right! \nThe numbers rolled were " + rollOne + 
											", " + rollTwo + " and " + rollThree + ". \nYou get €" + result + 
											"0 added to your wallet.", "Chuck-A-Luck", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			result -= betAmount;
			JOptionPane.showMessageDialog(null, "Sorry, you were wrong! \nThe numbers rolled were " + rollOne + 
											", " + rollTwo + " and " + rollThree + ". \nYour bet of €" + betAmount + 
											"0 will be taken from your wallet.", "Chuck-A-Luck", JOptionPane.PLAIN_MESSAGE);
		}
		return result;
	}

	public static void drawDice(Wallet playerWallet, int rollOne, int rollTwo, int rollThree, int bet, double betAmount, double result)
	{
		int xcoord = 100;
		int ycoord = 300;
		StdDraw.setCanvasSize(SCREEN_HEIGHT, SCREEN_WIDTH);
		StdDraw.setXscale(0, SCREEN_WIDTH);
		StdDraw.setYscale(0, SCREEN_HEIGHT);
		Font textFont = new Font("SansSerif", Font.PLAIN, 12);
		StdDraw.setFont(textFont);		
		StdDraw.setPenColor(StdDraw.BLACK);
		
		//dice one
		StdDraw.square(xcoord, ycoord, DICE_HW);
		switch(rollOne)
		{
		case 1:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			break;
		case 2:
			StdDraw.filledCircle(xcoord, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 3:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 4:
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 5:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		default:
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord, CIRCLE_RADIUS);
			break;
		}
		
		//dice two
		xcoord += SPACING;
		StdDraw.square(xcoord, ycoord, DICE_HW);
		switch(rollTwo)
		{
		case 1:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			break;
		case 2:
			StdDraw.filledCircle(xcoord, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 3:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 4:
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 5:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		default:
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord, CIRCLE_RADIUS);
			break;
		}
		
		//dice three
		xcoord -= SPACING/2;
		ycoord -= SPACING;
		StdDraw.square(xcoord, ycoord, DICE_HW);
		switch(rollThree)
		{
		case 1:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			break;
		case 2:
			StdDraw.filledCircle(xcoord, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 3:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 4:
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		case 5:
			StdDraw.filledCircle(xcoord, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			break;
		default:
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord - CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord - CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord + CIRCLE_SPACING, CIRCLE_RADIUS);
			StdDraw.filledCircle(xcoord + CIRCLE_SPACING, ycoord, CIRCLE_RADIUS);
			break;
		}
		
		String betString = new String ("");
		switch(bet)
		{
		case 1:
			betString = "triple roll";
			break;
		case 2:
			betString = "big result";
			break;	
		case 3:
			betString = "field result";
			break;	
		default:
			betString = "small result";
			break;
		}
		
		//text
		StdDraw.text(250, 50, "You bet it would be a " + betString + " and wagered €" + betAmount + "0.");
		if (result > 0)
		{
			StdDraw.text(260, 38, "Congratulations, you were right! You win €" + result + "0.");
		}
		else
		{
			StdDraw.text(260, 38, "Sorry, you were wrong. You lose €" + Math.abs(result) + "0.");
		}
		StdDraw.text(260, 26, "Your current balance is €" + playerWallet.check() + "0.");
	}

}
