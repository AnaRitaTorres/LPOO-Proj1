package maze.logic;

import maze.logic.CharacterState.characterState;
import maze.logic.CharacterType.characterType;

public class Character
{	
	protected Point position;
	protected char c;
	protected characterType type;
	protected characterState state;
	
	public characterType getType()
	{
		return type;
	}
	
	public characterState getState()
	{
		return state;
	}
	
	public void setState(characterState cs)
	{
		state = cs;
	}
	
	public Point getCharacterPosition()
	{
		return position;
	}
	
	public void setCharacterPosition(Point p)
	{
		position =p;
	}
	
	public char getChar ()
	{
		return c;
	}
    
	public void setChar()
	{
		
	}
	
	public void setArmed()
	{
		
	}
	
	public void setDisarmed()
	{
		
	}


    
}
