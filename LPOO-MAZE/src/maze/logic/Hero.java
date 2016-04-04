package maze.logic;

import maze.logic.CharacterState.characterState;

/**
 * The Class Hero.
 */
public class Hero extends Character 
{
	
	
	/**
	 * Instantiates a new hero.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param c the c that represents the hero
	 */
	public Hero(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
		state = characterState.DISARMED;
	}
	
	/**
	 * Gets the char of the hero.
	 * 
	 * @param char of the hero
	 */
	public char getChar()
	{
		return c;
	}
	
	/**
	 * Sets the char of the hero to armed.
	 * 
	 * @param char of the hero when armed
	 */
	public void setArmed()
	{
		c = 'A';
	}
	
	/**
	 * Sets the char of the hero to disarmed.
	 * 
	 * @param char of the hero when disarmed
	 */
	public void setDisarmed()
	{
		c = 'H';
	}
}
