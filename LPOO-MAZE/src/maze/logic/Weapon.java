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
	
	
	public Point getPosition()
	{
		return position;
	}
	
	public void eraseWeapon()
	{
		c = ' ';
	}
}
