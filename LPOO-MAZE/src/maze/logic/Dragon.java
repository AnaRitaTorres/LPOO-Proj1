package maze.logic;

import maze.logic.CharacterState.characterState;


/**
 * The Class Dragon.
 */
public class Dragon extends Character 
{
		
	/**
	 * Instantiates a new dragon.
	 *
	 * @param x coordinate 
	 * @param y coordinate
	 * @param c char that represents the dragon
	 */
	public Dragon(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
		state = characterState.ALIVE;
	}
	
	
	
	/**
	 * Gets the char of the dragon.
	 * 
	 * @param char of the dragon
	 */
	public char getChar()
	{
		return this.c;
	}
	
	/**
	 * Sets the char of the dragon.
	 *
	 * @param c the new char of the dragon
	 */
	public void setChar(char c)
	{
		this.c=c;
	}
	
	/**
	 * Sets the sleep char.
	 *
	 * @param c the new sleep char of the dragon
	 */
	public void setSleep(char c)
	{
		setChar(c);
	}

}
