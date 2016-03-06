package maze.logic;

import maze.logic.CharacterState.characterState;
import maze.logic.CharacterType.characterType;

public class Character 
{	
	protected Point position;
	protected char c;
	protected static characterType type;
	protected static characterState state;
	
	characterType getType()
	{
		return type;
	}
	
	characterState getState()
	{
		return state;
	}
	
	public void setState(characterState cs)
	{
		state = cs;
	}
	
	Point getCharacterPosition()
	{
		return position;
	}
	
	void setCharacterPosition(Point p)
	{
		position =p;
	}
	
    char getChar ()
	{
		return c;
	}
}
