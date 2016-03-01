package maze.logic;

public class Weapon 
{
	private char w;
	protected int position[] = new int [2] ;
	
	public Weapon(int x, int y, char c)
	{
		w = c;
		position[0] = x;
		position[1] = y;
	}
	
	public char getChar()
	{
		return w;
	}
	
	public int[] getPosition()
	{
		return position;
	}
	
	
}
