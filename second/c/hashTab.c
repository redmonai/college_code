/* Hash Table ADT + test client
*  lab 6
*  written by: Ailbhe Redmond
*/

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

struct node {
  char * data;
  struct node * next;
};

//each hash table object can hold a linked list of nodes
struct hashtable {
  int size;
  struct node ** table;
};

struct node * node_new(char * input)
{
  struct node * this;
  this = malloc(sizeof(struct node *));
  this->data = input;
  this->next = NULL;
  return this;
}

//this is the hash function
unsigned hash_string(struct hashtable * this, char * str)
{
  unsigned hash = 0;
  for ( int i = 0; str[i] != '\0'; i++ ) {
    hash = hash * 37 + str[i];
  }
  return hash % (this->size);
}


// construct a new hash table with size elements
struct hashtable * hashtable_new(int size)
{
  struct hashtable * this;
  this = malloc(sizeof(struct hashtable));
  this->table = malloc(sizeof(struct node *) * size);
  for (int i = 0; i < size; i++)
  {
    this->table[i] = NULL;
  }
  this->size = size;
  return this;
}

// free the memory for all parts of the hashtable
void hashtable_free(struct hashtable * this)
{
  if (this->size > 0)
  {
    struct node * temp;
    for (int i = 0; i < this->size; i++)
    {
      temp = this->table[i];
      free(temp->data);
      while(temp->next !=  NULL)
      {
        temp = temp->next;
        free(temp->data);
      }
    }
  }
  free(this);
}

// return 1 if string is already in table, 0 otherwise
int lookup_item(struct hashtable * this, char * item)
{
  unsigned hash = (hash_string(this, item));
  struct node * temp;
  temp = this->table[hash];
  if (temp == NULL)
  {
    return 0;
  }
  else
  {
    if(strcmp(item, temp->data) == 0)
    {
      return 1;
    }    
    while(temp->next != NULL)
    {
      temp = temp->next;
      if(strcmp(item, temp->data) == 0)   //will be 0 if they are the same
      {
        return 1;
      }
    }
  }
  return 0; 
}

// insert string into the hash table, no effect if it's already there
void insert_item(struct hashtable * this, char * item)
{
  if (lookup_item(this, item) == 0)
  {
    struct node * temp;
    temp = node_new(item);
    unsigned hash = (hash_string(this, item));
    if (this->table[hash] == NULL)
    {
      this->table[hash] = temp;
    }
    else if (this->table[hash]->data != NULL)
    {
      this->table[hash]->next = temp;
    }
    else
    {
      this->table[hash] = temp;
    }
  }
}

// remove string from the hash table, no effect if not in table
void remove_item(struct hashtable * this, char * item)
{
  if (lookup_item(this, item) == 1)
  {
    unsigned hash = hash_string(this, item);
    struct node * temp;
    if (this->table[hash] != NULL)
    {
      temp = this->table[hash];
      
      while(temp->next != NULL)
      {
        if(strcmp(item, temp->next->data) == 0)   //will be 0 if they are the same
        {
          struct node * toDelete = temp->next;
          if (toDelete->next != NULL)
          {
            temp->next = toDelete->next;
          }
          //toDelete->data = NULL;
          free(toDelete);
        }
        temp = temp->next; 
      } 
      //temp is only node at that point
      if(strcmp(item, temp->data) == 0)
      {
        //temp->data = NULL;
        free(temp);
      }   
    }
  }
}


// print out each entry in the hash table and the values
// stored at that entry
void print_table(struct hashtable * this)   
{
  for (int i = 0; i < this->size; i++)
  {
    printf("%u. ", i+1);   		//print line number
    if (this->table[i] == NULL || this->table[i]->data == NULL)
    {
      printf("<empty>");     		//print empty table location
    }
    else
    {
      struct node * temp = this->table[i];
      printf("%s", temp->data);		//print node data
      while (temp->next != NULL)
      {
        printf(" | ");	                //print barrier between nodes
        temp = temp->next;		//loop through list nodes if they exist
        printf("%s", temp->data);
      }
    }
    printf("\n");
  }
}

//hash table test client
int main(int argc, char * argv[])  //argc argument count, argv argument vector
{
  if (argc >= 2)
  {
    struct hashtable * new_table;
    new_table = hashtable_new(argc-1);

    for (int i = 1; i < argc; i++)
    {
      char * temp = argv[i];
      //if string is + the following string is added to the table
      if(strcmp(temp, "+") == 0)
      {
        i++;
        insert_item(new_table, argv[i]);
      }
      //if string is - the following string is removed
      else if(strcmp(temp, "-") == 0)
      {
        i++;
        remove_item(new_table, argv[i]);
      }
      //if string is = print the current table
      else if(strcmp(temp, "=") == 0)
      {
        print_table(new_table);
      }
    }
  }
  else
    printf("User must enter an argument");
  return 0;
}

