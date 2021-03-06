package maze.logic;


/**
 * The Class Point.
 */
public class Point 
{
	
	/** The x. */
	int x;
	
	/** The y. */
	int y;

	/**
	 * Instantiates a new point.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Point(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Gets the x coordinate.
	 *
	 * @return the x
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * Gets the y coordinate.
	 *
	 * @return the y
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * Sets the x coordinate.
	 *
	 * @param x the new x
	 */
	public void setX(int x)
	{
		this.x=x;
	}
	
	/**
	 * Sets the y coordinate.
	 *
	 * @param y the new y
	 */
	public void setY(int y)
	{
		this.y=y;
	}
	
	
}
