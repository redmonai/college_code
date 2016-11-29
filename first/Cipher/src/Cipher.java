/* Write a Java program which converts (user entered) plain text to cipher text using a substitution cipher 
 * (in which plain text letters are randomly assigned to cipher text letters). 
 * */

import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Scanner;

public class Cipher 
{
	public static void main(String[] args) 
	{
		try
		{
			char[] alphabet = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
			char[] cipher = createCipher(alphabet);
			boolean finished = false;
			while (!finished)
			{ 
				String[] options = {"Encrypt", "Decrypt", "Exit"};
				int answer = 0;
				answer = JOptionPane.showOptionDialog(null, "Would you like to encrypt or decrypt text?", "Cipher", JOptionPane.YES_NO_CANCEL_OPTION, 
																						JOptionPane.QUESTION_MESSAGE, null, options, 0);
				//encrypting text
				if (answer == JOptionPane.YES_OPTION)
				{
					boolean invalidInput = true;
					while (invalidInput)
					{
						String input = JOptionPane.showInputDialog(null, "Enter the text to be encrypted: ");
						Scanner inputScanner = new Scanner (input);
						if (inputScanner.hasNext())
						{
							String lowercaseInput = input.toLowerCase();
							char[] text = lowercaseInput.toCharArray();
							char[] encrypted = encrypt(text, cipher, alphabet);
							String encryptedString = new String(encrypted);
							JOptionPane.showMessageDialog(null, "The original text was: " + input + "\nThe encrypted text is: " + encryptedString);
							invalidInput = false;
						}
						else
						{
							invalidInput = true;
							JOptionPane.showMessageDialog(null, "Invalid input.");
						}
						inputScanner.close();
					}
				}
				//decrypting text
				else if (answer == JOptionPane.NO_OPTION)
				{
					boolean invalidInput = true;
					while (invalidInput)
					{
						String input = JOptionPane.showInputDialog(null, "Enter the text to be decrypted: ");
						Scanner inputScanner = new Scanner (input);
						if (inputScanner.hasNext())
						{
							String lowercaseInput = input.toLowerCase();
							char[] text = lowercaseInput.toCharArray();
							char[] decrypted = decrypt(text, cipher, alphabet);
							String decryptedString = new String(decrypted);
							JOptionPane.showMessageDialog(null, "The encrypted text was: " + input + "\nThe decrypted text is: " + decryptedString);
							invalidInput = false;
						}
						else
						{
							invalidInput = true;
							JOptionPane.showMessageDialog(null, "Invalid input.");
						}
						inputScanner.close();
					}
				}
				//finished with this cipher
				else
				{
					JOptionPane.showMessageDialog(null, "You have exited the program.");
					finished = true;
				}
			}
		}
		catch (NullPointerException exception)
		{
			JOptionPane.showMessageDialog(null, "You have exited the program.");
		}	
	}
	
	// determines and returns the mapping from plain text to cipher text - each plain text character must be randomly assigned a cipher-text character
	public static char[] createCipher (char[] alphabet)
	{
		char[] cipher = new char[alphabet.length];
		System.arraycopy(alphabet, 0, cipher, 0, alphabet.length);
		Random generator = new Random();
		for (int alphabetIndex = 0; (alphabetIndex < alphabet.length); alphabetIndex++)
		{
			int cipherIndex = generator.nextInt(alphabet.length);
			char temp = cipher[alphabetIndex];
			cipher[alphabetIndex] = cipher[cipherIndex];
			cipher[cipherIndex] = temp;	
		}
		return cipher;	
	}
	
	// takes a plain text phrase & the cipher and returns an encrypted version of the phrase according to the substitution cipher
	public static char[] encrypt (char[] string, char[] cipher, char[] alphabet)
	{
		char[] encrypted = new char[string.length];
		for (int stringIndex = 0; (stringIndex < string.length); stringIndex++)
		{
			for (int alphabetIndex = 0; (alphabetIndex < alphabet.length); alphabetIndex++)
			{
				if (string[stringIndex] == alphabet[alphabetIndex])
				{
					encrypted[stringIndex] = cipher[alphabetIndex];
				}
			}
		}
		return encrypted;
	}
	
	// takes an encrypted phrase & the cipher mapping and returns a plain text version of the phrase according to the substitution cipher
	public static char[] decrypt (char[] encrypted, char[] cipher, char[] alphabet)
	{
		char[] decrypted = new char[encrypted.length];
		for (int stringIndex = 0; (stringIndex < encrypted.length); stringIndex++)
		{
			for (int cipherIndex = 0; (cipherIndex < cipher.length); cipherIndex++)
			{
				if (encrypted[stringIndex] == cipher[cipherIndex])
				{
					decrypted[stringIndex] = alphabet[cipherIndex];
				}
			}
		}
		return decrypted;
	}
}