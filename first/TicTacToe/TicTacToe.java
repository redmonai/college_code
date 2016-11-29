/*
 * Tic-tac-toe Game
 * Tic-tac-toe, also called noughts and crosses or Xs and Os, is a game of two players, X and O, who take turns marking the 
 * spaces in a 3 × 3 grid. The player who succeeds in placing three respective marks in a horizontal, vertical, or diagonal 
 * row wins the game [see Figure 1].
 * 
 * Write a Java program which will allow to two users to play a game of Tic-tac-toe against each other.  [If  you get this 
 * done consider extending your program to allow a user to play a game of Tic-tac-toe against an intelligent computer player.]  
 */
import javax.swing.JOptionPane;

public class TicTacToe {
	
	public static void main(String[] args) 
	{
		try
		{
			int choice = JOptionPane.showOptionDialog(null, "Would you like to play Tic-Tac-Toe?", "Tic-Tac-Toe", 
											JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (choice == 0)
			{
				TTTGame game = new TTTGame();
				game.play();
			}
		}
		catch (NullPointerException exception)
		{
			
		}
	}
}
