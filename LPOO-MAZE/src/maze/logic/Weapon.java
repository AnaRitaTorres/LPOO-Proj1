package maze.logic;

// TODO: Auto-generated Javadoc
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
	 * @param x the x
	 * @param y the y
	 * @param c the c
	 */
	public Weapon(int x, int y, char c)
	{
		this.c = c;
		position = new Point(x,y);
	}
	
	/**
	 * Gets the char.
	 *
	 * @return the char
	 */
	public char getChar()
	{
		return c;
	}
	
	
	/**
	 * Gets the position.
	 *
	 * @return the position
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
