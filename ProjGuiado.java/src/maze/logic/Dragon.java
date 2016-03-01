package maze.logic;

public class Dragon extends Character 
{
	private char d;
	private boolean dState = true;
	
	public Dragon(int x, int y, char c)
	{
		d = c;
		position[0] = x;
		position[1] = y;
	}
	
	public char getChar()
	{
		return d;
	}
	
	public void setState()
	{
		dState=false;
	}
	
	public boolean getState()
	{
		return dState;
	}
}

