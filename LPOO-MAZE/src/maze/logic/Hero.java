package maze.logic;

import maze.logic.CharacterState.characterState;

// TODO: Auto-generated Javadoc
/**
 * The Class Hero.
 */
public class Hero extends Character 
{
	
	
	/**
	 * Instantiates a new hero.
	 *
	 * @param x the x
	 * @param y the y
	 * @param c the c
	 */
	public Hero(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
		state = characterState.DISARMED;
	}
	
	/* (non-Javadoc)
	 * @see maze.logic.Character#getChar()
	 */
	public char getChar()
	{
		return c;
	}
	
	/* (non-Javadoc)
	 * @see maze.logic.Character#setArmed()
	 */
	public void setArmed()
	{
		c = 'A';
	}
	
	/* (non-Javadoc)
	 * @see maze.logic.Character#setDisarmed()
	 */
	public void setDisarmed()
	{
		c = 'H';
	}
}
