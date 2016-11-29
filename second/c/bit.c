/* Bit Vector Sets
*  bitset ADT
*  lab 5
*  written by: Ailbhe Redmond
*/

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

const int MAX_STRING = 256;

struct bitset
{
  unsigned char * vector;
  int size_in_bits;
  int size_in_bytes;
};

//create a new empty bit vector of passed size
struct bitset * bitset_new(int size)
{
  struct bitset * s;
  s = malloc(sizeof(s));
  s->vector = calloc(size, sizeof(char));
  
  s->size_in_bits = size;
  s->size_in_bytes = (size/sizeof(char)+1);
  return s;
}

//checks to see if an item is in the set
//returns 1 for true, 0 for false, -1 for out of bounds
int bitset_lookup(struct bitset * this, int item)
{
  int byte_index = item/sizeof(char);
  int bit_index = item%sizeof(char);
  unsigned char the_byte = this->vector[byte_index];
  int result = (the_byte >> bit_index) & 1;
  return result;
}

//add an item with number "item" to the set
//returns 0 if out of bounds, 1 otherwise
//has no effect if item already in set
int bitset_add(struct bitset * this, int item)
{
  if (bitset_lookup(this, item) == 0)
  {
    int byte_index = item/sizeof(char);
    int bit_index = item%sizeof(char);
    
    unsigned char my_byte = this->vector[byte_index];
    unsigned char new_byte = my_byte | (1 << bit_index);
    
    this->vector[byte_index] = new_byte;
    return 1;
  }
  else
    return 0;
}

//remove an item with number "item" from the set
//returns 0 if out of bounds, 1 otherwise
int bitset_remove(struct bitset * this, int item)
{
  if (bitset_lookup(this, item) == 1)
  {
    int byte_index = item/sizeof(char);
    int bit_index = item%sizeof(char);
    
    unsigned char my_byte = this->vector[byte_index];
    unsigned char new_byte = my_byte & ~(1<< bit_index);

    this->vector[byte_index] = new_byte;
    return 1;
  }
  else
    return 0;
}

//place the union of src1 and src2 into dest
void bitset_union(struct bitset * dest, struct bitset * src1, struct bitset * src2)
{
  int i;
  for (i = 0; i < src1->size_in_bytes; i++)
  {
    dest->vector[i] = src1->vector[i] | src2->vector[i];
  }
}

//place the intersection of src1 and src2 into dest
void bitset_intersect(struct bitset * dest, struct bitset * src1, struct bitset * src2)
{
  int i;
  for (i = 0; i < src1->size_in_bytes; i++)
  {
    dest->vector[i] = src1->vector[i] & src2->vector[i];
  }
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
  struct bitset * firstSet;
  firstSet = bitset_new(MAX_STRING);
  struct bitset * secondSet;
  secondSet = bitset_new(MAX_STRING);
  struct bitset * resultSet;
  resultSet = bitset_new(MAX_STRING);

  char stringInput[MAX_STRING];
  char result[MAX_STRING];

  int start;
  int i;
   
  for (i = 0; i < MAX_STRING - 1; i++)          // clear result array
  {
      result[i] = '\0';
  }  
  //first set
  printf("Input your first string: \n");
  fgets(stringInput, MAX_STRING, stdin);

  start = 0;
  while (start != -1)
  {
    start = tokenise(stringInput, start, result);
    for(i = 0; i < MAX_STRING; i++)
    {
      unsigned char item = (unsigned char) result[i]; 

      if (bitset_lookup(firstSet,item) == 0)
      {
        bitset_add(firstSet, item);
      }
    }
  }
  for (i = 0; i < MAX_STRING - 1; i++)          // clear result array
  {
      result[i] = '\0';
  }
  printf("Set 1: \n");
  for (i = 0; i < MAX_STRING; i++)
  { 
    if(bitset_lookup(firstSet, i) == 1)
    {
      printf("%c ", i);
    }
  }
  printf("\n");
  
  //second set
  printf("Input your second string: \n");
  fgets(stringInput, MAX_STRING, stdin);

  start = 0;
  while (start != -1)
  {
    start = tokenise(stringInput, start, result);
    for(i = 0; i < MAX_STRING; i++)
    {
      unsigned char item = (unsigned char) result[i]; 

      if (bitset_lookup(secondSet,item) == 0)
      {
        bitset_add(secondSet, item);
      }
    }
  }
  for (i = 0; i < MAX_STRING - 1; i++)          // clear result array
  {
      result[i] = '\0';
  }
  printf("Set 2: \n");
  for (i = 0; i < MAX_STRING; i++)
  { 
    if(bitset_lookup(secondSet, i) == 1)
    {
      printf("%c ", i);
    }
  }
  printf("\n");
  
  //intersection
  bitset_intersect(resultSet, firstSet, secondSet);
  printf("The intersection of set one and set two is: \n");
  for (i = 0; i < MAX_STRING; i++)
  {
    if(bitset_lookup(resultSet, i) == 1)
    {
      printf("%c ", i);
    }
  }
  printf("\n");
  
  //union
  bitset_union(resultSet, firstSet, secondSet);
  printf("The union of set one and set two is: \n");
  for (i = 0; i < MAX_STRING; i++)
  {
    if(bitset_lookup(resultSet, i) == 1)
    {
      printf("%c ", i);
    }
  }
  printf("\n");
  return 0;
}


