
public class Hero extends Character 
{
	private char h;
	
	public Hero(int x, int y, char c)
	{
		h = c;
		position[0] = x;
		position[1] = y;
	}
	
	public char getChar()
	{
		return h;
	}
}
