package maze.logic;

public class Dragon extends Character 
{
		
	public Dragon(int x, int y, char c)
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
	
	void setSleep(char c)
	{
		setChar(c);
	}

}
