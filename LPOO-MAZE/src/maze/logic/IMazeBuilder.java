package maze.logic;


/**
 * The Interface IMazeBuilder.
 */
public interface IMazeBuilder 
{
	
	/**
	 * Builds the maze.
	 *
	 * @param size the size
	 * @return the char[][]
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}
