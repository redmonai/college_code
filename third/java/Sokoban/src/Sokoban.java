/* Sokoban consists of a man pushing a box in a maze from its source to its destination.
 * The maze cells are either empty or have obstacles.
 * The man can push the box in a given direction only if the square behind the box is empty.
 * There is one unique path through the given matrix maze.
 * The man can move without limitation as long as the square is free.
 * 
 * Sample input:
 * 5 5 			//the size of the matrix
 * @@@D@ 
 * @##@@ 
 * @##@@ 
 * MB@@@ 
 * @@@@@
 * where D is the destination, B is the box, M is the man, @ is a free space, and # is an obstacle
 * 
 * The program should output the length of the path from B to D, in this case 5
 */

import java.util.Scanner;

public class Sokoban 
{
	static int R, C, destR, destC, boxR, boxC, count;
    static int[][] arr;
    static boolean found;
    static int [] changeX = {0, 1, 0, -1};  //for checking the squares around the box
    static int [] changeY = {-1, 0, 1, 0};
    static int [] prevX = {0, -1, 0, 1};	//for checking the inverse of those squares ie is there space for the man
    static int [] prevY = {1, 0, -1, 0};

    static void read()throws Exception {
        Scanner sin = new Scanner(System.in);
        R = sin.nextInt();
        C = sin.nextInt();
        arr = new int[R + 2][C + 2];
        for (int i = 0; i < R + 2; i++) {
            for (int j = 0; j < C + 2; j++) {
                arr[i][j] = 9;					//mark exterior boundary of matrix
            }
        }
        sin.nextLine();
        for (int i = 1; i <= R; i++) {
            String tmp = sin.nextLine();
            for (int j = 1; j <= C; j++) {                
                char ch = tmp.charAt(j-1);
                switch (ch) {
                    case '#':					//mark obstacles
                        arr[i][j] = 8;
                        break;
                    case 'B':					//mark box start location
                        arr[i][j] = 1;
                        boxR = i;
                        boxC = j;
                        break;
                    case 'D':					//mark destination
                        destR = i;
                        destC = j;
                    default:					//mark available spaces
                        arr[i][j] = 0;
                }
            }
        }
        sin.close();
    }

    
    public static void main(String[] args)throws Exception 
    {
    	read();
    	count = 0;
    	findPath(boxR, boxC);
    	int pathLength = calculatePath();
   		System.out.println(pathLength);
    }
    
    public static void findPath(int boxX, int boxY)
    {
    	int direction;
    	int nextX, nextY, manX, manY;
    	
    	if (boxX == destR && boxY == destC)
    	{
    		found = true;
    	}
    	else
    	{
    		direction = 0;
    		while (direction < 4 && !found)
    		{
    			nextX = boxX + changeX[direction];
    			nextY = boxY + changeY[direction];
    			manX = boxX + prevX[direction];
    			manY = boxY + prevY[direction];
    			if ((arr[nextX][nextY] == 0) && (arr[manX][manY] == 0 || arr[manX][manY] == 2 || arr[manX][manY] == 1))
    			{
    				arr[nextX][nextY] = 2;			//mark visited    		    	
    				findPath(nextX, nextY);
    				if (!found)
    				{
    					arr[nextX][nextY] = 0;		//unmark visited
    				}
    			}
    			direction++;
    		}
    	}
    } 
    
    public static int calculatePath()
    {
    	int pathLength = 0;
    	for (int i = 0; i < R+2; i++)
    	{
    		for (int j = 0; j < C+2; j++)
    		{
    			if (arr[i][j] == 2)
    			{
    				pathLength++;
    			}
    		}
    	}
    	return pathLength;
    }
}
