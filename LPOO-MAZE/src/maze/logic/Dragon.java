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
	 * @param x the x
	 * @param y the y
	 * @param c the c
	 */
	public Dragon(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
		state = characterState.ALIVE;
	}
	
	
	
	/**
	 * Gets the char
	 * 
	 * @param the char
	 */
	public char getChar()
	{
		return this.c;
	}
	
	/**
	 * Sets the char.
	 *
	 * @param c the new char
	 */
	public void setChar(char c)
	{
		this.c=c;
	}
	
	/**
	 * Sets the sleep.
	 *
	 * @param c the new sleep
	 */
	public void setSleep(char c)
	{
		setChar(c);
	}

}
