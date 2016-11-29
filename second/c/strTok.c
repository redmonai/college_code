/*String Tokenizing*/

#include <stdio.h>
#include <stdlib.h>

const int MAX_STRING = 256;

int main(void)
{
  //const int MAX_STRING = 256;
  char string[MAX_STRING];
  fgets(string, MAX_STRING, stdin);     	//reading user input into a string 
  int start;
  int i;
  int j;
  char result[MAX_STRING];

  start = 0;
  while (start != -1)
  {
    start = tokenise(string, start, result);
    for (i = 0; i < start; i++)			//print the token
    { 
      printf("%c", result[i]);
    }    
    printf("\n");				//print blank line
    for (j = 0; j < MAX_STRING-1; j++)          // clear result array
    {
      result[j] = ' ';
    }
  }
  return 0;
} 

/*
tokenise finds the next token starting at index 'start' in the string 'str'
the token will be copied into 'result' and communicated back to the caller
if there are no more tokens, the funtion returns -1
*/
int tokenise(char str[], int start, char result[])
{
  int i;
  int j = 0;
  for (i = start; i < MAX_STRING; i++)
  {
    if (str[i] == ' ' || str[i] == '\n')	//if the char is a space or enter key, the token is complete
    {
      i++;					//the counter is incremented so that, when searching for the next token,
						// the function does not find an empty space
      return i;
    }
    else if (str[i] == '\0')                    //when no more tokens remain, returns -1
      return -1;
    else
    {
      result[j++] = str[i];                     //otherwise, the characters from input are copied to result
    }
  }
}
