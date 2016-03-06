package maze.logic;

import maze.logic.CharacterState.characterState;

public class Dragon extends Character 
{
		
	public Dragon(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
		state = characterState.ALIVE;
	}
	
	public char getChar()
	{
		return c;
	}
	
	public void setChar(char c)
	{
		this.c=c;
	}
	
	public void setSleep(char c)
	{
		setChar(c);
	}

}
