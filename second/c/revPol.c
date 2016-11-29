/*Reverse Polish Notation
* lab 3
* revPol.c
* author: Ailbhe Redmond
* 21.10.15
*/

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

const int MAX = 1024;
const int MAX_STRING = 256;

struct stack
{
  double values[MAX];
  int top;
};

struct stack * new_stack()
{
  struct stack * new;
  new = malloc(sizeof(struct stack));
  new->top = 0;
  return new;
}

int stack_empty(struct stack * this)
{
  if (this->top == 0)
    return 1;
  else
    return 0;
}

void stack_push(struct stack * this, double item)
{
  assert(this->top < MAX);
  this->values[this->top] = item;
  this->top = this->top + 1;
}

double stack_pop(struct stack * this)
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
    if(str[i] == '\0')                    
      return -1;
    else
    {
      result[j++] = str[i++];                  
    }  
  }
  return i;
}

int main(void)
{
  struct stack * my_stack;
  my_stack = new_stack();

  char string[MAX_STRING];
  char result[MAX_STRING];
  int start;
  int i;
  double value;
  double valuea;
  double valueb;
  double resultab;
  
  printf("Input your sum: ");
  fgets(string, MAX_STRING, stdin);     	//reading user input into a string 

  start = 0;
  while (start != -1)
  {
    start = tokenise(string, start, result);
    value = atof(result);			//cast char to double
    if (value != 0)				//value will be 0 if no number present
    {  
       stack_push(my_stack, value);
    }

    else if (value == 0 && result[0] != '0' && (stack_empty(my_stack) != 1))
    {
      valuea = stack_pop(my_stack);
      valueb = stack_pop(my_stack);
      switch (result[0])
      {
        case '+':
          resultab = valuea + valueb;
          break;
        case '-':
          resultab = valueb - valuea;
          break;
        case '*':
          resultab = valuea * valueb;
          break;
        case '/':
          resultab = valueb / valuea;
          break;
        default:
          break;
      }
      stack_push(my_stack, resultab);
    }
    for (i = 0; i < MAX_STRING-1; i++)          // clear result array
    {
      result[i] = ' ';
    }
  }
  resultab = stack_pop(my_stack);
  printf("The result is: %f\n", resultab);
  return 0;
}

