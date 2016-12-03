
public class Cell {
	
	public boolean disp;
	public boolean trap;
	public boolean gold;
	public int dispX;
	public int dispY;
	
	public Cell(boolean displace, boolean trapped, boolean gilded, int X, int Y)
	{
		disp = displace;
		trap = trapped;
		gold = gilded;
		dispX = X;
		dispY = Y;
	}

}
