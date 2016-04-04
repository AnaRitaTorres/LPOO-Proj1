package maze.logic;

/**
 * The Class Weapon.
 */
public class Weapon 
{
	
	/** The position. */
	protected Point position;
	
	/** The c. */
	protected char c;
	
	/**
	 * Instantiates a new weapon.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param c the weapon char
	 */
	public Weapon(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
	}
	
	/**
	 * Gets the char of the weapon.
	 *
	 * @return the char of the weapon
	 */
	public char getChar()
	{
		return c;
	}
	
	
	/**
	 * Gets the position of the weapon.
	 *
	 * @return the position of the weapon
	 */
	public Point getPosition()
	{
		return position;
	}
	
	/**
	 * Erase weapon.
	 */
	public void eraseWeapon()
	{
		c = ' ';
	}
}
