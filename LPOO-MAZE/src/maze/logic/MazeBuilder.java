package maze.logic;

import java.util.Arrays;
import java.util.Stack;


/**
 * The Class MazeBuilder.
 */
public class MazeBuilder implements IMazeBuilder
{
	
	/** The lab. */
	private char[][] lab;
	
	/** The visited cells. */
	private boolean[][] visitedCells;
	
	/** The path history. */
	private Stack <Point> pathHistory = new Stack <Point> ();


	/**
	 * Checks if a number is odd.
	 *
	 * @param i the number
	 * @return true, if is odd, otherwise false
	 */
	public boolean isOdd(int i)
	{
		if (i % 2 == 0) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
	}

	/**
	 * Instantiates a new maze builder.
	 *
	 * @param size the size
	 */
	public MazeBuilder(int size)
	{
		lab = new char[size][size];

		//fill the maze with X
		for ( int i =0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				lab[i][j]= 'X';
			}
		}

		//space cells defined
		for ( int i =0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				if (isOdd(i)&& isOdd(j))
				{
					lab[i][j]=' ';
				}
			}
		}
		createVisitedCell(size);
		generateExit(size);
		addHero(size);
		addDragon(size);
		addSword(size);
		randomPath();
		
	}

	/**
	 * Instantiates a new maze builder.
	 */
	public MazeBuilder() 
	{
		
	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public char[][] getMaze()
	{
		return lab;
	}
	
	/**
	 * Builds the maze.
	 * 
	 *  @exception IllegalArgumentException , if the size of the maze is an even number.
	 * */
	public char[][] buildMaze(int size) throws IllegalArgumentException
	{
		if (size % 2 != 0) 
		{
			MazeBuilder mb = new MazeBuilder(size);
			return mb.getMaze();
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Describes a representation of the labyrinth in string format.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() 
	{
		String s ="";
		for(int j=0; j < lab.length;j++)
		{
			for(int i=0; i < lab[j].length; i++)
			{
				s+= lab[j][i];
				s+=' ';
			}
			s+='\n';
		}
		
		return s;
	}

	/**
	 * Construction of a random maze.
	 */
	public void randomPath()
	{
		int random = (int)(Math.random() * 4);
		Point r;

		if (!pathHistory.isEmpty())
		{
			Point guideCell = new Point (pathHistory.peek().getX(),pathHistory.peek().getY());
			
			if (openCellAround(guideCell))
			{
				switch(random)
				{
				case 0:
					guideCell.setX(guideCell.getX()+2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()][guideCell.getX()-1]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				case 1:
					guideCell.setX(guideCell.getX()-2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()][guideCell.getX()+1]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				case 2:
					guideCell.setY(guideCell.getY()-2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()+1][guideCell.getX()]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				case 3:
					guideCell.setY(guideCell.getY()+ 2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()-1][guideCell.getX()]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				}
			}
			else
			{
				pathHistory.pop();
				randomPath();
			}
		}

	}



	/**
	 * Prints the maze.
	 */
	public void printMaze()
	{
		for(int j=0; j < lab.length;j++)
		{
			for(int i=0; i < lab[j].length; i++)
			{
				System.out.print(lab[j][i]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}

	/**
	 * Prints the visited cells of the maze.
	 */
	public void printVisited()
	{
		for(int j=0; j < visitedCells.length;j++)
		{
			for(int i=0; i < visitedCells[j].length; i++)
			{
				if(visitedCells[j][i])
					System.out.print("T ");
				else
					System.out.print("F ");
			}
			System.out.print('\n');
		}
	}

	/**
	 * Generates an exit.
	 *
	 * @param size the size
	 */
	public void generateExit(int size)
	{
		int random = (int)(Math.random() * (size-1));

		while(!isOdd(random))
		{
			random = (int)(Math.random() * (size-1));
		}

		lab[random][size-1]= 'S';

		visitedCells[(random-1) /2][(size-2) /2] = true;

		pathHistory.push(new Point (size-2,random));
	}

	/**
	 * Creates the visited cell.
	 *
	 * @param size the size
	 */
	public void createVisitedCell(int size)
	{
		int vcDimension = (size - 1) / 2;

		visitedCells = new boolean[vcDimension][vcDimension];

		for ( int i =0; i < vcDimension; i++)
		{
			for(int j=0; j < vcDimension; j++)
			{
				visitedCells[i][j]= false;
			}
		}
	}

	/**
	 * Checks if the point is out of the bounds of the visited cells.
	 *
	 * @param p the point
	 * @return true, if is out, otherwise false
	 */
	public boolean isOut(Point p)
	{
		if(p.getY() > visitedCells.length-1  || p.getY() < 0 || p.getX() > visitedCells[0].length -1|| p.getX() < 0)
			return true;
		else
			return false;
	}

	/**
	 * Checks if the cells around a specific one are free.
	 *
	 * @param p the point
	 * @return true, if it is successful, otherwise false
	 */
	public boolean openCellAround(Point p)
	{
		Point newp = conversionToCells(p);

		newp.setX(newp.getX()+1);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
				return true;
		}

		newp.setX(newp.getX()-2);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
				return true;
		}

		newp.setY(newp.getY()+1);
		newp.setX(newp.getX()+1);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
				return true;
		}

		newp.setY(newp.getY()-2);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
				return true;
		}

		return false;
	}

	/**
	 * Conversion of the cell of the maze to a cell of the visited cells.
	 *
	 * @param p the point
	 * @return the point
	 */
	public Point conversionToCells(Point p)
	{
		return new Point((p.getX() -1) / 2,(p.getY() -1) / 2);
	}

	/**
	 * Conversion of the cell of the visited cells to a cell of the maze.
	 *
	 * @param p the point
	 * @return the point
	 */
	public Point conversionToMaze(Point p)
	{
		return new Point((p.getX()*2) +1,(p.getY()*2) +1);
	}

	/**
	 * Adds the hero to the maze randomly.
	 *
	 * @param size the size
	 */
	public void addHero(int size)
	{
		int random1 = (int)(Math.random() * (size-1));

		while(!isOdd(random1))
		{
			random1 = (int)(Math.random() * (size-1));
		}
		
		int random2 = (int)(Math.random() * (size-1));

		while(!isOdd(random2))
		{
			random2 = (int)(Math.random() * (size-1));
		}
		
		lab[random1][random2] = 'H';
	}
	
	/**
	 * Adds the dragon to the maze randomly.
	 *
	 * @param size the size
	 */
	public void addDragon(int size)
	{
		int random1 = (int)(Math.random() * (size-1));

		while(!isOdd(random1))
		{
			random1 = (int)(Math.random() * (size-1));
		}
		
		int random2 = (int)(Math.random() * (size-1));

		while(!isOdd(random2))
		{
			random2 = (int)(Math.random() * (size-1));
		}
		
		if(lab[random1][random2] == 'E')
		{
			addDragon(size);
			return;
		}
		if(lab[random1][random2] == 'H' || lab[random1][random2] == 'D')
		{
			addDragon(size);
			return;
		}
		if(lab[random1+1][random2] == 'H'|| lab[random1+1][random2] == 'D')
		{
			addDragon(size);
			return;
		}
		if(lab[random1][random2+1] == 'H' || lab[random1][random2+1] == 'D')
		{
			addDragon(size);
			return;
		}
		if(lab[random1-1][random2] == 'H' || lab[random1-1][random2] == 'D')
		{
			addDragon(size);
			return;
		}
		if(lab[random1][random2-1] == 'H' || lab[random1][random2-1] == 'D')
		{
			addDragon(size);
			return;
		}
		lab[random1][random2] = 'D';
			
	}
	
	/**
	 * Adds the sword to the maze randomly.
	 *
	 * @param size the size
	 */
	public void addSword(int size)
	{
		int random1 = (int)(Math.random() * (size-1));

		while(!isOdd(random1))
		{
			random1 = (int)(Math.random() * (size-1));
		}
		
		int random2 = (int)(Math.random() * (size-1));

		while(!isOdd(random2))
		{
			random2 = (int)(Math.random() * (size-1));
		}
		
		if(lab[random1][random2] == 'X')
		{
			addSword(size);
			return;
		}
		
		if(lab[random1][random2] == 'H')
		{
			addSword(size);
			return;
		}
		
		if(lab[random1][random2] == 'D')
		{
			addSword(size);
			return;
		}
		
		
		
		
		lab[random1][random2] = 'E';
	}
}
