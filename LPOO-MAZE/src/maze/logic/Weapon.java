package maze.logic;

public class Weapon 
{
	protected Point position;
	protected char c;
	
	public Weapon(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
	}
	
	char getChar()
	{
		return c;
	}
	
	void setChar(char c)
	{
		this.c=c;
	}
}
