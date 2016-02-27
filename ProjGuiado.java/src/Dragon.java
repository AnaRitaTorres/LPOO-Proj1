
public class Dragon extends Character 
{
	private char d;
	
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
}
