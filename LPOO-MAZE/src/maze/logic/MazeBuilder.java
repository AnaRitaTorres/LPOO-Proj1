package maze.logic;

import java.util.Stack;

public class MazeBuilder
{
	private char[][] lab;
	private boolean[][] visitedCells;
	private Point guideCell;
	private Stack <Point> pathHistory = new Stack <Point> ();


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
	}

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
	
	public void generateExit(int size)
	{
		int random = (int)(Math.random() * (size-1));
		
		while(!isOdd(random))
		{
			random = (int)(Math.random() * (size-1));
		}
		
		lab[random][size-1]= 'S';
		
		
	}

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
	
	public Point conversionToCells(Point p)
	{
		
	}

	public Point conversionToMaze(Point p)
	{
		
	}
}
