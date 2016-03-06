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
	
	public char getChar()
	{
		return c;
	}
	
	public void setArmed()
	{
		c = 'A';
	}
	
	public void setDisarmed()
	{
		c = 'H';
	}
}
