/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author Ailbhe Redmond
 *
 * @version 01/12/15 02:03:28 TODO
 */
public class FacebookCircles {
	
	public int numberOfFacebookUsers;
	public int[] id;
	WeightedQuickUnionUF fbUnion;

  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int numberOfFacebookUsers) 
  {
	  fbUnion = new WeightedQuickUnionUF(numberOfFacebookUsers);
  }

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int user1, int user2 ) 
  {
	  fbUnion.union(user1, user2);
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() 
  {
	  return fbUnion.count();
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() 
  {
	  int largest = 0;
	  
	  for (int i = 0; i < fbUnion.size.length; i++)
	  {
		  if (largest < fbUnion.size[i])
		  {
			  largest = fbUnion.size[i];
		  }
	  }
	  return largest;
  }

  /**
   * @return the size of the median circle in the data already loaded.
   */
  public int sizeOfAverageCircle() 
  {
	  int avg = 0;
	  int n = numberOfCircles();
	  
	  for (int i = 0; i < fbUnion.size.length; i++)
	  {
		  if (fbUnion.parent[i] == i)
		  {
			  avg += fbUnion.size[i];
		  }
	  }
	  return (avg/n);
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() 
  {
	  int smallest = sizeOfLargestCircle();

	  for (int i = 0; i < fbUnion.size.length; i++)
	  {
		  if (fbUnion.parent[i] == i)
		  {
			  if (smallest > fbUnion.size[i])
			  {
				  smallest = fbUnion.size[i];
			  }
		  }
	  }
	  return smallest;
  }
}