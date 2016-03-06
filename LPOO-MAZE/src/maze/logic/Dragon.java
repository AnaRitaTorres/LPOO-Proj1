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
