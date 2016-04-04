package maze.logic;

import maze.logic.CharacterState.characterState;
import maze.logic.CharacterType.characterType;


/**
 * The Class Character.
 */
public class Character
{	

	/** The position. */
	protected Point position;

	/** The c. */
	protected char c;

	/** The type. */
	protected characterType type;

	/** The state. */
	protected characterState state;

	/**
	 * Gets the type of character.
	 *
	 * @return the type of the character
	 */
	public characterType getType()
	{
		return type;
	}

	/**
	 * Gets the state of the character.
	 *
	 * @return the state of the character
	 */
	public characterState getState()
	{
		return state;
	}

	/**
	 * Sets the state of the character.
	 *
	 * @param cs the new state of the character
	 */
	public void setState(characterState cs)
	{
		state = cs;
	}

	/**
	 * Gets the character position.
	 *
	 * @return the character position
	 */
	public Point getCharacterPosition()
	{
		return position;
	}

	/**
	 * Sets the character position.
	 *
	 * @param p the new character position
	 */
	public void setCharacterPosition(Point p)
	{
		position =p;
	}

	/**
	 * Gets the char of the character.
	 *
	 * @return the char of the character
	 */
	public char getChar ()
	{
		return c;
	}

	/**
	 * Sets the char.
	 */
	public void setChar()
	{

	}

	/**
	 * Sets the armed.
	 */
	public void setArmed()
	{

	}

	/**
	 * Sets the disarmed.
	 */
	public void setDisarmed()
	{

	}



}
