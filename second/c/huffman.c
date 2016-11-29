#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

const int MAX_CHAR = 256;

struct node{
  int freq;
  int is_leaf;
  union{
    struct{
      struct node * left;
      struct node * right;
    }compound;
    unsigned char c;
  }u;
};

struct node * new_node(int cfreq, int leaf, unsigned char pc)
{
  struct node * n;
  n = malloc(sizeof(n));
  n->freq = cfreq;
  n->is_leaf = leaf;
  n->u.c = pc;
  return n;
}

struct node * new_compound(int cfreq, int leaf, struct node * left, struct node * right)
{
  struct node * comp;
  comp = malloc(sizeof(comp));
  comp->freq = cfreq;
  comp->is_leaf = leaf;
  comp->u.compound.left = left;
  comp->u.compound.right = right;
  return comp;
}


struct node * create_compound_node(struct node * p, struct node * p2)
{
  int compfreq;
  compfreq = ((p->freq) + (p2->freq));
  struct node * result;
  result = new_compound(compfreq, 0, p, p2);
  return result;
}

//finds the index of the lowest frequency in the passed array
struct node * delete_smallest_node(struct node * * list, int size)
{
  int i, lowest, lowestIndex;
  struct node * temp, * p;
  lowestIndex = 0;
  lowest = list[0]->freq;
  for (i = 0; i < size; i++)
  {
    assert(list[i] != NULL);
    temp = list[i];
    if (temp->freq < lowest)
    {
      lowest = temp->freq;
      lowestIndex = i;
    }
  }
  p = list[lowestIndex];
  list[lowestIndex] = list[size-1];
  return p;
}

//builds a huffman tree from leaf to final compound node
struct node * build_huffman_tree(int * freqs, int nfreqs)
{
  struct node * * list;
  list = malloc(sizeof(struct node *) * nfreqs);
  for (int i = 0; i < nfreqs; i++)
  {
    struct node * n = new_node(freqs[i], 1, (unsigned char) i);
    list[i] = n;
  }
  int size = nfreqs;
  while (size > 1)
  {
    //find two smallest nodes
    struct node * smallest, * second_smallest;	
    smallest = delete_smallest_node(list, size);
    size--;

    second_smallest = delete_smallest_node(list, size);
    
    assert(smallest != NULL && second_smallest != NULL);
    //create compound node
    struct node * comp;
    comp = create_compound_node(smallest, second_smallest);
    list[size-1] = comp;
  }
  return list[0];
}
    
//print the passed node
void print_huffman_node(struct node * my_node, char * prefix)
{
  if (my_node->is_leaf == 0)
  {
    char temp [strlen(prefix + 2)];
    //go left  
    strcpy(temp, prefix);
    strcat(temp, "0");
    print_huffman_node(my_node->u.compound.left, temp);
    //go right
    strcpy(temp, prefix);
    strcat(temp, "1");
    print_huffman_node(my_node->u.compound.right, temp);
  }
  else
  {
    //print code
    printf("'%c' %s\n", my_node->u.c, prefix);
  }
}

int main(int argc, char ** argv)
{
  unsigned char c;
  FILE * doc;
  int frequencies[MAX_CHAR];
  int * freqs;
  int nfreqs = MAX_CHAR;
  
  for (int i = 0; i < MAX_CHAR; i++)
  {
    frequencies[i] = 0;
  } 

  if (argc != 2) 
  {
    fprintf(stderr, "Usage: huffman <filename>\n");
    exit(1);	    // exit with error code
  }

  doc = fopen(argv[1], "r");
  assert(doc != NULL);
  c = fgetc(doc);	// attempt to read a byte
  while(!feof(doc)) 
  {
    frequencies[c]++;
    c = fgetc(doc);
  }
  fclose(doc);
 
  for (int i = 0; i < MAX_CHAR; i++)
  {
    if (frequencies[i] == 0)
      frequencies[i] = 1;
  } 

  freqs = &frequencies[0];
  struct node * top = build_huffman_tree(freqs, nfreqs);
  print_huffman_node(top, "");

  return 0;  // exit without error code
}
