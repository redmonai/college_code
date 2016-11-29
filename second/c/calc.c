/* Calculator
*  lab 4
*  written by: Ailbhe Redmond
*/

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

const int MAX_STRING = 256;
const int MAX_STACK = 1024;

//char stack functions
struct charStack
{
  char values[MAX_STACK];
  int top;
};

struct charStack * new_charStack()
{
  struct charStack * new;
  new = malloc(sizeof(struct charStack));
  new->top = 0;
  return new;
}

int char_stack_empty(struct charStack * this)
{
  if (this->top == 0)
    return 1;
  else
    return 0;
}

void char_stack_push(struct charStack * this, char item)
{
  assert(this->top < MAX_STACK);
  this->values[this->top] = item;
  this->top = this->top + 1;
}

char char_stack_pop(struct charStack * this)
{
  char item;
  this->top = this->top-1;			//decrease value of top to reach last item added
  item = this->values[this->top];
  return item;					//return most recently pushed item
}

//double stack functions
struct doubleStack
{
  double values[MAX_STACK];
  int top;
};

struct doubleStack * new_doubleStack()
{
  struct doubleStack * new;
  new = malloc(sizeof(struct doubleStack));
  new->top = 0;
  return new;
}

int double_stack_empty(struct doubleStack * this)
{
  if (this->top == 0)
    return 1;
  else
    return 0;
}

void double_stack_push(struct doubleStack * this, double item)
{
  assert(this->top < MAX_STACK);
  this->values[this->top] = item;
  this->top = this->top + 1;
}

double double_stack_pop(struct doubleStack * this)
{
  double item;
  this->top = this->top-1;			//decrease value of top to reach last item added
  item = this->values[this->top];
  return item;					//return most recently pushed item
}


int tokenise(char str[], int start, char result[])
{
  int i = start;
  int j = 0;
  while (str[i] == ' ')
  {
    i++;
  }
  while (str[i] != ' ')
  {
    if(str[i] == '\0')                    //when no more tokens remain, returns -1
      return -1;
    else
    {
      result[j++] = str[i++];             //otherwise, the characters from input are copied to result
    }
  }  
  return i;
}


int main(void)
{
  struct charStack * pol_stack;
  pol_stack = new_charStack();
  struct doubleStack * compute_stack;
  compute_stack = new_doubleStack(); 

  char string[MAX_STRING];
  char result[MAX_STRING];
  char polish[MAX_STRING];
  char tempChar;
  char tempOp;
  char popOp;
  int start;
  int polIndex;
  int resultIndex;
  int i;
  int j;
  double inputValue;
  double numbera;
  double numberb;
  double resultab;

  printf("Input your sum: ");
  fgets(string, MAX_STRING, stdin);     	//reading user input into a string 
  polIndex = 0;
  start = 0;
  while (start != -1)
  {
    start = tokenise(string, start, result);
    inputValue = atof(result);
    if (inputValue != 0)                       //value is a number
    {
      resultIndex = 0;
      while((result[resultIndex] != '\0') && ((result[resultIndex] >= '0' && result[resultIndex] <= '9') || (result[resultIndex] == '-' && 							result[resultIndex+1] >= '0' && result[resultIndex+1] <= '9')))
      {
        polish[polIndex] = result[resultIndex];
        resultIndex++;
        polIndex++;
      }
      polish[polIndex] = ' ';
      polIndex++;
    }
    else					//value is not a number
    {
      //input is left bracket
      if (result[0] == '(')
      {
        char_stack_push(pol_stack, result[0]);
      } 
      //input is an operator
      else if ((result[0] == '+') || (result[0] == '-') || (result[0] == '*') || (result[0] == '/'))
      {
        tempOp = result[0];
        if (tempOp == '+' || tempOp == '-')
        {
          while((pol_stack->values[pol_stack->top-1] == '*') || (pol_stack->values[pol_stack->top-1] == '/'))
          {
            popOp = char_stack_pop(pol_stack);
    	    polish[polIndex] = popOp;
            polIndex++;
            polish[polIndex] = ' ';
            polIndex++;
          }
        }
        char_stack_push(pol_stack, tempOp);
      }
      //input is right bracket
      else if (result[0] == ')')
      {
        while(pol_stack->values[pol_stack->top-1] != '(')
        {
          tempChar = char_stack_pop(pol_stack);
          polish[polIndex] = tempChar;
          polIndex++;
          polish[polIndex] = ' ';
          polIndex++;
        } 
        char_stack_pop(pol_stack);
      }
    }

    for (i = 0; i < MAX_STRING - 1; i++)          // clear result array
    {
      result[i] = '\0';
    }
  }
  while(!char_stack_empty(pol_stack))
  {
    tempChar = char_stack_pop(pol_stack);
    polish[polIndex] = tempChar;
    polIndex++;
    polish[polIndex] = ' ';
    polIndex++;
  }
  //polish
  start = 0;
  while (start != -1)
  {
    start = tokenise(polish, start, result);
    inputValue = atof(result);			        //cast char to double
    if (inputValue != 0)			 	//value will be 0 if no number present
    {  
       double_stack_push(compute_stack, inputValue);
    }

    else if (inputValue == 0 && result[0] != '0' && (double_stack_empty(compute_stack) != 1))
    {
      numbera = double_stack_pop(compute_stack);
     numberb = double_stack_pop(compute_stack);
      switch (result[0])
      {
        case '+':
          resultab = numbera + numberb;
          break;
        case '-':
          resultab = numberb - numbera;
          break;
        case '*':
          resultab = numbera * numberb;
          break;
        case '/':
          resultab = numberb / numbera;
          break;
        default:
          break;
      }
      double_stack_push(compute_stack, resultab);
    }
    for (i = 0; i < MAX_STRING-1; i++)          // clear result array
    {
      result[i] = '\0';
    }
  }
  resultab = double_stack_pop(compute_stack);
  printf("The result is: %f\n", resultab);
  return 0;
}
