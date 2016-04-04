package maze.logic;

import java.util.Arrays;
import java.util.Stack;

// TODO: Auto-generated Javadoc
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
	 * Checks if is odd.
	 *
	 * @param i the i
	 * @return true, if is odd
	 */
	//auxiliar function (odd numbers)
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
		
		//printMaze();
		//System.out.print("\n");
		//printVisited();
		//System.out.print("\n");
	}

	/**
	 * Instantiates a new maze builder.
	 */
	public MazeBuilder() 
	{
		// TODO Auto-generated constructor stub
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
	
	/* (non-Javadoc)
	 * @see maze.logic.IMazeBuilder#buildMaze(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
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
	 * Random path.
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
	 * Prints the visited.
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
	 * Generate exit.
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
	 * Checks if is out.
	 *
	 * @param p the p
	 * @return true, if is out
	 */
	public boolean isOut(Point p)
	{
		if(p.getY() > visitedCells.length-1  || p.getY() < 0 || p.getX() > visitedCells[0].length -1|| p.getX() < 0)
			return true;
		else
			return false;
	}

	/**
	 * Open cell around.
	 *
	 * @param p the p
	 * @return true, if successful
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
	 * Conversion to cells.
	 *
	 * @param p the p
	 * @return the point
	 */
	public Point conversionToCells(Point p)
	{
		return new Point((p.getX() -1) / 2,(p.getY() -1) / 2);
	}

	/**
	 * Conversion to maze.
	 *
	 * @param p the p
	 * @return the point
	 */
	public Point conversionToMaze(Point p)
	{
		return new Point((p.getX()*2) +1,(p.getY()*2) +1);
	}

	/**
	 * Adds the hero.
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
	 * Adds the dragon.
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
	 * Adds the sword.
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
