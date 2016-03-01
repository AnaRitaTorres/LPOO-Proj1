package maze.logic;

import maze.logic.Dragon.typeDragon;

public class Character 
{
	
	protected int position[] = new int [2] ;
	
	public int[] getPosition()
	{
		return position;
	}
	
	public char getChar()
	{
		return '\0';
	}
	
	public void setPosition(char c)
	{
		switch(c)
		{
		case 'U':
			position[0] = position[0] - 1;
			break;
		case 'D':
			position[0] = position[0] + 1;
			break;
		case 'L':
			position[1] = position[1] - 1;
			break;
		case 'R':
			position[1] = position[1] + 1;
			break;
		}
	}
	
	public void setArmed()
	{
		
	}
	
	public void setDisarmed()
	{
		
	}
	
	public boolean isAlive(labirinth lab)
	{
		return true;
	}
	
	public boolean encounter(labirinth lab)
	{
		return true;
	}
	
	public void setState()
	{
		
	}
	
	public boolean getState()
	{
		return true;
	}
	
	public void moveType(labirinth lab)
	{
		
	}
	
	public void setType(typeDragon td)
	{
		
	}
}


