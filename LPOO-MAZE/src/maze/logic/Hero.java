package maze.logic;

import maze.logic.CharacterState.characterState;

public class Hero extends Character 
{
	
	
	public Hero(int x, int y, char c)
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
	
	void setArmed(char c)
	{
		setChar(c);
	}
	
	void setDisarmed(char c)
	{
		setChar(c);
	}
}
