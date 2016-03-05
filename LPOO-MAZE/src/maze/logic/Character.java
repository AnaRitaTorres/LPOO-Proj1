package maze.logic;

public class Character {
	
	protected Point position;
	protected char c;
	private static CharacterType type;
	
	CharacterType getType()
	{
		return type;
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
